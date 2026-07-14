Library Management System

A Java Swing desktop application for managing a library. The system provides a user-friendly interface for librarians to manage books, authenticate users, track borrowing and returns, and maintain borrowing history. The application uses Java Swing for the graphical user interface and MySQL with JDBC for persistent data storage.

Features:
User Authentication
Secure login using username and password.
Create new librarian profiles.
Database authentication using MySQL.
Invalid login detection.

Dashboard:
Central navigation panel.
Access to all library management features.
Simple and user-friendly interface.

Book Management:
Add new books.
View all books using JTable.
Remove books from the library.
Display available copies of each book.

Borrow Books:
Borrow books by Book ID.
Automatically reduces available stock.
Records borrowing date.
Stores borrower information.

Return Books:
Return borrowed books.
Automatically updates return date.
Increases available stock.
Prevents duplicate returns.

Borrowing History:
Displays all borrowing records.
 Shows:
 Borrow ID
 Book ID
 Member Name
 Borrow Date
 Return Date
 Status (Borrowed / Returned)

Technologies Used:
Java
Java Swing
JDBC (Java Database Connectivity)
MySQL
VS Code
MySQL Connector/J
