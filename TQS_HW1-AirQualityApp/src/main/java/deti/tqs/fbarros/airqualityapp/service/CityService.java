package deti.tqs.fbarros.airqualityapp.service;

import deti.tqs.fbarros.airqualityapp.model.City;
import deti.tqs.fbarros.airqualityapp.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City getCityByIdx(Long idx){
        return cityRepository.findById(idx).orElse(null);
    }

    public List<City> getAllCities(){
        return cityRepository.findAll();
    }
}
