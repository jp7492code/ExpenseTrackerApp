## Expense Tracker
Overview

The Expense Tracker is a personal finance management application designed to help users track and manage their daily expenses efficiently. Built in Java, the application leverages an SQLite database to store and manage expense records, offering both Command Line Interface (CLI) and Graphical User Interface (GUI) options for user interaction. The app allows users to add, view, and generate reports for their expenses, helping them better understand their spending habits and make more informed financial decisions.
Key Features

    Add Expense: Users can add new expenses, specifying the name and amount.
    View Expenses: Users can view a list of all recorded expenses.
    Generate Report: A CSV report can be generated, summarizing all expenses for external use or record-keeping.
    Database Integration: All expenses are stored in an SQLite database, ensuring persistent data storage.

Technologies Used

    Java: The application is built using Java, making it cross-platform and easy to run on any system with the Java Runtime Environment (JRE).
    SQLite: Used for lightweight, serverless database management. The database is automatically created when the application runs for the first time.
    JavaFX: Provides the graphical user interface for a more intuitive user experience.

System Requirements

    Java 8 or higher
    SQLite (database is created at runtime)

How to Use

    Download and extract the project.
    Compile and run either the ExpenseTracker.java for a CLI or ExpenseGUI.java for a GUI version of the application.
    On the first run, the SQLite database expenses.db will be automatically created to store the expenses.
    Use the CLI or GUI to add, view, and generate reports on your expenses.
