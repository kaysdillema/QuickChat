package com.quickchat.quickchat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONObject;

public class MessageReport {
    // Parallel Arrays
    public String[] sentMessages = new String[100];
    public String[] disregardedMessages = new String[100];
    public String[] storedMessages = new String[100];
    public String[] messageHashes = new String[100];
    public String[] messageIds = new String[100];
    public String[] recipients = new String[100];
    public String[] senders = new String[100];

    // Counters for each array
    public int sentCount = 0;
    public int disregardedCount = 0;
    public int storedCount = 0;
    public int totalRecords = 0;

    // add a message into the parallel arrays
    public void addMessageRecord(String sender, String recipient, String id, String hash, String payload, String status) {
        if (totalRecords < 100) {
            senders[totalRecords] = sender;
            recipients[totalRecords] = recipient;
            messageIds[totalRecords] = id;
            messageHashes[totalRecords] = hash;
            totalRecords++;
        }

        if (status.equalsIgnoreCase("Sent") && sentCount < 100) {
            sentMessages[sentCount] = payload;
            sentCount++;
        } else if (status.equalsIgnoreCase("Disregarded") && disregardedCount < 100) {
            disregardedMessages[disregardedCount] = payload;
            disregardedCount++;
        } else if (status.equalsIgnoreCase("Stored") && storedCount < 100) {
            storedMessages[storedCount] = payload;
            storedCount++;
        }
    }

    // Loads previously saved messages from  Part 2
    public void loadStoredMessagesFromJSON(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    JSONObject obj = new JSONObject(line);
                    String id = obj.optString("MessageID", "0000000000");
                    String hash = obj.optString("MessageHash", "00:0:NONE");
                    String recipient = obj.optString("Recipient", "Unknown");
                    String payload = obj.optString("Payload", "");
                    
                    addMessageRecord("System User", recipient, id, hash, payload, "Stored");
                }
            }
        } catch (IOException e) {
            
        }
    }

    // 2a. Display the sender and recipient of all sent messages
    public String displaySendersAndRecipients() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < totalRecords; i++) {
            sb.append("Sender: ").append(senders[i])
              .append(" | Recipient: ").append(recipients[i]).append("\n");
        }
        return sb.length() > 0 ? sb.toString().trim() : "No messages found.";
    }

    // 2b. Display the longest sent message
    public String getLongestMessage() {
        if (sentCount == 0) return "No sent messages available.";
        String longest = sentMessages[0];
        for (int i = 1; i < sentCount; i++) {
            if (sentMessages[i] != null && sentMessages[i].length() > longest.length()) {
                longest = sentMessages[i];
            }
        }
        return longest;
    }

    // 2c. Search for a message ID and display the corresponding recipient and message
    public String searchByMessageId(String searchId, String[] allPayloads) {
        for (int i = 0; i < totalRecords; i++) {
            if (messageIds[i] != null && messageIds[i].equals(searchId)) {
                return "Recipient: " + recipients[i] + " | Message: " + allPayloads[i];
            }
        }
        return "Message ID not found.";
    }

    // 2d. Search for all messages sent to a particular recipient
    public String searchByRecipient(String targetRecipient, String[] allPayloads) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < totalRecords; i++) {
            if (recipients[i] != null && recipients[i].equals(targetRecipient)) {
                sb.append(allPayloads[i]).append("\n");
            }
        }
        return sb.length() > 0 ? sb.toString().trim() : "No messages found for this recipient.";
    }

    // 2e. Delete a message using the message hash
    public String deleteByMessageHash(String targetHash, String[] allPayloads) {
        for (int i = 0; i < totalRecords; i++) {
            if (messageHashes[i] != null && messageHashes[i].equalsIgnoreCase(targetHash)) {
                String deletedPayload = allPayloads[i];
                // Shifts elements left to remove the record
                for (int j = i; j < totalRecords - 1; j++) {
                    senders[j] = senders[j + 1];
                    recipients[j] = recipients[j + 1];
                    messageIds[j] = messageIds[j + 1];
                    messageHashes[j] = messageHashes[j + 1];
                    allPayloads[j] = allPayloads[j + 1];
                }
                totalRecords--;
                return "Message \"" + deletedPayload + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    // 2f. Display a report that lists the full details of all sent messages
    public String generateFullReport(String[] allPayloads) {
        if (totalRecords == 0) return "No messages recorded.";
        StringBuilder report = new StringBuilder("=== FULL SENT MESSAGES REPORT ===\n");
        for (int i = 0; i < totalRecords; i++) {
            report.append("Message Hash: ").append(messageHashes[i]).append("\n")
                  .append("Recipient: ").append(recipients[i]).append("\n")
                  .append("Message: ").append(allPayloads[i]).append("\n")
                  .append("----------------------------------\n");
        }
        return report.toString().trim();
    }
}
