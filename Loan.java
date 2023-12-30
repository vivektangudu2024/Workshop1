package com.workday;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Loan {
    private Book book;
    private Member member;
    private Date dueDate;
    private boolean returned;

    public Loan(Book book, Member member, Date dueDate) {
        this.book = book;
        this.member = member;
        this.dueDate = dueDate;
        this.returned = false;
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void markAsReturned() {
        returned = true;
    }
}