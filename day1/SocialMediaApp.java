import java.util.ArrayList;

class User {
    int userId;
    String name;
    int age;
    ArrayList<Integer> friendIds;
    User next;

    public User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new ArrayList<>();
        this.next = null;
    }
}

class SocialNetwork {
    User head = null;

    public void addUser(int id, String name, int age) {
        User newUser = new User(id, name, age);
        if (head == null) {
            head = newUser;
        } else {
            User current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newUser;
        }
    }

    public User getUser(int id) {
        User current = head;
        while (current != null) {
            if (current.userId == id) return current;
            current = current.next;
        }
        return null;
    }

    public void addFriendConnection(int id1, int id2) {
        User user1 = getUser(id1);
        User user2 = getUser(id2);
        if (user1 != null && user2 != null && id1 != id2) {
            if (!user1.friendIds.contains(id2)) user1.friendIds.add(id2);
            if (!user2.friendIds.contains(id1)) user2.friendIds.add(id1);
        }
    }

    public void removeFriendConnection(int id1, int id2) {
        User user1 = getUser(id1);
        User user2 = getUser(id2);
        if (user1 != null && user2 != null) {
            user1.friendIds.remove(Integer.valueOf(id2));
            user2.friendIds.remove(Integer.valueOf(id1));
        }
    }

    public void displayFriends(int userId) {
        User user = getUser(userId);
        if (user != null) {
            System.out.println("Friends of " + user.name + ":");
            for (int id : user.friendIds) {
                User friend = getUser(id);
                if (friend != null) {
                    System.out.println("- " + friend.name + " (ID: " + friend.userId + ")");
                }
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public void mutualFriends(int id1, int id2) {
        User user1 = getUser(id1);
        User user2 = getUser(id2);
        if (user1 != null && user2 != null) {
            System.out.println("Mutual friends between " + user1.name + " and " + user2.name + ":");
            for (int id : user1.friendIds) {
                if (user2.friendIds.contains(id)) {
                    User mutual = getUser(id);
                    if (mutual != null)
                        System.out.println("- " + mutual.name + " (ID: " + mutual.userId + ")");
                }
            }
        } else {
            System.out.println("One or both users not found.");
        }
    }

    public void searchByIdOrName(String query) {
        User current = head;
        boolean found = false;
        while (current != null) {
            if (String.valueOf(current.userId).equals(query) || current.name.equalsIgnoreCase(query)) {
                System.out.println("User found: " + current.name + " (ID: " + current.userId + ")");
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No user found with the given input.");
        }
    }

    public void countFriends() {
        User current = head;
        while (current != null) {
            System.out.println(current.name + " has " + current.friendIds.size() + " friend(s).");
            current = current.next;
        }
    }
}

public class SocialMediaApp {
    public static void main(String[] args) {
        SocialNetwork sn = new SocialNetwork();
        
        sn.addUser(1, "Alice", 25);
        sn.addUser(2, "Bob", 24);
        sn.addUser(3, "Charlie", 23);
        sn.addUser(4, "David", 26);

        sn.addFriendConnection(1, 2);
        sn.addFriendConnection(1, 3);
        sn.addFriendConnection(2, 3);

        sn.displayFriends(1);
        sn.displayFriends(2);
        sn.mutualFriends(1, 2);
        
        sn.removeFriendConnection(1, 2);
        sn.displayFriends(1);

        sn.searchByIdOrName("Charlie");
        sn.searchByIdOrName("5");

        sn.countFriends();
    }
}
