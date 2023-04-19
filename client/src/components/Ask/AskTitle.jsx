import React from 'react';
import styled from 'styled-components';
import askTitleImg from '../../images/img.png';

export default function AskTitle() {
  const AskTitle = styled.div`
    display: flex;
    justify-content: space-between;

    img {
      width: 500px;
    }
  `;
  const AskGuide = styled.div`
    border: 1px solid #b0d3ee;
    border-radius: 5px;
    padding: 24px;

    background-color: #ebf4fb;
  `;

  return (
    <div>
      <AskTitle>
        <h2>Ask a publick question</h2>
        <img src={askTitleImg} alt="askTitleImg" />
      </AskTitle>

      <AskGuide>
        <h2>Writing a good question</h2>
        <p>You’re ready to ask a programming-related question and this form will help guide you through the process.</p>
        <p>Looking to ask a non-programming question? See the topics here to find a relevant site.</p>
        <h5>Steps</h5>
        <ul>
          <li>Summarize your problem in a one-line title.</li>
          <li>Describe your problem in more detail.</li>
          <li>Describe what you tried and what you expected to happen.</li>
          <li>Add “tags” which help surface your question to members of the community.</li>
          <li>Review your question and post it to the site.</li>
        </ul>
      </AskGuide>
    </div>
  );
}
