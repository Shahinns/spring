package org.example.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

    @RequestMapping("/welcome")

    public String welcome(){

        return "forward:/welcome/greeting";



    }

    @RequestMapping("/welcome/greeting")
    public String greeting() {
        return "welcome";
    }


    //    public String welcome(Model model) {
//        model.addAttribute("greeting", "Welcome to Web Store!");
//        model.addAttribute("tagline", "The one and only amazing webstore");
//        return "welcome";
//    }
}