import java.time.Clock;
import java.time.Instant;

public class Timer {
    private Clock clock = Clock.systemDefaultZone();
    private long sec = 0;
    private long nano = 0;
    private double elapsed = 0;
    public void start(){
        Instant instant = clock.instant();
        sec = instant.getEpochSecond();
        nano = instant.getNano();
    }

    public void update(){
        Instant instant = clock.instant();
        elapsed = (instant.getEpochSecond() - sec) + instant.getNano() * 0.000000001;
    }

    public double getTime(){
        return elapsed;
    }
}
