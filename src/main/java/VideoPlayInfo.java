package main.java;

import java.time.Instant;

class VideoPlayInfo implements Comparable<VideoPlayInfo>{

    public Instant startTime;
    public Instant endTime;

    public VideoPlayInfo(Instant startTime, Instant endTime) {
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
