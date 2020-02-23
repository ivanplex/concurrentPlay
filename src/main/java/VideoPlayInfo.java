package main.java;

import java.time.Instant;

class VideoPlayInfo implements Comparable<VideoPlayInfo>{

    public Instant startTime;
    public Instant endTime;

    public VideoPlayInfo(Instant startTime, Instant endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toString() {
        return "Start: "+ this.startTime + "   End: "+ this.endTime;
    }

    public int compareTo(VideoPlayInfo v) {
        return this.startTime.compareTo(v.startTime);
    }
}
