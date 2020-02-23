package main.java;

import java.time.Instant;

class VideoPlayInfo {
    public Instant startTime;
    public Instant endTime;

    public VideoPlayInfo(Instant startTime, Instant endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toString() {
        return "Start: "+ this.startTime + "   End: "+ this.endTime;
    }
}
