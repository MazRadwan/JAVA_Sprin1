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

### Example Usage for the Library Menu

#### 1. Add Library Item

- **Action**: Adds a new book or periodical to the library.
- **Steps**:
  1. Select option `1` from the menu.
  2. Enter the details for the new library item:
     - **Title**: The title of the item.
     - **Author**: The author of the item.
     - **ISBN**: The ISBN of the item.
     - **Publisher**: The publisher of the item.
     - **Number of Copies**: The number of copies available.
     - **Item Type**: Choose `1` for Book or `2` for Periodical.
     - **Book Type**: (If item type is Book) Choose the type of book (Printed, Electronic, Audio).

#### 2. Edit Library Item

- **Action**: Edits the details of an existing library item.
- **Steps**:
  1. Select option `2` from the menu.
  2. Enter the ID of the item to edit.
  3. Update the details for the library item as prompted.

#### 3. Delete Library Item

- **Action**: Deletes a library item from the library.
- **Steps**:
  1. Select option `3` from the menu.
  2. Enter the ID of the item to delete.
  3. Confirm the deletion by entering `Y` or cancel by entering `N`.

#### 4. Borrow Library Item

- **Action**: Allows a patron to borrow a library item.
- **Steps**:
  1. Select option `4` from the menu.
  2. Enter the name of the patron.
  3. Enter the ID of the item to borrow.
  4. If the item is available, it will be borrowed by the patron, and the status will be updated.

#### 5. Return Library Item

- **Action**: Allows a patron to return a borrowed library item.
- **Steps**:
  1. Select option `5` from the menu.
  2. Enter the name of the patron.
  3. Enter the ID of the item to return.
  4. The item will be returned, and the status will be updated.

#### 6. Search Library Items

- **Action**: Searches for library items by title, author, or ISBN.
- **Steps**:
  1. Select option `6` from the menu.
  2. Choose the search criterion:
     - `1` for Title
     - `2` for Author
     - `3` for ISBN
  3. Enter the search term.
  4. The search results will be displayed.

#### 7. Display All Library Items

- **Action**: Displays a list of all library items.
- **Steps**:
  1. Select option `7` from the menu.
  2. All library items will be listed with their details.

#### 8. Exit

- **Action**: Exits the application.
- **Steps**:
  1. Select option `8` from the menu.
  2. The application will close with a goodbye message.

This detailed example usage guide should help users understand how to navigate and use the Library Management System through the console-based menu provided by the `LibraryMenu` class. If you need further assistance, please refer to the README file in the repository or the generated Javadocs for more information.

### Using the GUI

The Library Management System also provides a Graphical User Interface (GUI) for a more user-friendly experience. The GUI offers the same functionalities as the console-based menu but with a more intuitive and visual approach.

#### Starting the GUI

1. **Compile the Project**:

   - Ensure that the `Gui.java` file is compiled along with the other source files:
     ```sh
     javac -d bin src/Library/*.java
     ```

2. **Run the GUI Application**:
   - Execute the `Gui` class to start the GUI:
     ```sh
     java -cp bin Library.Gui
     ```

#### GUI Components

The GUI provides several components for interacting with the library system:

1. **Main Window**:

   - The main window contains buttons and menus to access various functionalities, such as adding, editing, deleting, borrowing, returning, and searching library items.

2. **Add Library Item**:

   - A form where users can input details for a new library item, including title, author, ISBN, publisher, number of copies, and item type.

3. **Edit Library Item**:

   - A form that allows users to modify details of an existing library item. Users need to select the item to edit from a list or search for it.

4. **Delete Library Item**:

   - A prompt to confirm the deletion of a selected library item.

5. **Borrow Library Item**:

   - A form where users can input the patron's name and the ID of the item to borrow. The form will update the item status if it is available for borrowing.

6. **Return Library Item**:

   - A form where users can input the patron's name and the ID of the item to return. The form will update the item status upon return.

7. **Search Library Items**:

   - A search bar and filters to find library items by title, author, or ISBN. The results are displayed in a list format.

8. **Display All Library Items**:
   - A table or list displaying all library items with their details, such as title, author, ISBN, status, and item type.

#### Example Usage in the GUI

1. **Adding a Library Item**:

   - Click on the "Add Item" button.
   - Fill in the form with the required details (title, author, ISBN, publisher, number of copies, item type).
   - Click "Submit" to add the item to the library.

2. **Editing a Library Item**:

   - Click on the "Edit Item" button.
   - Search for the item to edit or select it from the list.
   - Modify the details in the form.
   - Click "Save" to update the item details.

3. **Deleting a Library Item**:

   - Click on the "Delete Item" button.
   - Search for the item to delete or select it from the list.
   - Confirm the deletion in the prompt.

4. **Borrowing a Library Item**:

   - Click on the "Borrow Item" button.
   - Enter the patron's name and the item ID in the form.
   - Click "Borrow" to update the item status.

5. **Returning a Library Item**:

   - Click on the "Return Item" button.
   - Enter the patron's name and the item ID in the form.
   - Click "Return" to update the item status.

6. **Searching for Library Items**:

   - Use the search bar and filters to find items by title, author, or ISBN.
   - View the search results in the list.

7. **Displaying All Library Items**:
   - Click on the "View All Items" button.
   - Browse through the list of all library items displayed in the table.

For any further details, refer to the README file in the repository and the generated Javadocs.
