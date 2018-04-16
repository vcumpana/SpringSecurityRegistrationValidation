package com.springapp.mvc.datasource;

import com.springapp.mvc.model.Gender;
import com.springapp.mvc.model.Role;
import com.springapp.mvc.model.RoleName;
import com.springapp.mvc.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.stream.Collectors;

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

    public void insertNewUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        sessionFactory.getCurrentSession().save(user); }

    public boolean updateUser(User user) {
        User userDB = getUserById(user.getId());
        userDB.setName(user.getName());
        userDB.setSurname(user.getSurname());
        userDB.setUsername(user.getUsername());
        userDB.setBirthDate(user.getBirthDate());
        userDB.setEmail(user.getEmail());
        userDB.setGender(user.getGender());
        userDB.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
      //  List<RoleName> roles = user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList());
        //RoleName[] role = new RoleName[roles.size()];
       // role = roles.toArray(role);
       // userDB.setRoles(getRoleByRoleName(role));
        sessionFactory.getCurrentSession().merge(userDB);
        return true;
    }

    public boolean mailIsPresentInDB(String mail, int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where email=:mail and id !=:id");
        query.setParameter("mail", mail);
        query.setParameter("id", id);
        List<User> userlist = query.getResultList();
        return userlist.size() == 0 ? false : true;
    }

    public boolean usernameIsPresentInDB(String username, int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where username=:user and id !=:id");
        query.setParameter("user", username);
        query.setParameter("id", id);
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

    public List getRoles() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Role")
                .list();
    }

    public Collection<Role> getRoleByRoleName(RoleName ... roles) {
        Query query= sessionFactory.getCurrentSession()
                .createQuery("from Role where roleName IN (:role)");
        query.setParameter("role", Arrays.asList(roles));
        return query.getResultList();
    }

    public List<User> getUsersByRole(RoleName roleName) {
    }
}
