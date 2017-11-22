package com.example.myapplication3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddMenu extends AppCompatActivity {

    M_DBHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        checkDangerousPermissions();

        ImageButton cameraBtn = (ImageButton) findViewById(R.id.cameraBtn);
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TakeMenuPictureIntent();
            }
        });

        Button addResBtn = (Button) findViewById(R.id.addResBtn);
        addResBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddMenu();
            }
        });
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private File mPhotoFile = null;
    private String mPhotoFileName = null;

    // 카메라 앱 실행
    private String currentDateFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String currentTimeStamp = dateFormat.format(new Date());
        return currentTimeStamp;
    }

    private void TakeMenuPictureIntent() {
        Intent takePictureIntent = new
                Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager())
                != null) {
//1. 카메라 앱으로 찍은 이미지를 저장할 파일 객체 생성
            mPhotoFileName = "IMG" + currentDateFormat() + ".jpg";
            mPhotoFile = new
                    File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                    mPhotoFileName);
            if (mPhotoFile != null) {
//2. 생성된 파일 객체에 대한 Uri 객체를 얻기
                Uri imageUri = FileProvider.getUriForFile(this,
                        "com.example.myapplication3",
                        mPhotoFile);
//3. Uri 객체를 Extras를 통해 카메라 앱으로 전달
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(takePictureIntent,
                        REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (mPhotoFileName != null) {
                mPhotoFile = new
                        File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                        mPhotoFileName);
                ImageButton cameraBtn = (ImageButton) findViewById(R.id.cameraBtn);
                String IMAGE_URL = mPhotoFile.getPath();
                cameraBtn.setImageURI(Uri.parse(IMAGE_URL));
            } else
                Toast.makeText(getApplicationContext(), "mPhotoFile is null",
                        Toast.LENGTH_SHORT).show();
        }
    }

    final int REQUEST_EXTERNAL_STORAGE_FOR_MULTIMEDIA = 1;

    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        };
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions,
                    REQUEST_EXTERNAL_STORAGE_FOR_MULTIMEDIA);
        }
    }

    private void AddMenu() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);

        mDbHelper = new M_DBHelper(this);

        String[] addMenuData = addMenuArray();
        long nOfRows = mDbHelper.insertMenuByMethod(addMenuData[0], addMenuData[1], addMenuData[2], addMenuData[3], addMenuData[4]);

        startActivity(intent);

        if (nOfRows > 0) {
            Toast.makeText(this, nOfRows + " Record Inserted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No Record Inserted", Toast.LENGTH_SHORT).show();
        }
    }

    private String[] addMenuArray() {

        EditText edit_name = (EditText)findViewById(R.id.edit_name);
        EditText edit_price = (EditText)findViewById(R.id.edit_price);
        EditText edit_explain = (EditText)findViewById(R.id.edit_explain);
        ImageButton cameraBtn  = (ImageButton)findViewById(R.id.cameraBtn);

        String restaurant = getIntent().getStringExtra("resName");
        String name = edit_name.getText().toString();
        String price = edit_price.getText().toString()+"원";
        String explain = edit_explain.getText().toString();
        String image= mPhotoFileName;

        String[] dataArray = {restaurant, name, price, explain, image};
        return dataArray;
    }
}


