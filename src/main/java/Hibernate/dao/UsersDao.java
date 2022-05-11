package Hibernate.dao;

import Hibernate.models.Note;
import Hibernate.models.User;
import Hibernate.utils.HibUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class UsersDao implements DaoInterface{

    @Override
    public List<Note> select(User user) {
        Session session = HibUtils.getSessionFactory().openSession();
        String hql = " FROM  Note n where n.user = : user ";
        Query query = session.createQuery(hql);
        query.setParameter("user", user);
        List<Note> noteList = query.getResultList();
        session.close();
        return noteList;

    }

    public <T> void insert(T t) {
        Session session = HibUtils.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(t);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        session.close();
    }

    public void update(User user, String setn, String compl, String imp, int id) {
        Session session = HibUtils.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            String hql = " update Note n set n.sentence=:sentence,n.COMPLDATE =:comp,n.IMPORTANCE =:imp where n.user = : user and n.id=:id";
            Query query = session.createQuery(hql);
            query.setParameter("sentence", setn);
            query.setParameter("comp", compl);
            query.setParameter("imp", imp);
            query.setParameter("user", user);
            query.setParameter("id", id);
            System.out.println(query.executeUpdate());
            tx.commit();
            session.close();

        } catch (Exception e) {
            tx.rollback();
        }
    }

    @Override
    public List<Note> delete(User user, int id) {
        Session session = HibUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            // your code
            String hql = "delete from Note n where n.user= : user AND n.id= :id";
            Query query = session.createQuery(hql);
            query.setParameter("user", user);
            query.setParameter("id", id);
            System.out.println(query.executeUpdate());

            transaction.commit();
        } catch (Throwable t) {
            transaction.rollback();
            throw t;
        }
        session.close();
        return select(user);
    }


    public User checkUser(String username, String password) {
        Session session = HibUtils.getSessionFactory().openSession();

        String hql = "FROM User u where u.name = :username AND " +
                "u.password = :password";
        Query query = session.createQuery(hql);
        query.setParameter("username", username).setParameter("password", password);
        List<User> users = query.getResultList();

        session.close();
        if (users.size() == 0) {
            return null;
        } else {
            User result = null;
            for (User u : users) {
                result = u;
            }
            return result;
        }
    }
    public List<Note> searchById(User user, int i) {
        List<Note> notes = select(user).stream()
                .filter(x -> x.getId() == i)
                .collect(Collectors.toList());
        return notes;
    }

    @Override
    public List<Note> searchNotId(User user, String name, String imp, String date) {
        List<Note> notes = select(user).stream()
                .filter(x -> x.getIMPORTANCE().equals(imp)).filter(x -> x.getCOMPLDATE().contains(date)
                        && x.getSentence().contains(name))
                .collect(Collectors.toList());

        return notes;
    }

    @Override
    public void registr(User user) {
        Session session = HibUtils.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String hql = "  from User u where u.name =:name and u.password = : password ";
        Query query = session.createQuery(hql);
        query.setParameter("name", user.getName());
        query.setParameter("password", user.getPassword());
        List<User> users = query.getResultList();
        if (users.isEmpty()) {
            insert(user);

        } else
            tx.rollback();


    }
}
