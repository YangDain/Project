package com.example.myapplication3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static com.example.myapplication3.MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addMenu = (Button) findViewById(R.id.addMenu);
        addMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddMenu.class);
                startActivity(intent);
            }
        });
        Button addRestaurant = (Button) findViewById(R.id.addRestaurant);
        addRestaurant.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddRestaurant.class);
                startActivity(intent);
            }
        });


/*
        Cursor cursor = R_DBHelper.getAllRestaurantsByMethod(); // 전체 레스토랑 정보를 db 에서 가져옴
        String[] restaurants = new String[cursor.getCount()]; // 레스토랑 이름을 저장하기 위한 String 배열
        int i = 0;

        while (cursor.moveToNext()) { // 모든 레스토랑 이름을 배열에 저장하는 반복문
            restaurants[i++] = cursor.getString(1).toString();
        }
    }
}


*/


        ArrayList<MyItem> data = new ArrayList<MyItem>();
        data.add(new MyItem(R.drawable.sample_0, "냉면", "6000"));
        data.add(new MyItem(R.drawable.sample_1, "라면", "4000"));
        data.add(new MyItem(R.drawable.sample_2, "김밥", "2000"));
        data.add(new MyItem(R.drawable.sample_3, "돈가스", "5000"));

        adapter = new MyAdapter(this, R.layout.item, data);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View vClicked,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(), com.example.myapplication3.MenuDetail.class);
                MyItem item = (MyItem)adapter.getItem(position);
                int image = item.mIcon;
                String name = item.nName;
                String cost = item.nCost;

                intent.putExtra("image", image);
                intent.putExtra("name", name);
                intent.putExtra("cost", cost);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onGotoCallClicked(View v) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:023456789"));
        startActivity(myIntent);
    }

}


