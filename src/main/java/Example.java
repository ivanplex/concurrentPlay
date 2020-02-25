package main.java;

import java.time.Instant;

public class Example {

    /**
     * Example code on how to run the Analytics function
     * @param args
     */
    public static void main(String[] args){
        try {
            // Create a list of dummy VideoPlayInfo
            VideoPlayInfo one = new VideoPlayInfo(Instant.now().minusSeconds(5), Instant.now().minusSeconds(3));
            VideoPlayInfo two = new VideoPlayInfo(Instant.now().minusSeconds(9), Instant.now().minusSeconds(3));
            VideoPlayInfo[] plays = {one, two};

            // Create new class
            Analytics analytics = new Analytics();
            int maxConcurrency = analytics.getMaximumConcurrentPlays(plays);

            System.out.println("Max Watching: " + maxConcurrency);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
