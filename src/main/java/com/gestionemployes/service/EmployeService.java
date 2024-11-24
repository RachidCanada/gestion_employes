package com.gestionemployes.service;

import com.gestionemployes.model.Employe;
import com.gestionemployes.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeService {
    @Autowired
    private EmployeRepository employeRepository;

    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    public Employe saveEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    public Employe getEmployeById(Long id) {
        return employeRepository.findById(id).orElse(null);
    }

    public void deleteEmploye(Long id) {
        employeRepository.deleteById(id);
    }
}
