package com.quickchat.quickchat;

import java.util.Scanner;
import java.util.Random;

public class QuickChat {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        Random randomEngine = new Random();
        Login authSystem = new Login();
        
        System.out.println("=========================================");
        System.out.println("        Welcome to QuickChat Registration  ");
        System.out.println("=========================================\n");
        
        System.out.print("Enter your First Name: ");
        String firstName = inputScanner.nextLine();
        
        System.out.print("Enter your Last Name: ");
        String lastName = inputScanner.nextLine();
        
        String fullName = firstName + " " + lastName;
        boolean registrationSuccessful = false;
        
        // --- PART 1: REGISTRATION LOOP ---
        while (!registrationSuccessful) {
            System.out.print("\nCreate a Username (Must contain '_' and be max 5 characters): ");
            String username = inputScanner.nextLine();
            
            System.out.print("Create a Password (Min 8 characters, 1 Capital, 1 Number, 1 Special character): ");
            String password = inputScanner.nextLine();
            
            System.out.print("Enter your Cell Phone Number (+27...): ");
            String cellNumber = inputScanner.nextLine();
            
            authSystem = new Login(firstName, lastName, username, password, cellNumber);
            String registrationResponse = authSystem.registerUser();
            System.out.println("\n--- Registration Status Update ---");
            System.out.println(registrationResponse);
            
            if (authSystem.checkUserName() && authSystem.checkPasswordComplexity() && authSystem.checkCellPhoneNumber()) {
                registrationSuccessful = true;
            } else {
                System.out.println("\nRegistration failed due to formatting errors. Please try again.");
            }
        }
        
        System.out.println("\n=========================================");
        System.out.println("        Account Setup Complete! Please Log In ");
        System.out.println("=========================================\n");
        
        boolean loggedIn = false;
        
        // --- PART 1: LOGIN LOOP ---
        while (!loggedIn) {
            System.out.print("Enter Username: ");
            String loginUser = inputScanner.nextLine();
            
            System.out.print("Enter Password: ");
            String loginPass = inputScanner.nextLine();
            
            boolean isAuthenticated = authSystem.loginUser(loginUser, loginPass);
            String loginStatusMessage = authSystem.returnLoginStatus(isAuthenticated);
            
            System.out.println("\n" + loginStatusMessage + "\n");
            
            if (isAuthenticated) {
                loggedIn = true;
            }
        }
        
        // --- PART 3: INITIALIZE REPORT MANAGER & ARRAY ---
        MessageReport reportManager = new MessageReport();
        String[] allPayloads = new String[100];
        
        // Load any saved messages from JSON file at startup
        reportManager.loadStoredMessagesFromJSON("messages.json");
        
        // --- PART 2 & 3: MAIN MENU LOOP ---
        System.out.println("Welcome to QuickChat.");
        boolean keepRunning = true;
        
        while (keepRunning) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("Option 1) Send Messages");
            System.out.println("Option 2) Show Recently Sent Messages / Reports");
            System.out.println("Option 3) Quit");
            System.out.print("Select an option (1-3): ");
            
            int menuChoice = inputScanner.nextInt();
            inputScanner.nextLine(); // Clear scanner buffer
            
            switch (menuChoice) {
                case 1:
                    System.out.print("How many messages do you wish to enter? ");
                    int totalMessages = inputScanner.nextInt();
                    inputScanner.nextLine(); // Clear scanner buffer
                    
                    for (int i = 0; i < totalMessages; i++) {
                        System.out.println("\n--- Entering Message #" + (i + 1) + " ---");
                        
                        System.out.print("Enter Recipient Cell Number (+27...): ");
                        String recipient = inputScanner.nextLine();
                        
                        System.out.print("Enter short message text: ");
                        String text = inputScanner.nextLine();
                        
                        long rawTenDigit = 1000000000L + (long)(randomEngine.nextDouble() * 9000000000L);
                        String genId = String.valueOf(rawTenDigit);
                        
                        Message msg = new Message(genId, recipient, text, i);
                        String generatedHash = msg.createMessageHash();
                        
                        System.out.println(msg.validateMessageLength());
                        System.out.println(msg.checkRecipientCell());
                        System.out.println("Message ID generated: " + genId);
                        System.out.println("Generated Hash: " + generatedHash);
                        
                        System.out.println("\nChoose Action: 1) Send Message, 2) Disregard Message, 3) Store Message");
                        int action = inputScanner.nextInt();
                        inputScanner.nextLine(); // Clear scanner buffer
                        System.out.println(msg.SentMessage(action));
                        
                        String statusText = "Disregarded";
                        if (action == 1) {
                            statusText = "Sent";
                        } else if (action == 3) {
                            statusText = "Stored";
                            System.out.println("-> JSON System Report: " + msg.storeMessage());
                        }
                        
                        // --- WIRE INTO MESSAGEREPORT ---
                        allPayloads[reportManager.totalRecords] = text;
                        reportManager.addMessageRecord(fullName, recipient, genId, generatedHash, text, statusText);
                        
                        System.out.println("\n--- Final Summary Details ---");
                        System.out.println(msg.printMessages());
                    }
                    break;
                    
                case 2:
                    // --- PART 3: REPORT SUB-MENU ---
                    System.out.println("\n--- MESSAGE REPORTS MENU ---");
                    System.out.println("A) Display sender and recipient of all messages");
                    System.out.println("B) Display the longest sent message");
                    System.out.println("C) Search for a message by Message ID");
                    System.out.println("D) Search for all messages sent to a recipient");
                    System.out.println("E) Delete a message using Message Hash");
                    System.out.println("F) Display full report of all messages");
                    System.out.print("Choose a report option (A-F): ");
                    
                    String reportChoice = inputScanner.nextLine().trim().toUpperCase();
                    System.out.println();
                    
                    switch (reportChoice) {
                        case "A":
                            System.out.println(reportManager.displaySendersAndRecipients());
                            break;
                        case "B":
                            System.out.println("Longest Sent Message: " + reportManager.getLongestMessage());
                            break;
                        case "C":
                            System.out.print("Enter the 10-digit Message ID: ");
                            String searchId = inputScanner.nextLine();
                            System.out.println(reportManager.searchByMessageId(searchId, allPayloads));
                            break;
                        case "D":
                            System.out.print("Enter the Recipient Cell Number (+27...): ");
                            String searchRecipient = inputScanner.nextLine();
                            System.out.println(reportManager.searchByRecipient(searchRecipient, allPayloads));
                            break;
                        case "E":
                            System.out.print("Enter the Message Hash to delete: ");
                            String deleteHash = inputScanner.nextLine();
                            System.out.println(reportManager.deleteByMessageHash(deleteHash, allPayloads));
                            break;
                        case "F":
                            System.out.println(reportManager.generateFullReport(allPayloads));
                            break;
                        default:
                            System.out.println("Invalid report choice. Please select A through F.");
                    }
                    break;
                    
                case 3:
                    System.out.println("You have successfully logged out!");
                    keepRunning = false;
                    break;
                    
                default:
                    System.out.println("Invalid selection. Please choose 1, 2, or 3.");
            }
        }
        inputScanner.close();
    }
}
