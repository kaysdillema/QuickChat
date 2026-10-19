package com.quickchat.quickchat;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        // Test Data Case 1: Within 250 characters
        String msgId = "0012345678";
        String recipient = "+27718693002";
        String payload = "Hi Mike, can you join us for dinner tonight?"; //
        
        Message message = new Message(msgId, recipient, payload, 0);
        assertEquals("Message ready to send.", message.validateMessageLength()); //
    }

    @Test
    public void testMessageLengthFailure() {
        // Constructing a payload that deliberately exceeds 250 characters
        StringBuilder longPayload = new StringBuilder();
        for (int i = 0; i < 30; i++) {
            longPayload.append("This message is getting way too long ");
        }
        
        Message message = new Message("0012345678", "+27718693002", longPayload.toString(), 0);
        String result = message.validateMessageLength();
        
        assertTrue(result.contains("Message exceeds 250 characters")); //
    }

    @Test
    public void testRecipientNumberSuccess() {
        // Test Data from rubric: +27718693002 (Correct format)
        Message message = new Message("0012345678", "+27718693002", "Hello", 0);
        assertEquals("Cell phone number successfully captured.", message.checkRecipientCell()); //
    }

    @Test
    public void testRecipientNumberFailure() {
        // Test Data from rubric: 08575975889 (Incorrect international format)
        Message message = new Message("0012345678", "08575975889", "Hello", 0);
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", message.checkRecipientCell()); //
    }

    @Test
    public void testMessageHashGeneration() {
        // Test Data Case 1: ID starting with 00, loop index 0, payload "Hi Mike..."
        // First word: "HI", Last word: "TONIGHT" -> Expected clean hash format: 00:0:HITONIGHT
        String msgId = "0012345678";
        String recipient = "+27718693002";
        String payload = "Hi Mike, can you join us for dinner tonight?"; //
        
        Message message = new Message(msgId, recipient, payload, 0);
        assertEquals("00:0:HITONIGHT", message.createMessageHash()); //
    }
}
