package Library;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Gui extends JFrame {
    private Library library;
    private JTextField titleField, authorField, isbnField, publisherField, copiesField;
    private JComboBox<String> itemTypeCombo;
    private JComboBox<BookType> bookTypeCombo;
    private JButton addButton;
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

        // Create menu bar
        createMenuBar();

        // Create main panel with card layout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create and add panels
        cardPanel.add(createAddItemPanel(), "ADD_ITEM");
        cardPanel.add(createDisplayAllItemsPanel(), "DISPLAY_ALL");

        add(cardPanel, BorderLayout.CENTER);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu operationsMenu = new JMenu("Operations");

        JMenuItem addItemMenuItem = new JMenuItem("Add Item");
        addItemMenuItem.addActionListener(e -> cardLayout.show(cardPanel, "ADD_ITEM"));

        JMenuItem displayAllMenuItem = new JMenuItem("Display All Items");
        displayAllMenuItem.addActionListener(e -> {
            cardLayout.show(cardPanel, "DISPLAY_ALL");
            displayAllItems();
        });

        operationsMenu.add(addItemMenuItem);
        operationsMenu.add(displayAllMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(operationsMenu);
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
        panel.add(titleField = new JTextField(20), gbc);

        // Author
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Author:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(authorField = new JTextField(20), gbc);

        // ISBN
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(new JLabel("ISBN:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(isbnField = new JTextField(20), gbc);

        // Publisher
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Publisher:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(publisherField = new JTextField(20), gbc);

        // Number of Copies
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Number of Copies:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(copiesField = new JTextField(5), gbc);

        // Item Type
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Item Type:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(itemTypeCombo = new JComboBox<>(new String[]{"Book", "Periodical"}), gbc);

        // Book Type
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Book Type:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(bookTypeCombo = new JComboBox<>(BookType.values()), gbc);

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

    private void addItem() {
        try {
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String ISBN = isbnField.getText().trim();
            String publisher = publisherField.getText().trim();
            String copiesStr = copiesField.getText().trim();
            String itemType = (String) itemTypeCombo.getSelectedItem();
            BookType bookType = (BookType) bookTypeCombo.getSelectedItem();

            if (title.isEmpty() || author.isEmpty() || ISBN.isEmpty() || publisher.isEmpty() || copiesStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int numberOfCopies;
            try {
                numberOfCopies = Integer.parseInt(copiesStr);
                if (numberOfCopies < 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Number of copies must be a non-negative integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int newId = library.getNewId();
            Status status = Status.AVAILABLE; // Set default status to AVAILABLE

            LibraryItem newItem;
            if ("Book".equals(itemType)) {
                newItem = new Book(String.valueOf(newId), title, author, ISBN, publisher, numberOfCopies, status, bookType);
                library.addItem((Book) newItem);
            } else if ("Periodical".equals(itemType)) {
                newItem = new Periodical(String.valueOf(newId), title, author, ISBN, publisher, numberOfCopies, status, bookType);
                library.addItem((Periodical) newItem);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid item type selected.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            library.saveItems(); // Ensure the new item is saved to the file

            JOptionPane.showMessageDialog(this, "Item added successfully with ID: " + newId, "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
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

    private void clearFields() {
        titleField.setText("");
        authorField.setText("");
        isbnField.setText("");
        publisherField.setText("");
        copiesField.setText("");
        itemTypeCombo.setSelectedIndex(0);
        bookTypeCombo.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }
}