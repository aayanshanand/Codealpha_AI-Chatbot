package com.chatbot.service;

import com.chatbot.model.ChatMessage;
import com.chatbot.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ChatbotService {
    
    private final NLPService nlpService;
    private final FAQService faqService;
    private final List<ChatMessage> conversationHistory;

    @Autowired
    public ChatbotService(NLPService nlpService, FAQService faqService) {
        this.nlpService = nlpService;
        this.faqService = faqService;
        this.conversationHistory = new CopyOnWriteArrayList<>();
    }

    public ChatMessage processUserMessage(String messageContent, String userId) {
        // Create user message
        ChatMessage userMessage = new ChatMessage(messageContent, userId, ChatMessage.MessageType.USER);
        conversationHistory.add(userMessage);

        // Check FAQ first
        String faqResponse = faqService.getFAQResponse(messageContent);
        
        ChatResponse response;
        if (faqResponse != null) {
            response = new ChatResponse(faqResponse, 0.95, "faq");
        } else {
            // Use NLP service for processing
            response = nlpService.processMessage(messageContent);
        }

        // Create bot response message
        ChatMessage botMessage = new ChatMessage(
            response.getResponse(), 
            "ChatBot", 
            ChatMessage.MessageType.BOT
        );
        conversationHistory.add(botMessage);

        return botMessage;
    }

    public List<ChatMessage> getConversationHistory() {
        return new ArrayList<>(conversationHistory);
    }

    public void clearConversationHistory() {
        conversationHistory.clear();
    }
}