package com.example.exo_tab.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class PageViewModel extends ViewModel {

    private MutableLiveData<String> valueList = new MutableLiveData<>();
    private MutableLiveData<Date> dateTask = new MutableLiveData<>();


    //public void setIndex(int index) {
        //mIndex.setValue(index);
   // }

    public LiveData<String> getTask() {
        return valueList;

    }
    public LiveData<Date> getDate() {
        return dateTask;
    }

    public void setTask(String value) {
        valueList.setValue(value);
    }

    public void setDate(Date d) {
        dateTask.setValue(d);
    }


}