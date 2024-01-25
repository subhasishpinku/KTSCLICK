package com.kits.kitsclick;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class Forgotpassword_Activity extends AppCompatActivity {
    EditText confromId;
    TextInputEditText passId;
    Button resetbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpass_activity);
        confromId = (EditText)findViewById(R.id.confromId);
        passId = (TextInputEditText)findViewById(R.id.passId);
        resetbutton = (Button)findViewById(R.id.resetbutton);
        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
