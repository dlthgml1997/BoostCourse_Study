package com.sohee.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class ThreadAnimActivity extends AppCompatActivity {

    ArrayList<Drawable> imageList = new ArrayList<Drawable>();

    ImageView imageView;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        Resources res = getResources();
        imageList.add(res.getDrawable(R.drawable.ic_crying));
        imageList.add(res.getDrawable(R.drawable.ic_laughing));
        imageList.add(res.getDrawable(R.drawable.ic_sad_24dp));
        imageList.add(res.getDrawable(R.drawable.ic_smile_24dp));
        imageList.add(res.getDrawable(R.drawable.ic_suprised_24dp));

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimThread thread = new AnimThread();
                thread.start();
            }
        });
    }

    class AnimThread extends Thread {
        public void run() {
            for (int i = 0; i < 100; i++) {
                int curIndex = i % 5;
                final Drawable drawable = imageList.get(curIndex);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageDrawable(drawable);
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
