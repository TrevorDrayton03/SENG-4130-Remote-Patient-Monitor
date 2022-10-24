import java.util.*;
import java.lang.*;


public class HeartRate {

    public HeartRate() {
        ArrayList<Double> heartRate = new ArrayList<Double>();
        Random bpm = new Random();
        heartRate.add(bpm.nextDouble(10 + (130)));
        Iterator<Double> iterator = heartRate.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next() + " HEART RATE");
        }
    }
}
