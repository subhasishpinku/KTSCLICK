package com.kits.kitsclick;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.kits.kitsclick.gps.GPSTracker;
import java.io.File;
import java.io.IOException;
public class Activitykyc_details extends AppCompatActivity {
    Toolbar toolbar;
    Button record,upload_video,nextId_agent;
    private static final int SELECT_VIDEO = 3;
    private String selectedPath;
    private int PICK_IMAGE_REQUEST = 1;
    private File actualImage;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_details);
        record = (Button)findViewById(R.id.record);
        upload_video = (Button)findViewById(R.id.upload_video);
        initToolBar();
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,10);
                startActivityForResult(intent,1);
            }
        });
        upload_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseVideo();
            }
        });
        GPSTracker gpsTracker = new GPSTracker(this);
        if (gpsTracker.getIsGPSTrackingEnabled()){
            String  stringLatitude = String.valueOf(gpsTracker.latitude);
            String  stringLongitude = String.valueOf(gpsTracker.longitude);
            String country = gpsTracker.getCountryName(this);
            String  city = gpsTracker.getLocality(this);
            String postalCode = gpsTracker.getPostalCode(this);
            String addressLine = gpsTracker.getAddressLine(this);
            Log.e("gps",stringLatitude+ "," +stringLongitude+ ","+country+ ","+city+""+postalCode+""+addressLine);
           // locationaddressId.setText(addressLine);

        }
        else
        {
            gpsTracker.showSettingsAlert();
        }
    }
    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );
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
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            VideoView videoView = new VideoView(this);
            videoView.setVideoURI(data.getData());
            Log.e("data",""+data.getData());
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
                actualImage = FileUtil.from(getApplication(),data.getData());
                //  Log.e("Path", String.valueOf(actualImage));
                bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), Uri.fromFile(actualImage));
               // imageview_account_profile.setImageBitmap(bitmap);
                String fileImage = String.valueOf(actualImage);
                String file_extn = fileImage.substring(fileImage.lastIndexOf("/") + 1);
                System.out.println("SelectVideo"+""+file_extn);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();
        cursor = getContentResolver().query(
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
}
