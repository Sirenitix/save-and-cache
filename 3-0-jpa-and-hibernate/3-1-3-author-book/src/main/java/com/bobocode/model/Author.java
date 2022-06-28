package com.bobocode.model;

import lombok.*;
import javax.persistence.*;
import java.util.*;


/**
 * todo:
 * - implement hashCode() that return constant value 31
 * - make setter for field {@link Author#books} private
 * - initialize field {@link Author#books} as new {@link HashSet}
 * - implement a helper {@link Author#addBook(Book)} that establishes a relation on both sides
 * - implement a helper {@link Author#removeBook(Book)} that drops a relation on both sides
 * <p>
 * - configure JPA entity
 * - specify table name: "author"
 * - configure auto generated identifier
 * - configure mandatory column "first_name" for field {@link Author#firstName}
 * - configure mandatory column "last_name" for field {@link Author#lastName}
 * <p>
 * - configure many-to-many relation between {@link Author} and {@link Book}
 * - configure cascade operations for this relations {@link CascadeType#PERSIST} and {@link CascadeType#MERGE}
 * - configure link (join) table "author_book"
 * - configure foreign key column "book_id" references book table
 * - configure foreign key column "author_id" references author table
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false, unique = true)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToMany(cascade = { CascadeType.PERSIST,CascadeType.MERGE })
    @JoinTable(
            name = "author_book",
            joinColumns = { @JoinColumn(name = "author_id") },
            inverseJoinColumns = { @JoinColumn(name = "book_id") }
    )
    private Set<Book> books = new HashSet<>();

    public void addBook(Book book) {
        book.getAuthors().add(this);
        books.add(book);
    }

    public void removeBook(Book book) {
        book.getAuthors().remove(this);
        books.remove(book);
    }

    private void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName);
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
