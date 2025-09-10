package com.chatbot.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class FAQService {
    
    private Map<String, String> faqData;

    @PostConstruct
    public void initializeFAQData() {
        faqData = new HashMap<>();
        
        // General FAQs
        faqData.put("who created you", "I was created by developers using artificial intelligence and programming languages like Java and Python.");
        faqData.put("can you learn", "I don't learn in real-time, but my knowledge can be updated and improved by my developers.");
        faqData.put("can you understand any language", "I mainly understand English, but I can also handle some other languages depending on how I’ve been trained.");
        faqData.put("do you cost money", "I’m free to chat here, but advanced versions of AI may have subscription or premium plans.");
        faqData.put("can you remember me", "I don’t permanently remember conversations unless my developers add that feature for personalization.");
        faqData.put("what can you do", "I can help answer questions, have conversations, provide information about various topics, and assist with general queries. I'm designed to be helpful and friendly!");
        faqData.put("how do you work", "I use natural language processing to understand your questions and provide relevant responses. I can recognize patterns in text and match them with appropriate answers.");
        faqData.put("are you human", "No, I'm an AI chatbot designed to help and interact with users. I'm a computer program created to simulate conversation.");
        faqData.put("what is your purpose", "My purpose is to assist users by answering questions, providing information, and engaging in helpful conversations. I'm here to make your experience more interactive and informative.");
        
        // Technical FAQs
        faqData.put("what is nlp", "NLP stands for Natural Language Processing. It's the technology that allows me to understand and respond to human language.");
        faqData.put("what algorithms do you use", "I use natural language processing techniques, rule-based logic, and sometimes machine learning models for better accuracy.");
        faqData.put("can you integrate with other apps", "Yes, developers can integrate me with other systems like websites, mobile apps, or even messaging platforms.");
        faqData.put("can you make mistakes", "Yes, sometimes I may give incomplete or incorrect answers, but I always try to be as accurate as possible.");
        faqData.put("do you need internet", "Yes, I require internet to connect to servers and process your queries.");
        faqData.put("what programming languages", "I was built using Java for the backend with Spring Boot framework, and React for the frontend. I use natural language processing techniques to understand and respond to messages.");
        faqData.put("how accurate are you", "My accuracy depends on the complexity of the question and how well it matches my training data. I strive to provide helpful responses but I'm continuously learning and improving.");
        
        // Business/Service FAQs
        faqData.put("how can i use you for my business", "I can be integrated into your website or application to answer customer questions and provide support 24/7.");
        faqData.put("can you be customized", "Yes, developers can train me with custom data, branding, and responses for a specific business.");
        faqData.put("are my chats saved", "Conversations may be stored temporarily for improvement but not permanently, unless specified by the service.");
        faqData.put("do you replace humans", "I don’t replace humans, but I can assist by handling repetitive questions, letting humans focus on complex tasks.");
        faqData.put("how secure are you", "I follow strict data security guidelines to protect your information and privacy.");
        faqData.put("contact support", "For technical support or specific inquiries, please reach out through the contact form on our website or email us at support@chatbot.com.");
        faqData.put("privacy policy", "We take your privacy seriously. We don't store personal conversations permanently and follow strict data protection guidelines. Check our privacy policy for detailed information.");
        faqData.put("how to get started", "Simply start typing your question or message in the chat box! I'll do my best to understand and provide a helpful response. Try asking me about anything you're curious about.");
    }

    public String getFAQResponse(String question) {
        String normalizedQuestion = question.toLowerCase().trim();
        
        // Direct match
        if (faqData.containsKey(normalizedQuestion)) {
            return faqData.get(normalizedQuestion);
        }
        
        // Partial match using keywords
        for (Map.Entry<String, String> entry : faqData.entrySet()) {
            if (normalizedQuestion.contains(entry.getKey()) || entry.getKey().contains(normalizedQuestion)) {
                return entry.getValue();
            }
        }
        
        // Check for keyword matches
        for (Map.Entry<String, String> entry : faqData.entrySet()) {
            String[] keywords = entry.getKey().split(" ");
            int matchCount = 0;
            for (String keyword : keywords) {
                if (normalizedQuestion.contains(keyword)) {
                    matchCount++;
                }
            }
            if (matchCount >= keywords.length / 2) { // At least half the keywords match
                return entry.getValue();
            }
        }
        
        return null; // No FAQ match found
    }
}