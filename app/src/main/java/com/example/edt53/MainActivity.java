package com.example.edt53;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnwebview, btnweb, btnMap, btnInsta, btnFacebook, btnMail, btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnwebview = findViewById(R.id.buttonWebView);
        btnweb = findViewById(R.id.buttonWeb);
        btnMap = findViewById(R.id.buttonMap);
        btnInsta = findViewById(R.id.buttonInstagram);
        btnFacebook = findViewById(R.id.buttonFacebook);
        btnMail = findViewById(R.id.buttonMail);
        btnCall = findViewById(R.id.buttonCall);

        btnwebview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), webview.class);
                startActivity(intent);
            }
        });
        btnweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //opcio 1
//                String url = "http://www.escoladeltreball.org";
//                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                startActivity(i);
                //opcio2
                String url = "http://www.escoladeltreball.org";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // per adreça con si busquesim
//                Uri location = Uri.parse("geo:0,0?q=1600+Escola+del+Treball,+Barcelona,+Espanya");
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
//                startActivity(mapIntent);

                // utilizant cordenades , la z es el zoom
                Uri location = Uri.parse("geo:41.3890464,2.1454964?z=17"); // z param is zoom level
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                startActivity(mapIntent);

            }
        });
        btnInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("instagram://edtbarcelona");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try
                {
                    startActivity(intent);
                } catch (ActivityNotFoundException e)
                {
                    //Si no troba la app Instagram, obrirà internet
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/edtbarcelona")));
                }


            }
        });
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlId = "fb://page/proactivaservice";
                String urlUrl = "https://www.facebook.com/proactivaservice";
                try
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlId)));
                } catch (ActivityNotFoundException e)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlUrl)));
                }

            }
        });
        btnMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                String[] TO = {"olmosmoog@gmail", "olmosescolatreball@gmail.com"};
                String[] CC = {"address1@gmail.com", "address2@gmail.com"};


                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Mail test app");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Aixo es un mail de proba de la asignatura de android");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "There is no email client installed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Call.class);
                startActivity(i);
            }
        });


    }
}