package Library;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Gui extends JFrame {
    private Library library;
    // Fields for adding items
    private JTextField addTitleField, addAuthorField, addIsbnField, addPublisherField, addCopiesField;
    private JComboBox<String> addItemTypeCombo;
    private JComboBox<BookType> addBookTypeCombo;
    // Fields for editing items
    private JTextField editTitleField, editAuthorField, editIsbnField, editPublisherField, editCopiesField, editIdField;
    private JComboBox<String> editItemTypeCombo;
    private JComboBox<BookType> editBookTypeCombo;
    private JButton addButton, editButton;
    private JTable itemTable;
    private DefaultTableModel tableModel;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public Gui() {
        library = new Library();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Library Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createMenuBar();

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(createAddItemPanel(), "ADD_ITEM");
        cardPanel.add(createDisplayAllItemsPanel(), "DISPLAY_ALL");
        cardPanel.add(createEditItemPanel(), "EDIT_ITEM");

        add(cardPanel, BorderLayout.CENTER);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
    
        JMenuItem addItemMenuItem = new JMenuItem("Add Item");
        addItemMenuItem.addActionListener(e -> cardLayout.show(cardPanel, "ADD_ITEM"));
    
        JMenuItem displayAllMenuItem = new JMenuItem("Display All Items");
        displayAllMenuItem.addActionListener(e -> {
            cardLayout.show(cardPanel, "DISPLAY_ALL");
            displayAllItems();
        });
    
        JMenuItem editItemMenuItem = new JMenuItem("Edit Item");
        editItemMenuItem.addActionListener(e -> cardLayout.show(cardPanel, "EDIT_ITEM"));
    
        menu.add(addItemMenuItem);
        menu.add(displayAllMenuItem);
        menu.add(editItemMenuItem);
    
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }
    
    private JPanel createAddItemPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Add New Item"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Title:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(addTitleField = new JTextField(20), gbc);

        // Author
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Author:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(addAuthorField = new JTextField(20), gbc);

        // ISBN
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(new JLabel("ISBN:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(addIsbnField = new JTextField(20), gbc);

        // Publisher
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Publisher:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(addPublisherField = new JTextField(20), gbc);

        // Number of Copies
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Number of Copies:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(addCopiesField = new JTextField(5), gbc);

        // Item Type
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Item Type:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(addItemTypeCombo = new JComboBox<>(new String[]{"Book", "Periodical"}), gbc);

        // Book Type
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Book Type:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(addBookTypeCombo = new JComboBox<>(BookType.values()), gbc);

        // Add Item Button
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        addButton = new JButton("Add Item");
        addButton.addActionListener(e -> addItem());
        panel.add(addButton, gbc);

        return panel;
    }

    private JPanel createDisplayAllItemsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel();
        itemTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(itemTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> displayAllItems());
        panel.add(refreshButton, BorderLayout.SOUTH);

        return panel;
    }
    private JPanel createEditItemPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Edit Item"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // ID Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Item ID:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(editIdField = new JTextField(10), gbc);

        // Title
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Title:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(editTitleField = new JTextField(20), gbc);

        // Author
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Author:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(editAuthorField = new JTextField(20), gbc);

        // ISBN
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(new JLabel("ISBN:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(editIsbnField = new JTextField(20), gbc);

        // Publisher
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Publisher:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(editPublisherField = new JTextField(20), gbc);

        // Number of Copies
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Number of Copies:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(editCopiesField = new JTextField(5), gbc);

        // Item Type
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Item Type:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(editItemTypeCombo = new JComboBox<>(new String[]{"Book", "Periodical"}), gbc);

        // Book Type
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Book Type:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(editBookTypeCombo = new JComboBox<>(BookType.values()), gbc);

        // Load and Edit buttons
        gbc.gridy = 8;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        JButton loadButton = new JButton("Load Item");
        loadButton.addActionListener(e -> loadItemForEditing());
        panel.add(loadButton, gbc);

        gbc.gridx = 1;
        editButton = new JButton("Save Changes");
        editButton.addActionListener(e -> editItem());
        panel.add(editButton, gbc);

        return panel;
    }

    private void loadItemForEditing() {
        String itemId = editIdField.getText().trim();
        if (itemId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an item ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LibraryItem item = library.findItemById(itemId);
        if (item == null) {
            JOptionPane.showMessageDialog(this, "Item not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        editTitleField.setText(item.getTitle());
        editAuthorField.setText(item.getAuthor());
        editIsbnField.setText(item.getISBN());
        editPublisherField.setText(item.getPublisher());
        editCopiesField.setText(String.valueOf(item.getNumberOfCopies()));
        editItemTypeCombo.setSelectedItem(item instanceof Book ? "Book" : "Periodical");
        editBookTypeCombo.setSelectedItem(item.getBookType());
    }

    private void editItem() {
        String itemId = editIdField.getText().trim();
        if (itemId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an item ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LibraryItem item = library.findItemById(itemId);
        if (item == null) {
            JOptionPane.showMessageDialog(this, "Item not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            item.setTitle(editTitleField.getText().trim());
            item.setAuthor(editAuthorField.getText().trim());
            item.setISBN(editIsbnField.getText().trim());
            item.setPublisher(editPublisherField.getText().trim());
            item.setNumberOfCopies(Integer.parseInt(editCopiesField.getText().trim()));
            item.setBookType((BookType) editBookTypeCombo.getSelectedItem());

            library.saveItems();
            JOptionPane.showMessageDialog(this, "Item updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearEditFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number of copies.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating item: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addItem() {
        try {
            String title = addTitleField.getText().trim();
            String author = addAuthorField.getText().trim();
            String ISBN = addIsbnField.getText().trim();
            String publisher = addPublisherField.getText().trim();
            String copiesStr = addCopiesField.getText().trim();
            String itemType = (String) addItemTypeCombo.getSelectedItem();
            BookType bookType = (BookType) addBookTypeCombo.getSelectedItem();

            if (title.isEmpty() || author.isEmpty() || ISBN.isEmpty() || publisher.isEmpty() || copiesStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int numberOfCopies = Integer.parseInt(copiesStr);
            if (numberOfCopies < 0) {
                throw new NumberFormatException();
            }

            int newId = library.getNewId();
            Status status = Status.AVAILABLE;

            LibraryItem newItem;
            if ("Book".equals(itemType)) {
                newItem = new Book(String.valueOf(newId), title, author, ISBN, publisher, numberOfCopies, status, bookType);
            } else if ("Periodical".equals(itemType)) {
                newItem = new Periodical(String.valueOf(newId), title, author, ISBN, publisher, numberOfCopies, status, bookType);
            } else {
                throw new IllegalArgumentException("Invalid item type selected.");
            }

            library.addItem(newItem);
            library.saveItems();

            JOptionPane.showMessageDialog(this, "Item added successfully with ID: " + newId, "Success", JOptionPane.INFORMATION_MESSAGE);
            clearAddFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number of copies. Please enter a non-negative integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error adding item: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void displayAllItems() {
        tableModel.setRowCount(0);
        tableModel.setColumnIdentifiers(new Object[]{"ID", "Title", "Author", "ISBN", "Publisher", "Copies", "Status", "Type"});

        List<LibraryItem> items = library.getItems();

        for (LibraryItem item : items) {
            Object[] row = new Object[]{
                item.getId(),
                item.getTitle(),
                item.getAuthor(),
                item.getISBN(),
                item.getPublisher(),
                item.getNumberOfCopies(),
                item.getStatus(),
                item.getItemType()
            };
            tableModel.addRow(row);
        }
    }

    private void clearAddFields() {
        addTitleField.setText("");
        addAuthorField.setText("");
        addIsbnField.setText("");
        addPublisherField.setText("");
        addCopiesField.setText("");
        addItemTypeCombo.setSelectedIndex(0);
        addBookTypeCombo.setSelectedIndex(0);
    }

    private void clearEditFields() {
        editIdField.setText("");
        editTitleField.setText("");
        editAuthorField.setText("");
        editIsbnField.setText("");
        editPublisherField.setText("");
        editCopiesField.setText("");
        editItemTypeCombo.setSelectedIndex(0);
        editBookTypeCombo.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }
}