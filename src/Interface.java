import com.toedter.calendar.JDateChooser;
import lu.tudor.santec.jtimechooser.JTimeChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

public class Interface {
    static int SENSORS_SIZE = 15;
    public JPanel rootPanel;
    int index = 0;
    Statement stmt = null;
    int a[] = new int[SENSORS_SIZE];
    String col[] = {"WHP", "WHT", "CP", "SEP.P", "SEP.T", "DIFF", "OIL METER", "OIL TEMP", "WATER METER", "WATER TEMP", "SC DIFF", "TANK LVL", "pH", "NaCl", "CT"};
    Config[] conf = new Config[SENSORS_SIZE];
    JCheckBox[] boxes = new JCheckBox[SENSORS_SIZE];
    ESP_Timer ESP_T = new ESP_Timer();
    Vector IDs = new Vector();
    private JTabbedPane tabbedPane1;
    private JTable main_table;
    private JTextField min_0;
    private JTextField min_1;
    private JTextField max_0;
    private JTextField max_1;
    private JTextField max_2;
    private JTextField max_3;
    private JTextField min_2;
    private JTextField min_3;
    private JTextField min_4;
    private JTextField min_5;
    private JTextField min_6;
    private JTextField min_7;
    private JTextField min_8;
    private JTextField min_9;
    private JTextField max_4;
    private JTextField max_5;
    private JTextField max_6;
    private JTextField max_7;
    private JTextField max_8;
    private JTextField max_9;
    private JTextField min_10;
    private JTextField min_11;
    private JTextField min_12;
    private JTextField min_14;
    private JTextField min_13;
    private JTextField max_10;
    private JTextField max_11;
    private JTextField max_12;
    private JTextField max_13;
    private JTextField max_14;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JCheckBox checkBox4;
    private JCheckBox checkBox5;
    private JCheckBox checkBox6;
    private JCheckBox checkBox7;
    private JCheckBox checkBox8;
    private JCheckBox checkBox9;
    private JCheckBox checkBox10;
    private JCheckBox checkBox11;
    private JCheckBox checkBox12;
    private JCheckBox checkBox13;
    private JCheckBox checkBox14;
    private JCheckBox checkBox15;
    private JTextField id_0;
    private JTextField id_1;
    private JTextField id_2;
    private JTextField id_3;
    private JTextField id_4;
    private JTextField id_5;
    private JTextField id_6;
    private JTextField id_7;
    private JTextField id_8;
    private JTextField id_9;
    private JTextField id_10;
    private JTextField id_11;
    private JTextField id_12;
    private JTextField id_13;
    private JTextField id_14;
    private JTextField whp_loc;
    private JTextField wht_loc;
    private JTextField cp_loc;
    private JTextField sep_loc;
    private JTextField sept_loc;
    private JTextField diff_loc;
    private JTextField oilm_loc;
    private JTextField oil_loc;
    private JTextField water_loc;
    private JTextField lvl_loc;
    private JTextField sc_loc;
    private JTextField tank_loc;
    private JTextField ph_Loc;
    private JTextField Nacl_loc;
    private JTextField CT_loc;
    private JLabel whp_lbl;
    private JLabel wht_lbl;
    private JLabel cp_lbl;
    private JLabel sepp_lbl;
    private JLabel sept_lbl;
    private JLabel diff_lbl;
    private JLabel oilm_lbl;
    private JLabel oilt_lbl;
    private JLabel waterT_lbl;
    private JLabel water_lbl;
    private JLabel sc_lbl;
    private JLabel tank_lbl;
    private JLabel ph_lbl;
    private JLabel nacl_lbl;
    private JLabel ct_lbl;
    private JButton activateButton;
    private JCheckBox selectAllCheckBox;
    private JPanel Config_panel;
    private JPanel Side;
    private JTable average_table;
    private JTable live_table;
    private JTextField con_txt;
    private JButton reconnectButton;
    private JTextField Average_every_txt;
    private JTextField textField3;
    private JButton startButton;
    private JDateChooser date_chooser;
    private JTimeChooser start_time;
    private JTimeChooser gen_time;
    private Connection conn = null;
    private DefaultTableModel tableModel;


    public Interface() {
        ini();
        date_chooser.setDateFormatString("yyyy-MM-dd");

        Check_connection();
        activateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ACTIVATAE
                configure(conf);

                view();

            }
        });
        selectAllCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectAllCheckBox.isSelected())
                    for (JCheckBox box : boxes)
                        box.setSelected(true);
                else
                    for (JCheckBox box : boxes)
                        box.setSelected(false);
            }
        });

        reconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn = JConnection.ConnectDB();
                Check_connection();
            }
        });


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String Generate_every= gen_time.getTimeField().getText();
                //Call TImer
               try{
                   String start_Time = start_time.getTimeField().getText();
                   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                   String start_Date = dateFormat.format(date_chooser.getDate());
                   String date_time = start_Date + " " + start_Time;
                   System.out.println(date_time);
                   java.sql.Timestamp ts = java.sql.Timestamp.valueOf(date_time);
                   Long timer = Long.parseLong(Average_every_txt.getText());
                   ESP_T.Average_Repeat(Interface.this, timer * 60 * 1000L, timer * 60 * 1000L, ts);
                   //Calculations
               }catch (Exception e1)
               {
                   JOptionPane.showMessageDialog(null,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
               }



            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

    }

    public void Average(Timestamp ts) {
        //FROM START TIME
        //TIME
        ArrayList<Float> WHP_Array = new ArrayList<Float>();
        float WHP_total = 0;
        float WHP_average = 0;
        Vector<Float> WHP_Averages = new Vector<Float>();

        for (int i = 0; i < index; i++) {
            if (conf[i].isChecked()) {
                for (int j = 0; j < conf[i].getTime_stamps().size(); j++) {
                    if (java.sql.Timestamp.valueOf(conf[i].getTime_stamps().get(j).toString()).after(ts)) {
                        String value = conf[i].getValue().get(j).toString().replace('F', ' ').trim();

                        float value1 = Float.parseFloat(value);
                        WHP_Array.add(value1);
                    }
                }
            }

        }


        for (int i = 0; i < WHP_Array.size(); i++) {
            WHP_total += WHP_Array.get(i);
        }
        WHP_average = WHP_total / WHP_Array.size();
        WHP_Averages.add(WHP_average);


        tableModel = new DefaultTableModel();
        for (int i = 0; i < index; i++) {
            if (conf[i].isChecked()) {
                if (conf[i].getName() == "WHP")
                    tableModel.addColumn(col[i], WHP_Averages);



            }

        }

        average_table.setModel(tableModel);


    }

    private void ini() {
        conn = JConnection.ConnectDB();

        for (int i = 0; i < SENSORS_SIZE; i++) {
            a[i] = 0;

        }
        boxes[0] = checkBox1;
        boxes[1] = checkBox2;
        boxes[2] = checkBox3;
        boxes[3] = checkBox4;
        boxes[4] = checkBox5;
        boxes[5] = checkBox6;
        boxes[6] = checkBox7;
        boxes[7] = checkBox8;
        boxes[8] = checkBox9;
        boxes[9] = checkBox10;
        boxes[10] = checkBox11;
        boxes[11] = checkBox12;
        boxes[12] = checkBox13;
        boxes[13] = checkBox14;
        boxes[14] = checkBox15;


    }

    private void Check_connection() {
        if (JConnection.connected) {
            con_txt.setBackground(Color.GREEN);
            con_txt.setText("Connected");
        } else {
            con_txt.setBackground(Color.RED);
            con_txt.setText("Disconnected");
        }
    }



    public void view() {
        try {

            System.out.println("OXXX");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM temp ";
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();

            int columnCount = md.getColumnCount();
            Vector columns = new Vector(columnCount);
            for (int i = 1; i <= columnCount; i++)
                columns.add(md.getColumnName(i));


            while (rs.next()) {
                String ID = rs.getString(2);
                IDs.add(ID);
                for (int i = 0; i < index; i++) {
                    if (conf[i].getId().equals(ID.trim()) && conf[i].isChecked()) {
                        conf[i].add_value(rs.getString(5));
                        conf[i].add_time_stamp(rs.getString(4));

                    }
                }

            }

            //Display in JTable
            tableModel = new DefaultTableModel();
            for (int i = 0; i < index; i++) {
                if (conf[i].isChecked()) {
                        tableModel.addColumn(col[i], conf[i].getValue());



                }

            }

            main_table.setModel(tableModel);


        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }

    }

    void configure(Config conf[]) {
        index = 0;

        if (checkBox1.isSelected()) {
            conf[index] = new Config(whp_lbl.getText(), id_0.getText(), whp_loc.getText(), min_0.getText(), max_0.getText(), checkBox1.isSelected());
            index++;
        }
        if (checkBox2.isSelected()) {
            conf[index] = new Config(wht_lbl.getText(), id_1.getText(), wht_loc.getText(), min_1.getText(), max_1.getText(), checkBox2.isSelected());
            index++;
        }
        if (checkBox3.isSelected()) {
            conf[index] = new Config(cp_lbl.getText(), id_2.getText(), cp_loc.getText(), min_2.getText(), max_2.getText(), checkBox3.isSelected());
            index++;
        }
        if (checkBox4.isSelected()) {
            conf[index] = new Config(sepp_lbl.getText(), id_3.getText(), sep_loc.getText(), min_3.getText(), max_3.getText(), checkBox4.isSelected());
            index++;
        }
        if (checkBox5.isSelected()) {
            conf[index] = new Config(sept_lbl.getText(), id_4.getText(), sept_loc.getText(), min_4.getText(), max_4.getText(), checkBox5.isSelected());
            index++;
        }
        if (checkBox6.isSelected()) {
            conf[index] = new Config(diff_lbl.getText(), id_5.getText(), diff_loc.getText(), min_5.getText(), max_5.getText(), checkBox6.isSelected());
            index++;
        }
        if (checkBox7.isSelected()) {
            conf[index] = new Config(oilm_lbl.getText(), id_6.getText(), oilm_loc.getText(), min_6.getText(), max_6.getText(), checkBox7.isSelected());
            index++;
        }
        if (checkBox8.isSelected()) {
            conf[index] = new Config(oilt_lbl.getText(), id_7.getText(), oil_loc.getText(), min_7.getText(), max_7.getText(), checkBox8.isSelected());
            index++;
        }
        if (checkBox9.isSelected()) {
            conf[index] = new Config(waterT_lbl.getText(), id_8.getText(), waterT_lbl.getText(), min_8.getText(), max_8.getText(), checkBox9.isSelected());
            index++;
        }
        if (checkBox10.isSelected()) {
            conf[index] = new Config(water_lbl.getText(), id_9.getText(), water_loc.getText(), min_9.getText(), max_9.getText(), checkBox10.isSelected());
            index++;
        }
        if (checkBox11.isSelected()) {
            conf[index] = new Config(sc_lbl.getText(), id_10.getText(), sc_loc.getText(), min_10.getText(), max_10.getText(), checkBox11.isSelected());
            index++;
        }
        if (checkBox12.isSelected()) {
            conf[index] = new Config(tank_lbl.getText(), id_11.getText(), tank_loc.getText(), min_11.getText(), max_11.getText(), checkBox12.isSelected());
            index++;
        }
        if (checkBox13.isSelected()) {
            conf[index] = new Config(ph_lbl.getText(), id_12.getText(), ph_Loc.getText(), min_12.getText(), max_12.getText(), checkBox13.isSelected());
            index++;
        }
        if (checkBox14.isSelected()) {
            conf[index] = new Config(nacl_lbl.getText(), id_13.getText(), Nacl_loc.getText(), min_13.getText(), max_13.getText(), checkBox14.isSelected());
            index++;
        }
        if (checkBox15.isSelected()) {
            conf[index] = new Config(ct_lbl.getText(), id_14.getText(), CT_loc.getText(), min_14.getText(), max_14.getText(), checkBox15.isSelected());
            index++;
        }

    }

    public void view_temp() {
        try {
            conn = JConnection.ConnectDB();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM temp ";
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            Vector columns = new Vector(columnCount);

            for (int i = 1; i <= columnCount; i++)
                columns.add(md.getColumnName(i));

            Vector data = new Vector();
            Vector row;
            while (rs.next()) {
                row = new Vector(columnCount);

                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);


            }


            //Display in JTable
            tableModel = new DefaultTableModel(data, columns);
            main_table.setModel(tableModel);


        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }

    }
}