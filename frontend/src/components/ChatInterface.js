import React, { useState, useRef, useEffect } from 'react';
import MessageList from './MessageList';
import MessageInput from './MessageInput';
import TypingIndicator from './TypingIndicator';
import { chatService } from '../services/chatService';
import './ChatInterface.css';

const ChatInterface = () => {
  const [messages, setMessages] = useState([]);
  const [isTyping, setIsTyping] = useState(false);
  const [userId] = useState(`user_${Date.now()}`);
  const messagesEndRef = useRef(null);

  const scrollToBottom = () => {
    messagesEndRef.current?.scrollIntoView({ behavior: 'smooth' });
  };

  useEffect(() => {
    scrollToBottom();
  }, [messages]);

  useEffect(() => {
    // Add welcome message when component mounts
    const welcomeMessage = {
      id: 'welcome',
      content: "Hello! I'm your AI assistant. How can I help you today?",
      sender: 'ChatBot',
      type: 'BOT',
      timestamp: new Date().toISOString()
    };
    setMessages([welcomeMessage]);
  }, []);

  const handleSendMessage = async (messageContent) => {
    if (!messageContent.trim()) return;

    // Add user message immediately
    const userMessage = {
      id: Date.now().toString(),
      content: messageContent,
      sender: userId,
      type: 'USER',
      timestamp: new Date().toISOString()
    };

    setMessages(prev => [...prev, userMessage]);
    setIsTyping(true);

    try {
      // Send message to backend
      const response = await chatService.sendMessage(messageContent, userId);
      
      // Add bot response
      setMessages(prev => [...prev, response]);
    } catch (error) {
      console.error('Error sending message:', error);
      
      // Add error message
      const errorMessage = {
        id: Date.now().toString(),
        content: "Sorry, I'm having trouble connecting right now. Please try again.",
        sender: 'ChatBot',
        type: 'BOT',
        timestamp: new Date().toISOString()
      };
      
      setMessages(prev => [...prev, errorMessage]);
    } finally {
      setIsTyping(false);
    }
  };

  const handleClearChat = async () => {
    try {
      await chatService.clearHistory();
      setMessages([]);
      // Add welcome message back
      const welcomeMessage = {
        id: 'welcome',
        content: "Hello! I'm your AI assistant. How can I help you today?",
        sender: 'ChatBot',
        type: 'BOT',
        timestamp: new Date().toISOString()
      };
      setMessages([welcomeMessage]);
    } catch (error) {
      console.error('Error clearing chat:', error);
    }
  };

  return (
    <div className="chat-interface">
      <div className="chat-container">
        <div className="chat-header">
          <div className="chat-info">
            <div className="status-indicator online"></div>
            <span className="chat-title">AI Assistant</span>
          </div>
          <button className="clear-button" onClick={handleClearChat}>
            Clear Chat
          </button>
        </div>
        
        <div className="chat-messages">
          <MessageList messages={messages} />
          {isTyping && <TypingIndicator />}
          <div ref={messagesEndRef} />
        </div>
        
        <MessageInput onSendMessage={handleSendMessage} disabled={isTyping} />
      </div>
    </div>
  );
};

export default ChatInterface;