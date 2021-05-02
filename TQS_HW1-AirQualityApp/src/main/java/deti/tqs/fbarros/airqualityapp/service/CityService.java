package deti.tqs.fbarros.airqualityapp.service;

import deti.tqs.fbarros.airqualityapp.controller.CityController;
import deti.tqs.fbarros.airqualityapp.model.City;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class CityService {

    @Autowired
    private KindOfCacheService kindOfCacheService;


    static final String API_BASEURL = "https://api.waqi.info/feed/";
    static final String API_TOKEN = "/?token=6addccba8550e0c70f2d10e8dc3e1d5d2e89238b";

    Logger logger = LoggerFactory.getLogger(CityController.class);


    public City getCityAirQuality(String city_name) {
        // Utilities
        RestTemplate restTemplate = new RestTemplate();
        // Try fetching data from Cache
        String incache = kindOfCacheService.getFromCache(city_name);
        if (incache != null){
            logger.info("[CityService] Found city in cache! --> SUCCESS, HIT");
        } else {
            logger.info("[CityService] City not in cache yet! --> FAILURE, MISS");
            String url = API_BASEURL + city_name + API_TOKEN;
            incache = restTemplate.getForObject(url, String.class);
            System.out.println(incache);
            kindOfCacheService.storeInCache(city_name, incache);
        }
        JSONObject object = new JSONObject(incache);
        System.out.println(object.keySet());
        System.out.println(object.get("data"));
        //JSONArray arr = object.getJSONArray("data");
        //System.out.println(arr);


        if (incache != null){
            // Convert to CityOBJ

        } else {
            return new City(); // The city was invalid or something
        }

        return null;
    }

}
