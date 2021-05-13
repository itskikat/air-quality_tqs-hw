package deti.tqs.fbarros.airqualityapp.controller;

import deti.tqs.fbarros.airqualityapp.model.City;
import deti.tqs.fbarros.airqualityapp.model.KindOfCache;
import deti.tqs.fbarros.airqualityapp.service.CityService;
import deti.tqs.fbarros.airqualityapp.service.KindOfCacheService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class CityController {


    Logger logger = LoggerFactory.getLogger(CityController.class);

    @Autowired
    private CityService cityService;

    @Autowired
    private KindOfCacheService kindOfCacheService;

    /*
        Gets AQI for the city the user inputs in the form
        From the API 
     */

    @ApiOperation(value = "Get the AQI of a desired city")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = City.class, responseContainer = "List"),
             })
    @GetMapping("/city")
    public String getCityAQI(@ApiParam(
            name =  "form_city",
            type = "City",
            value = "Name of the desired city",
            example = "Berlin",
            required = true) City formCity, Model model) {
        String userInput = formCity.getName();
        logger.info("[CityController] Displaying AQI for city {}", userInput);
        City fromservice = cityService.getCityAirQuality(userInput);
        // The City was not in cache yet
        if (fromservice.getName() == null){
            return "error404";
        }
        model.addAttribute("city", fromservice);
        model.addAttribute("user_input", userInput);
        return "city_aqi";
    }

    @ApiOperation(value = "Get the Cache's Statistics")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = KindOfCache.class, responseContainer = "List"),
    })
    @GetMapping("/kindofcache")
    public String kindofcachestatistics(Model model){
        logger.info("[CityController] Displaying KindOfCache all-time statistics");
        KindOfCache statistics = kindOfCacheService.getStatistics();
        model.addAttribute("statistics", statistics);
        return "kindofcachestats";
    }

    /*
        CMD-FOCUSED / POSTMAN endpoints
     */

    @ApiOperation(value = "[Developer Oriented] Get the Cache's Statistics")
    @GetMapping("/api/kindofcache")
    public ResponseEntity<KindOfCache> getKindOfCacheStatistics(){
        logger.info("[CityController] Fetching KindOfCache all-time statistics, to API");
        KindOfCache statisticsreport = kindOfCacheService.getStatistics();
        return new ResponseEntity<>(statisticsreport, HttpStatus.OK);
    }

    @ApiOperation(value = "[Developer Oriented] Get the AQI of a specific city")
    @GetMapping("/api/city/{city}")
    public ResponseEntity<City> getAPICityAQI(@ApiParam(
            name =  "city",
            type = "String",
            value = "Name of the desired city",
            example = "Berlin",
            required = true) @PathVariable String city) {
        logger.info("[KindOfCacheService] Fetching AQI for city {}, to API", city);
        City fromservice = cityService.getCityAirQuality(city);
        // The City was not in cache yet
        if (fromservice.getName() == null){
            City output = new City();
            return new ResponseEntity<>(output, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(fromservice, HttpStatus.OK);
        }

    }



}
