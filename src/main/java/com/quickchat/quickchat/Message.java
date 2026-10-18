package com.quickchat.quickchat;

import java.util.regex.Pattern;

public class Message {
    private String messageId;
    private String recipientCell;
    private String payload;
    private int loopCounter;
    private String messageStatus;

    // Default constructor
    public Message() {}

    // Overloaded constructor for message processing instances
    public Message(String messageId, String recipientCell, String payload, int loopCounter) {
        this.messageId = messageId;
        this.recipientCell = recipientCell;
        this.payload = payload;
        this.loopCounter = loopCounter;
    }

    // 1. Verify Message ID bounds
    public boolean checkMessageID() {
        if (messageId == null) return false;
        return messageId.length() <= 10;
    }

    // 2. Verify Recipient Number format matches Part 1 parameters
    public String checkRecipientCell() {
        if (recipientCell == null) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
        // Matches +27 format from previous task guidelines
        String regex = "^\\+27\\d{9}$";
        if (recipientCell.startsWith("+27") && recipientCell.length() == 12 && Pattern.matches(regex, recipientCell)) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    // 3. Autogenerate custom Message Hash (e.g., 00:0:HITHANKS)
    public String createMessageHash() {
        if (payload == null || payload.trim().isEmpty() || messageId == null || messageId.length() < 2) {
            return "INVALID_HASH";
        }
        
        // Extract first two digits of Message ID
        String idPrefix = messageId.substring(0, 2);
        
        // Isolate words from string text payload block
        String[] words = payload.trim().split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        
        // Remove common punctuation if present at ends of strings
        firstWord = firstWord.replaceAll("[^A-Z0-9]", "");
        lastWord = lastWord.replaceAll("[^A-Z0-9]", "");

        return idPrefix + ":" + loopCounter + ":" + firstWord + lastWord;
    }

    // 4. Validate Message length requirements
    public String validateMessageLength() {
        if (payload == null || payload.length() > 250) {
            int overflowAmount = (payload != null) ? (payload.length() - 250) : 0;
            return "Message exceeds 250 characters by " + overflowAmount + "; please reduce the size.";
        }
        return "Message ready to send.";
    }

    // 5. Handle action choices (Send, Disregard, Store)
    public String SentMessage(int choice) {
        if (choice == 1) {
            this.messageStatus = "Sent";
            return "Message successfully sent.";
        } else if (choice == 2) {
            this.messageStatus = "Disregarded";
            return "Press 0 to delete the message";
        } else if (choice == 3) {
            this.messageStatus = "Stored";
            return "Message successfully stored.";
        }
        return "Invalid selection action choice parameter.";
    }

    // 6. Format single record layout summaries
    public String printMessages() {
        return "Message ID: " + messageId + "\n" +
               "Message Hash: " + createMessageHash() + "\n" +
               "Recipient: " + recipientCell + "\n" +
               "Message: " + payload + "\n" +
               "-------------------------------------";
    }
}
