package com.workday;

class Book{
    private String title;
    private String author;

    private String publisher;
    private String genre;
    private int numberOfPages;

    public Book(String title ,String author, String publisher,String genre,int numberOfPages){
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.numberOfPages = numberOfPages;
    }

    //getters

    public  String getTitle(){
        return title;
    }

    public  void setTitle(String title){
        this.title = title;
    }
    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getPublisherr(){
        return author;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public String getGenre(){
        return genre;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public int getNumberOfPages(){
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages){
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return title + " of " + author;
    }
}