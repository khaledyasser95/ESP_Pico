import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JConnection {
    static boolean connected = false;

    /*
        public static Connection ConnectDB() {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sensors", "root", "");

                connected = true;
                return conn;
            } catch (Exception e) {
                connected = false;
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
    */
    public static JSONObject call_me() throws Exception {
        String url = "http://192.168.1.3:8060/sensors/api.php";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
       // System.out.println("\nSending 'GET' request to URL : " + url);
        //System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print in String
        //System.out.println(response.toString());

        //Read JSON response and print
        JSONObject myResponse = new JSONObject(response.toString());
/*
        JSONArray arr = myResponse.getJSONArray("data");
        for (int i = 0; i < arr.length(); i++) {
            String post_id = arr.getJSONObject(i).getString("temp_ID")+arr.getJSONObject(i).getString("Time")+arr.getJSONObject(i).getString("Temp");
            System.out.println(post_id);
        }
        */
        return myResponse;

    }


    public boolean isConnected() {
        return connected;
    }
}
