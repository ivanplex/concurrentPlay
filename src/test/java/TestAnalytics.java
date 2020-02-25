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
     * Scenario:
     * Test a single video play
     */
    public void testSingleVideo(){
        try {
            VideoPlayInfo[] plays = {
                    new VideoPlayInfo(Instant.parse("2020-01-01T12:00:00.00Z"), Instant.parse("2020-01-01T13:00:00.00Z")),
            };

            assertEquals(1, analytics.getMaximumConcurrentPlays(plays));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * Scenario:
     * Two video of the same time
     */
    public void test2StackedVideos(){
        try {
            VideoPlayInfo[] plays = {
                    new VideoPlayInfo(Instant.parse("2020-01-01T12:00:00.00Z"), Instant.parse("2020-01-01T13:00:00.00Z")),
                    new VideoPlayInfo(Instant.parse("2020-01-01T12:00:00.00Z"), Instant.parse("2020-01-01T13:00:00.00Z")),
            };

            assertEquals(2, analytics.getMaximumConcurrentPlays(plays));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * Scenario:
     * Two video plays. One starts before another but overlapping each other
     */
    public void testStartFirstVideos(){
        try {
            VideoPlayInfo[] plays = {
                    new VideoPlayInfo(Instant.parse("2020-01-01T12:00:00.00Z"), Instant.parse("2020-01-01T13:00:00.00Z")),
                    new VideoPlayInfo(Instant.parse("2020-01-01T12:30:00.00Z"), Instant.parse("2020-01-01T13:00:00.00Z")),
            };

            assertEquals(2, analytics.getMaximumConcurrentPlays(plays));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * Scenario:
     * Two video plays. One starts later but overlapping each other
     *
     * This is effectively the same test but challenges the sorting machanism as well
     */
    public void testStartLatterVideos(){
        try {
            VideoPlayInfo[] plays = {
                    new VideoPlayInfo(Instant.parse("2020-01-01T12:30:00.00Z"), Instant.parse("2020-01-01T13:00:00.00Z")),
                    new VideoPlayInfo(Instant.parse("2020-01-01T12:00:00.00Z"), Instant.parse("2020-01-01T13:00:00.00Z")),
            };

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
        try {
            VideoPlayInfo[] plays = {
                    new VideoPlayInfo(Instant.parse("2020-01-01T12:00:00.00Z"), Instant.parse("2020-01-01T13:00:00.00Z")),
                    new VideoPlayInfo(Instant.parse("2020-01-01T12:20:00.01Z"), Instant.parse("2020-01-01T12:30:00.00Z"))
            };

            assertEquals(2, analytics.getMaximumConcurrentPlays(plays));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * Scenario:
     * 2 sets of playing time apart. No overlapping.
     *
     * Expected: 1
     */
    public void testApart(){
        try {
            VideoPlayInfo[] plays = {
                    new VideoPlayInfo(Instant.parse("2020-01-01T12:00:00.00Z"), Instant.parse("2020-01-01T13:00:00.00Z")),
                    new VideoPlayInfo(Instant.parse("2020-01-01T15:00:00.00Z"), Instant.parse("2020-01-01T16:30:00.00Z"))
            };

            assertEquals(1, analytics.getMaximumConcurrentPlays(plays));
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
     *
     * As our algorithm is not resolution based, this should still return 1
     */
    public void testMillisecondApart(){
        try {
            VideoPlayInfo[] plays = {
                    new VideoPlayInfo(Instant.parse("2020-01-01T12:00:00.00Z"), Instant.parse("2020-01-01T13:00:00.00Z")),
                    new VideoPlayInfo(Instant.parse("2020-01-01T13:00:00.01Z"), Instant.parse("2020-01-01T13:30:00.00Z"))
            };

            assertEquals(1, analytics.getMaximumConcurrentPlays(plays));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * Scenario:
     * 3 video plays. v1 and v2 overlaps, v2 and v3 overlaps. But v1 and v3 are apart.
     *
     * Expect: 2
     */
    public void testDisconnectedStackApart(){
        try {
            VideoPlayInfo[] plays = {
                    new VideoPlayInfo(Instant.parse("2020-01-01T12:00:00.00Z"), Instant.parse("2020-01-01T12:30:00.00Z")),
                    new VideoPlayInfo(Instant.parse("2020-01-01T12:20:00.01Z"), Instant.parse("2020-01-01T12:55:00.00Z")),
                    new VideoPlayInfo(Instant.parse("2020-01-01T12:45:00.01Z"), Instant.parse("2020-01-01T13:40:00.00Z"))
            };

            assertEquals(2, analytics.getMaximumConcurrentPlays(plays));
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
        try {
            VideoPlayInfo[] plays = {
                    new VideoPlayInfo(Instant.parse("2020-01-01T12:00:00.00Z"), Instant.parse("2020-01-01T13:00:00.00Z")),
                    new VideoPlayInfo(Instant.parse("2020-01-01T11:30:00.00Z"), Instant.parse("2020-01-01T12:30:00.00Z")),
                    new VideoPlayInfo(Instant.parse("2020-01-01T14:30:00.00Z"), Instant.parse("2020-01-01T15:30:00.00Z")),
                    new VideoPlayInfo(Instant.parse("2020-01-01T14:35:00.00Z"), Instant.parse("2020-01-01T15:30:00.00Z")),
            };

            assertEquals(2, analytics.getMaximumConcurrentPlays(plays));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * Scenario:
     * 3 groups of watch times.
     *  - Group 1 has 2 overlaps
     *  - Group 2 has 3 overlaps
     *  - Group 3 has 2 overlaps
     *
     * Expect: It should return the middle group: max 3
     */
    public void testUnEqualZones(){
        try {
            VideoPlayInfo[] plays = {
                    new VideoPlayInfo(Instant.parse("2020-01-01T12:00:00.00Z"), Instant.parse("2020-01-01T13:00:00.00Z")),
                    new VideoPlayInfo(Instant.parse("2020-01-01T11:30:00.00Z"), Instant.parse("2020-01-01T12:30:00.00Z")),
                    new VideoPlayInfo(Instant.parse("2020-01-02T14:30:00.00Z"), Instant.parse("2020-01-02T15:30:00.00Z")),
                    new VideoPlayInfo(Instant.parse("2020-01-02T14:35:00.00Z"), Instant.parse("2020-01-02T15:30:00.00Z")),
                    new VideoPlayInfo(Instant.parse("2020-01-02T14:37:00.00Z"), Instant.parse("2020-01-02T15:21:00.00Z")),
                    new VideoPlayInfo(Instant.parse("2020-01-10T14:37:00.00Z"), Instant.parse("2020-01-10T15:21:00.00Z")),
                    new VideoPlayInfo(Instant.parse("2020-01-10T15:00:00.00Z"), Instant.parse("2020-01-10T15:48:00.00Z")),
            };

            assertEquals(3, analytics.getMaximumConcurrentPlays(plays));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(expected = OutOfMemoryError.class)
    /**
     * Scenario:
     * Large array
     */
    public void testLargeEntries() throws Exception, OutOfMemoryError {

        VideoPlayInfo[] plays = new VideoPlayInfo[Integer.MAX_VALUE];
        analytics.getMaximumConcurrentPlays(plays);

    }
}
