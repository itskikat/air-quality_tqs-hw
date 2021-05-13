package deti.tqs.fbarros.airqualityapp.model;

import lombok.Data;

@Data
public class KindOfCache {

    private Integer reqcount;
    private Integer hits;
    private Integer misses;

    public KindOfCache(){
        this.reqcount = 0;
        this.hits = 0;
        this.misses = 0;
    }

    public void addReqCount() {
        this.reqcount++;
    }

    public void addHit() {
        this.hits++;
    }

    public void addMiss() {
        this.misses++;
    }
    

}
