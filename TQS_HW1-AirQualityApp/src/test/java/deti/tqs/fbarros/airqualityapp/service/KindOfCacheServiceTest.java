package deti.tqs.fbarros.airqualityapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class KindOfCacheServiceTest {

    private KindOfCacheService defaultEmptyKindOfCacheService;
    private KindOfCacheService expiringKindOfCacheService;


    @BeforeEach
    public void setUp() {
        defaultEmptyKindOfCacheService = new KindOfCacheService();

        expiringKindOfCacheService = new KindOfCacheService(3000); // 3 seconds

    }

    @DisplayName("The cache service should be empty upon creation")
    @Test
    public void givenEmptyCache_whenGetSomething_thenReturnNull() {
        String something = "something";
        assertThat(defaultEmptyKindOfCacheService.getFromCache(something), equalTo(null));
        assertThat(defaultEmptyKindOfCacheService.getStatistics().getReqcount(), is(1));
        assertThat(defaultEmptyKindOfCacheService.getStatistics().getMisses(), is(1));
    }

    @DisplayName("The cache service should return the correct value, when asked for a key")
    @Test
    public void givenCacheWithItem_whenGetKey_thenReturnValue() {
        String key = "key";
        String value = "value";

        defaultEmptyKindOfCacheService.storeInCache(key, value);

        assertThat(defaultEmptyKindOfCacheService.getFromCache(key), equalTo(value));
        assertThat(defaultEmptyKindOfCacheService.getStatistics().getReqcount(), is(1));
        assertThat(defaultEmptyKindOfCacheService.getStatistics().getHits(), is(1));
    }

    @DisplayName("The cache service should return null, when the storing time expires")
    @Test
    public void givenExpiringCache_whenGetKeyAfterTimeExpires_thenReturnValue() {
        String key = "key";
        String value = "value";
        // Store pair in cache
        expiringKindOfCacheService.storeInCache(key, value);
        // Wait timeout+1 ms
        try {
            Thread.sleep(3001);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Expect to be empty
        assertThat(expiringKindOfCacheService.getFromCache(key), equalTo(null));
        assertThat(expiringKindOfCacheService.getStatistics().getMisses(), is(1));

    }


}