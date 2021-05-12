package deti.tqs.fbarros.airqualityapp.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CityErrorController implements ErrorController {

    Logger logger = LoggerFactory.getLogger(CityController.class);

    @ApiOperation(value = "Show the error page to the user")
    @GetMapping(path = "/error404")
    public String throwerror404(){
        logger.error("[CityErrorController] Page unavailable");
        return "error404";
    }

    @Override
    public String getErrorPath() {
        return null;
    }

}
