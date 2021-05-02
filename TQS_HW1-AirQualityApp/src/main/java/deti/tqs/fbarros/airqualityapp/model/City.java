package deti.tqs.fbarros.airqualityapp.model;

import lombok.Data;


@Data
public class City {

    /*
        DATA AVAILABLE IN EACH JSON RESPONSE FROM THE API
     */
    private Long id;          // Unique ID for the city monitoring station
    private String name;      // City Name
    private String timestamp; // Of the reading
    private Long aqi;         // Air Quality Information (Real-Time)

    /*
        Pollutants Measurements
     */
    private Double co;        // Carbon Monoxide
    private Double no2;       // Nitrogen Dioxide
    private Double o3;        // Ozone
    private Double pm10;      // Particulate Matter 10
    private Double pm25;      // Particulate Matter 2.5
    private Double so2;       // Sulphur Dioxide

    /*
        General Weather Measurements
     */
    private Double t;         // Temperature
    private Double h;         // Humidity
    private Double p;         // Pressure
    private Double w;         // Wind

    public City(){
    }

    public City(Long id, String name, String timestamp, Long aqi, Double co, Double no2, Double o3, Double pm10, Double pm25, Double so2, Double t, Double h, Double p, Double w) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
        this.aqi = aqi;
        this.co = co;
        this.no2 = no2;
        this.o3 = o3;
        this.pm10 = pm10;
        this.pm25 = pm25;
        this.so2 = so2;
        this.t = t;
        this.h = h;
        this.p = p;
        this.w = w;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", aqi=" + aqi +
                ", co=" + co +
                ", no2=" + no2 +
                ", o3=" + o3 +
                ", pm10=" + pm10 +
                ", pm25=" + pm25 +
                ", so2=" + so2 +
                ", t=" + t +
                ", h=" + h +
                ", p=" + p +
                ", w=" + w +
                '}';
    }
}
