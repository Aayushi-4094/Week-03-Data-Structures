import java.util.Scanner;

class Task {
    int id;
    String name;
    int priority;
    String dueDate;
    Task next;

    Task(int id, String name, int priority, String dueDate) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

class CircularTaskList {
    private Task head = null;
    private Task tail = null;
    private Task current = null;

    public void addAtBeginning(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            newTask.next = head;
        } else {
            newTask.next = head;
            tail.next = newTask;
            head = newTask;
        }
    }

    public void addAtEnd(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            newTask.next = head;
        } else {
            tail.next = newTask;
            newTask.next = head;
            tail = newTask;
        }
    }

    public void addAtPosition(int pos, int id, String name, int priority, String dueDate) {
        if (pos <= 1) {
            addAtBeginning(id, name, priority, dueDate);
            return;
        }

        Task newTask = new Task(id, name, priority, dueDate);
        Task temp = head;
        int count = 1;

        while (count < pos - 1 && temp.next != head) {
            temp = temp.next;
            count++;
        }

        newTask.next = temp.next;
        temp.next = newTask;

        if (temp == tail) {
            tail = newTask;
        }
    }

    public void removeById(int id) {
        if (head == null) return;

        Task temp = head;
        Task prev = tail;

        do {
            if (temp.id == id) {
                if (temp == head) {
                    head = head.next;
                    tail.next = head;
                } else if (temp == tail) {
                    tail = prev;
                    tail.next = head;
                } else {
                    prev.next = temp.next;
                }

                if (current == temp) current = current.next;
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);
    }

    public void viewCurrentAndMoveNext() {
        if (current == null) {
            current = head;
        }
        if (current != null) {
            System.out.println("Current Task: ID=" + current.id + ", Name=" + current.name + ", Priority=" + current.priority + ", Due=" + current.dueDate);
            current = current.next;
        } else {
            System.out.println("No tasks in the list.");
        }
    }

    public void displayTasks() {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }

        Task temp = head;
        System.out.println("-- All Tasks --");

        do {
            System.out.println("ID: " + temp.id + ", Name: " + temp.name + ", Priority: " + temp.priority + ", Due: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks to search.");
            return;
        }

        Task temp = head;
        boolean found = false;

        do {
            if (temp.priority == priority) {
                System.out.println("Found Task: ID=" + temp.id + ", Name=" + temp.name + ", Due=" + temp.dueDate);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("No task found with priority: " + priority);
        }
    }
}

public class TaskScheduler {
    public static void main(String[] args) {
        CircularTaskList scheduler = new CircularTaskList();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Remove by ID\n5. View Current Task and Move to Next\n6. Display All Tasks\n7. Search by Priority\n8. Exit");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID, Name, Priority, Due Date: ");
                    scheduler.addAtBeginning(sc.nextInt(), sc.next(), sc.nextInt(), sc.next());
                    break;
                case 2:
                    System.out.print("Enter ID, Name, Priority, Due Date: ");
                    scheduler.addAtEnd(sc.nextInt(), sc.next(), sc.nextInt(), sc.next());
                    break;
                case 3:
                    System.out.print("Enter Position, ID, Name, Priority, Due Date: ");
                    int pos = sc.nextInt();
                    scheduler.addAtPosition(pos, sc.nextInt(), sc.next(), sc.nextInt(), sc.next());
                    break;
                case 4:
                    System.out.print("Enter Task ID to Remove: ");
                    scheduler.removeById(sc.nextInt());
                    break;
                case 5:
                    scheduler.viewCurrentAndMoveNext();
                    break;
                case 6:
                    scheduler.displayTasks();
                    break;
                case 7:
                    System.out.print("Enter Priority to Search: ");
                    scheduler.searchByPriority(sc.nextInt());
                    break;
            }
        } while (choice != 8);
        sc.close();
    }
}
