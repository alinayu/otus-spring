package ru.otus.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Book;

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
    public void insert(Book book) {
        em.persist(book);
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
        TypedQuery<Book> query = em.createQuery("select b from Book b join b.authori_id a where a.id = :authorId", Book.class);
        query.setParameter("authorId", authorId );
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        em.createQuery("delete from Book b where b.id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public void updateNameById(long id, String newName) {
        em.createQuery("update Book b set b.name = :name where b.id = :id")
                .setParameter("name", newName)
                .setParameter("id", id)
                .executeUpdate();
    }
}
