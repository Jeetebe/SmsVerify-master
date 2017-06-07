package com.noc.smsverify.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.noc.smsverify.R;
import com.noc.smsverify.utils.Json;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.tajchert.nammu.Nammu;
import pl.tajchert.nammu.PermissionCallback;

public class ImgPickerActivity extends AppCompatActivity {

    Context context;
    ImageView imgcam1,imgcam2,imgcam3,imgcam4;
    ImageView imgmattruoc;

    private ArrayList<File> photos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_picker);
        context=this;
        Nammu.init(this);

        imgmattruoc= (ImageView) findViewById(R.id.imgmattruoc);



        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Nammu.askForPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, new PermissionCallback() {
                @Override
                public void permissionGranted() {
                    //Nothing, this sample saves to Public gallery so it needs permission
                }

                @Override
                public void permissionRefused() {
                    finish();
                }
            });
        }

        imgcam1= (ImageView) findViewById(R.id.imgcam1);
        imgcam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Activity activity=(Activity) context;
                EasyImage.openCamera(activity, 0);
            }
        });
        EasyImage.configuration(this)
                .setImagesFolderName("EasyImage sample");
                //.setCopyTakenPhotosToPublicGalleryAppFolder(true)
                //.setCopyPickedImagesToPublicGalleryAppFolder(true)
                //.setAllowMultiplePickInGallery(true);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling
                e.printStackTrace();
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                onPhotosReturned( imageFile);
            }


            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                //Cancel handling, you might wanna remove taken photo if it was canceled
                if (source == EasyImage.ImageSource.CAMERA) {
                    File photoFile = EasyImage.lastlyTakenButCanceledPhoto(ImgPickerActivity.this);
                    if (photoFile != null) photoFile.delete();
                }
            }
        });
    }

    private void onPhotosReturned(List<File> returnedPhotos) {
        photos.addAll(returnedPhotos);
        for (File photo: returnedPhotos
             ) {
            Json.logi("onPhotosReturned:"+photo.toString());

        }
        Json.logi("onPhotosReturned:"+returnedPhotos);

    }
    private void onPhotosReturned(File returnedPhotos) {
        //photos.addAll(returnedPhotos);
            Json.logi("onPhotosReturned:"+returnedPhotos.toString());
        Picasso.with(context)
                .load(returnedPhotos)
                .fit()
                .centerCrop()
                .into(imgmattruoc);
    }
    @Override
    protected void onDestroy() {
        // Clear any configuration that was done!
        EasyImage.clearConfiguration(this);
        super.onDestroy();
    }
}
