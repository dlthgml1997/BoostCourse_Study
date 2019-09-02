package com.sohee.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    SingerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new SingerAdapter(getApplicationContext());

        adapter.addItem(new SingerItem("소녀시대", "010-2233-2543"));
        adapter.addItem(new SingerItem("걸스데이", "010-2093-1243"));
        adapter.addItem(new SingerItem("트와이스", "010-2323-0843"));
        //  변경

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SingerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SingerAdapter.ViewHolder holder, View view, int position) {
                SingerItem item = adapter.getItem(position);
                Toast.makeText(getApplicationContext(),"아이템 선택됨."+item.getName(),Toast.LENGTH_LONG);
            }
        });

    }
}
