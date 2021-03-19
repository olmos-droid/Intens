package com.example.edt53;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Call extends AppCompatActivity {
    private EditText textPhone;
    private ImageButton btCall;
    private final int PHONE_CALL_CODE = 100;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        textPhone = findViewById(R.id.textPhone);
        String phoneNumber = textPhone.getText().toString();
        btCall = findViewById(R.id.imageButtonCall);


        btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = textPhone.getText().toString();
                if (phoneNumber != null && !phoneNumber.isEmpty())
                {
                    //comprovar versio API23
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    {
                        //demanar permís (posterior a API23)
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                                PHONE_CALL_CODE);
                    } else
                    {
                        //No cal demanar permís (anterior a API23)
                        //Mirar a dins del manifest
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +
                                phoneNumber));
                        if (CheckPermission(Manifest.permission.CALL_PHONE))
                        {
                            startActivity(intent);
                        } else
                        {
                            Toast.makeText(Call.this, "No access", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else
                {
                    Toast.makeText(Call.this, "Insert a phone number", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
    private boolean CheckPermission(String permission) {
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED; //GRANTE = 0, DENIED = 1
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case PHONE_CALL_CODE:
                String permission = permissions[0];
                int result = grantResults[0];
                if (permission.equals(Manifest.permission.CALL_PHONE)) {
                    //Comprovar si ha estat acceptat o rebutjat el permis
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        //Ha concedit permisos
                        String phoneNumber = textPhone.getText().toString();
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +
                                phoneNumber));
                        startActivity(intent);
                    } else {
                        Toast.makeText(Call.this, "No tens permissos",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;

        }
    }
}