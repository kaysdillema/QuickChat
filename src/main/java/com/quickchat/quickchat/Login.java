package com.quickchat.quickchat;

import java.util.regex.Pattern;

// Property Declarations

public class Login {
    // Credentials needed for the registration
    private String registeredUsername;
    private String registeredPassword;
    private String registeredCellNumber;
    private String firstName;
    private String lastName;

    // input values to check
    private String inputUsername;
    private String inputPassword;
    private String inputCellNumber;

    // Used during registration setup
    public Login(String firstName, String lastName, String username, String password, String cellNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.inputUsername = username;
        this.inputPassword = password;
        this.inputCellNumber = cellNumber;
    }

    public Login() {}

// Core Validation Functions    
    
    // Check Username contains an underscore and is not longer than 5 characters
    public boolean checkUserName() {
        if (inputUsername == null) return false;
        return inputUsername.contains("_") && inputUsername.length() <= 5;
    }

    // Check Password is following the complexity rules
    public boolean checkPasswordComplexity() {
        if (inputPassword == null || inputPassword.length() < 8) return false;
        
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
    // The password must follow the following rules:
        for (char c : inputPassword.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (String.valueOf(c).matches("[!@#$%^&*(),.?\":{}|<>]")) hasSpecial = true;
        }
        return hasUpper && hasDigit && hasSpecial;
    }
    
    // Check Cell Phone Number contains requirements via Regex
    public boolean checkCellPhoneNumber() {
    if (inputCellNumber == null) return false;
    //cleaned up cellphone number checking to only accept +27 instead of international
    // '+27' followed by 9 digits for the rest of the number
    String regex = "^\\+27\\d{9}$";
    
    return inputCellNumber.startsWith("+27") 
           && inputCellNumber.length() == 12
           && java.util.regex.Pattern.matches(regex, inputCellNumber);
}


    // Messages to reply to conditions check
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber()) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        // Save valid information to registered storage fields
        this.registeredUsername = this.inputUsername;
        this.registeredPassword = this.inputPassword;
        this.registeredCellNumber = this.inputCellNumber;
        
        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }


    // System responds with the following messages to verify user status
    public boolean loginUser(String username, String password) {
        if (this.registeredUsername == null || this.registeredPassword == null) return false;
        return this.registeredUsername.equals(username) && this.registeredPassword.equals(password);
    }

    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
