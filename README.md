# Concurrent Play Analyser

### Assumptions:
- all end times are after their corresponding start time
- each play lasts at most a few hours
- all of the plays happen during one calendar month

### Approach:

Couple of approach that I could think of:
1. Since the time period is 1 month only, we could dissect the month into
1 second resolution. Go through all the seconds in the month and count the number of 
plays in each second. Space complexity bad.

2. Loop through all the plays and compare if any other plays are concurrently playing. O(n^2) time

### This approach
Tracks each start end end time in time order while monitoring the number of concurrent connections.
O(n log(n))
