import java.time.*;
//Timer class to time our methods
public class timer 
{
    boolean startStop;
    Instant startTime;
    Instant endTime;

    //constructor
    timer()
    {
        startStop = true;
        startTime = Instant.now();
    }

    //grabs the end time
    public void stopTime()
    {
        if(!startStop)
        {
            endTime = Instant.now();   
        }
    }
    
    //prints out the time
    public void printTime(String method)
    {
        System.out.println(method + " time to run was: " + (endTime.toEpochMilli() - startTime.toEpochMilli()) + " milliseconds");
    }
}
