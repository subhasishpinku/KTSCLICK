package com.kits.kitsclick.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.kits.kitsclick.ApiClientToken;
import com.kits.kitsclick.Consts;
import com.kits.kitsclick.FileUtil;
import com.kits.kitsclick.R;
import com.kits.kitsclick.SharedPrefManager;
import com.kits.kitsclick.gps.GPSTracker;
import com.kits.kitsclick.setget.Packagelist;
import com.kits.kitsclick.setget.Pagecheck;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class Photokyc extends Fragment{
    EditText adherId,panId,voterId,adressId;
    Button upId,upId1,upId3,record,uploadvideo;
    private static final int SELECT_VIDEO = 3;
    private String selectedPath;
    private int PICK_IMAGE_REQUEST = 1;
    private File actualImage;
    private Bitmap bitmap;
    private static final int Image_Capture_Code = 2;
    private static final int STORAGE_PERMISSION_CODE = 123;
    String file_extn = "name";
    String url;
    SharedPreferences sp;
    public static final String URL = "https://b2bktsclickworld.com/ktsapi/public/api/complete-photo-kyc";
    public static final String UPLOAD_KEY = "tmp_name";
    Button nextId_agent;
    String videopath;
    String completephotokyc= "";
    String completebusinessdetails= "";
    String completepersonaldetails ="";
    String Latitude = "";
    String Longitude = "";
    VideoView videoView;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_kyc, container, false);
        adherId = (EditText)rootView.findViewById(R.id.adherId);
        panId = (EditText)rootView.findViewById(R.id.panId);
        voterId = (EditText)rootView.findViewById(R.id.voterId);
//        latId = (EditText)rootView.findViewById(R.id.latId);
        adressId = (EditText)rootView.findViewById(R.id.adressId);
        upId = (Button)rootView.findViewById(R.id.upId);
        upId1 = (Button)rootView.findViewById(R.id.upId1);
        upId3 = (Button)rootView.findViewById(R.id.upId3);
        record = (Button)rootView.findViewById(R.id.record);
        uploadvideo = (Button)rootView.findViewById(R.id.upload_video);
         videoView = rootView.findViewById(R.id.videoView);
        sp = getActivity().getSharedPreferences(Consts.ADHER, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("ADHER", "0"); //chang vlue 1 for od not logout
        edit.commit();
        ///////////////////////
        sp = getActivity().getSharedPreferences(Consts.PAN, MODE_PRIVATE);
        SharedPreferences.Editor edit1 = sp.edit();
        edit1.putString("PAN", "0"); //chang vlue 1 for od not logout
        edit1.commit();
        ///////////////////////////
        sp = getActivity().getSharedPreferences(Consts.VOTER, MODE_PRIVATE);
        SharedPreferences.Editor edit2 = sp.edit();
        edit2.putString("VOTER", "0"); //chang vlue 1 for od not logout
        edit2.commit();
        //////////////////////////////////
        sp = getActivity().getSharedPreferences(Consts.ADHERURL, MODE_PRIVATE);
        SharedPreferences.Editor edit3 = sp.edit();
        edit3.putString("ADHERURL", "0"); //chang vlue 1 for od not logout
        edit3.commit();
        ///////////////////////
        sp = getActivity().getSharedPreferences(Consts.PANURL, MODE_PRIVATE);
        SharedPreferences.Editor edit4 = sp.edit();
        edit4.putString("PANURL", "0"); //chang vlue 1 for od not logout
        edit4.commit();
        ///////////////////////////
        sp = getActivity().getSharedPreferences(Consts.VOTERURL, MODE_PRIVATE);
        SharedPreferences.Editor edit5 = sp.edit();
        edit5.putString("VOTERURL", "0"); //chang vlue 1 for od not logout
        edit5.commit();

        ///////////////////////////////////////
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,10);
                startActivityForResult(intent,1);
            }
        });
        uploadvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseVideo();
            }
        });
        upId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp = getActivity().getSharedPreferences(Consts.ADHER, MODE_PRIVATE);
                SharedPreferences.Editor edit1 = sp.edit();
                edit1.putString("ADHER", "1"); //chang vlue 1 for od not logout
                edit1.commit();
                createDialog();
            }
        });
        upId1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp = getActivity().getSharedPreferences(Consts.PAN, MODE_PRIVATE);
                SharedPreferences.Editor edit1 = sp.edit();
                edit1.putString("PAN", "1"); //chang vlue 1 for od not logout
                edit1.commit();
                createDialog();
            }
        });
        upId3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp = getActivity().getSharedPreferences(Consts.VOTER, MODE_PRIVATE);
                SharedPreferences.Editor edit1 = sp.edit();
                edit1.putString("VOTER", "1"); //chang vlue 1 for od not logout
                edit1.commit();
                createDialog();
            }
        });
        nextId_agent = (Button)rootView.findViewById(R.id.nextId_agent);
        nextId_agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UpdateData();

            }
        });
        GPSTracker gpsTracker = new GPSTracker(getContext());
        if (gpsTracker.getIsGPSTrackingEnabled()){
             Latitude = String.valueOf(gpsTracker.latitude);
             Longitude = String.valueOf(gpsTracker.longitude);
            String country = gpsTracker.getCountryName(getContext());
            String  city = gpsTracker.getLocality(getContext());
            String postalCode = gpsTracker.getPostalCode(getContext());
            String addressLine = gpsTracker.getAddressLine(getContext());
            Log.e("gps",Latitude+ "," +Longitude+ ","+country+ ","+city+""+postalCode+""+addressLine);
            // locationaddressId.setText(addressLine);
//            latId.setText(stringLatitude);
//            langId.setText(stringLongitude);
            adressId.setText(addressLine);
        }
        else
        {
            gpsTracker.showSettingsAlert();
        }
        requestStoragePermission();
        pagecheck();




        return rootView;
    }
    private void chooseVideo() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a Video "), SELECT_VIDEO);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK && requestCode == 1){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            VideoView videoView = new VideoView(getActivity());
            videoView.setVideoURI(data.getData());
            videopath = String.valueOf(data.getData());
            Log.e("file_extn3",""+data.getData());
            videoView.start();
            builder.setView(videoView).show();


        }
//        if (resultCode == RESULT_OK) {
//            if (requestCode == SELECT_VIDEO) {
//                System.out.println("SELECT_VIDEO");
//                Uri selectedImageUri = data.getData();
//                selectedPath = getPath(selectedImageUri);
//                Log.e("Path",selectedPath);
//            }
//        }
        if (requestCode == SELECT_VIDEO && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try {
                actualImage = FileUtil.from(getActivity(),data.getData());
                //  Log.e("Path", String.valueOf(actualImage));
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.fromFile(actualImage));
                // imageview_account_profile.setImageBitmap(bitmap);
                videopath = String.valueOf(actualImage);
                String file_extn = videopath.substring(videopath.lastIndexOf("/") + 1);
                Log.e("file_extn3", videopath);
                System.out.println("SelectVideo"+""+file_extn);
                Uri uri = Uri.parse(videopath);
                videoView.setVideoURI(uri);
                MediaController mediaController = new MediaController(getActivity());
                mediaController.setAnchorView(videoView);
                mediaController.setMediaPlayer(videoView);
                videoView.setMediaController(mediaController);
                videoView.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try {
                actualImage = FileUtil.from(getContext(),data.getData());
                //  Log.e("Path", String.valueOf(actualImage));
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.fromFile(actualImage));
                //imageview_account_profile.setImageBitmap(bitmap);
                //  Uri tempUri = getImageUri(getContext(), bitmap);
                // File finalFile = new File(getRealPathFromURII(tempUri));
                //  actualImage = finalFile;
                String fileImage = String.valueOf(actualImage);
                file_extn = fileImage.substring(fileImage.lastIndexOf("/") + 1);
                //////////////////////////
                Uri uri = Uri.parse(fileImage);
                videoView.setVideoURI(uri);
                MediaController mediaController = new MediaController(getActivity());
                mediaController.setAnchorView(videoView);
                mediaController.setMediaPlayer(videoView);
                videoView.setMediaController(mediaController);
                videoView.start();
                //Log.e("file_extnn", file_extn);
                // resizeAndCompressImageBeforeSend(context,fileImage,fileImage);
                String file_extn = fileImage.substring(fileImage.lastIndexOf("/") + 1);
                System.out.println("SelectVideo"+""+file_extn);
                Log.e("file_extn", fileImage+file_extn);
                sp = getContext().getSharedPreferences(Consts.ADHER, MODE_PRIVATE);
                String ADHER = sp.getString("ADHER","");
                sp = getContext().getSharedPreferences(Consts.PAN, MODE_PRIVATE);
                String PAN = sp.getString("PAN","");
                sp = getContext().getSharedPreferences(Consts.VOTER, MODE_PRIVATE);
                String VOTER = sp.getString("VOTER","");
                if (ADHER.equals("1")){
                    sp = getActivity().getSharedPreferences(Consts.ADHERURL, MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("ADHERURL", fileImage); //chang vlue 1 for od not logout
                    edit.commit();
                }
                if (PAN.equals("1")){
                    sp = getActivity().getSharedPreferences(Consts.PANURL, MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("PANURL", fileImage); //chang vlue 1 for od not logout
                    edit.commit();
                }
                if (VOTER.equals("1")){
                    sp = getActivity().getSharedPreferences(Consts.VOTERURL, MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("VOTERURL", fileImage); //chang vlue 1 for od not logout
                    edit.commit();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
//            if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
//                bitmap = (Bitmap) data.getExtras().get("data");
//                imageview_account_profile.setImageBitmap(bitmap);
//                // setPic();
//                //  galleryAddPic();
//                Log.e("Camera"," "+bitmap);
//            }
        }
        if (requestCode == Image_Capture_Code) {
            if (resultCode == RESULT_OK) {
                bitmap = (Bitmap) data.getExtras().get("data");
               // imageview_account_profile.setImageBitmap(bitmap);
                Uri tempUri = getImageUri(getContext(), bitmap);
                File finalFile = new File(getRealPathFromURII(tempUri));
                //Log.e("IMGPATH"," "+finalFile);
                url = String.valueOf(finalFile);
                actualImage = finalFile;
                Log.e("path", String.valueOf(actualImage));
                String fileImage = String.valueOf(actualImage);
                file_extn = fileImage.substring(fileImage.lastIndexOf("/") + 1);
                Log.e("file_extn1", fileImage+file_extn);
                // resizeAndCompressImageBeforeSend(context,fileImage,fileImage);
                sp = getContext().getSharedPreferences(Consts.ADHER, MODE_PRIVATE);
                String ADHER = sp.getString("ADHER","");
                sp = getContext().getSharedPreferences(Consts.PAN, MODE_PRIVATE);
                String PAN = sp.getString("PAN","");
                sp = getContext().getSharedPreferences(Consts.VOTER, MODE_PRIVATE);
                String VOTER = sp.getString("VOTER","");
                if (ADHER.equals("1")){
                    sp = getActivity().getSharedPreferences(Consts.ADHERURL, MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("ADHERURL", fileImage); //chang vlue 1 for od not logout
                    edit.commit();
                }
                if (PAN.equals("1")){
                    sp = getActivity().getSharedPreferences(Consts.PANURL, MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("PANURL", fileImage); //chang vlue 1 for od not logout
                    edit.commit();
                }
                if (VOTER.equals("1")){
                    sp = getActivity().getSharedPreferences(Consts.VOTERURL, MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("VOTERURL", fileImage); //chang vlue 1 for od not logout
                    edit.commit();
                }
            } else if (resultCode == RESULT_CANCELED) {

            }
        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    public String getRealPathFromURII(Uri uri) {
        String path = "";
        if (getContext().getContentResolver() != null) {
            Cursor cursor = getContext().getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }
    public String getPath(Uri uri) {
        Cursor cursor = getContext().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();
        cursor = getContext().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
//        cursor.moveToFirst();
//        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//        cursor.close();
//
//        return path;
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            cursor.close();
            return path;
        }
        return null;
    }
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {

        }
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }
    public AlertDialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.aler_diolog, null);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        ((ImageView) dialogView.findViewById(R.id.openGallery)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);


            }
        });
        ((ImageView) dialogView.findViewById(R.id.openCamera)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 110);
                } else {

                }
                takePhotoFromCamera();
            }
        });
        ((Button) dialogView.findViewById(R.id.cancelId)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        builder.setView(dialogView);
        dialog.show();
        return dialog;
    }
    @SuppressLint("UnsupportedChromeOsCameraSystemFeature")
    private void takePhotoFromCamera() {
        if (getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, Image_Capture_Code);
        } else {

        }
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//
//    }

    public void UpdateData(){
        final String adher = adherId.getText().toString().trim();
        final String pan = panId.getText().toString().trim();
        final String voter = voterId.getText().toString().trim();
//        final String lat = latId.getText().toString().trim();
//        final String lang = langId.getText().toString().trim();
        if (TextUtils.isEmpty(adher)) {
            adherId.setError("Please enter Adher Number");
            adherId.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(pan)) {
            panId.setError("Please enter Pan Number");
            panId.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(voter)) {
            voterId.setError("Please enter Voter Number");
            voterId.requestFocus();
            return;
        }
//        if (TextUtils.isEmpty(lat)) {
//            latId.setError("Please enter latitude");
//            latId.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(lang)) {
//            langId.setError("Please enter longitude");
//            langId.requestFocus();
//            return;
//        }
        sp = getActivity().getSharedPreferences(Consts.ADHERURL, MODE_PRIVATE);
        String ADHERURL = sp.getString("ADHERURL","");
        sp = getActivity().getSharedPreferences(Consts.PANURL, MODE_PRIVATE);
        String PANURL = sp.getString("PANURL","");
        sp = getActivity().getSharedPreferences(Consts.VOTERURL, MODE_PRIVATE);
        String VOTERURL = sp.getString("VOTERURL","");
        Log.e("DATAVIEW",ADHERURL+" "+" "+PANURL+" "
                +" "+VOTERURL+" "+" "+adher+" "+pan+" "+" "+voter+" "+" "
                +SharedPrefManager.getInstance(getActivity()).getKeyToken()+" "+videopath);
        AndroidNetworking.upload(URL)
                .addMultipartFile("addhar_image", new File(ADHERURL))
                .addMultipartFile("pan_image", new File(PANURL))
                .addMultipartFile("voter_image", new File(VOTERURL))
                .addMultipartParameter("adharNo",adher)
                .addMultipartParameter("pannumber",pan)
                .addMultipartParameter("voter_id",voter)
                .addMultipartFile("voter_image", new File(videopath))
                .addMultipartParameter("video","video")
                .addMultipartParameter("latitude",Latitude)
                .addMultipartParameter("longitude",Longitude)
                .addHeaders("Authorization", "Bearer" + " " + SharedPrefManager.getInstance(getContext()).getKeyToken())
                .setTag("uploadTest")
                .setPriority(Priority.HIGH)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        // do anything with progress
                        Log.e("TAG", "onProgress: called..." );
                        // Intent intent = new Intent(getContext(),HomeNavigationDrawerActivity.class);
                        // startActivity(intent);
                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.e("TAG", "onResponse: "+response );
                        String res = String.valueOf(response);
                        Log.e("rex",res);
                        try {
                            JSONObject obj =new JSONObject(res);
                            String msg = obj.getString("message");
                            String error = obj.getString("false");
                            if (error.equals("false")){
                                Intent intent = new Intent(getContext(), Personaldetails.class);
                                getContext().startActivity(intent);
                                getActivity().finish();
                                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.e("TAG", "onError: "+error.getErrorDetail() );
                    }
                });
    }

    public void pagecheck(){
        Call<Pagecheck> call = ApiClientToken.getInstance().pagecheck(
        );
        call.enqueue(new Callback<Pagecheck>() {
            @Override
            public void onResponse(Call<Pagecheck> call, retrofit2.Response<Pagecheck> response) {
                if (response.isSuccessful()){
                    String error = response.body().getError();
                    Log.e("packagelist",""+error);
                    if (error.equals("false")){
                        for (int i = 0; i <response.body().getData().getList().size(); i++) {
                            Packagelist listdata =  response.body().getData().getList().get(i);
                            String name = listdata.getName();
                            String userID = listdata.getUserID();
                            String email = listdata.getEmail();
                            String agencyID = listdata.getAgencyID();
                            String emailID = listdata.getEmailID();
                            String mobile = listdata.getMobile();
                            completephotokyc = listdata.getCompletephotokyc();
                            completebusinessdetails = listdata.getCompletebusinessdetails();
                            completepersonaldetails = listdata.getCompletepersonaldetails();
                            System.out.println("packagelist"+name+" "
                                    +userID+" "+email+" "+agencyID+" "
                                    +emailID+" "+mobile+" "+completephotokyc+" "
                                    +completebusinessdetails+" "+completepersonaldetails);
                            if (completephotokyc.equals("1")){
                                Log.e("completephotokyc",completephotokyc);
                                adherId.setEnabled(false);
                                panId.setEnabled(false);
                                voterId.setEnabled(false);
                                adressId.setEnabled(false);
                                upId.setEnabled(false);
                                upId1.setEnabled(false);
                                upId3.setEnabled(false);
                                record.setEnabled(false);
                                uploadvideo.setEnabled(false);
                                nextId_agent.setEnabled(false);

                            }
                        }
//
                    }
                }
                else {
                    Log.e("DATA", " "+response.message());
                }

            }

            @Override
            public void onFailure(Call<Pagecheck> call, Throwable t) {

            }
        });

    }

}
