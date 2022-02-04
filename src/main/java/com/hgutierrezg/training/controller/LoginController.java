package com.hgutierrezg.training.controller;

import com.hgutierrezg.training.dto.CredentialsDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "loginPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView getHomepage(@ModelAttribute CredentialsDto credentialsDto){
        ModelAndView indexMaV = new ModelAndView("dashboardPage");
        indexMaV.addObject("credentialsDto", credentialsDto);
        return indexMaV;
    }

}