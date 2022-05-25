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

    public BootstrapData(BookRepository bookRepository, AuthorRepository authorRepository,PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository=publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author author=new Author("Arthur","Evans");
        Set<Author> authors= new HashSet<Author>();
        Book book=new Book("Domain driven design","1254564");
        Set<Book> books= new HashSet<Book>();
        books.add(book);
        authors.add(author);
        author.setBooks(books);
        book.setAuthors(authors);

        this.authorRepository.save(author);
        this.bookRepository.save(book);
        Author rod=new Author("Rod","Jonhson");
        Book noEJB= new Book("J2ee dev without EJB", "556458");
        Set<Author> noEJBAuthors= new HashSet<Author>();
        Set<Book> rodBooks= new HashSet<Book>();
        rodBooks.add(noEJB);
        noEJBAuthors.add(rod);
        rod.setBooks(books);
        noEJB.setAuthors(noEJBAuthors);

        Publisher publisher=new Publisher(" kasbah"," said hamdine","alger","16000","alger","Algeria");
        this.publisherRepository.save(publisher);
        this.authorRepository.save(rod);
        this.bookRepository.save(noEJB);
        System.out.println("the number of books is :" + this.bookRepository.count());
    }
}
