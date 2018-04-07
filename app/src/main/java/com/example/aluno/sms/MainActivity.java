package com.example.aluno.sms;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button enviar;
    EditText num;
    EditText msm;
    private static final int PERMISSION_SEND_SMS=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enviar = (Button) findViewById(R.id.enviar);
        num = (EditText) findViewById(R.id.num);
        msm = (EditText) findViewById(R.id.msm);
        enviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                enviarSMS();
            }
        });
    }

    protected void enviarSMS() {
        String paraCel = num.getText().toString();
        String smsMsg = msm.getText().toString();
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_SEND_SMS);
            }
            else{
               SmsManager smsManager = SmsManager.getDefault();
               smsManager.sendTextMessage(paraCel, null, smsMsg, null, null);
               Toast.makeText(getApplicationContext(), "SMS enviado.", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Falha no envio do SMS", Toast.LENGTH_LONG).show();
            e.printStackTrace();

        }
    }
}
