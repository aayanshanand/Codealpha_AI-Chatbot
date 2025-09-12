# AI Chatbot with NLP

A sophisticated Java-based chatbot application featuring Natural Language Processing capabilities, rule-based responses, FAQ handling, and real-time web interface interaction.

## Features

- **Natural Language Processing**: Advanced NLP techniques for understanding user intent
- **Rule-based Responses**: Intelligent pattern matching for accurate responses  
- **FAQ System**: Pre-configured frequently asked questions with smart matching
- **Real-time Communication**: WebSocket support for instant messaging
- **Modern Web Interface**: Responsive React frontend with beautiful UI
- **Conversation History**: Message persistence and history management
- **Intent Detection**: Automatic classification of user messages
- **Multi-format Support**: REST API and WebSocket endpoints

## Technology Stack

### Backend
- **Java 11+** - Core programming language
- **Spring Boot 2.7** - Application framework
- **Spring WebSocket** - Real-time communication
- **Maven** - Dependency management
- **Jackson** - JSON processing

### Frontend
- **React 18** - UI library
- **Axios** - HTTP client
- **SockJS & STOMP** - WebSocket communication
- **Modern CSS** - Responsive design with gradients and animations

## Project Structure

```
├── backend/
│   ├── src/main/java/com/chatbot/
│   │   ├── ChatbotApplication.java
│   │   ├── controller/
│   │   │   └── ChatController.java
│   │   ├── model/
│   │   │   ├── ChatMessage.java
│   │   │   └── ChatResponse.java
│   │   ├── service/
│   │   │   ├── ChatbotService.java
│   │   │   ├── NLPService.java
│   │   │   └── FAQService.java
│   │   └── config/
│   │       └── WebSocketConfig.java
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
└── frontend/
    ├── public/
    │   └── index.html
    ├── src/
    │   ├── components/
    │   │   ├── ChatInterface.js
    │   │   ├── MessageList.js
    │   │   ├── Message.js
    │   │   ├── MessageInput.js
    │   │   ├── TypingIndicator.js
    │   │   └── Header.js
    │   ├── services/
    │   │   └── chatService.js
    │   ├── App.js
    │   └── App.css
    └── package.json
```

## Installation & Setup

### Prerequisites
- Java 11 or higher
- Node.js 14+ and npm
- Maven 3.6+

### Backend Setup

1. Navigate to the backend directory:
```bash
cd backend
```

2. Install dependencies and run:
```bash
mvn clean install
mvn spring-boot:run
```

The backend will start on `http://localhost:3000`

### Frontend Setup

1. Navigate to the frontend directory:
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm start
```

The frontend will start on `http://localhost:3000`

## API Endpoints

### REST API
- `POST /api/chat/message` - Send a message to the chatbot
- `GET /api/chat/history` - Retrieve conversation history
- `DELETE /api/chat/history` - Clear conversation history

### WebSocket
- `/ws` - WebSocket connection endpoint
- `/app/chat.sendMessage` - Send message via WebSocket
- `/topic/public` - Subscribe to public messages

## NLP Features

The chatbot implements several NLP techniques:

### Intent Detection
- **Greeting**: Recognizes various greeting patterns
- **Goodbye**: Detects farewell messages
- **Help**: Identifies requests for assistance
- **Weather**: Understands weather-related queries
- **Time**: Recognizes time-related questions
- **Name**: Responds to identity questions

### Pattern Matching
Uses regex patterns for:
- Greeting detection: `\b(hello|hi|hey|greetings)\b`
- Help requests: `\b(help|assist|support)\b`
- Weather queries: `\b(weather|temperature|forecast)\b`

### FAQ System
Pre-configured responses for:
- General capabilities
- Technical information
- Privacy and support inquiries
- Getting started guides

## Usage Examples

### Basic Conversation
```
User: Hello!
Bot: Hello! How can I help you today?

User: What can you do?
Bot: I can help answer questions, have conversations, provide information about various topics, and assist with general queries. I'm designed to be helpful and friendly!

User: What's the weather like?
Bot: I don't have access to real-time weather data, but I recommend checking a weather app or website for current conditions in your area.
```

### FAQ Queries
```
User: How do you work?
Bot: I use natural language processing to understand your questions and provide relevant responses. I can recognize patterns in text and match them with appropriate answers.

User: Are you human?
Bot: No, I'm an AI chatbot designed to help and interact with users. I'm a computer program created to simulate conversation.
```

## Customization

### Adding New Intents
1. Add patterns to `NLPService.java`:
```java
private static final Pattern CUSTOM_PATTERN = Pattern.compile("\\b(custom|specific)\\b", Pattern.CASE_INSENSITIVE);
```

2. Update intent detection:
```java
private String detectIntent(String message) {
    if (CUSTOM_PATTERN.matcher(message).find()) {
        return "custom";
    }
    // ... existing patterns
}
```

3. Add response generation:
```java
case "custom":
    return "Your custom response here!";
```

### Adding FAQ Entries
Update `FAQService.java`:
```java
@PostConstruct
public void initializeFAQData() {
    faqData.put("your question", "Your answer here");
    // ... existing FAQs
}
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## Future Enhancements

- Integration with external NLP services (OpenAI, Dialogflow)
- Voice input/output capabilities
- Multi-language support
- Advanced analytics and conversation insights
- Machine learning model training
- Database integration for persistent storage
- User authentication and personalization
- Mobile app development
