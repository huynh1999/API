package com.appdd.api.Model;

import com.appdd.api.Controller.GroupUserController;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;

public class ThreadDelete extends Thread{
    @Autowired
    EntityManager em;
    @Override
    public void run() {

        while (true)
        {
            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder().url("http://127.0.0.1:8080/api/group/rmotp")
                    .delete()
                    .build();
            try {
                client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
