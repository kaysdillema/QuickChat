# Student Details
* **Name & Surname:** Ken Kalala
* **Student Number:** ST10525792

# QuickChat - PROG5121 POE (Complete: Parts 1, 2, 3)
An interactive Java console messaging application featuring secure user registration, input validation, login authentication, message hashing, JSON storage, and parallel-array data analytics reports.

## Features Implemented

### Part 1: Registration & Authentication
* **Username Validation:** Ensures an underscore (`_`) is included and total length is ≤ 5 characters.
* **Password Complexity:** Validates length (≥ 8), uppercase letters, numbers, and special characters.
* **Cell Phone Match:** Strictly validates South African international numbers (`+27`).

### Part 2: Messaging & Automation
* **Message Hash Generation:** Autogenerates an identification string combining message ID prefixes, loop counters, and uppercase first/last words.
* **Length Constraints:** Validates text payloads to ensure messages do not exceed 250 characters.
* **JSON Persistence:** Serializes and stores saved messages locally into `messages.json`.
* **CI/CD Automation:** Integrated with active GitHub Actions workflows to automatically execute Maven JUnit test builds upon every push.

### Part 3: Parallel Arrays & Reporting Analytics
* **Parallel Array Storage:** Tracks sent, disregarded, and stored messages alongside message IDs, hashes, senders, and recipients.
* **JSON File Ingestion:** Automatically loads previously stored messages from `messages.json` into memory on startup.
* **Interactive Report Menu (Options A–F):**
  * **A:** Displays senders and recipients for all recorded messages.
  * **B:** Identifies and displays the longest sent message.
  * **C:** Searches for a specific message using its 10-digit Message ID.
  * **D:** Retrieves all messages sent to a specific recipient cell number.
  * **E:** Deletes a message record from the parallel arrays using its Message Hash and shifts array elements cleanly.
  * **F:** Generates a full formatted report of all recorded messages.
* **Comprehensive Unit Testing:** Backed by automated JUnit 4 test suites (`LoginTest`, `MessageTest`, and `MessageReportTest`).

## Architecture Planning & Framework Design
The core architecture and transactional workflows of this messaging client were planned by analyzing standard chat configurations and remote client-server layout matrices. The application uses foundational system scanners and regular expression matching objects compliant with standard platform specifications. For local transaction caching, payload properties are parsed, structured, and saved externally leveraging a lightweight data token serialization engine, while Part 3 reporting modules utilize single-dimensional parallel arrays and buffered file readers to retrieve and manipulate stored records. Version control logs were recorded through a visual repository deployment pipeline, while code modifications were continually checked by an external cloud automation runner framework.

## Features & Boundary Rules
The variable parameters, required class structures, array search algorithms, and input boundaries (including character length checks for usernames and South African phone rules) were structured to conform with the official institutional Portfolio of Evidence guidelines [3] and the implementation clarifications demonstrated in the module support session.

## References

[1] K. Wright, "Git and GitHub Automated Testing Tutorial for Beginners," YouTube, Oct. 30, 2021. [Online Video]. Available: https://youtube.com. [Accessed: Sep. 28, 2026].

[2] GitHub, Inc., "How to use GitHub Desktop," YouTube, May 20, 2021. [Online Video]. Available: https://youtube.com. [Accessed: Sep. 28, 2026].

[3] The Independent Institute of Education, "Introduction to Programming (PROG5121) Module Outline & Portfolio of Evidence (PoE) Guidelines," The IIE, Sandton, 2026.

[4] The Independent Institute of Education, "PROG5121 Assignment support-20260915_060748-Meeting Recording," The IIE, Sandton, Sep. 15, 2026. [Online Lecture Video].

[5] Oracle, "Java Class String - Substring, Split, and Case Conversion Specifications," Oracle Java SE Docs, 2023. [Online]. Available: https://oracle.com. [Accessed: Sep. 28, 2026].

[6] QuickBlox, "A Beginner’s Guide to Chat App Architecture," QuickBlox Blog, Dec. 16, 2022. [Online]. Available: https://quickblox.com. [Accessed: Sep. 28, 2026].

[7] JSON Organization, "Java Reference Library for Object Serialization and Token Handling," JSON Org Docs, 2024. [Online]. Available: https://github.io. [Accessed: Sep. 28, 2026].

[8] Oracle, "Arrays and BufferedReader I/O Specifications (Java SE 17)," Oracle Java SE Docs, 2023. [Online]. Available: https://oracle.com. [Accessed: Nov. 15, 2026].

[9] W3Schools, "Java Arrays Loop and Search Techniques," W3Schools Online Web Tutorials, 2025. [Online]. Available: w3schools.com. [Accessed: Nov. 15, 2026].

[10] Baeldung, "Removing an Element from an Array in Java," Baeldung, May 18, 2024. [Online]. Available: baeldung.com. [Accessed: Nov. 15, 2026].

[11] Oracle, "Class BufferedReader (Java SE 17 & JDK 17)," Oracle Java Documentation, 2023. [Online]. Available: oracle.com. [Accessed: Nov. 15, 2026].

[12] GeeksforGeeks, "Parallel Array in Java," GeeksforGeeks, Jul. 12, 2024. [Online]. Available: geeksforgeeks.org. [Accessed: Nov. 15, 2026].
