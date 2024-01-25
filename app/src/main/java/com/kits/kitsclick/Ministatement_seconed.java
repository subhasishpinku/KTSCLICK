package com.kits.kitsclick;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import com.kits.kitsclick.setget.Banksetget;
import com.kits.kitsclick.setget.Listbankdata;
import com.kits.kitsclick.setget.Listdevice;
import com.kits.kitsclick.setget.Logindata;
import com.kits.kitsclick.setget.Scannerdatalist;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class Ministatement_seconed extends AppCompatActivity {
    TextView balance;
    ImageView Img,Img2;
    Button btn_next;
    AppCompatAutoCompleteTextView bankId;
    ArrayList<String> banknamearray;
    String bankname,info;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ministatement_activity_seconedadher);
        TextView textView = (TextView)findViewById(R.id.tram);
        SpannableString content = new SpannableString(" Terms & Condition");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView.setText(content);
        balance = (TextView)findViewById(R.id.balance);
        btn_next = (Button)findViewById(R.id.btn_next);
        Img = (ImageView)findViewById(R.id.Img);
        Img2 = (ImageView)findViewById(R.id.Img2);
        bankId = (AppCompatAutoCompleteTextView)findViewById(R.id.bankId);
        Logindata logindata = SharedPrefManagerLogin.getInstance(getApplicationContext()).getLogindata();
        String name = String.valueOf(logindata.getName());
        String blance = String.valueOf(logindata.getBalance());
        balance.setText(blance);
        Img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(),NavigationDrawerActivity.class);
               startActivity(intent);
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Ministatement_thirdactivityadher.class);
                startActivity(intent);
            }
        });
        banknamearray = new ArrayList<>();
        bank();
    }
    public void bank(){
        Call<Banksetget> call = ApiClientToken.getInstance().getaepsbank(
        );
        call.enqueue(new Callback<Banksetget>() {
            @Override
            public void onResponse(Call<Banksetget> call, retrofit2.Response<Banksetget> response) {
                if (response.isSuccessful()){
                    String error = response.body().getError();
                    Log.e("response",""+response.body());
                    if (error.equals("false")){
                        for (int i = 0; i <response.body().getData().getList().size(); i++) {
                            Listbankdata listdata =  response.body().getData().getList().get(i);
                            String bankname = listdata.getBankname();
                            String info = listdata.getIno();
                            System.out.println("laveldata"+bankname+" "+info);
                            banknamearray.add(bankname);
                            ArrayAdapter bank= new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,banknamearray);
                            bank.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            bankId.setAdapter(bank);
                        }
                        bankId.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                bankname = response.body().getData().getList().get(position).getBankname();
                                info = response.body().getData().getList().get(position).getIno();
                                Log.e("operatortype",bankname+" "+info);
                            }
                        });
                    }


                }
                else {
                    Log.e("DATA", " "+response.message());
                }

            }

            @Override
            public void onFailure(Call<Banksetget> call, Throwable t) {

            }
        });
    }
}
