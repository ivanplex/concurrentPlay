package test.java;

import main.java.VideoPlayInfo;
import org.junit.Test;

import java.time.Instant;


public class TestVideoPlayInfo {

    @Test(expected = Exception.class)
    /**
     * Scenario:
     * Adding VideoPlayInfo from the future
     */
    public void testFuture() throws Exception {
        new VideoPlayInfo(Instant.parse("2022-01-01T12:00:00.00Z"), Instant.parse("2022-01-01T13:00:00.00Z"));
    }

}
