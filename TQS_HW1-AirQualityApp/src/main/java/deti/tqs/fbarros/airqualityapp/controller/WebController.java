package deti.tqs.fbarros.airqualityapp.controller;

import deti.tqs.fbarros.airqualityapp.model.City;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping("/")
    public String home(Model model){
        City model_city = new City();
        model.addAttribute("city", model_city);
        return "home";
    }
}
