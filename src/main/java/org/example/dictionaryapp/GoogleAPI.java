package org.example.dictionaryapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleAPI {

    private static final String API_URL = "https://script.google.com/macros/s/AKfycbw3eoxifK9c2mwf5UzR_ed_sa3x8TOGuXHiketruSU1tZIELGh2fHuWts_19sMSVsEH/exec";

    public static String translate(String text, String langFrom, String langTo) throws IOException {
        String urlStr = API_URL +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&source=" + langFrom +
                "&target=" + langTo;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public static void main(String[] args) {
        try {
            String text = "Hello world!";
            String translatedText = translate(text, "en", "vi");
            System.out.println("Translated text: " + translatedText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
