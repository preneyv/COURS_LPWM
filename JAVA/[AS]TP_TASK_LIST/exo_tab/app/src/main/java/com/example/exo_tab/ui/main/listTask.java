package com.example.exo_tab.ui.main;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.exo_tab.R;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link listTask#newInstance} factory method to
 * create an instance of this fragment.
 */
public class listTask extends Fragment {


    private PageViewModel pageViewModel;
    RecyclerView myRecicler;
    ListAdapter myList;

    public listTask() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment listTask.
     */
    // TODO: Rename and change types and number of parameters
    public static listTask newInstance() {
        listTask fragment = new listTask();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(requireActivity()).get(PageViewModel.class);
        myList = new ListAdapter();
        try {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Date d = df.parse("07-04-2021");
            myList.addItemsToHashMap(d, "Patate à acheter");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_list_task, container, false);
        myRecicler = root.findViewById(R.id.list);
        myRecicler.setHasFixedSize(true);
        LinearLayoutManager layManage = new LinearLayoutManager(this.getContext());
        myRecicler.setLayoutManager(layManage);

        myRecicler.setAdapter(myList);


        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView infoDate = (TextView) view.findViewById(R.id.dateInfo);
        pageViewModel.getTask().observe(getViewLifecycleOwner(), v -> {
            Date d = (Date) pageViewModel.getDate().getValue();
            myList.addItemsToHashMap(d, v);
        });
        pageViewModel.getDate().observe(getViewLifecycleOwner(), d -> {
            myList.setListByDate(d);
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String date = df.format(d);
            infoDate.setText("Tâches du "+date);
        });

        myList.notifyDataSetChanged();
    }

}