package com.workday;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LibraryManagementSystem {
    private List<Book>books;
    private List<Member>members;
    private List<Loan>loans;

    public LibraryManagementSystem(){
        books = new ArrayList<>();
        members = new ArrayList<>();
        loans = new ArrayList<>();

    }

    //add a new book

    public void addBook(Book book){
        books.add(book);
    }

    // add a new member

    public void addMember(Member member){
        members.add(member);
    }



    // Check out a book to a member
    public void checkOutBook(Book book, Member member, Date dueDate) {
        if (books.contains(book) && members.contains(member)) {
            Loan loan = new Loan(book, member, dueDate);
            loans.add(loan);
            System.out.println("Book '" + book.getTitle() + "' checked out to " + member.getName() + ".");
        } else {
            System.out.println("Book or member not found. Unable to check out the book.");
        }
    }

    // Check in a book
    public void checkInBook(Book book) {
        for (Loan loan : loans) {
            if (loan.getBook().equals(book) && !loan.isReturned()) {
                loan.markAsReturned();
                System.out.println("Book '" + book.getTitle() + "' checked in.");
                return;
            }
        }
        System.out.println("Book not found or already checked in.");
    }

    // Calculate overdue fines for a member
    public double calculateOverdueFines(Member member) {
        double overdueFines = 0.0;
        Date currentDate = new Date();

        for (Loan loan : loans) {
            if (loan.getMember().equals(member) && !loan.isReturned() && loan.getDueDate().before(currentDate)) {
                // Assuming a fixed fine rate per day
                long daysOverdue = (currentDate.getTime() - loan.getDueDate().getTime()) / (24 * 60 * 60 * 1000);
                double fineRate = 0.50; // Change this as needed
                overdueFines += daysOverdue * fineRate;
            }
        }

        return overdueFines;
    }

    // Generate reports

    // A list of all books in the library
    public List<Book> getAllBooks() {
        return books;
    }

    // A list of all members of the library
    public List<Member> getAllMembers() {
        return members;
    }

    // A list of all books that are currently checked out
    public List<Book> getCheckedOutBooks() {
        List<Book> checkedOutBooks = new ArrayList<>();
        for (Loan loan : loans) {
            if (!loan.isReturned()) {
                checkedOutBooks.add(loan.getBook());
            }
        }
        return checkedOutBooks;
    }

    // A list of all overdue books
    public List<Book> getOverdueBooks() {
        List<Book> overdueBooks = new ArrayList<>();
        Date currentDate = new Date();

        for (Loan loan : loans) {
            if (!loan.isReturned() && loan.getDueDate().before(currentDate)) {
                overdueBooks.add(loan.getBook());
            }
        }

        return overdueBooks;
    }

    public static void main(String[] args) {
        // Create a library
        LibraryManagementSystem library = new LibraryManagementSystem();

        // Add books to the library
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "Scribner", "Fiction", 180);
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "J.B. Lippincott & Co.", "Fiction", 281);
        library.addBook(book1);
        library.addBook(book2);

        // Add members to the library
        Member member1 = new Member("John Doe", "123 Main St", "555-1234", "john.doe@email.com");
        Member member2 = new Member("Jane Smith", "456 Oak St", "555-5678", "jane.smith@email.com");
        library.addMember(member1);
        library.addMember(member2);

        // Check out books to members
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dueDate1 = dateFormat.parse("2023-01-10");
            Date dueDate2 = dateFormat.parse("2023-02-15");
            library.checkOutBook(book1, member1, dueDate1);
            library.checkOutBook(book2, member2, dueDate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Generate reports
        System.out.println("All Books in the Library:");
        for (Book book : library.getAllBooks()) {
            System.out.println(book);
        }

        System.out.println("\nAll Members of the Library:");
        for (Member member : library.getAllMembers()) {
            System.out.println(member);
        }

        System.out.println("\nCurrently Checked Out Books:");
        for (Book book : library.getCheckedOutBooks()) {
            System.out.println(book);
        }

        System.out.println("\nOverdue Books:");
        for (Book book : library.getOverdueBooks()) {
            System.out.println(book);
        }

        // Check in a book
        library.checkInBook(book1);

        // Calculate overdue fines for a member
        double overdueFines = library.calculateOverdueFines(member2);
        System.out.println("\nOverdue Fines for " + member2.getName() + ": $" + overdueFines);
    }
}
