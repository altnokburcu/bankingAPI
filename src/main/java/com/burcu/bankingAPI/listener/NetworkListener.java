package com.burcu.bankingAPI.listener;

import com.burcu.bankingAPI.service.SyncService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class NetworkListener implements ApplicationListener<ApplicationReadyEvent> {


    @Autowired
    SyncService syncService;


    private static boolean isNetworkAvailable() {
        {
            try {
                //make a URL to a known source
                URL url = new URL("http://www.google.com");
                //open a connection to that source
                HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
                //trying to retrieve data from the source. If there is no connection, this line will fail
                urlConnect.getContent();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if(isNetworkAvailable()){
            try {
                System.out.println(isNetworkAvailable() ?"Network is available.": "Network is not available!" );
                syncService.processData();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else{
            Thread thread = new Thread(() ->{
                try{
                    Thread.sleep(3000);
                    isNetworkAvailable();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();

        }

    }
}
