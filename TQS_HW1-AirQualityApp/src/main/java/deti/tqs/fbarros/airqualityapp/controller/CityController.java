package deti.tqs.fbarros.airqualityapp.controller;

import deti.tqs.fbarros.airqualityapp.model.City;
import deti.tqs.fbarros.airqualityapp.model.KindOfCache;
import deti.tqs.fbarros.airqualityapp.service.CityService;
import deti.tqs.fbarros.airqualityapp.service.KindOfCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


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

    @GetMapping("/city")
    public String getCityAQI(City form_city, Model model){
        City city = new City();
        String user_input = form_city.getName();
        System.out.println(user_input);
        city.setName(user_input);
        City fromservice = cityService.getCityAirQuality(user_input);
        // The City was not in cache yet
        if (fromservice == null){

        }
        model.addAttribute("infoteste", user_input);
        model.addAttribute("city", city);
        return "city_aqi";
    }

    @GetMapping("/kindofcache")
    public String kindofcachestatistics(Model model){
        logger.info("[KindOfCacheService] Displaying KindOfCache all-time statistics");
        KindOfCache statistics = kindOfCacheService.getStatistics();
        model.addAttribute("statistics", statistics);
        return "kindofcachestats";
    }

    /*
        CMD-FOCUSED / POSTMAN endpoints
     */

    @GetMapping("/api/kindofcache")
    public ResponseEntity<KindOfCache> getKindOfCacheStatistics(){
        logger.info("[KindOfCacheService] Fetching KindOfCache all-time statistics, to API");
        KindOfCache statisticsreport = kindOfCacheService.getStatistics();
        return new ResponseEntity<>(statisticsreport, HttpStatus.OK);
    }



}
