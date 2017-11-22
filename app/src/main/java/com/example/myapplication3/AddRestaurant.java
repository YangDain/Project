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

public class AddRestaurant extends AppCompatActivity {

    R_DBHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        // 권한 검사
        checkDangerousPermissions();

        // 카메라 버튼(이미지 버튼) 클릭시 TakeResPictureIntent 함수로
        ImageButton cameraBtn = (ImageButton) findViewById(R.id.cameraBtn);
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TakeResPictureIntent();
            }
        });

        // 맛집 등록 버튼 클릭시 AddRestaurant 함수로
        Button addResBtn = (Button) findViewById(R.id.addResBtn);
        addResBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddRestaurant();
            }
        });
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private File mPhotoFile = null;
    private String mPhotoFileName = null;

    private String currentDateFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String currentTimeStamp = dateFormat.format(new Date());
        return currentTimeStamp;
    }

    // 카메라 버튼 클릭시 카메라 어플 실행 후 저장
    // 10주차 강의자료 참고
    private void TakeResPictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager())
                != null) {
            mPhotoFileName = "IMG" + currentDateFormat() + ".jpg";
            mPhotoFile = new
                    File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                    mPhotoFileName);
            if (mPhotoFile != null) {
                Uri imageUri = FileProvider.getUriForFile(this,
                        "com.example.myapplication3",
                        mPhotoFile);
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

    // 권한 검사 함수
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

    // 맛집 등록 버튼을 누를시 실행
    String[] addResData;
    private void AddRestaurant() {

        addResData = addResArray();
        Intent intent = new Intent(this, RestaurantDetail.class);
        intent.putExtra("resName", addResData[0]);

        long nOfRows = mDbHelper.insertRestaurantsByMethod(addResData[0], addResData[1], addResData[2], addResData[3], addResData[4]);

        startActivity(intent);

        if (nOfRows > 0) {
            Toast.makeText(this, nOfRows + " Record Inserted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No Record Inserted", Toast.LENGTH_SHORT).show();
        }
    }

    private String[] addResArray() {

        EditText edit_name = (EditText) findViewById(R.id.edit_name);
        EditText edit_address = (EditText) findViewById(R.id.edit_address);
        EditText edit_call = (EditText) findViewById(R.id.edit_call);

        String name = edit_name.getText().toString();
        String address = edit_address.getText().toString();
        String call = edit_call.getText().toString();
        String image = mPhotoFileName;

        String[] dataArray = {name, address, call, image};
        return dataArray;
    }
}


