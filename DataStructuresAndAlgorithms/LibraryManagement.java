package DataStructuresAndAlgorithms;
import java.util.Arrays;
import java.util.Comparator;

class Book {
    int bookId;
    String title;
    String author;

    Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author;
    }
}

class Library {
    public static Book linearSearch(Book[] books, String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public static Book binarySearch(Book[] books, String title) {
        int left = 0;
        int right = books.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = books[mid].title.compareToIgnoreCase(title);

            if (cmp == 0) {
                return books[mid];
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return null;
    }
}

public class  LibraryManagement{
    public static void main(String[] args) {
        Book[] books = {
                new Book(1, "Java Programming", "James Gosling"),
                new Book(2, "Data Structures", "Mark Allen"),
                new Book(3, "Algorithms", "Robert Sedgewick"),
                new Book(4, "Operating Systems", "Abraham Silberschatz"),
                new Book(5, "Computer Networks", "Andrew Tanenbaum")
        };

        Book linearResult = Library.linearSearch(books, "Algorithms");

        if (linearResult != null) {
            System.out.println("Linear Search Result:");
            System.out.println(linearResult);
        } else {
            System.out.println("Book not found");
        }

        Arrays.sort(books, Comparator.comparing(book -> book.title.toLowerCase()));

        Book binaryResult = Library.binarySearch(books, "Algorithms");

        if (binaryResult != null) {
            System.out.println("\nBinary Search Result:");
            System.out.println(binaryResult);
        } else {
            System.out.println("Book not found");
        }


        // Time Complexity 
        // 1. Linear Search goes index by index in sequential data , so it takes it to go through all the 
        // data points that we in between starting and the target so TC becomes , O(n) in worst case

        //2 . Binary Search , while it can only be applied on sorted arrays , but TC is O(n) because at each step ,we decrease the search space with logn()
        
    }
}


