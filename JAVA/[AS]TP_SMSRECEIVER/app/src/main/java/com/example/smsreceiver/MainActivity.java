package com.example.smsreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    SMSReceiver smsreceive;
    static TextView tx;
    RecyclerView recycler;
    static ListAdapter mon_adapter;
    FloatingActionButton gotoStat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx = (TextView) findViewById(R.id.textViewPanel);
        gotoStat = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        gotoStat.setOnClickListener(this);
        recycler = (RecyclerView) findViewById(R.id.list_sms);
        recycler.setHasFixedSize(true);
        LinearLayoutManager layManage = new LinearLayoutManager(this);
        recycler.setLayoutManager(layManage);
        // Explique comment marche l'interaction avec un Adapter
        mon_adapter = new ListAdapter();

        recycler.setAdapter(mon_adapter);

        Intent iSMS = new Intent(this, SMSService.class);
        iSMS.putExtra("type_intent", "Ceci est un service SMS");
        iSMS.getExtras().get("type_intent");

        // Demande à Android
        this.startService(iSMS);
        requestPermission();

    }

    public static void addToList(Intent sms) {
        String smsBody = (String) sms.getExtras().get("sms");
        String smsNum = (String) sms.getExtras().get("num");
        MainActivity.tx.setText(smsBody);
        MainActivity.mon_adapter.addItems(smsNum, smsBody);
        MainActivity.mon_adapter.notifyDataSetChanged();
    }

    private void requestPermission()
    {
        String[] perm = new String[]{Manifest.permission.RECEIVE_SMS};
        ActivityCompat.requestPermissions(this, perm, 23);
        System.out.println("Yop");
    }

    @Override
    public void onClick(View v) {
        Activity stat = new StatActivity();
        Intent iSMS = new Intent(this, SMSService.class);
        stat.startActivity(iSMS);
    }
}