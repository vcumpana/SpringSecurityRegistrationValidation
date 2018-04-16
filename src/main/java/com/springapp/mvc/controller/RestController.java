package com.springapp.mvc.controller;

import com.springapp.mvc.model.Gender;
import com.springapp.mvc.model.RoleName;
import com.springapp.mvc.model.User;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/rest/allusers")
    public @ResponseBody List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/rest/users/id", params = "id")
    public @ResponseBody User getAUserById(@PathVariable("id") int id){
        return userService.getUserById(id);
    }

    @GetMapping(value = "/rest/users/username", params = "username")
    public @ResponseBody User getAUserById(@PathVariable("username") String username){
        return userService.getUserByName(username).get();
    }

    @GetMapping(value = "/rest/users/gender/", params = "gender")
    public @ResponseBody List<User> getUsersByGender(@PathVariable("gender") Gender gender){
        return userService.getAllByGender(gender);
    }

    @GetMapping(value = "/rest/users/role/", params = "role")
    public @ResponseBody List<User> getUsersByRole(@PathVariable("role") RoleName roleName){
        return userService.getUsersByRole(roleName);
    }

    @GetMapping(value = "/rest/users/age/", params = "age")
    public @ResponseBody List<User> getUsersByAge(@PathVariable("age") int age){
        return userService.getUsersByAge(age);
    }
}
