package deti.tqs.fbarros.airqualityapp.controller;

import deti.tqs.fbarros.airqualityapp.model.City;
import deti.tqs.fbarros.airqualityapp.model.KindOfCache;
import deti.tqs.fbarros.airqualityapp.service.CityService;
import deti.tqs.fbarros.airqualityapp.service.KindOfCacheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.aMapWithSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CityController_MockMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CityService cityService;

    @MockBean
    private KindOfCacheService kindOfCacheService;

    @Test
    public void whenGetValidCity_thenReturnCityAQI() throws Exception {
        City berlin = new City(6132L, "Berlin, Germany", "2021-05-08 20:00:00 +02:00", 37L, null, 29.3, 36.6, 21.0, null, null, 13.3, 45.3, 1015.2, 6.3);

        when(cityService.getCityAirQuality(any())).thenReturn(berlin);

        mvc.perform(get("/api/city/berlin").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is("Berlin, Germany")));

        verify(cityService, times(1)).getCityAirQuality(any());
    }

    @Test
    public void whenGetInvalidCity_thenReturnNullCity() throws Exception {
        City invalid = new City();

        when(cityService.getCityAirQuality(any())).thenReturn(invalid);

        mvc.perform(get("/api/city/XYZ").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", aMapWithSize(14)));

        verify(cityService, times(1)).getCityAirQuality(any());
    }

    @Test
    public void whenCacheRequested_thenReturnCacheStats() throws Exception {
        KindOfCache empty = new KindOfCache();

        when(kindOfCacheService.getStatistics()).thenReturn(empty);

        mvc.perform(get("/api/kindofcache").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", aMapWithSize(3)));

        verify(kindOfCacheService, times(1)).getStatistics();
    }

}
