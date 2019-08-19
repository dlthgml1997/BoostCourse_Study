package com.sohee.week5;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ThreadActivity extends AppCompatActivity {

    TextView textView;
    ProgressBar progressBar;

    ValueHandler handler = new ValueHandler();
    Handler handler2 = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textView);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                BackgroundThread thread = new BackgroundThread();
//                thread.start();
/*
                new Thread(new Runnable() {
                    int value = 0;
                    boolean running = false;

                    @Override
                    public void run() {
                        running = true;
                        while (running) {
                            value += 1;


                            handler2.post(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText("현재 값 : " + value);
                                }
                            });


                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                            }
                        }
                    }
                }).start();*/

                ProgressTask task = new ProgressTask();
                task.execute("시작");

            }
        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("현재 값 : ");
            }
        });

    }

    class ProgressTask extends AsyncTask<String, Integer, Integer> {

        int value = 0;


        @Override
        protected Integer doInBackground(String... strings) {
            //스레드 안에 있는 코드 넣으면 됨

            while (true) {
                if (value > 100) {
                    break;
                }

                value += 1;

                publishProgress(value);
/*
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(value);
                    }
                });*/

                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //중간에 ui 변경 하고 싶을 때
            //doin에서 publishProgress 사용하면 호출 됨
            //핸들러에서 사용하듯이 사용됨
            super.onProgressUpdate(values);

            progressBar.setProgress(values[0].intValue());

        }

        @Override
        protected void onPostExecute(Integer integer) {

            //doInBackgroung   에서 리턴헤주는 값에 따라서 결과값을 전달 받음.
            //마지막에 과정이 다 끝나고 나서 호출 됨
            super.onPostExecute(integer);

            Toast.makeText(getApplicationContext(), "완료됨.", Toast.LENGTH_LONG).show();
        }

    }
/*
    class BackgroundThread extends Thread {

        int value = 0;

        boolean running = false;

        public void run() {
            running = true;
            while (running) {
                value += 1;

                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("value", value);
                message.setData(bundle);
                handler.sendMessage(message);

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
        }
    }*/

    class ValueHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            textView.setText("현재 값 : " + value);

        }
    }
}
