package com.sohee.week5;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerActivity extends AppCompatActivity {
    Handler handler = new Handler();

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ClientThread thread = new ClientThread();
                thread.start();
            }
        });

        text = (TextView) findViewById(R.id.textView2);
    }

    class ClientThread extends Thread {
        public void run(){
            String host = "localhost";
            int port = 5001;

            try{

                Socket socket = new Socket(host,port);

                ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
                outStream.writeObject("안녕!");
                outStream.flush();
                Log.d("ClientThread","서버로 보냄.");

                ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
                final Object input = inStream.readObject();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("ClientThread","받은 데이터 : " + input);
                    }
                });

            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
