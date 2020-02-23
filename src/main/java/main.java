package main.java;

import java.time.Instant;
import java.util.Collections;

public class main {

    public static void main(String[] args){
        VideoPlayInfo one = new VideoPlayInfo(Instant.now().minusSeconds(5), Instant.now().minusSeconds(3));
        VideoPlayInfo two = new VideoPlayInfo(Instant.now().minusSeconds(9), Instant.now().minusSeconds(3));

        VideoPlayInfo[] plays = {one, two};
        Analytics analytics = new Analytics();

        System.out.println(analytics.getMaximumConcurrentPlays(plays));
    }
}
