package com.yinhe.recyclerviewdemo;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> list = new ArrayList<>();
        for (int i = 0;i<100;i++){
            list.add("item "+i);
        }
        final MyRecyclerAdapter adapter = new MyRecyclerAdapter(list);

        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerwidget);
//        //垂直线性布局
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        //设置分隔线
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        //横向线性布局
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.HORIZONTAL));
//        //设置成3列网格布局
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        recyclerView.addItemDecoration(new DividerGridViewItemDecoration(this));
//        //瀑布流布局
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new DividerGridViewItemDecoration(this));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String data) {
                Toast.makeText(MainActivity.this, "您点击了：" +position+"  " + data, Toast.LENGTH_SHORT).show();
                adapter.addItem(position,data+"附件");
            }
        });
        adapter.setOnItemLongClickListener(new MyRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position, String data) {
                Toast.makeText(MainActivity.this, "您长按点击了："+position+"  " + data, Toast.LENGTH_SHORT).show();
                adapter.deleteItem(position);
            }
        });

        recyclerView.setAdapter(adapter);
    }
}
