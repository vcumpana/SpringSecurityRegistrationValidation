package com.springapp.mvc.datasource;

import com.springapp.mvc.model.Gender;
import com.springapp.mvc.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Optional<User> getUserByName(String username) {
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("select u from User u where username=:name",
                        User.class);
        query.setParameter("name", username);

        return query.getResultList().stream().findFirst();
    }

    public List<User> getListOfUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("from User")
                .list();
    }

    public List<User> getAllByGender(Gender gender) {
        sessionFactory.getCurrentSession();
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from User where gender=:gender");
        query.setParameter("gender", gender);

        return query.getResultList();
    }

    public void insertNewUser(User user) { sessionFactory.getCurrentSession().save(user); }

    public boolean updateUser(User user) {
//        User newUser = getUserByName(user.getUsername()).get();
//        newUser.setEmail(user.getEmail());
        sessionFactory.getCurrentSession().merge(user);
        return true;
    }

    public boolean mailIsPresentInDB(String mail) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where email=:mail");
        query.setParameter("mail", mail);
        List<User> userlist = query.getResultList();
        return userlist.size() == 0 ? false : true;
    }

    public boolean usernameIsPresentInDB(String username) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where username=:user");
        query.setParameter("user", username);
        List<User> userlist = query.getResultList();
        return userlist.size() == 0 ? false : true;
    }

    public User getUserById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where id=:id");
        query.setParameter("id", id);
        User user = (User)query.getSingleResult();
        return  user;
    }

    public void deleteUserById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery(" delete from User where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
