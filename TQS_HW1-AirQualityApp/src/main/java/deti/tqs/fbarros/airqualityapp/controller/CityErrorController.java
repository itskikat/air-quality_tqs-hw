package deti.tqs.fbarros.airqualityapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CityErrorController implements ErrorController {

    Logger logger = LoggerFactory.getLogger(CityController.class);

    @RequestMapping("/error404")
    public String throwerror404(){
        logger.error("[CityErrorController] Page unavailable");
        return "error404";
    }

    @Override
    public String getErrorPath() {
        return null;
    }

}
