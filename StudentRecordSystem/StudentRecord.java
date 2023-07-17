package StudentRecordSystem;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class StudentRecord {
    private List<Student> students;

    public StudentRecord() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public void removeStudent(String name) {
        students.removeIf(student -> student.getName().equalsIgnoreCase(name));
    }

    public Student getStudentByRollNumber(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public Student getStudentByName(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }
    
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Student student : students) {
                String line = student.getName() + "," + student.getRollNumber() + "," +
                        student.getAddress() + "," + student.getPhoneNumber();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadFromFile(String filename) {
        students.clear(); // Clear the existing records before loading from file
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0];
                    int rollNumber = Integer.parseInt(parts[1]);
                    String address = parts[2];
                    String phoneNumber = parts[3];
                    Student student = new Student(name, rollNumber, address, phoneNumber);
                    students.add(student);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
