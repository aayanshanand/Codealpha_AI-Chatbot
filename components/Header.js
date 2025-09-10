import React from 'react';
import './Header.css';

const Header = () => {
  return (
    <header className="header">
      <div className="header-content">
        <h1 className="header-title">
          <span className="robot-emoji">🤖</span>
          AI ChatBot
        </h1>
        <p className="header-subtitle">
          Powered by Natural Language Processing
        </p>
      </div>
    </header>
  );
};

export default Header;