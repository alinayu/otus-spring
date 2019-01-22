package ru.otus.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Comment insert(Comment comment) {
        Book book = em.find(Book.class, comment.getBook().getId());
        comment.setBook(book);
        return em.merge(comment);
    }

    @Override
    public List<Comment> getByBookId(long bookId) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c join c.book b where b.id = :bookId", Comment.class);
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }


}
