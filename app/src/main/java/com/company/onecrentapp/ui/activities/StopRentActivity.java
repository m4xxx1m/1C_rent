package com.company.onecrentapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import com.company.onecrentapp.R;
import com.company.onecrentapp.db.Database;
import com.company.onecrentapp.models.History;
import com.company.onecrentapp.models.Nomenclature;
import com.company.onecrentapp.models.User;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StopRentActivity extends AppCompatActivity {
    private ImageView imageView;
    private Uri path = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_rent);
        imageView = findViewById(R.id.image);
        dispatchTakePictureIntent();
        findViewById(R.id.take_new_photo).setOnClickListener(view -> {
            dispatchTakePictureIntent();
        });
        findViewById(R.id.stop_rent_button).setOnClickListener(view -> {
            User.getInstance().inRentVendorCode = null;
            History history = History.getLastInstance();
            history.stationPut = history.stationTake;
            Date currentDate = Calendar.getInstance().getTime();
            String pattern = "dd/MM/yyyy HH:mm";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            history.datetimePut = format.format(currentDate);
            Date takeDate = null;
            try {
                takeDate = new SimpleDateFormat(pattern).parse(history.datetimeTake);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            assert takeDate != null;
            long diff = currentDate.getTime() - takeDate.getTime();
            history.time = diff / 60000L;
            int price = 0;
            for (Nomenclature n : Nomenclature.getInstanceArray())
            {
                if (n.vendorCode.equals(history.vendorCode))
                {
                    price = n.price;
                }
            }
            User.getInstance().minusBalance(history.time, price);
            Database.updateUser();
            Database.updateHistory();
            finish();
        });
        findViewById(R.id.cancel_stopping_rent).setOnClickListener(view -> {
            finish();
        });
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

//    private void dispatchTakePictureIntent() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        try {
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//        } catch (ActivityNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getApplicationContext(),
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                path = photoURI;
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            imageView.setImageBitmap(imageBitmap);
//            Uri path = (Uri) extras.get(MediaStore.EXTRA_OUTPUT);
//            Uri path = Uri.parse(data.getStringExtra(MediaStore.EXTRA_OUTPUT));
            Bitmap imageBitmap = null;
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(imageBitmap);
        }
    }

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
}