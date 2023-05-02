/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import org.json.JSONObject;

public class GptRequest {
//    private final static String key = "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";
    
    public static String enviar(String mensagem){
        String resposta = "";
        System.out.println("mensagem: " + mensagem);
        try {
            URL url = new URL("https://api.openai.com/v1/chat/completions");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer "+"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");

            JSONObject data = new JSONObject();
            data.put("model", "text-davinci-003");
            data.put("prompt", mensagem);
            data.put("max_tokens", 4000);
            data.put("temperature", 1.0);
        
            conn.setDoOutput(true);
            conn.getOutputStream().write(data.toString().getBytes());

            String output = new BufferedReader(new InputStreamReader(conn.getInputStream())).lines()
                    .reduce((a, b) -> a + b).get();

            resposta = new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text");

        } catch (Exception e) {
            e.printStackTrace();
            return("Request error: "+e);
        }
        return resposta;
    }
}
