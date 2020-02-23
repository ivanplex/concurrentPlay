package main.java;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Analytics program for counting the number of concurrent plays
 *
 * Assumptions:
 *      - all end times are after their corresponding start time
 *      - each play lasts at most a few hours
 *      - all of the plays happen during one calendar month
 *
 * Potential Problems:
 *      - plays hasn't ended
 *      -
 *
 * @author  Ivan Chan
 * @since   2020-02-23
 */
public class Analytics {

    List<VideoPlayInfo> plays;

    int getMaximumConcurrentPlays(VideoPlayInfo[] plays) {

        this.plays = Arrays.asList(plays);
        Collections.sort(this.plays);

        for (VideoPlayInfo play: plays) {
            System.out.println(play);
        }

        return plays.length;
    }
}
