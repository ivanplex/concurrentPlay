package main.java;

import java.time.Instant;

public class VideoPlayInfo implements Comparable<VideoPlayInfo>{

    public Instant startTime;
    public Instant endTime;

    public VideoPlayInfo(Instant startTime, Instant endTime) throws Exception {
        // Throw error if Instant is in the future
        if(startTime.compareTo(Instant.now()) > 0 || endTime.compareTo(Instant.now()) > 0){
            throw new Exception("Empty plays");
        }

        // Throw error start time is after end time
        if(startTime.compareTo(endTime) > 0){
            throw new Exception("Start Time is after end time");
        }

        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    /**
     * Printable
     * @return string printable
     */
    public String toString() {
        return "Start: "+ this.startTime + "   End: "+ this.endTime;
    }

    /**
     * Implement comparable
     * @param v  video play instant to compare with
     * @return   Greater than 0 if this instant is newer
     *           0 if the same
     *           less than 0 if instant is older
     */
    public int compareTo(VideoPlayInfo v) {
        return this.startTime.compareTo(v.startTime);
    }
}
