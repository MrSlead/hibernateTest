import entity.Author;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Slf4j
public class AuthorHelper {
    private SessionFactory sessionFactory;

    public AuthorHelper() {
        log.info("constructor AuthorHelper");
        log.debug("get sessionFactory");
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Author> getAuthorList() {
        log.info("start method getAuthorList");
        log.debug("open session");
        Session session = sessionFactory.openSession();

        /*log.debug("create criteriaBuilder");
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Author> cq = criteriaBuilder.createQuery(Author.class);
        Root<Author> root = cq.from(Author.class);

        cq.select(root);

        log.debug("create query");
        Query query = session.createQuery(cq);
        List<Author> authors = query.getResultList();*/

        //hql request
        String hql = "FROM author";

        Query query = session.createQuery(hql);
        List<Author> authors = query.list();

        session.close();
        log.debug("close session");

        return authors;
    }

    public Author getAuthor(long id) {
        log.info("start method getAuthor param: id");
        log.debug("open session");
        Session session = sessionFactory.openSession();

        Author author = session.get(Author.class, id);

        session.close();
        log.debug("close session");

        return author;
    }

    public List<Author> getAuthor(String name) {
        log.info("start method getAuthorList param: name");
        log.debug("open session");
        Session session = sessionFactory.openSession();

        String hql = "FROM author where name = :param";

        Query query = session.createQuery(hql);
        query.setParameter("param", name);

        session.close();
        log.debug("close session");

        return query.list();
    }

    public Author addAuthor(Author author){
        log.info("start method addAuthor");
        log.debug("open session");
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(author);

        session.getTransaction().commit();
        session.close();
        log.debug("close session");

        return  author;
    }

    public Author removeAuthor(long id) {
        log.info("start method removeAuthor param: id");
        log.debug("open session");
        Session session = sessionFactory.openSession();

        Author author = session.load(Author.class, id);
        String hql = "DELETE  author  WHERE id = " + id;

        session.beginTransaction();
        Query query = session.createQuery(hql);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        session.close();
        log.debug("close session");

        return author;
    }

    public int removeAuthor(String name) {
        log.info("start method removeAuthor param: name");
        log.debug("open session");
        Session session = sessionFactory.openSession();

        String hql = "DELETE  author  WHERE name = :param";

        session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("param", name);

        int result = query.executeUpdate();

        session.getTransaction().commit();
        session.close();
        log.debug("close session");

        return result;
    }

    public Author updateAuthor(long id, String newName) {
        log.info("start method updateAuthor param: id");
        log.debug("open session");
        Session session = sessionFactory.openSession();

        String hql = "UPDATE author SET name = :param WHERE id = " + id;

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

    public int updateAuthor(String oldName, String newName) {
        log.info("start method updateAuthor param: oldName");
        log.debug("open session");
        Session session = sessionFactory.openSession();

        String hql = "UPDATE author SET name = :param WHERE name = :param2";

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
