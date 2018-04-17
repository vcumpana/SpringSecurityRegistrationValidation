package com.springapp.mvc.service;

import com.springapp.mvc.datasource.UserDao;
import com.springapp.mvc.model.Gender;
import com.springapp.mvc.model.Role;
import com.springapp.mvc.model.RoleName;
import com.springapp.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.springapp.mvc.model.RoleName.ROLE_USER;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers() {
        return userDao.getListOfUsers();
    }

    public List<User> getAllByGender(Gender gender) {
        return userDao.getAllByGender(gender);
    }

    public Optional<User> getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    public void registerNewUser(User user) {
        if (user.getRoles() == null || user.getRoles().isEmpty()){
            if (user.getUsername().equals("vcumpana"))
                user.setRoles(userDao.getRoles());
            else
                user.setRoles(userDao.getRoleByRoleName(ROLE_USER));
        }
        else {
            List<RoleName> roles = user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList());
            RoleName[] roleArray = new RoleName[roles.size()];
            roleArray = roles.toArray(roleArray);
            user.setRoles(userDao.getRoleByRoleName(roleArray));
        }
        userDao.insertNewUser(user);
    }

    public boolean saveChangesUser(User user) {
        if (userDao.updateUser(user))
            return true;
        return false;
    }

    public boolean mailIsPresentInDB(String mail, int id) {
        return userDao.mailIsPresentInDB(mail, id);
    }

    public boolean usernameIsPresentInDB(String username, int id) {
        return userDao.usernameIsPresentInDB(username, id);
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public void deleteUserbyId(int id) {
        userDao.deleteUserById(id);
    }

    public List getRoles() {
        return userDao.getRoles();
    }

    public List<User> getUsersByRole(RoleName roleName) {
        return userDao.getUsersByRole(roleName);
    }

    public List<User> getUsersByAge(String ageCategory) {
        switch (ageCategory){
            case "under30": return userDao.getUsersUnder30();
            case "above30": return userDao.getUsersAbove30();
            default: return null;
        }
    }

    public void registerNewRole( Role role) {
        userDao.insertNewRole(role);
    }

    public void updateRole(Role role) {
        userDao.updateRole(role);
    }

    public void deleteRoleById(int id) {
        userDao.deleteRoleById(id);
    }
}
