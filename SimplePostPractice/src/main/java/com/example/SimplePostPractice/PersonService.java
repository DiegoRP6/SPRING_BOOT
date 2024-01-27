package com.example.SimplePostPractice;

import org.springframework.stereotype.Service;

@Service
public class PersonService {

    public boolean isValid(PersonDTO personDTO) {
        // Aquí puedes implementar tus lógicas de validación
        if (personDTO.getFirstName() == null || personDTO.getFirstName().isEmpty()) {
            return false;
        }

        if (personDTO.getSecondName() == null || personDTO.getSecondName().isEmpty()) {
            return false;
        }

        // Agrega más validaciones según tus necesidades

        return true;
    }
}
