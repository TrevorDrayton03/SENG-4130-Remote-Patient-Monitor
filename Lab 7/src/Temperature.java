import java.util.*;
import java.lang.*;

public class Temperature{
    public Temperature() {
        ArrayList<Double> temperature = new ArrayList<Double>();
        Random temp = new Random();
        Random safeOrNot = new Random();
        int safeOrNo;
        try {
                safeOrNo = safeOrNot.nextInt(2);
                if (safeOrNo == 0) {
                    temperature.add(36.5 + (37.5 - 36.5) * temp.nextDouble());
                } else {
                    temperature.add(35 + (42 - 35) * temp.nextDouble());
                }
            Iterator<Double> tempiter = temperature.iterator();
            while (tempiter.hasNext()) {
                System.out.println(tempiter.next() + " TEMPERATURE ");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}