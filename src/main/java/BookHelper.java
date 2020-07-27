import entity.Author;
import entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Slf4j
public class BookHelper {
    private SessionFactory sessionFactory;

    public BookHelper() {
        log.info("constructor BookHelper");
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Book> getBookList() {
        log.info("start method getBookList");
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Book> cq = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = cq.from(Book.class);

        Query query = session.createQuery(cq);
        List<Book> books = query.getResultList();

        return books;
    }

    public Book getBook(long id) {
        Session session = sessionFactory.openSession();

        Book book = session.get(Book.class, id);

        session.close();

        return book;
    }

    public List<Book> getBook(String name) {
        log.info("start method getAuthorList param: name");
        log.debug("open session");
        Session session = sessionFactory.openSession();

        String hql = "FROM book where name = :param";

        Query query = session.createQuery(hql);
        query.setParameter("param", name);
        List<Book> books = query.list();

        session.close();
        log.debug("close session");

        return books;
    }

    public Book addBook(Book book){
        log.info("start method addBook");
        log.debug("open session");
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(book);

        session.getTransaction().commit();
        session.close();
        log.debug("close session");

        return book;
    }

    public Author removeBook(long id) {
        log.info("start method removeBook param: id");
        log.debug("open session");
        Session session = sessionFactory.openSession();

        Author author = session.load(Author.class, id);
        String hql = "DELETE  book  WHERE id = " + id;

        session.beginTransaction();
        Query query = session.createQuery(hql);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        session.close();
        log.debug("close session");

        return author;
    }

    public int removeBook(String name) {
        log.info("start method removeBook param: name");
        log.debug("open session");
        Session session = sessionFactory.openSession();

        String hql = "DELETE  book  WHERE name = :param";

        session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("param", name);

        int result = query.executeUpdate();

        session.getTransaction().commit();
        session.close();
        log.debug("close session");

        return result;
    }

    public Author updateBook(long id, String newName) {
        log.info("start method updateBook param: id");
        log.debug("open session");
        Session session = sessionFactory.openSession();

        String hql = "UPDATE book SET name = :param WHERE id = " + id;

        session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("param", newName);

        int result = query.executeUpdate();
        Author author = session.get(Author.class, id);

        session.getTransaction().commit();
        session.close();
        log.debug("close session");

        return author;
    }

    public int updateBook(String oldName, String newName) {
        log.info("start method updateBook param: oldName");
        log.debug("open session");
        Session session = sessionFactory.openSession();

        String hql = "UPDATE book SET name = :param WHERE name = :param2";

        session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("param", newName);
        query.setParameter("param2", oldName);

        int result = query.executeUpdate();

        session.getTransaction().commit();
        session.close();
        log.debug("close session");

        return result;
    }
}
