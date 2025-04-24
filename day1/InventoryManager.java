import java.util.Scanner;

class Item {
    String itemName;
    int itemId;
    int quantity;
    double price;
    Item next;

    public Item(String itemName, int itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class Inventory {
    Item head = null;

    public void addAtBeginning(String name, int id, int qty, double price) {
        Item newItem = new Item(name, id, qty, price);
        newItem.next = head;
        head = newItem;
    }

    public void addAtEnd(String name, int id, int qty, double price) {
        Item newItem = new Item(name, id, qty, price);
        if (head == null) {
            head = newItem;
        } else {
            Item temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newItem;
        }
    }

    public void addAtPosition(int pos, String name, int id, int qty, double price) {
        if (pos <= 1 || head == null) {
            addAtBeginning(name, id, qty, price);
            return;
        }

        Item newItem = new Item(name, id, qty, price);
        Item temp = head;
        int count = 1;
        while (temp != null && count < pos - 1) {
            temp = temp.next;
            count++;
        }
        newItem.next = temp.next;
        temp.next = newItem;
    }

    public void removeById(int id) {
        if (head == null) return;

        if (head.itemId == id) {
            head = head.next;
            return;
        }

        Item current = head;
        while (current.next != null && current.next.itemId != id)
            current = current.next;

        if (current.next != null)
            current.next = current.next.next;
    }

    public void updateQuantity(int id, int newQty) {
        Item current = head;
        while (current != null) {
            if (current.itemId == id) {
                current.quantity = newQty;
                return;
            }
            current = current.next;
        }
    }

    public void searchById(int id) {
        Item current = head;
        while (current != null) {
            if (current.itemId == id) {
                printItem(current);
                return;
            }
            current = current.next;
        }
        System.out.println("Item not found.");
    }

    public void searchByName(String name) {
        Item current = head;
        boolean found = false;
        while (current != null) {
            if (current.itemName.equalsIgnoreCase(name)) {
                printItem(current);
                found = true;
            }
            current = current.next;
        }
        if (!found)
            System.out.println("Item not found.");
    }

    public void displayInventory() {
        Item current = head;
        while (current != null) {
            printItem(current);
            current = current.next;
        }
    }

    public void totalInventoryValue() {
        double total = 0;
        Item current = head;
        while (current != null) {
            total += current.quantity * current.price;
            current = current.next;
        }
        System.out.println("Total Inventory Value: $" + total);
    }

    public void sortByName(boolean ascending) {
        head = mergeSortByName(head, ascending);
    }

    public void sortByPrice(boolean ascending) {
        head = mergeSortByPrice(head, ascending);
    }

    private Item mergeSortByName(Item node, boolean ascending) {
        if (node == null || node.next == null) return node;

        Item middle = getMiddle(node);
        Item nextOfMiddle = middle.next;
        middle.next = null;

        Item left = mergeSortByName(node, ascending);
        Item right = mergeSortByName(nextOfMiddle, ascending);

        return mergeByName(left, right, ascending);
    }

    private Item mergeSortByPrice(Item node, boolean ascending) {
        if (node == null || node.next == null) return node;

        Item middle = getMiddle(node);
        Item nextOfMiddle = middle.next;
        middle.next = null;

        Item left = mergeSortByPrice(node, ascending);
        Item right = mergeSortByPrice(nextOfMiddle, ascending);

        return mergeByPrice(left, right, ascending);
    }

    private Item mergeByName(Item a, Item b, boolean ascending) {
        if (a == null) return b;
        if (b == null) return a;

        Item result;
        int cmp = a.itemName.compareToIgnoreCase(b.itemName);
        if ((ascending && cmp <= 0) || (!ascending && cmp > 0)) {
            result = a;
            result.next = mergeByName(a.next, b, ascending);
        } else {
            result = b;
            result.next = mergeByName(a, b.next, ascending);
        }
        return result;
    }

    private Item mergeByPrice(Item a, Item b, boolean ascending) {
        if (a == null) return b;
        if (b == null) return a;

        Item result;
        if ((ascending && a.price <= b.price) || (!ascending && a.price > b.price)) {
            result = a;
            result.next = mergeByPrice(a.next, b, ascending);
        } else {
            result = b;
            result.next = mergeByPrice(a, b.next, ascending);
        }
        return result;
    }

    private Item getMiddle(Item node) {
        if (node == null) return node;

        Item slow = node, fast = node.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private void printItem(Item item) {
        System.out.println("ID: " + item.itemId + ", Name: " + item.itemName + ", Quantity: " + item.quantity + ", Price: $" + item.price);
    }
}

public class InventoryManager {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Remove by ID\n5. Update Quantity\n6. Search by ID\n7. Search by Name\n8. Display Inventory\n9. Total Inventory Value\n10. Sort by Name\n11. Sort by Price\n12. Exit");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Name, ID, Quantity, Price: ");
                    inventory.addAtBeginning(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextDouble());
                    break;
                case 2:
                    System.out.print("Enter Name, ID, Quantity, Price: ");
                    inventory.addAtEnd(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextDouble());
                    break;
                case 3:
                    System.out.print("Enter Position, Name, ID, Quantity, Price: ");
                    inventory.addAtPosition(sc.nextInt(), sc.next(), sc.nextInt(), sc.nextInt(), sc.nextDouble());
                    break;
                case 4:
                    System.out.print("Enter ID to Remove: ");
                    inventory.removeById(sc.nextInt());
                    break;
                case 5:
                    System.out.print("Enter ID and New Quantity: ");
                    inventory.updateQuantity(sc.nextInt(), sc.nextInt());
                    break;
                case 6:
                    System.out.print("Enter ID to Search: ");
                    inventory.searchById(sc.nextInt());
                    break;
                case 7:
                    System.out.print("Enter Name to Search: ");
                    inventory.searchByName(sc.next());
                    break;
                case 8:
                    inventory.displayInventory();
                    break;
                case 9:
                    inventory.totalInventoryValue();
                    break;
                case 10:
                    System.out.print("Sort Ascending (true/false): ");
                    inventory.sortByName(sc.nextBoolean());
                    break;
                case 11:
                    System.out.print("Sort Ascending (true/false): ");
                    inventory.sortByPrice(sc.nextBoolean());
                    break;
            }
        } while (choice != 12);
        sc.close();
    }
}
