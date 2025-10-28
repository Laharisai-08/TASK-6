import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ToDoListApp extends JFrame {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskInput;
    private JButton addButton, deleteButton, clearButton;

    public ToDoListApp() {
        // Frame setup
        setTitle("To-Do List App");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Title label
        JLabel titleLabel = new JLabel("📝 To-Do List", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(titleLabel, BorderLayout.NORTH);

        // List model and JList
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(taskList), BorderLayout.CENTER);

        // Input and buttons panel
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        taskInput = new JTextField();
        inputPanel.add(taskInput, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear All");

        addButton.setBackground(new Color(102, 205, 170));
        deleteButton.setBackground(new Color(255, 160, 122));
        clearButton.setBackground(new Color(255, 218, 185));

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        inputPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(inputPanel, BorderLayout.SOUTH);

        // Add Button Action
        addButton.addActionListener(e -> {
            String task = taskInput.getText().trim();
            if (!task.isEmpty()) {
                taskListModel.addElement(task);
                taskInput.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Enter a task first!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Delete Button Action
        deleteButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                taskListModel.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(this, "Select a task to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Clear All Button Action
        clearButton.addActionListener(e -> {
            if (taskListModel.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No tasks to clear!", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear all tasks?",
                        "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    taskListModel.clear();
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ToDoListApp app = new ToDoListApp();
            app.setVisible(true);
        });
    }
}
