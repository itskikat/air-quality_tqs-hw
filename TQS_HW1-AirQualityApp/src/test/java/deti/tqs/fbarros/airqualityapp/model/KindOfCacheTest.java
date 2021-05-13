package deti.tqs.fbarros.airqualityapp.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KindOfCacheTest {

    private KindOfCache emptyKindOfCache;
    private KindOfCache requestedKindOfCache;
    private KindOfCache hitKindOfCache;
    private KindOfCache missKindOfCache;

    @BeforeEach
    public void setUp() {
        emptyKindOfCache = new KindOfCache();

        requestedKindOfCache = new KindOfCache();
        requestedKindOfCache.addReqCount();

        hitKindOfCache = new KindOfCache();
        hitKindOfCache.addHit();

        missKindOfCache = new KindOfCache();
        missKindOfCache.addMiss();
    }

    @DisplayName("The cache should be empty upon construction")
    @Test
    void isEmpty_Test() {
        assertEquals(0, emptyKindOfCache.getReqcount());
        assertEquals(0, emptyKindOfCache.getHits());
        assertEquals(0, emptyKindOfCache.getMisses());
    }

    @DisplayName("The cache shouldn't be empty, if it was previously requested")
    @Test
    void hasRequests_Test() {
        assertEquals(1, requestedKindOfCache.getReqcount());
        assertNotEquals(0, requestedKindOfCache.getReqcount());
    }

    @DisplayName("The cache shouldn't be empty, if it was hit")
    @Test
    void hasHits_Test() {
        assertEquals(1, hitKindOfCache.getHits());
        assertNotEquals(0, hitKindOfCache.getHits());
    }

    @DisplayName("The cache shouldn't be empty, if it was missed")
    @Test
    void hasMisses_Test() {
        assertEquals(1, missKindOfCache.getMisses());
        assertNotEquals(0, missKindOfCache.getMisses());
    }
}
