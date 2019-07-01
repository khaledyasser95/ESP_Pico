import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

public class ESP_Timer {
    void Live_Repeat(Interface x, long delay, long period) {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                x.Fetch_Data();
                x.live_data();
            }
        };
        Timer timer = new Timer("Timer");
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }

    void Average_Repeat(Interface x, long delay, long period, Timestamp ts) {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {

                ;
            }
        };
        Timer timer = new Timer("Timer");
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }

    void Average_every_min(Interface x) {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                x.Average_Min();
                x.initUI();
            }
        };
        Timer timer = new Timer("Timer");
        timer.scheduleAtFixedRate(repeatedTask, 60 * 1000L, 60 * 1000L);
    }


}
