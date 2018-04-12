package com.springapp.mvc.controller;

import com.springapp.mvc.model.User;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.security.Security;
import java.util.Optional;

import static com.springapp.mvc.model.Gender.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/")
    public String redirectLogin() {
        return "redirect:/login";
    }

    @RequestMapping(value = "/secret")
    public String returnSecret() {
        return "secret";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String printWelcome(Model model) {
        if (!model.containsAttribute("message"))
            model.addAttribute("message", "Hi there! Log in, please");
        return "index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String printRegistration(Model model) {
        model.addAttribute("message", "Hi there! Register, please");
        model.addAttribute("user",new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(Model model, @Valid @ModelAttribute("user") User user,  BindingResult result,RedirectAttributes attributes) {
        if (result.hasErrors())
            return "registration";
        userService.registerNewUser(user);
        attributes.addFlashAttribute("message", "Succesful registration");
        return "redirect:/login";
    }

    @RequestMapping(value = "/edituser", method = RequestMethod.POST)
    public String saveChangesUser(Model model, @Valid @ModelAttribute("user") User user, BindingResult result){
        if (result.hasErrors())
            return "edituser";
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> loggedUser = userService.getUserByName(username);
        if (loggedUser.isPresent())
            user.setId(loggedUser.get().getId());
        if (userService.saveChangesUser(user))
            ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).setUsername(user.getUsername());
        return "welcome";
    }

    @RequestMapping(value = "/edituser", method = RequestMethod.GET)
    public String editUser(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userService.getUserByName(username);
        if (user.isPresent())
            model.addAttribute("user", user.get());
        return "edituser";
    }

    @RequestMapping(value = "/error")
    public String returnError() {
        return "error";
    }

    @RequestMapping(value = "/allusers", method = RequestMethod.GET)
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "welcome";
    }

    @RequestMapping(value = "/showMales", method = RequestMethod.GET)
    public String showOnlyMales(Model model) {
        model.addAttribute("gender", "boys");
        model.addAttribute("list", userService.getAllByGender(MALE));
        return "gender";
    }

    @RequestMapping(value = "/showFemales", method = RequestMethod.GET)
    public String showOnlyFemales(Model model) {
        model.addAttribute("gender", "girls");
        model.addAttribute("list", userService.getAllByGender(FEMALE));
        return "gender";
    }
}