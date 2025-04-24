import java.util.Scanner;

class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie prev;
    Movie next;

    Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.prev = null;
        this.next = null;
    }
}

class MovieList {
    private Movie head, tail;

    public void addAtBeginning(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }

    public void addAtEnd(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (tail == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }

    public void addAtPosition(int position, String title, String director, int year, double rating) {
        if (position <= 1) {
            addAtBeginning(title, director, year, rating);
            return;
        }
        Movie current = head;
        for (int i = 1; i < position - 1 && current != null; i++) {
            current = current.next;
        }
        if (current == null || current == tail) {
            addAtEnd(title, director, year, rating);
            return;
        }
        Movie newMovie = new Movie(title, director, year, rating);
        newMovie.next = current.next;
        newMovie.prev = current;
        if (current.next != null) current.next.prev = newMovie;
        current.next = newMovie;
    }

    public void removeByTitle(String title) {
        Movie current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                if (current == head) {
                    head = head.next;
                    if (head != null) head.prev = null;
                } else if (current == tail) {
                    tail = tail.prev;
                    if (tail != null) tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                return;
            }
            current = current.next;
        }
    }

    public void searchByDirector(String director) {
        Movie current = head;
        boolean found = false;
        while (current != null) {
            if (current.director.equalsIgnoreCase(director)) {
                System.out.println("Title: " + current.title + ", Year: " + current.year + ", Rating: " + current.rating);
                found = true;
            }
            current = current.next;
        }
        if (!found) System.out.println("No movie found for director: " + director);
    }

    public void searchByRating(double rating) {
        Movie current = head;
        boolean found = false;
        while (current != null) {
            if (current.rating == rating) {
                System.out.println("Title: " + current.title + ", Director: " + current.director + ", Year: " + current.year);
                found = true;
            }
            current = current.next;
        }
        if (!found) System.out.println("No movie found with rating: " + rating);
    }

    public void updateRating(String title, double newRating) {
        Movie current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                current.rating = newRating;
                System.out.println("Rating updated for: " + title);
                return;
            }
            current = current.next;
        }
        System.out.println("Movie not found: " + title);
    }

    public void displayForward() {
        Movie current = head;
        System.out.println("\n-- Movie List (Forward) --");
        while (current != null) {
            System.out.println("Title: " + current.title + ", Director: " + current.director + ", Year: " + current.year + ", Rating: " + current.rating);
            current = current.next;
        }
    }

    public void displayBackward() {
        Movie current = tail;
        System.out.println("\n-- Movie List (Reverse) --");
        while (current != null) {
            System.out.println("Title: " + current.title + ", Director: " + current.director + ", Year: " + current.year + ", Rating: " + current.rating);
            current = current.prev;
        }
    }
}

public class MovieManager {
    public static void main(String[] args) {
        MovieList list = new MovieList();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Remove by Title\n5. Search by Director\n6. Search by Rating\n7. Update Rating\n8. Display Forward\n9. Display Reverse\n10. Exit");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter Title, Director, Year, Rating: ");
                    list.addAtBeginning(sc.nextLine(), sc.nextLine(), sc.nextInt(), sc.nextDouble());
                    sc.nextLine();
                    break;
                case 2:
                    System.out.print("Enter Title, Director, Year, Rating: ");
                    list.addAtEnd(sc.nextLine(), sc.nextLine(), sc.nextInt(), sc.nextDouble());
                    sc.nextLine();
                    break;
                case 3:
                    System.out.print("Enter Position, Title, Director, Year, Rating: ");
                    int pos = sc.nextInt();
                    sc.nextLine();
                    list.addAtPosition(pos, sc.nextLine(), sc.nextLine(), sc.nextInt(), sc.nextDouble());
                    sc.nextLine();
                    break;
                case 4:
                    System.out.print("Enter Title to Remove: ");
                    list.removeByTitle(sc.nextLine());
                    break;
                case 5:
                    System.out.print("Enter Director Name: ");
                    list.searchByDirector(sc.nextLine());
                    break;
                case 6:
                    System.out.print("Enter Rating to Search: ");
                    list.searchByRating(sc.nextDouble());
                    sc.nextLine();
                    break;
                case 7:
                    System.out.print("Enter Title and New Rating: ");
                    list.updateRating(sc.nextLine(), sc.nextDouble());
                    sc.nextLine();
                    break;
                case 8:
                    list.displayForward();
                    break;
                case 9:
                    list.displayBackward();
                    break;
            }
        } while (choice != 10);
        sc.close();
    }
}
