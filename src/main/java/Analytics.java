package main.java;

import java.time.Instant;
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
 *      - VideoPlay in the future
 *      - Out of memory error due to list size
 *
 * @author  Ivan Chan
 * @since   2020-02-23
 */
public class Analytics {

    List<VideoPlayInfo> plays;

    /**
     * Fetch maximum number of concurrent plays in the logs
     * @param plays unordered list of videoplayinfo
     * @return int  max concurrency
     * @throws Exception
     * @throws OutOfMemoryError
     */
    public int getMaximumConcurrentPlays(VideoPlayInfo[] plays) throws Exception, OutOfMemoryError {

        //If no play record, throw error
        if (plays.length <1) {
            throw new Exception("Empty plays");
        }

        this.plays = Arrays.asList(plays); // Convert into list
        Collections.sort(this.plays);      // Sort by start time

        int watching = 1, max_watching = 1;
        Instant timeAtMaxWatch = this.plays.get(0).getStartTime(); // Track time when maximum concurrency occurs
        int i = 1, j = 0;   //Start and end time indexes

        while (i < plays.length && j < plays.length)
        {
            // If the next event is a connection, increment watch
            if (this.plays.get(i).getStartTime().compareTo(this.plays.get(j).getEndTime()) < 0) {
                watching++;

                // Update max_watching
                if (watching > max_watching){
                    max_watching = watching;
                    timeAtMaxWatch = this.plays.get(i).getStartTime();
                }
                System.out.println(this.plays.get(i).getStartTime()+ "   Connect      "+watching);
                i++; //increment index of arrival array
            }
            else {
                // If event a disconnection, decrement watching
                watching--;
                System.out.println(this.plays.get(j).getEndTime()+ "   Exit         "+watching);
                j++;
            }
        }

        return max_watching;
    }

    /**
     * Print list in their current order
     */
    private void printList(){

        for (VideoPlayInfo play: this.plays) {
            System.out.println(play);
        }
    }
}
