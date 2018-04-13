package com.springapp.mvc.controller;

import com.springapp.mvc.model.User;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/deleteuser/{id}", method = RequestMethod.GET)
    public String deleteUserAdmin(Model model, @PathVariable("id") int id){
        userService.deleteUserbyId(id);
        return "redirect:/admin/panel";
    }

    @RequestMapping(value = "/admin/newuser", method = RequestMethod.POST)
    public String updateUserAdmin(Model model, @Valid @ModelAttribute("user") User user,  BindingResult result) {
        boolean mailIsPresent = userService.mailIsPresentInDB(user.getEmail());
        if (mailIsPresent)
            model.addAttribute("uniquemail", "Choose another email!A user with this email already exists.");
        boolean usernameIsPresent = userService.usernameIsPresentInDB(user.getUsername());
        if (usernameIsPresent)
            model.addAttribute("uniqueusername", "This username already exists");
        boolean doesPassMatch = user.getPassword().equals(user.getRepeatPassword());
        if (!doesPassMatch)
            model.addAttribute("repassword", "Repeated password does not match.");
        if (result.hasErrors() || mailIsPresent || usernameIsPresent || !doesPassMatch)
            return "registrationAdmin";
        userService.registerNewUser(user);
        return "redirect:/admin/panel";
    }

    @RequestMapping(value = "/admin/newuser", method = RequestMethod.GET)
    public String printRegistration(Model model) {
        model.addAttribute("message", "New user by Admin");
        model.addAttribute("user", new User());
        return "registrationAdmin";
    }

    @RequestMapping(value = "/admin/edituser/{id}", method = RequestMethod.GET)
    public String editUserAdmin(Model model, @PathVariable("id") int id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edituserAdmin";
    }

    @RequestMapping(value = "/admin/edituser/{id}", method = RequestMethod.POST)
    public String saveChangesUser(Model model, @Valid @ModelAttribute("user") User user, BindingResult result,
                                  @PathVariable("id") int id){
        boolean mailIsPresent = userService.mailIsPresentInDB(user.getEmail());
        if (mailIsPresent)
            model.addAttribute("uniquemail", "Choose another email!A user with this email already exists.");
        boolean usernameIsPresent = userService.usernameIsPresentInDB(user.getUsername());
        if (usernameIsPresent)
            model.addAttribute("uniqueusername", "This username already exists");
        boolean doesPassMatch = user.getPassword().equals(user.getRepeatPassword());
        if (!doesPassMatch)
            model.addAttribute("repassword", "Repeated password does not match.");
        if (result.hasErrors() || mailIsPresent || usernameIsPresent || !doesPassMatch)
            return "edituserAdmin";

        user.setId(id);
        if (userService.saveChangesUser(user)) {
            // ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).setUsername(user.getUsername());
//            Collection<SimpleGrantedAuthority> nowAuthorities =(Collection<SimpleGrantedAuthority>)SecurityContextHolder
//                    .getContext().getAuthentication().getAuthorities();
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), nowAuthorities);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        return "redirect:/admin/panel";
    }
}
