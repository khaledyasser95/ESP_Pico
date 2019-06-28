import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

public class ESP_Timer {
    void Live_Repeat(Interface x, long delay, long period) {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                x.view();
                System.out.println("YAAAAAAY");
            }
        };
        Timer timer = new Timer("Timer");
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }

    void Average_Repeat(Interface x, long delay, long period, Timestamp ts) {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                x.Average(ts);
            }
        };
        Timer timer = new Timer("Timer");
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }

    void Main_Repeat(Interface x, long delay, long period) {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                x.view();
                System.out.println("YAAAAAAY");
            }
        };
        Timer timer = new Timer("Timer");
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }
}
