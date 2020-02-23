package test.java;
import static org.junit.Assert.assertEquals;

import main.java.Analytics;
import main.java.VideoPlayInfo;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Instant;

public class TestAnalytics {

    static Analytics analytics;

    @BeforeClass
    public static void testSetup(){
        analytics = new Analytics();
    }


    @Test(expected = Exception.class)
    /**
     * Scenario:
     * When provided with empty list. List of 0 length has no max.
     */
    public void testBlankVideoPlays() throws Exception {
        VideoPlayInfo[] plays = new VideoPlayInfo[0];
        analytics.getMaximumConcurrentPlays(plays);
    }

    @Test
    /**
     * Simple test of 2 concurrent plays.
     */
    public void test2Videos(){
        VideoPlayInfo[] plays = {
                new VideoPlayInfo(Instant.now().minusSeconds(5), Instant.now().minusSeconds(3)),
                new VideoPlayInfo(Instant.now().minusSeconds(9), Instant.now().minusSeconds(3))
        };
        try {
            assertEquals(2, analytics.getMaximumConcurrentPlays(plays));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * Scenario:
     * Video 1 plays within the time of Video 2
     */
    public void testInside(){
        VideoPlayInfo[] plays = {
                new VideoPlayInfo(Instant.parse("2020-01-01T12:00:00.00Z"), Instant.parse("2020-01-01T13:00:00.00Z")),
                new VideoPlayInfo(Instant.parse("2020-01-01T12:20:00.01Z"), Instant.parse("2020-01-01T12:30:00.00Z"))
        };
        try {
            assertEquals(2, analytics.getMaximumConcurrentPlays(plays));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * Scenario:
     * 2 sets of playing time extremely close to each other but do NOT overlap
     * VideoPlayInfo 1 finishes miliseconds before VideoPlayInfo 2 starts.
     * Result should remain as max 1 concurrent play
     */
    public void testMillisecondApart(){
        VideoPlayInfo[] plays = {
                new VideoPlayInfo(Instant.parse("2020-01-01T12:00:00.00Z"), Instant.parse("2020-01-01T13:00:00.00Z")),
                new VideoPlayInfo(Instant.parse("2020-01-01T13:00:00.01Z"), Instant.parse("2020-01-01T13:30:00.00Z"))
        };
        try {
            assertEquals(1, analytics.getMaximumConcurrentPlays(plays));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * Scenario:
     * Two group of watch times. Both having the same number of concurrent count
     */
    public void testTwoEqualZones(){
        VideoPlayInfo[] plays = {
                new VideoPlayInfo(Instant.parse("2020-01-01T12:00:00.00Z"), Instant.parse("2020-01-01T13:00:00.00Z")),
                new VideoPlayInfo(Instant.parse("2020-01-01T11:30:00.00Z"), Instant.parse("2020-01-01T12:30:00.00Z")),
                new VideoPlayInfo(Instant.parse("2020-01-01T14:30:00.00Z"), Instant.parse("2020-01-01T15:30:00.00Z")),
                new VideoPlayInfo(Instant.parse("2020-01-01T14:35:00.00Z"), Instant.parse("2020-01-01T15:30:00.00Z")),
        };
        try {
            assertEquals(2, analytics.getMaximumConcurrentPlays(plays));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * Scenario:
     * Two group of watch times. The later group has more viewers
     */
    public void testUnEqualZones(){
        VideoPlayInfo[] plays = {
                new VideoPlayInfo(Instant.parse("2020-01-01T12:00:00.00Z"), Instant.parse("2020-01-01T13:00:00.00Z")),
                new VideoPlayInfo(Instant.parse("2020-01-01T11:30:00.00Z"), Instant.parse("2020-01-01T12:30:00.00Z")),
                new VideoPlayInfo(Instant.parse("2020-01-01T14:30:00.00Z"), Instant.parse("2020-01-01T15:30:00.00Z")),
                new VideoPlayInfo(Instant.parse("2020-01-01T14:35:00.00Z"), Instant.parse("2020-01-01T15:30:00.00Z")),
                new VideoPlayInfo(Instant.parse("2020-01-01T14:37:00.00Z"), Instant.parse("2020-01-01T15:21:00.00Z")),
        };
        try {
            assertEquals(3, analytics.getMaximumConcurrentPlays(plays));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
