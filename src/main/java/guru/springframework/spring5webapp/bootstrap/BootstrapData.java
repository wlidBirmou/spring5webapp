package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BootstrapData implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        Author author = new Author("Arthur", "Evans");
        Author rod = new Author("Rod", "Jonhson");

        Book book = new Book("Domain driven design", "1254564");
        Book noEJB = new Book("J2ee dev without EJB", "556458");

        Publisher publisher = new Publisher(" kasbah", " said hamdine", "alger", "16000", "alger", "Algeria");

        Set<Book> books = new HashSet<Book>();
        books.add(book);
        author.setBooks(books);
        Set<Book> rodBooks = new HashSet<Book>();
        rodBooks.add(noEJB);
        rod.setBooks(books);

        Set<Author> authors = new HashSet<Author>();
        authors.add(author);
        book.setAuthors(authors);
        Set<Author> noEJBAuthors = new HashSet<Author>();
        noEJBAuthors.add(rod);
        noEJB.setAuthors(noEJBAuthors);
        this.publisherRepository.save(publisher);

        publisher.getBooks().add(noEJB);
        noEJB.setPublisher(publisher);
        book.setPublisher(publisher);
        this.authorRepository.save(rod);
        this.bookRepository.save(noEJB);

        this.authorRepository.save(author);
        this.bookRepository.save(book);

        System.out.println("the number of publisher is :" + this.publisherRepository.count());
        System.out.println("Publisher number of books is :" + publisher.getBooks().size());
    }
}
