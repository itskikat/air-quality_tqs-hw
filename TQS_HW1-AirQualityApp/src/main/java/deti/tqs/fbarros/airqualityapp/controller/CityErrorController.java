package deti.tqs.fbarros.airqualityapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CityErrorController {

    Logger logger = LoggerFactory.getLogger(CityController.class);

    @RequestMapping("/error404")
    public String throwerror404(){
        logger.error("[ERROR 404] Invalid city name; Page unavailable.");
        return "error404";
    }

}
