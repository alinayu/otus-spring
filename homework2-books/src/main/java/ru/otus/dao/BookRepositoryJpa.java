package ru.otus.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Book insert(Book book) {
        Author author = em.find(Author.class, book.getAuthor().getId());
        Genre genre = em.find(Genre.class, book.getGenre().getId());
        book.setAuthor(author);
        book.setGenre(genre);
        return em.merge(book);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Book book = em.find(Book.class, id);
        em.remove(book);
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public Book getById(long id) {
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> getByAuthorId(long authorId) {
        TypedQuery<Book> query = em.createQuery("select b from Book b join b.author a where a.id = :authorId", Book.class);
        query.setParameter("authorId", authorId );
        return query.getResultList();
    }

    @Override
    @Transactional
    public void updateNameById(long id, String newName) {
        em.createQuery("update Book b set b.name = :name where b.id = :id")
                .setParameter("name", newName)
                .setParameter("id", id)
                .executeUpdate();
    }
}
