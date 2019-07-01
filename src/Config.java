import java.util.Vector;

public class Config {
    boolean checked;
    private String name;
    private String id;
    private Vector value = new Vector();
    private Vector Time_stamps = new Vector();

    //Every min
    private Vector Aver_min = new Vector();
    private Vector Aver_min_timestamp = new Vector();
    //Average
    private Vector Aver_total = new Vector();
    private Vector Aver_total_timestamp = new Vector();

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

    public Vector getAver_total() {
        return Aver_total;
    }

    public Vector getAver_total_timestamp() {
        return Aver_total_timestamp;
    }

    public void Clear_Average_total() {
        Aver_total.clear();
        Aver_total_timestamp.clear();
    }

    public void add_averg_total_value(Object e) {
        Aver_total.add(e);
    }

    public void addAver_total_timestamp(Object e) {
        Aver_total_timestamp.add(e);
    }

    public Vector getAver_min() {
        return Aver_min;
    }

    public void setAver_min(Object aver_min) {
        Aver_min.add(aver_min);
    }

    public Vector getAver_min_timestamp() {
        return Aver_min_timestamp;
    }

    public void setAver_min_timestamp(Object aver_min_timestamp) {
        Aver_min_timestamp.add(aver_min_timestamp);
    }

    public int get_last_Aver_min_timestamp() {
        return Aver_min_timestamp.size() - 1;
    }

    public void clear() {
        value.clear();
        Time_stamps.clear();
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

    public Object getValue_last() {
        return value.get(value.size() - 1);
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
