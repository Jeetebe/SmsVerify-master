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
import com.noc.smsverify.object.CollimgObj;
import com.noc.smsverify.utils.Json;
import com.noc.smsverify.utils.UploadFileToServer;
import com.noc.smsverify.utils.Utils;
import com.scalified.fab.ActionButton;
import com.scalified.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.tajchert.nammu.Nammu;
import pl.tajchert.nammu.PermissionCallback;

public class ImgPickerActivity extends AppCompatActivity  {

    Context context;
    Utils utits;

    ImageView imgcam1,imgcam2,imgcam3,imgcam4;
    ImageView imgfold1,imgfold2,imgfold3,imgfold4;
    ImageView imgmattruoc,imgmatsau,imgphieuthongtin,imganhnguoi;
    private ActionButton fbutton;

    private int currPic=1;

    //List<Map.Entry<String, String>> listimg = new ArrayList<Map.Entry<String, String>>();
    //Map<String,String> listimg = new HashMap<String, String>();
    //private ArrayList<File> photos = new ArrayList<>();
    ArrayList<String> myList = new ArrayList<String>();

    String StrMattruoc="";
    String StrMatsau="";
    String Strphieuthongtin="";
    String Stranhnguoi="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_picker);
        context=this;
        Nammu.init(this);
        utits=new Utils(this);


        imgmattruoc= (ImageView) findViewById(R.id.imgmattruoc);
        imgmatsau= (ImageView) findViewById(R.id.imgmatsau);
        imgphieuthongtin= (ImageView) findViewById(R.id.imgphieuthongtin);
        imganhnguoi= (ImageView) findViewById(R.id.imganhnguoi);

        imgcam1= (ImageView) findViewById(R.id.imgcam1);
        imgcam2= (ImageView) findViewById(R.id.imgcam2);
        imgcam3= (ImageView) findViewById(R.id.imgcam3);
        imgcam4= (ImageView) findViewById(R.id.imgcam4);

        imgfold1= (ImageView) findViewById(R.id.imgfold1);
        imgfold2= (ImageView) findViewById(R.id.imgfold2);
        imgfold3= (ImageView) findViewById(R.id.imgfold3);
        imgfold4= (ImageView) findViewById(R.id.imgfold4);


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

        imgcam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Cap_Camera(1);
            }
        });
        imgcam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Cap_Camera(2);
            }
        });
        imgcam3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Cap_Camera(3);
            }
        });
        imgcam4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Cap_Camera(4);
            }
        });


        imgfold1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Select_folder(1);
            }
        });
        imgfold2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Select_folder(2);
            }
        });
        imgfold3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Select_folder(3);
            }
        });
        imgfold4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Select_folder(4);
            }
        });

        fbutton=(ActionButton) findViewById(R.id.action_button);
        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myList.size()<1)
                {
                    utits.thongbao("Lỗi","Bạn chưa chọn đủ 4 ảnh");
                }
                else
                {
                    Json.logi("mylist size:"+myList.size());
                    Intent resultIntent = new Intent();
                    //resultIntent.putExtra("mylist", myList);
                    CollimgObj collimgObj=new CollimgObj();
                    collimgObj.setImgtruoc(StrMattruoc);
                    collimgObj.setImgsau(StrMatsau);
                    collimgObj.setImgphieu(Strphieuthongtin);
                    collimgObj.setImganhnguoi(Stranhnguoi);
                    resultIntent.putExtra("mylist", (Serializable) collimgObj);

                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();

                    //new UploadFileToServer(StrImg1).execute();
                    //new UploadFileToServer(StrImg1,"939170707","mattruoc").execute();
                }

            }
        });
        EasyImage.configuration(this)
                .setImagesFolderName("EasyImage sample");
                //.setCopyTakenPhotosToPublicGalleryAppFolder(true)
                //.setCopyPickedImagesToPublicGalleryAppFolder(true)
                //.setAllowMultiplePickInGallery(true);


    }
    private void Cap_Camera(int i)
    {
        currPic=i;
        Activity activity=(Activity) context;
        EasyImage.openCamera(activity, 0);
    }
    private void Select_folder(int i)
    {
        currPic=i;
        Activity activity=(Activity) context;
        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            EasyImage.openDocuments(activity, 0);
        } else {
            Nammu.askForPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE, new PermissionCallback() {
                @Override
                public void permissionGranted() {
                    EasyImage.openDocuments(ImgPickerActivity.this, 0);
                }

                @Override
                public void permissionRefused() {

                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        overridePendingTransition(R.anim.null_animation, R.anim.slide_out_bottom);
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
        //photos.addAll(returnedPhotos);
        for (File photo: returnedPhotos
             ) {
            Json.logi("onPhotosReturned:"+photo.toString());

        }
        Json.logi("onPhotosReturned:"+returnedPhotos);

    }
    private void onPhotosReturned(File returnedPhotos) {
        //photos.addAll(returnedPhotos);
        Json.logi("onPhotosReturned:"+returnedPhotos.toString());
        String imgpath=returnedPhotos.toString();
        myList.add(imgpath);
        switch (currPic)
        {
            case 1:
                Picasso.with(context)
                        .load(returnedPhotos)
                        .fit()
                        .centerCrop()
                        .into(imgmattruoc);
                StrMattruoc=imgpath;
                break;
            case 2:
                Picasso.with(context)
                        .load(returnedPhotos)
                        .fit()
                        .centerCrop()
                        .into(imgmatsau);
                StrMatsau=imgpath;
                break;
            case 3:
                Picasso.with(context)
                        .load(returnedPhotos)
                        .fit()
                        .centerCrop()
                        .into(imgphieuthongtin);
                Strphieuthongtin=imgpath;
                break;
            case 4:
                Picasso.with(context)
                        .load(returnedPhotos)
                        .fit()
                        .centerCrop()
                        .into(imganhnguoi);
                Stranhnguoi=imgpath;
                break;
        }

    }
    @Override
    protected void onDestroy() {
        // Clear any configuration that was done!
        EasyImage.clearConfiguration(this);
        super.onDestroy();
    }

}
