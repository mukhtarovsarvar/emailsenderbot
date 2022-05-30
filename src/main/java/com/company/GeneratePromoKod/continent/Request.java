package com.company.GeneratePromoKod.continent;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request extends Thread{
    @Override
    public void run() {
        while (true) {

            try{Thread.sleep(20000);}catch(InterruptedException e){System.out.println(e);}

            try {
                URL url = new URL("https://emailsenderbot.herokuapp.com/test");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                System.out.println(con.getResponseCode());
                System.out.println("zapros");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
