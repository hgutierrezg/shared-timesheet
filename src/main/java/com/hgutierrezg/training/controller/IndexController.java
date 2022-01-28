package com.hgutierrezg.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {

    /**
     * Method that redirects to home page given the default path (/)
     * @return string value with the page to serve
     */
      @RequestMapping(method = RequestMethod.GET)
        public String getIndexPage() {
            return "home";
        }
}