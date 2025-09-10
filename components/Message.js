import React from 'react';
import './Message.css';

const Message = ({ message }) => {
  const isBot = message.type === 'BOT';
  const formatTime = (timestamp) => {
    return new Date(timestamp).toLocaleTimeString([], { 
      hour: '2-digit', 
      minute: '2-digit' 
    });
  };

  return (
    <div className={`message ${isBot ? 'bot-message' : 'user-message'}`}>
      <div className="message-content">
        <div className="message-avatar">
          {isBot ? '🤖' : '👤'}
        </div>
        <div className="message-bubble">
          <div className="message-text">{message.content}</div>
          <div className="message-time">{formatTime(message.timestamp)}</div>
        </div>
      </div>
    </div>
  );
};

export default Message;