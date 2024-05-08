package com.hira_academy.backend.service;

import com.hira_academy.backend.model.Assistant;

import java.util.List;

public interface AssistantService {
    List<Assistant> getAllAssistants();
    Assistant getAssistantById(Long id);
    Assistant createAssistant(Assistant assistant);
    Assistant updateAssistant(Long id, Assistant assistantDetails);
    void deleteAssistant(Long id);
}
