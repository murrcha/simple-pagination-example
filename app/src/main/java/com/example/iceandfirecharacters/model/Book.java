package com.example.iceandfirecharacters.model;

import java.util.Date;
import java.util.List;

/**
 * Book
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 */
public class Book {

    /*url	string	The hypermedia URL of this resource
    name	string	The name of this book
    isbn	string	The International Standard Book Number (ISBN-13) that uniquely identifies this book.
    authors	array of strings	An array of names of the authors that wrote this book.
    numberOfPages	integer	The number of pages in this book.
    publiser	string	The company that published this book.
    country	string	The country that this book was published in
    mediaType	string	The type of media this book was released in.
    released	date	The date (ISO 8601) when this book was released.
    characters	array of strings	An array of Character resource URLs that has been in this book.
    povCharacters	array of strings	An array of Character resource URLs that has had a POV-chapter in this book.*/

    public String url;
    public String name;
    public String isbn;
    public List<String> authors;
    public int numberOfPages;
    public String publiser;
    public String country;
    public String mediaType;
    public Date released;
    public List<String> characters;
    public List<String> povCharacters;
}
