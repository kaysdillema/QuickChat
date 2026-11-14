package com.quickchat.quickchat;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessageReportTest {
    private MessageReport report;
    private String[] allPayloads;

    @Before
    public void setUp() {
        report = new MessageReport();
        allPayloads = new String[100];

        // Populate Test Data Messages 1 - 5
        String[] testRecipients = {"+27834557896", "+27838884567", "+27834484567", "0838884567", "+27838884567"};
        String[] testPayloads = {
            "Did you get the cake?",
            "Where are you? You are late! I have asked you to be on time.",
            "Yohoooo, I am at your gate.",
            "It is dinner time !",
            "Ok, I am leaving without you."
        };
        String[] testStatuses = {"Sent", "Stored", "Disregarded", "Sent", "Stored"};
        String[] testIds = {"1000000001", "1000000002", "1000000003", "0838884567", "1000000005"};
        String[] testHashes = {"10:0:DIDCAKE", "10:1:WHERETIME", "10:2:YOHOOOOOGATE", "08:3:ITTIME", "10:4:OKYOU"};

        for (int i = 0; i < 5; i++) {
            allPayloads[i] = testPayloads[i];
            report.addMessageRecord("Ken Kalala", testRecipients[i], testIds[i], testHashes[i], testPayloads[i], testStatuses[i]);
        }
    }

    @Test
    public void testSentMessagesArrayPopulated() {
        assertEquals("Did you get the cake?", report.sentMessages[0]);
        assertEquals("It is dinner time !", report.sentMessages[1]);
    }

    @Test
    public void testDisplayLongestMessage() {
        // Note: Checking longest among all payloads or sent messages depending on test setup
        assertEquals("Did you get the cake?", report.getLongestMessage());
    }

    @Test
    public void testSearchByMessageId() {
        String result = report.searchByMessageId("0838884567", allPayloads);
        assertEquals("Recipient: 0838884567 | Message: It is dinner time !", result);
    }

    @Test
    public void testSearchByRecipient() {
        String result = report.searchByRecipient("+27838884567", allPayloads);
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteByMessageHash() {
        String result = report.deleteByMessageHash("10:1:WHERETIME", allPayloads);
        assertEquals("Message \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.", result);
    }
}
