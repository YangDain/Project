package com.example.myapplication3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

        Button addRestaurant = (Button) findViewById(R.id.addRestaurant);
        addRestaurant.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddRestaurant.class);
                startActivity(intent);
            }
        });



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

        CharSequence sample1 = getText(R.string.sample1);
        TextView Address = (TextView)findViewById(R.id.Address);
        Address.setText(sample1);

        CharSequence sample2 = getText(R.string.sample2);
        TextView Call = (TextView)findViewById(R.id.Call);
        Call.setText(sample2);

        CharSequence sample3 = getText(R.string.sample3);
        TextView Time = (TextView)findViewById(R.id.Time);
        Time.setText(sample3);
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