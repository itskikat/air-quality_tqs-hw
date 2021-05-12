package deti.tqs.fbarros.airqualityapp.controller;

import deti.tqs.fbarros.airqualityapp.model.City;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @ApiOperation(value = "Show the home page to the user")
    @GetMapping(path = "/")
    public String home(Model model){
        City model_city = new City();
        model.addAttribute("city", model_city);
        return "home";
    }
}
