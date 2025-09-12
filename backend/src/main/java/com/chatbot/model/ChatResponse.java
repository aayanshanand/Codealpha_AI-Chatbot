package com.chatbot.model;

public class ChatResponse {
    private String response;
    private double confidence;
    private String intent;

    public ChatResponse() {}

    public ChatResponse(String response, double confidence, String intent) {
        this.response = response;
        this.confidence = confidence;
        this.intent = intent;
    }

    // Getters and Setters
    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }

    public double getConfidence() { return confidence; }
    public void setConfidence(double confidence) { this.confidence = confidence; }

    public String getIntent() { return intent; }
    public void setIntent(String intent) { this.intent = intent; }
}