import java.util.Scanner;

class Student {
    int rollNumber;
    String name;
    int age;
    String grade;
    Student next;

    Student(int rollNumber, String name, int age, String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

class StudentList {
    private Student head;

    public void addAtBeginning(int roll, String name, int age, String grade) {
        Student newStudent = new Student(roll, name, age, grade);
        newStudent.next = head;
        head = newStudent;
    }

    public void addAtEnd(int roll, String name, int age, String grade) {
        Student newStudent = new Student(roll, name, age, grade);
        if (head == null) {
            head = newStudent;
            return;
        }
        Student current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newStudent;
    }

    public void addAtPosition(int position, int roll, String name, int age, String grade) {
        if (position == 1) {
            addAtBeginning(roll, name, age, grade);
            return;
        }
        Student newStudent = new Student(roll, name, age, grade);
        Student current = head;
        for (int i = 1; i < position - 1 && current != null; i++) {
            current = current.next;
        }
        if (current == null) return;
        newStudent.next = current.next;
        current.next = newStudent;
    }

    public void deleteByRollNumber(int roll) {
        if (head == null) return;
        if (head.rollNumber == roll) {
            head = head.next;
            return;
        }
        Student current = head;
        while (current.next != null && current.next.rollNumber != roll) {
            current = current.next;
        }
        if (current.next == null) return;
        current.next = current.next.next;
    }

    public Student searchByRollNumber(int roll) {
        Student current = head;
        while (current != null) {
            if (current.rollNumber == roll) return current;
            current = current.next;
        }
        return null;
    }

    public void updateGrade(int roll, String newGrade) {
        Student student = searchByRollNumber(roll);
        if (student != null) {
            student.grade = newGrade;
        }
    }

    public void displayAll() {
        Student current = head;
        while (current != null) {
            System.out.println("Roll: " + current.rollNumber + ", Name: " + current.name + ", Age: " + current.age + ", Grade: " + current.grade);
            current = current.next;
        }
    }
}

public class StudentRecordManager {
    public static void main(String[] args) {
        StudentList list = new StudentList();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Delete by Roll Number\n5. Search by Roll Number\n6. Update Grade\n7. Display All\n8. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Roll, Name, Age, Grade: ");
                    list.addAtBeginning(sc.nextInt(), sc.next(), sc.nextInt(), sc.next());
                    break;
                case 2:
                    System.out.print("Enter Roll, Name, Age, Grade: ");
                    list.addAtEnd(sc.nextInt(), sc.next(), sc.nextInt(), sc.next());
                    break;
                case 3:
                    System.out.print("Enter Position, Roll, Name, Age, Grade: ");
                    list.addAtPosition(sc.nextInt(), sc.nextInt(), sc.next(), sc.nextInt(), sc.next());
                    break;
                case 4:
                    System.out.print("Enter Roll Number to Delete: ");
                    list.deleteByRollNumber(sc.nextInt());
                    break;
                case 5:
                    System.out.print("Enter Roll Number to Search: ");
                    Student s = list.searchByRollNumber(sc.nextInt());
                    if (s != null)
                        System.out.println("Found: Roll: " + s.rollNumber + ", Name: " + s.name + ", Age: " + s.age + ", Grade: " + s.grade);
                    else
                        System.out.println("Not found.");
                    break;
                case 6:
                    System.out.print("Enter Roll and New Grade: ");
                    list.updateGrade(sc.nextInt(), sc.next());
                    break;
                case 7:
                    list.displayAll();
                    break;
            }
        } while (choice != 8);
        sc.close();
    }
}
