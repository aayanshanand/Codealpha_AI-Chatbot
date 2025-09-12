package com.chatbot.data;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chatbot.service.NLPService;

@Component
public class FAQData {

    @Autowired
    private NLPService nlpService;

    private Map<String, String> faqDatabase;
    private final double SIMILARITY_THRESHOLD = 0.3;

    @PostConstruct
    public void initializeFAQ() {
        faqDatabase = new HashMap<>();
        loadFAQData();
    }

    private void loadFAQData() {
        // General Information
        faqDatabase.put("what is ai", 
            "Artificial Intelligence (AI) is a branch of computer science that aims to create systems capable of performing tasks that typically require human intelligence, such as learning, reasoning, and problem-solving.");
        
        faqDatabase.put("what is machine learning", 
            "Machine Learning is a subset of AI that enables computers to learn and improve from experience without being explicitly programmed for every task.");
        
        faqDatabase.put("what is natural language processing", 
            "Natural Language Processing (NLP) is a field of AI that helps computers understand, interpret, and generate human language in a meaningful way.");

        // Chatbot specific
        faqDatabase.put("how do you work", 
            "I work by analyzing your messages using natural language processing techniques and matching them with pre-programmed responses or generating appropriate replies based on the context.");
        
        faqDatabase.put("who created you", 
            "I was created as an AI chatbot using Java and natural language processing techniques to help answer questions and have conversations.");

        // Technology
        faqDatabase.put("what is java", 
            "Java is a popular, object-oriented programming language known for its platform independence and 'write once, run anywhere' capability.");
        
        faqDatabase.put("what is react", 
            "React is a JavaScript library for building user interfaces, particularly web applications. It's maintained by Facebook and allows developers to create reusable UI components.");

        // Common questions
        faqDatabase.put("how can you help me", 
            "I can help answer questions, provide information on various topics, explain concepts, and have general conversations. Feel free to ask me anything!");
        
        faqDatabase.put("what are your capabilities", 
            "I can understand and respond to text messages, answer frequently asked questions, provide information on various topics, and engage in basic conversations using natural language processing.");

        // Programming
        faqDatabase.put("what is programming", 
            "Programming is the process of creating instructions for computers to follow. It involves writing code in various programming languages to build software applications, websites, and systems.");
        
        faqDatabase.put("how to learn programming", 
            "To learn programming, start with a beginner-friendly language like Python or Java, practice regularly with coding exercises, work on small projects, and use online resources like tutorials and coding platforms.");
    }

    public String findBestMatch(String userMessage) {
        if (userMessage == null || userMessage.trim().isEmpty()) {
            return null;
        }

        String bestMatch = null;
        double highestSimilarity = 0.0;

        for (Map.Entry<String, String> entry : faqDatabase.entrySet()) {
            double similarity = nlpService.calculateSimilarity(userMessage, entry.getKey());
            
            if (similarity > highestSimilarity && similarity >= SIMILARITY_THRESHOLD) {
                highestSimilarity = similarity;
                bestMatch = entry.getValue();
            }
        }

        return bestMatch;
    }

    public void addFAQ(String question, String answer) {
        faqDatabase.put(question.toLowerCase(), answer);
    }

    public Map<String, String> getAllFAQs() {
        return new HashMap<>(faqDatabase);
    }
}