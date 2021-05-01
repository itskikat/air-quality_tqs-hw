package deti.tqs.fbarros.airqualityapp.controller;

import deti.tqs.fbarros.airqualityapp.model.City;
import deti.tqs.fbarros.airqualityapp.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CityController {

    static final String API_BASEURL = "https://api.waqi.info/feed/";
    static final String API_TOKEN = "/?token=6addccba8550e0c70f2d10e8dc3e1d5d2e89238b";

    @Autowired
    private CityService cityService;

    @GetMapping("/cities")
    public String cities(@RequestParam(name="name", required=false, defaultValue="HOLA") String name, Model model){
        model.addAttribute("name", name);
        return "home";
    }

    /*
        Gets AQI for the city the user inputs in the form
        From the API 
     */
    @GetMapping("/city")
    public String getCityAQI(City form_city, Model model){
        City city = new City();
        String user_input = form_city.getName();
        System.out.println(user_input);
        city.setName(user_input);
        model.addAttribute("infoteste", user_input);
        model.addAttribute("city", city);
        return "city_aqi";

    }
    // @PostMapping()

}
