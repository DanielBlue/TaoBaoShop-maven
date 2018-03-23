package pers.mao.utils;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtils {

    public static String getExpressInfo(String express_code){
        String urlStr = "http://www.kuaidi100.com/autonumber/autoComNum?resultv2=1&text=" + express_code;
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String json = connect(urlStr, connection, reader);

        if (json!=null&&!json.isEmpty()){

            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(json);
            String express = jsonElement.getAsJsonObject().getAsJsonArray("auto").get(0).getAsJsonObject().get("comCode").getAsString();
            String result = "";
            if (!express.isEmpty()) {
                urlStr = "http://www.kuaidi100.com/query?type=" + express + "&postid=" + express_code;
                result = connect(urlStr, connection, reader);
            }
            return result;
        }
        return null;
    }

    private static String connect(String urlStr, HttpURLConnection connection, BufferedReader reader) {
        String json = "";
        try {
            URL url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            InputStream inputStream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            json = stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
            return json;
        }
    }
}
