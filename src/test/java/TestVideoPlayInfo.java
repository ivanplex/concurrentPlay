package test.java;

import main.java.VideoPlayInfo;
import org.junit.Test;

import java.time.Instant;


public class TestVideoPlayInfo {

    @Test(expected = Exception.class)
    /**
     * Scenario:
     * StartPlay instant in the future
     */
    public void testStartPlayInTheFuture() throws Exception {
        new VideoPlayInfo(Instant.parse("2022-01-01T12:00:00.00Z"), Instant.parse("2022-01-01T13:00:00.00Z"));
    }

    @Test(expected = Exception.class)
    /**
     * Scenario:
     * EndPlay Instant in the future
     */
    public void testEndPlayInTheFuture() throws Exception {
        new VideoPlayInfo(Instant.parse("2020-01-01T12:00:00.00Z"), Instant.parse("2022-01-01T13:00:00.00Z"));
    }

    @Test(expected = Exception.class)
    /**
     * Scenario:
     * StartPlay is after Endplay
     */
    public void testStartPlayAferEndPlay() throws Exception {
        new VideoPlayInfo(Instant.parse("2020-01-01T12:00:00.00Z"), Instant.parse("2019-01-01T13:00:00.00Z"));
    }


}
