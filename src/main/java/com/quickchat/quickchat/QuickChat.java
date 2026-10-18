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
                System.out.println("\n⚠️ Registration failed due to formatting errors. Please try again.");
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
        
        // --- PART 2: START OF MESSAGING APPLICATION ---
        System.out.println("Welcome to QuickChat."); // Required welcome message
        
        boolean keepRunning = true;
        
        while (keepRunning) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("Option 1) Send Messages");
            System.out.println("Option 2) Show recently sent messages");
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
                        System.out.println("\n--- Entering Message #" + i + " ---");
                        
                        System.out.print("Enter Recipient Cell Number (+27...): ");
                        String recipient = inputScanner.nextLine();
                        
                        System.out.print("Enter short message text: ");
                        String text = inputScanner.nextLine();
                        
                        // Generate a clean 10-digit numeric tracking ID string
                        long rawTenDigit = 1000000000L + (long)(randomEngine.nextDouble() * 9000000000L);
                        String genId = String.valueOf(rawTenDigit);
                        
                        Message msg = new Message(genId, recipient, text, i);
                        
                        // Output the execution status updates
                        System.out.println(msg.validateMessageLength());
                        System.out.println(msg.checkRecipientCell());
                        System.out.println("Message ID generated: " + genId);
                        System.out.println("Generated Hash: " + msg.createMessageHash());
                        
                        System.out.println("\nChoose Action: 1) Send Message, 2) Disregard Message, 3) Store Message");
                        int action = inputScanner.nextInt();
                        inputScanner.nextLine(); // Clear scanner buffer
                        System.out.println(msg.SentMessage(action));
                        
                        System.out.println("\n--- Final Summary Details ---");
                        System.out.println(msg.printMessages());
                    }
                    break;
                    
                case 2:
                    System.out.println("\nComing Soon."); // Required feature placeholder
                    break;
                    
                case 3:
                    System.out.println("🎉 You have successfully logged out!");
                    keepRunning = false; // Exits the loop cleanly
                    break;
                    
                default:
                    System.out.println("Invalid selection choice option bounds. Try again.");
            }
        }
        inputScanner.close();
    }
}
