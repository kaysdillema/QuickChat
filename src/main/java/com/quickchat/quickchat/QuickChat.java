package com.quickchat.quickchat;

import java.util.Scanner;

public class QuickChat {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        Login authSystem = new Login();
        
        System.out.println("=========================================");
        System.out.println("        Welcome to QuickChat    ");
        System.out.println("=========================================\n");
        
        // 1. Get users first and last name
        System.out.print("Please enter your First Name: ");
        String firstName = inputScanner.nextLine();
        
        System.out.print("Please enter your Last Name: ");
        String lastName = inputScanner.nextLine();
        
        boolean registrationSuccessful = false;
        
        // Interactive Control Loop
        
        // 2. The continuous loop until the user provides the correct credentials
        while (!registrationSuccessful) {
            System.out.print("\nCreate a Username (Must contain '_' and be max 5 characters): ");
            String username = inputScanner.nextLine();
            
            System.out.print("Create a Password (Must be a min 8 characters, 1 Capital, 1 Number, 1 Special character): ");
            String password = inputScanner.nextLine();
            
            System.out.print("Please Provide a Cell Phone Number (Must include international code e.g. +27838968976): ");
            String cellNumber = inputScanner.nextLine();
            
            // Bring back the Login object state with current inputs
            authSystem = new Login(firstName, lastName, username, password, cellNumber);
            
            // Process validation checks
            String registrationResponse = authSystem.registerUser();
            System.out.println("\n--- Registration Status Update ---");
            System.out.println(registrationResponse);
            
            // Do a check to ensure account registration passed all the rules successfully
            if (registrationResponse.contains("successfully captured")) {
                registrationSuccessful = true;
            } else {
                System.out.println("\n⚠️ Registration failed due to formatting errors. Please try again.");
            }
        }
        
        System.out.println("\n=========================================");
        System.out.println("        Account Setup Successful, Please Log In ");
        System.out.println("=========================================\n");
        
        boolean loggedIn = false;
        
        // 3. Authentication prompt flow loop
        while (!loggedIn) {
            System.out.print("Enter Username: ");
            String loginUser = inputScanner.nextLine();
            
            System.out.print("Enter Password: ");
            String loginPass = inputScanner.nextLine();
            
            // Validate credentials 
            boolean isAuthenticated = authSystem.loginUser(loginUser, loginPass);
            String loginStatusMessage = authSystem.returnLoginStatus(isAuthenticated);
            
            System.out.println("\n" + loginStatusMessage + "\n");
            
            if (isAuthenticated) {
                loggedIn = true;
            }
        }
        
        System.out.println("You have successfully logged in");
        inputScanner.close();
    }
}
