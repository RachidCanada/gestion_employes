package com.gestionemployes.controller;

import com.gestionemployes.model.Employe;
import com.gestionemployes.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employes")
public class EmployeController {
    @Autowired
    private EmployeService employeService;

    /**
     * Liste des employés (affichage HTML avec Thymeleaf)
     */
    @GetMapping
    public String listEmployes(Model model) {
        model.addAttribute("employes", employeService.getAllEmployes());
        return "employes"; // Vue employes.html
    }

    /**
     * Formulaire pour ajouter un nouvel employé (affichage HTML avec Thymeleaf)
     */
    @GetMapping("/nouveau")
    public String createEmployeForm(Model model) {
        model.addAttribute("employe", new Employe());
        return "employe-form"; // Vue dédiée pour le formulaire
    }

    /**
     * Ajouter un nouvel employé (compatible avec JSON pour Postman et HTML pour Thymeleaf)
     */
    @PostMapping
    @ResponseBody
    public ResponseEntity<Employe> saveEmploye(@RequestBody Employe employe) {
        Employe savedEmploye = employeService.saveEmploye(employe);
        return ResponseEntity.ok(savedEmploye);
    }

    /**
     * Formulaire pour modifier un employé existant (affichage HTML avec Thymeleaf)
     */
    @GetMapping("/modifier/{id}")
    public String editEmployeForm(@PathVariable Long id, Model model) {
        model.addAttribute("employe", employeService.getEmployeById(id));
        return "employe-form"; // Vue dédiée pour le formulaire
    }

    /**
     * Mettre à jour un employé existant
     */
    @PostMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Employe> updateEmploye(@PathVariable Long id, @RequestBody Employe employe) {
        Employe existingEmploye = employeService.getEmployeById(id);
        existingEmploye.setNom(employe.getNom());
        existingEmploye.setEmail(employe.getEmail());
        existingEmploye.setPoste(employe.getPoste());
        Employe updatedEmploye = employeService.saveEmploye(existingEmploye);
        return ResponseEntity.ok(updatedEmploye);
    }

    /**
     * Supprimer un employé par ID
     */
    @DeleteMapping("/supprimer/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteEmploye(@PathVariable Long id) {
        employeService.deleteEmploye(id);
        return ResponseEntity.ok("Employé supprimé avec succès");
    }
}
