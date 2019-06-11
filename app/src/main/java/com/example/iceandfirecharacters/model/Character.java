package com.example.iceandfirecharacters.model;

import java.util.List;

/**
 * Character
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 */
public class Character {

   /* url	string	The hypermedia URL of this resource
    name	string	The name of this character
    gender	string	The gender of this character.
    culture	string	The culture that this character belongs to.
    born	string	Textual representation of when and where this character was born.
    died	string	Textual representation of when and where this character died.
    titles	array of strings	TThe titles that this character holds.
    aliases	array of strings	The aliases that this character goes by.
    father	string	The character resource URL of this character's father.
    mother	string	The character resource URL of this character's mother.
    spouse	string	An array of Character resource URLs that has had a POV-chapter in this book.
    allegiances	array of strings	An array of House resource URLs that this character is loyal to.
    books	array of strings	An array of Book resource URLs that this character has been in.
    povBooks	array of strings	An array of Book resource URLs that this character has had a POV-chapter in.
    tvSeries	array of strings	An array of names of the seasons of Game of Thrones that this character has been in.
    playedBy	array of strings	An array of actor names that has played this character in the TV show Game Of Thrones.*/

   public String url;
   public String name;
   public String gender;
   public String culture;
   public String born;
   public String died;
   public List<String> titles;
   public List<String> aliases;
   public String father;
   public String mother;
   public String spouse;
   public List<String> allegiances;
   public List<String> books;
   public List<String> povBooks;
   public List<String> tvSeries;
}
