package com.example.myapplication3;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuDetail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        Intent intent = getIntent();
        int image = intent.getIntExtra("image", 0);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageResource(image);

        String name = intent.getStringExtra("name");
        TextView textItem1 = (TextView)findViewById(R.id.textItem1);
        textItem1.setText(name);

        String cost = intent.getStringExtra("cost");
        TextView textItem2 = (TextView)findViewById(R.id.textItem2);
        textItem2.setText(cost);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 0 || data == null)
            return;
    }
}





