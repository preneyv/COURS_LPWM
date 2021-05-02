package com.example.smsreceiver;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SMSService extends Service
{
    SMSReceiver smsreceive;
    static ArrayList<String> smsList;

    public void onCreate()
    {
        super.onCreate();


        // Cree mon broadcast receiver specifique aux SMS
        smsreceive = new SMSReceiver();
        smsList = new ArrayList<String>();
        // Expliquer ce a quoi notre receiver va "reagir"
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

        // Enregistrer le receiver sur notre filtre
        this.registerReceiver(smsreceive, filter);

    }

    public static void smsReceived(Intent sms)
    {

        //SMSService.smsList.add(sms.toString());
        MainActivity.addToList(sms);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

}
