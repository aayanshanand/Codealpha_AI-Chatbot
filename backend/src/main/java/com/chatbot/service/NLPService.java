package com.chatbot.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.chatbot.model.ChatResponse;

@Service
public class NLPService {

    // Intent patterns for NLP processing
    private static final Pattern GREETING_PATTERN = Pattern.compile("\\b(hello|hi|hey|greetings|good morning|good afternoon|good evening)\\b", Pattern.CASE_INSENSITIVE);
    private static final Pattern GOODBYE_PATTERN = Pattern.compile("\\b(bye|goodbye|see you|farewell|talk to you later|gtg)\\b", Pattern.CASE_INSENSITIVE);
    private static final Pattern HELP_PATTERN = Pattern.compile("\\b(help|assist|support|what can you do)\\b", Pattern.CASE_INSENSITIVE);
    private static final Pattern NAME_PATTERN = Pattern.compile("\\b(what is your name|who are you|your name)\\b", Pattern.CASE_INSENSITIVE);
    private static final Pattern WEATHER_PATTERN = Pattern.compile("\\b(weather|temperature|forecast|rain|sunny|cloudy)\\b", Pattern.CASE_INSENSITIVE);
    private static final Pattern TIME_PATTERN = Pattern.compile("\\b(time|what time|current time|clock)\\b", Pattern.CASE_INSENSITIVE);

    public ChatResponse processMessage(String message) {
        String cleanedMessage = preprocessMessage(message);
        String intent = detectIntent(cleanedMessage);
        String response = generateResponse(intent);
        double confidence = calculateConfidence(intent, cleanedMessage);

        return new ChatResponse(response, confidence, intent);
    }

    private String preprocessMessage(String message) {
        return message.trim().toLowerCase();
    }

    private String detectIntent(String message) {
        if (GREETING_PATTERN.matcher(message).find()) {
            return "greeting";
        } else if (GOODBYE_PATTERN.matcher(message).find()) {
            return "goodbye";
        } else if (HELP_PATTERN.matcher(message).find()) {
            return "help";
        } else if (NAME_PATTERN.matcher(message).find()) {
            return "name";
        } else if (WEATHER_PATTERN.matcher(message).find()) {
            return "weather";
        } else if (TIME_PATTERN.matcher(message).find()) {
            return "time";
        } else {
            return "unknown";
        }
    }

    private String generateResponse(String intent) {
        switch (intent) {
            case "greeting":
                return getRandomResponse(Arrays.asList(
                    "Hello! How can I help you today?",
                    "Hi there! What can I do for you?",
                    "Greetings! How may I assist you?",
                    "Hey! Nice to meet you!"
                ));
            case "goodbye":
                return getRandomResponse(Arrays.asList(
                    "Goodbye! Have a great day!",
                    "See you later!",
                    "Farewell! Take care!",
                    "Bye! Come back anytime!"
                ));
            case "help":
                return "I'm here to help! I can assist you with general questions, provide information about weather, time, and have casual conversations. Just ask me anything!";
            case "name":
                return "I'm ChatBot AI, your friendly virtual assistant. You can call me ChatBot!";
            case "weather":
                return "I don't have access to real-time weather data, but I recommend checking a weather app or website for current conditions in your area.";
            case "time":
                return "I don't have access to real-time data, but you can check the current time on your device or search online.";
            default:
                return getRandomResponse(Arrays.asList(
                    "I'm not sure I understand. Could you please rephrase that?",
                    "That's an interesting question. Could you provide more details?",
                    "I'm still learning. Can you ask me something else?",
                    "Hmm, I don't have information about that right now. Try asking me about greetings, help, or general topics!"
                ));
        }
    }

    private String getRandomResponse(List<String> responses) {
        return responses.get((int) (Math.random() * responses.size()));
    }

    private double calculateConfidence(String intent, @SuppressWarnings("unused") String message) {
        return "unknown".equals(intent) ? 0.3 : 0.85;
    }

    public List<String> extractKeywords(String message) {
        String[] words = message.toLowerCase().split("\\s+");
        return Arrays.asList(words);
    }

    // Added for FAQData integration: Jaccard similarity based on word sets
    @SuppressWarnings("SizeReplaceableByIsEmpty")
    public double calculateSimilarity(String message1, String message2) {
        Set<String> set1 = new HashSet<>(Arrays.asList(message1.toLowerCase().split("\\s+")));
        Set<String> set2 = new HashSet<>(Arrays.asList(message2.toLowerCase().split("\\s+")));

        if (set1.isEmpty() && set2.isEmpty()) return 1.0;
        if (set1.isEmpty() || set2.isEmpty()) return 0.0;

        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);

        return union.size() == 0 ? 0.0 : (double) intersection.size() / union.size();
    }
}
    