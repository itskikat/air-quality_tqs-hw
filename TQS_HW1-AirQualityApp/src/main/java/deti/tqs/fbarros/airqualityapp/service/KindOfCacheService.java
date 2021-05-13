package deti.tqs.fbarros.airqualityapp.service;

import deti.tqs.fbarros.airqualityapp.model.KindOfCache;
import org.apache.commons.collections4.map.PassiveExpiringMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class KindOfCacheService {

    private PassiveExpiringMap<String, String> map; // expiring time in ms
    private KindOfCache kindofcache;

    Logger logger = LoggerFactory.getLogger(KindOfCacheService.class); // to log everything

    public KindOfCacheService() {
        this.map = new PassiveExpiringMap<>((long)120000); // default == 2 minutes
        this.kindofcache = new KindOfCache();
    }

    // Ability to set the desired time to expire
    public KindOfCacheService(long exptime) {
        this.map = new PassiveExpiringMap<>(exptime); // default == 2 minutes
        this.kindofcache = new KindOfCache();
    }

    public KindOfCache getStatistics(){
        logger.info("[KindOfCacheService] Retrieving cache statistics");
        return this.kindofcache;
    }

    public String storeInCache(String k, String v){
        logger.info("[KindOfCacheService] Storing something in cache");
        return this.map.put(k, v);
    }

    public String getFromCache(String k){
        logger.info("[KindOfCacheService] Attempting to get something from cache");
        String v = this.map.get(k);
        this.kindofcache.addReqCount();
        if (v != null){
            logger.info("[KindOfCacheService] New hit in cache");
            this.kindofcache.addHit();
        } else {
            logger.info("[KindOfCacheService] New miss in cache");
            this.kindofcache.addMiss();
        }
        return v;
    }
}
