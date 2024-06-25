# Advanced Programming in JAVA

## Sprint1

### User Documentation for Library Management System

#### Overview

The Library Management System is designed to manage library items, authors, and patrons efficiently. The system allows librarians to add, edit, and delete library items and authors, as well as manage patron information. Additionally, patrons can borrow and return library items.

#### Classes and Their Working

1. **Library**

   - Manages lists of `LibraryItem` and `Patron` objects.
   - Provides methods to add, edit, delete, and search for library items and patrons.
   - Handles borrowing and returning of items.

2. **LibraryItem (Abstract Class)**

   - Represents a library item.
   - Contains common attributes like `id`, `title`, `author`, `ISBN`, `publisher`, `numberOfCopies`, `status`, and `bookType`.
   - Provides methods to get and set these attributes.
   - Abstract method `getItemType()` to be implemented by subclasses.

3. **Book (Extends LibraryItem)**

   - Represents a book in the library.
   - Implements `Borrowable` interface to provide borrowing and returning functionality.

4. **Periodical (Extends LibraryItem)**

   - Represents a periodical in the library.
   - Implements `Borrowable` interface to provide borrowing and returning functionality.

5. **Author**

   - Represents an author with attributes `name` and `dateOfBirth`.
   - Maintains a list of `LibraryItem` objects written by the author.
   - Provides methods to get and set these attributes.

6. **Patron**

   - Represents a library patron with attributes `name`, `address`, and `phoneNumber`.
   - Maintains a list of borrowed `LibraryItem` objects.
   - Provides methods to borrow and return items.

7. **Borrowable (Interface)**

   - Defines methods `borrowItem()` and `returnItem()` to be implemented by borrowable items like `Book` and `Periodical`.

8. **FileUtils**

   - Provides utility methods for reading from and writing to files.
   - Methods: `readFile(String fileName)` and `writeFile(String fileName, List<String> lines)`.

9. **LibraryMenu**
   - Provides a console-based menu for interacting with the library system.
   - Allows users to add, edit, delete, borrow, return, search, and display library items through the menu options.

#### Starting/Accessing the Application

1. **Prerequisites:**

   - Ensure you have Java installed on your machine.
   - Git for cloning the repository.

2. **Steps to Start the Application:**

   1. **Clone the Repository:**

      ```sh
      git clone <repository-url>
      cd LibraryManagementSystem
      ```

   2. **Compile the Project:**

      ```sh
      javac -d bin src/Library/*.java
      ```

   3. **Run the Application:**
      ```sh
      java -cp bin Library.LibraryMenu
      ```

3. **Using the Application:**
   - **Menu Options:**
     - **Add Library Item**: Adds a new book or periodical to the library.
     - **Edit Library Item**: Edits the details of an existing library item.
     - **Delete Library Item**: Deletes a library item from the library.
     - **Borrow Library Item**: Allows a patron to borrow a library item.
     - **Return Library Item**: Allows a patron to return a borrowed library item.
     - **Search Library Items**: Searches for library items by title, author, or ISBN.
     - **Display All Library Items**: Displays a list of all library items.
     - **Exit**: Exits the application.

Example Usage for the Library Menu

1. Add Library Item
   Action: Adds a new book or periodical to the library.
   Steps:
   Select option 1 from the menu.
   Enter the details for the new library item:
   Title: The title of the item.
   Author: The author of the item.
   ISBN: The ISBN of the item.
   Publisher: The publisher of the item.
   Number of Copies: The number of copies available.
   Item Type: Choose 1 for Book or 2 for Periodical.
   Book Type: (If item type is Book) Choose the type of book (Printed, Electronic, Audio).
2. Edit Library Item
   Action: Edits the details of an existing library item.
   Steps:
   Select option 2 from the menu.
   Enter the ID of the item to edit.
   Update the details for the library item as prompted.
3. Delete Library Item
   Action: Deletes a library item from the library.
   Steps:
   Select option 3 from the menu.
   Enter the ID of the item to delete.
   Confirm the deletion by entering Y or cancel by entering N.
4. Borrow Library Item
   Action: Allows a patron to borrow a library item.
   Steps:
   Select option 4 from the menu.
   Enter the name of the patron.
   Enter the ID of the item to borrow.
   If the item is available, it will be borrowed by the patron, and the status will be updated.
5. Return Library Item
   Action: Allows a patron to return a borrowed library item.
   Steps:
   Select option 5 from the menu.
   Enter the name of the patron.
   Enter the ID of the item to return.
   The item will be returned, and the status will be updated.
6. Search Library Items
   Action: Searches for library items by title, author, or ISBN.
   Steps:
   Select option 6 from the menu.
   Choose the search criterion:
   1 for Title
   2 for Author
   3 for ISBN
   Enter the search term.
   The search results will be displayed.
7. Display All Library Items
   Action: Displays a list of all library items.
   Steps:
   Select option 7 from the menu.
   All library items will be listed with their details.
8. Exit
   Action: Exits the application.
   Steps:
9. Select option 8 from the menu.
10. The application will close with a goodbye message.

This detailed example usage guide should help users understand how to navigate and use the Library Management System through the console-based menu provided by the LibraryMenu class. If you need further assistance, please refer to the README file in the repository or the generated Javadocs for more information.

This documentation provides a comprehensive guide to understanding and using the Library Management System. For further details, refer to the generated Javadocs and the class diagram. If you have any questions, please refer to the README file in the repository.
