package main.java;

import java.time.Instant;

public class main {

    public static void main(String[] args){
        try {
            VideoPlayInfo one = new VideoPlayInfo(Instant.now().minusSeconds(5), Instant.now().minusSeconds(3));
            VideoPlayInfo two = new VideoPlayInfo(Instant.now().minusSeconds(9), Instant.now().minusSeconds(3));

            VideoPlayInfo[] plays = {one, two};
            Analytics analytics = new Analytics();

            System.out.println("Max Watching:" + analytics.getMaximumConcurrentPlays(plays));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
