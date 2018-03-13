package pers.mao.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RequestUtils {

    public static String getRequestBody(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        String input = null;
        StringBuffer requestBody = new StringBuffer();
        while((input = reader.readLine()) != null) {
            requestBody.append(input);
        }
        return requestBody.toString();
    }

    public static String getRequestBody(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in,"utf-8"));
        String input = null;
        StringBuffer requestBody = new StringBuffer();
        while((input = reader.readLine()) != null) {
            requestBody.append(input);
        }
        return requestBody.toString();
    }
}
