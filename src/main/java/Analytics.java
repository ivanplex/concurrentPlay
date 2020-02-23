package main.java;

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


    int getMaximumConcurrentPlays(VideoPlayInfo[] plays) {
        // return maximum concurrency
        return plays.length;
    }
}
