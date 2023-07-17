package StudentRecordSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentRecordGUI extends JFrame {
    private StudentRecord studentRecord;

    private JTextField nameField;
    private JTextField rollNumberField;
    private JTextField addressField;
    private JTextField phoneNumberField;

    public StudentRecordGUI() {
        studentRecord = new StudentRecord();

        // Initialize Swing components
        JLabel nameLabel = new JLabel("Name:");
        JLabel rollNumberLabel = new JLabel("Roll Number:");
        JLabel addressLabel = new JLabel("Address:");
        JLabel phoneNumberLabel = new JLabel("Phone Number:");

        nameField = new JTextField(20);
        rollNumberField = new JTextField(10);
        addressField = new JTextField(30);
        phoneNumberField = new JTextField(10);

        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton displayButton = new JButton("Display Student");
        JButton saveButton = new JButton("Save to File");
        JButton loadButton = new JButton("Load from File");


        // Set up the layout
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(5, 5, 5, 5);

        // Add components to the frame
        add(nameLabel, gc);
        gc.gridy++;
        add(rollNumberLabel, gc);
        gc.gridy++;
        add(addressLabel, gc);
        gc.gridy++;
        add(phoneNumberLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(nameField, gc);
        gc.gridy++;
        add(rollNumberField, gc);
        gc.gridy++;
        add(addressField, gc);
        gc.gridy++;
        add(phoneNumberField, gc);

        gc.gridx = 0;
        gc.gridy++;
        gc.gridwidth = 2;
        add(addButton, gc);
        gc.gridy++;
        add(removeButton, gc);
        gc.gridy++;
        add(displayButton, gc);
        gc.gridy++;
        add(saveButton, gc);
        gc.gridy++;
        add(loadButton, gc);

        // Add action listeners for buttons
      
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayStudent();
            }
        });
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFromFile();
            }
        });
       
        // Set JFrame properties
        setTitle("Student Record Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addStudent() {
        String name = nameField.getText();
        int rollNumber = Integer.parseInt(rollNumberField.getText());
        String address = addressField.getText();
        String phoneNumber = phoneNumberField.getText();

        Student student = new Student(name, rollNumber, address, phoneNumber);
        studentRecord.addStudent(student);
        clearFields();
        JOptionPane.showMessageDialog(this, "Student added successfully!");
    }

    private void removeStudent() {
        String rollNumberText = rollNumberField.getText();
        if (rollNumberText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid roll number!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int rollNumber = Integer.parseInt(rollNumberText);
        Student student = studentRecord.getStudentByRollNumber(rollNumber);
        if (student != null) {
            studentRecord.removeStudent(rollNumber);
            clearFields();
            JOptionPane.showMessageDialog(this, "Student removed successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Student not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayStudent() {
        String rollNumberText = rollNumberField.getText();
        if (rollNumberText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid roll number!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int rollNumber = Integer.parseInt(rollNumberText);
        Student student = studentRecord.getStudentByRollNumber(rollNumber);
        if (student != null) {
            String info = "Name: " + student.getName() + "\n"
                    + "Roll Number: " + student.getRollNumber() + "\n"
                    + "Address: " + student.getAddress() + "\n"
                    + "Phone Number: " + student.getPhoneNumber();
            JOptionPane.showMessageDialog(this, info, "Student Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Student not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        rollNumberField.setText("");
        addressField.setText("");
        phoneNumberField.setText("");
    }
    private void saveToFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String filename = fileChooser.getSelectedFile().getPath();
            studentRecord.saveToFile(filename);
            JOptionPane.showMessageDialog(this, "Student data saved successfully!");
        }
    }

    private void loadFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String filename = fileChooser.getSelectedFile().getPath();
            studentRecord.loadFromFile(filename);
            JOptionPane.showMessageDialog(this, "Student data loaded successfully!");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
           	StudentRecordGUI studentRecordGUI = new StudentRecordGUI();
              studentRecordGUI.loadFromFile(); // Load data from file on application start
          
                
            }
        });
    }
}
