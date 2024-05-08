package com.hira_academy.backend.controller;

import com.hira_academy.backend.model.Assistant;
import com.hira_academy.backend.repository.AssistantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assistants")
public class AssistantController {
    @Autowired
    private AssistantRepository assistantRepository;

    @GetMapping
    public List<Assistant> getAllAssistants() {
        return assistantRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAssistantById(@PathVariable Long id) {
        Assistant assistant = assistantRepository.findById(id).orElse(null);
        if (assistant != null) {
            return ResponseEntity.ok(assistant);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assistant not found with ID: " + id);
        }
    }

    @PostMapping
    public Assistant createAssistant(@RequestBody Assistant assistant) {
        return assistantRepository.save(assistant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAssistant(@PathVariable Long id, @RequestBody Assistant assistantDetails) {
        Assistant assistant = assistantRepository.findById(id).orElse(null);
        if (assistant != null) {
            assistant.setName(assistantDetails.getName());
            assistant.setMobile(assistantDetails.getMobile());
            assistant.setEmail(assistantDetails.getEmail());
            assistant.setSalary(assistantDetails.getSalary());
            assistant.setCity(assistantDetails.getCity());
            assistant.setCountry(assistantDetails.getCountry());
            assistant.setDepartment(assistantDetails.getDepartment());
            assistant.setRole(assistantDetails.getRole());
            return ResponseEntity.ok(assistantRepository.save(assistant));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assistant not found with ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAssistant(@PathVariable Long id) {
        if (assistantRepository.existsById(id)) {
            assistantRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assistant not found with ID: " + id);
        }
    }
}
