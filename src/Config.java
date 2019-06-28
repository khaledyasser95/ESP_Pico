import java.util.Vector;

public class Config {
    boolean checked;
    private String name;
    private String id;
    private Vector value = new Vector();
    private Vector Time_stamps = new Vector();
    private String Location;
    private String Min;
    private String Max;

    public Config(String name, String id, String location, String min, String max, boolean checked) {
        this.name = name;
        this.id = id;
        this.Location = location;
        Min = min;
        Max = max;
        this.checked = checked;
    }

    public Config(Vector value, Vector time_stamps) {
        this.value = value;
        Time_stamps = time_stamps;
    }

    public void add_value(Object e) {
        value.add(e);
    }

    public void add_time_stamp(Object e) {
        Time_stamps.add(e);
    }

    public Vector getValue() {
        return value;
    }

    public Vector getTime_stamps() {
        return Time_stamps;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return Location;
    }

    public String getMin() {
        return Min;
    }

    public String getMax() {
        return Max;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
