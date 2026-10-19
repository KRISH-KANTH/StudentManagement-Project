import java.util.ArrayList;
import java.util.Scanner;

class Student {
    int id;
    String name;
    String course;
    double marks;

    Student(int id, String name, String course, double marks) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    void display() {
        System.out.printf("%d | %s | %s | %.2f%n", id, name, course, marks);
    }
}

public class StudentManagement {
    private static final ArrayList<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static int nextId = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add student");
            System.out.println("2. View students");
            System.out.println("3. Update student");
            System.out.println("4. Delete student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addStudent();
                case "2" -> viewStudents();
                case "3" -> updateStudent();
                case "4" -> deleteStudent();
                case "5" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Please choose an option from 1 to 5.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Student name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Course: ");
        String course = scanner.nextLine().trim();

        double marks = readMarks("Marks: ");

        students.add(new Student(nextId++, name, course, marks));
        System.out.println("Student added.");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\nID | Name | Course | Marks");
        for (Student student : students) {
            student.display();
        }
    }

    private static void updateStudent() {
        int id = readId("Enter student ID to update: ");
        Student student = findStudent(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("New name: ");
        student.name = scanner.nextLine().trim();

        System.out.print("New course: ");
        student.course = scanner.nextLine().trim();

        student.marks = readMarks("New marks: ");
        System.out.println("Student updated.");
    }

    private static void deleteStudent() {
        int id = readId("Enter student ID to delete: ");
        Student student = findStudent(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        students.remove(student);
        System.out.println("Student deleted.");
    }

    private static Student findStudent(int id) {
        for (Student student : students) {
            if (student.id == id) {
                return student;
            }
        }
        return null;
    }

    private static int readId(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int id = Integer.parseInt(scanner.nextLine().trim());
                if (id > 0) {
                    return id;
                }
            } catch (NumberFormatException ignored) {
               
            }
            System.out.println("Enter a valid positive number.");
        }
    }

    private static double readMarks(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double marks = Double.parseDouble(scanner.nextLine().trim());
                if (marks >= 0 && marks <= 100) {
                    return marks;
                }
            } catch (NumberFormatException ignored) {
               
            }
            System.out.println("Enter marks from 0 to 100.");
        }
    }
}