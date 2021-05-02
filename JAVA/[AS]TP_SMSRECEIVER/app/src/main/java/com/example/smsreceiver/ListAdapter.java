package com.example.smsreceiver;


import android.os.Build;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public  class ListAdapter extends RecyclerView.Adapter {
    private  ArrayList<String> myList;
    private HashMap<String, String> mapOfTask;

    public ListAdapter() {

        this.myList = new ArrayList<String>();
        this.mapOfTask = new HashMap<String, String>();
    }

    public static class ElementsHolder extends RecyclerView.ViewHolder {

        public TextView msg;
       // public TextView num;
        public ElementsHolder(TextView v) {
            super(v);
            msg = v;
            //num = n;
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView v = (TextView) new TextView(parent.getContext());
        //TextView n = (TextView) new TextView(parent.getContext());
        return new ElementsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ElementsHolder) holder).msg.setText((myList.get(position)));
        //((ElementsHolder) holder).num.setText((position));
    }

    @Override
    public int getItemCount() {
        return this.myList.size();
    }

    public void addItems(String num, String sms) {
        myList.add(sms+"\n"+num);
       // mapOfTask.put(num, sms);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getItems() {
        this.myList.forEach((el) -> System.out.println(el));
    }
}

