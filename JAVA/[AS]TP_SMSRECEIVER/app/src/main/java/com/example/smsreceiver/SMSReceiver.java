package com.example.smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver
{


    SMSService serv;
    public SMSReceiver() { super(); }

    public SMSReceiver(SMSService s)
    {
        super();
        serv = s;
    }


    @Override
    public void onReceive(Context context, Intent intent)
    {

        System.out.println("Message received !");
        Bundle msg = intent.getExtras();
        Object[] pdus = (Object []) msg.get("pdus");
        SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdus[0]);

        intent.putExtra("sms",sms.getMessageBody());
        intent.putExtra("num", sms.getDisplayOriginatingAddress());
        SMSService.smsReceived(intent);
    }

}
