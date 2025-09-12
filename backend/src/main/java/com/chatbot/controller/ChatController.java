package com.chatbot.controller;

import com.chatbot.model.ChatMessage;
import com.chatbot.service.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {

    private final ChatbotService chatbotService;

    @Autowired
    public ChatController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/message")
    public ResponseEntity<ChatMessage> sendMessage(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        String userId = request.get("userId");
        
        if (message == null || message.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        if (userId == null || userId.trim().isEmpty()) {
            userId = "anonymous";
        }
        
        ChatMessage response = chatbotService.processUserMessage(message, userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history")
    public ResponseEntity<List<ChatMessage>> getConversationHistory() {
        List<ChatMessage> history = chatbotService.getConversationHistory();
        return ResponseEntity.ok(history);
    }

    @DeleteMapping("/history")
    public ResponseEntity<Void> clearConversationHistory() {
        chatbotService.clearConversationHistory();
        return ResponseEntity.ok().build();
    }

    // WebSocket endpoints for real-time communication
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessageWebSocket(Map<String, String> request) {
        String message = request.get("message");
        String userId = request.get("userId");
        
        if (userId == null || userId.trim().isEmpty()) {
            userId = "anonymous";
        }
        
        return chatbotService.processUserMessage(message, userId);
    }
}