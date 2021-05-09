package deti.tqs.fbarros.airqualityapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CityErrorController implements ErrorController {

    Logger logger = LoggerFactory.getLogger(CityController.class);

    @RequestMapping(path = "/error404", method = RequestMethod.GET)
    public String throwerror404(){
        logger.error("[CityErrorController] Page unavailable");
        return "error404";
    }

    @Override
    public String getErrorPath() {
        return null;
    }

}
