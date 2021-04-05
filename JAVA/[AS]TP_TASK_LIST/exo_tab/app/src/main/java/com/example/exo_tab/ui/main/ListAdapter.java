package com.example.exo_tab.ui.main;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public  class ListAdapter extends RecyclerView.Adapter {
    private  ArrayList<String> myList;
    private HashMap<Date, ArrayList<String>> mapOfTask;

    public ListAdapter() {

        this.myList = new ArrayList<String>();
        this.mapOfTask = new HashMap<Date, ArrayList<String>>();
    }

    public static class ElementsHolder extends RecyclerView.ViewHolder {

        public TextView txt;
        public ElementsHolder(TextView v) {
            super(v);
            txt = v;
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView v = (TextView) new TextView(parent.getContext());
        return new ElementsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ElementsHolder) holder).txt.setText((myList.get(position)));
    }

    @Override
    public int getItemCount() {
        return this.myList.size();
    }

    public void addItems(String val) {

        myList.add(val);
    }

    public void setListByDate(Date date) {
        ArrayList temp = this.mapOfTask.get(date);
        System.out.println(this.mapOfTask);
        if(temp == null)  myList.clear();
        else myList = temp;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addItemsToHashMap(Date d, String v) {
        if(!this.mapOfTask.containsKey(d)) {
            ArrayList<String> nList = new ArrayList<String>();
            nList.add(v);
            this.mapOfTask.put(d, nList);

        } else {
            if(!this.mapOfTask.get(d).contains(v)) this.mapOfTask.get(d).add(v);

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getItems() {

        this.myList.forEach((el) -> System.out.println(el));
    }
}

