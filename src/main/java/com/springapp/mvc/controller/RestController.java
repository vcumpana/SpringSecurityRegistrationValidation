package com.springapp.mvc.controller;

import com.springapp.mvc.model.Gender;
import com.springapp.mvc.model.Role;
import com.springapp.mvc.model.RoleName;
import com.springapp.mvc.model.User;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private UserService userService;

    //GET methods
    @GetMapping(value = "/rest/allusers")
    public @ResponseBody List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/rest/users/id/{id}")
    public @ResponseBody User getAUserById(@PathVariable("id") int id){
        return userService.getUserById(id);
    }

    @GetMapping(value = "/rest/users/username/{username}")
    public @ResponseBody User getAUserById(@PathVariable("username") String username){
        return userService.getUserByName(username).get();
    }

    @GetMapping(value = "/rest/users/gender/{gender}")
    public @ResponseBody List<User> getUsersByGender(@PathVariable("gender") Gender gender){
        return userService.getAllByGender(gender);
    }

    @GetMapping(value = "/rest/users/role/{role}")
    public @ResponseBody List<User> getUsersByRole(@PathVariable("role") RoleName roleName){
        return userService.getUsersByRole(roleName);
    }

    @GetMapping(value = "/rest/users/age/{ageCategory}")
    public @ResponseBody List<User> getUsersByAge(@PathVariable("ageCategory") String ageCategory){
        return userService.getUsersByAge(ageCategory);
    }

    //POST methods
    @PostMapping(value = "/rest/newuser")
    public void insertNewUser(@RequestBody @Valid User user){
        userService.registerNewUser(user);
    }

    @PostMapping(value = "/rest/newrole")
    public void insertNewRole(@RequestBody Role role){
        userService.registerNewRole(role);
    }

    //PUT methods
    @PutMapping(value = "/rest/updateuser")
    public void updateUser(@RequestBody @Valid User user){
        userService.saveChangesUser(user);
    }

    @PutMapping(value = "/rest/updaterole")
    public void updateRole(@RequestBody Role role){
        userService.updateRole(role);
    }

    //DELETE methods
    @DeleteMapping(value = "/rest/deleteuser/{id}")
    public void deleteUser(@PathVariable("id") int id){
        userService.deleteUserbyId(id);
    }

    @DeleteMapping(value = "/rest/deleterole/{id}")
    public void deleteRole(@PathVariable("id") int id){
        userService.deleteRoleById(id);
    }
}
