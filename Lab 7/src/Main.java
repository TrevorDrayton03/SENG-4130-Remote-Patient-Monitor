import java.util.*;

public class Main {

    public static void main(String[] args){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                    new Temperature();
                    new HeartRate();
            }
        },1000, 1000);
    }
}
