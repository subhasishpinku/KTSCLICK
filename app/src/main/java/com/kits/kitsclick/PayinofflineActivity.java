package com.kits.kitsclick;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.google.android.material.textfield.TextInputEditText;
import com.kits.kitsclick.fragment.Personaldetails;
import com.kits.kitsclick.setget.Banklist;
import com.kits.kitsclick.setget.Banksetget;
import com.kits.kitsclick.setget.ListMethodListdata;
import com.kits.kitsclick.setget.Listbankdata;
import com.kits.kitsclick.setget.Listbankdatalist;
import com.kits.kitsclick.setget.Logindata;
import com.kits.kitsclick.setget.MethodList;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

import static com.kits.kitsclick.Consts.PANURL;
import static com.kits.kitsclick.Consts.VOTERURL;

public class PayinofflineActivity extends AppCompatActivity {
    AppCompatAutoCompleteTextView bankId,tv_transferID;
    ArrayList<String> banknamearray;
    ArrayList<String> methodarray;
    String banksId,banksname,methodname;
    TextInputEditText tv_depositId,tv_bankresferanceID,tv_ifccodeID,tv_amountId,tv_remarkssID;
    ImageView Imgm1,Imgm2;
    private String selectedPath;
    private int PICK_IMAGE_REQUEST = 1;
    private File actualImage;
    private Bitmap bitmap;
    private static final int Image_Capture_Code = 2;
    private static final int STORAGE_PERMISSION_CODE = 123;
    String file_extn = "name";
    String url;
    Button btn_payrecopt;
    String fileImage;
    public static final String URL = "https://b2bktsclickworld.com/ktsapi/public/api/add-manual-money";
    SharedPreferences sp;
    TextView balance;
    ImageView Img2,Img;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payin_offline_activity);
        bankId = (AppCompatAutoCompleteTextView)findViewById(R.id.bankId);
        tv_transferID = (AppCompatAutoCompleteTextView)findViewById(R.id.tv_transferID);

        tv_depositId = (TextInputEditText)findViewById(R.id.tv_depositId);
        tv_bankresferanceID = (TextInputEditText)findViewById(R.id.tv_bankresferanceID);
        tv_ifccodeID = (TextInputEditText)findViewById(R.id.tv_ifccodeID);
        tv_amountId = (TextInputEditText)findViewById(R.id.tv_amountId);
        tv_remarkssID = (TextInputEditText)findViewById(R.id.tv_remarkssID);
        Imgm1 = (ImageView)findViewById(R.id.Imgm1);
        Imgm2 = (ImageView)findViewById(R.id.Imgm2);
        btn_payrecopt = (Button)findViewById(R.id.btn_payrecopt);
        banknamearray = new ArrayList<>();
        methodarray = new ArrayList<>();
        bank();
        methodlist();
        Imgm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhotoFromCamera();
            }
        });

        Imgm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });
        btn_payrecopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        requestStoragePermission();
        balance = (TextView)findViewById(R.id.balance);
        Img2 = (ImageView)findViewById(R.id.Img2);
        Logindata logindata = SharedPrefManagerLogin.getInstance(getApplicationContext()).getLogindata();
        String name = String.valueOf(logindata.getName());
        String blance = String.valueOf(logindata.getBalance());
        balance.setText(blance);
        Img = (ImageView)findViewById(R.id.Img);
        Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NavigationDrawerActivity.class);
                startActivity(intent);
            }
        });
        Img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void bank(){
        Call<Banklist> call = ApiClientToken.getInstance().banklist(
        );
        call.enqueue(new Callback<Banklist>() {
            @Override
            public void onResponse(Call<Banklist> call, retrofit2.Response<Banklist> response) {
                if (response.isSuccessful()){
                    String error = response.body().getError();
                    Log.e("response",""+response.body());
                    if (error.equals("false")){
                        for (int i = 0; i <response.body().getData().getList().size(); i++) {
                            Listbankdatalist listdata =  response.body().getData().getList().get(i);
                            String bankid = listdata.getBankID();
                            String bankname = listdata.getBankName();
                            System.out.println("laveldata"+bankid+" "+bankname);
                            banknamearray.add(bankname);
                            ArrayAdapter bank= new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,banknamearray);
                            bank.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            bankId.setAdapter(bank);
                        }
                        bankId.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                banksId = response.body().getData().getList().get(position).getBankID();
                                banksname = response.body().getData().getList().get(position).getBankName();
                                Log.e("operatortype",banksId+" "+banksname);
                            }
                        });
                    }


                }
                else {
                    Log.e("DATA", " "+response.message());
                }

            }

            @Override
            public void onFailure(Call<Banklist> call, Throwable t) {

            }
        });
    }

    public void methodlist(){
        Call<MethodList> call = ApiClientToken.getInstance().methodlist(
        );
        call.enqueue(new Callback<MethodList>() {
            @Override
            public void onResponse(Call<MethodList> call, retrofit2.Response<MethodList> response) {
                if (response.isSuccessful()){
                    String error = response.body().getError();
                    Log.e("response",""+response.body());
                    if (error.equals("false")){
                        for (int i = 0; i <response.body().getData().getList().size(); i++) {
                            ListMethodListdata listdata =  response.body().getData().getList().get(i);
                            String name = listdata.getName();
                            System.out.println("laveldata"+name);
                            methodarray.add(name);
                            ArrayAdapter bank= new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,methodarray);
                            bank.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            tv_transferID.setAdapter(bank);
                        }
                        tv_transferID.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                methodname = response.body().getData().getList().get(position).getName();
                                Log.e("operatortype",banksId+" "+banksname);
                            }
                        });
                    }


                }
                else {
                    Log.e("DATA", " "+response.message());
                }

            }

            @Override
            public void onFailure(Call<MethodList> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("paypal", "in activity result");
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try {
                actualImage = FileUtil.from(getApplicationContext(), data.getData());
                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), Uri.fromFile(actualImage));
                fileImage = String.valueOf(actualImage);
//                image_show.setImageBitmap(bitmap);
                Log.e("fileImage",fileImage);
                sp = getApplicationContext().getSharedPreferences(Consts.DATASAVE, MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("DATASAVE", String.valueOf(actualImage));
                file_extn = fileImage.substring(fileImage.lastIndexOf("/") + 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

        }
        if (requestCode == Image_Capture_Code) {
            if (resultCode == RESULT_OK) {
                bitmap = (Bitmap) data.getExtras().get("data");
             //   image_show.setImageBitmap(bitmap);
                Uri tempUri = getImageUri(getApplicationContext(), bitmap);
                File finalFile = new File(getRealPathFromURII(tempUri));
                //Log.e("IMGPATH"," "+finalFile);
                url = String.valueOf(finalFile);
                actualImage = finalFile;
                sp = getApplicationContext().getSharedPreferences(Consts.DATASAVE, MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("DATASAVE", String.valueOf(actualImage)); //chang vlue 1 for od not logout
                edit.commit();
                Log.e("path", String.valueOf(actualImage));
                String fileImage = String.valueOf(actualImage);
                file_extn = fileImage.substring(fileImage.lastIndexOf("/") + 1);
            } else if (resultCode == RESULT_CANCELED) {

            }
        }
    }

    public void submit(){
        final String deposit = tv_depositId.getText().toString().trim();
        final String banktransfer = tv_bankresferanceID.getText().toString().trim();
        final String ifccode = tv_ifccodeID.getText().toString().trim();
        final String amount = tv_amountId.getText().toString().trim();
        final String remarks = tv_remarkssID.getText().toString().trim();
        if (TextUtils.isEmpty(deposit)) {
            tv_depositId.setError("Please enter Deposit");
            tv_depositId.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(banktransfer)) {
            tv_bankresferanceID.setError("Please enter Bank Transfer");
            tv_bankresferanceID.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(ifccode)) {
            tv_ifccodeID.setError("Please enter Ifccode");
            tv_ifccodeID.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(amount)) {
            tv_amountId.setError("Please enter Amount");
            tv_amountId.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(remarks)) {
            tv_remarkssID.setError("Please enter Remarks");
            tv_remarkssID.requestFocus();
            return;
        }
        sp = getApplicationContext().getSharedPreferences(Consts.DATASAVE, MODE_PRIVATE);
        String DATASAVE = sp.getString("DATASAVE","");
        System.out.println("Print"+DATASAVE+" "+banksId+methodname+" "
                +deposit+" "+banktransfer+" "+ifccode+" "+amount+" "+remarks);
        AndroidNetworking.upload(URL)
                .addMultipartFile("payment_image", new File(DATASAVE))
                .addMultipartParameter("bankID", banksId)
                .addMultipartParameter("method", methodname)
                .addMultipartParameter("dipo_bank",deposit)
                .addMultipartParameter("bankRefNo",banktransfer)
                .addMultipartParameter("ifscCode",ifccode)
                .addMultipartParameter("amount", amount)
                .addMultipartParameter("remark",remarks)
                .addHeaders("Authorization", "Bearer" + " " + SharedPrefManager.getInstance(getApplicationContext()).getKeyToken())
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
                            String error = obj.getString("error");
                            if (error.equals("false")){
                                Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
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

    public Uri getImageUri(Context Context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(Context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    public String getRealPathFromURII(Uri uri) {
        String path = "";
        if (getApplicationContext().getContentResolver() != null) {
            Cursor cursor = getApplicationContext().getContentResolver().query(uri, null, null, null, null);
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
        Cursor cursor = getApplicationContext().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();
        cursor = getApplicationContext().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            cursor.close();
            return path;
        }
        return null;
    }
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;
        if (ActivityCompat.shouldShowRequestPermissionRationale(PayinofflineActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

        }
        ActivityCompat.requestPermissions(PayinofflineActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Checking the request code of our request
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }
    @SuppressLint("UnsupportedChromeOsCameraSystemFeature")
    private void takePhotoFromCamera() {
        if (PayinofflineActivity.this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            PayinofflineActivity.this.startActivityForResult(intent, Image_Capture_Code);
        } else {

        }

    }
    private void showFileChooser() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);

    }
}
