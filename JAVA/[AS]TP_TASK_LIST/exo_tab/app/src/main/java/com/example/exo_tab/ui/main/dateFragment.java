package com.example.exo_tab.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.exo_tab.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link dateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class dateFragment extends Fragment implements CalendarView.OnDateChangeListener {

    private PageViewModel pageViewModel;
    private static final String ARG_SECTION_NUMBER = "section_number";

    public dateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment dateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static dateFragment newInstance() {
        dateFragment fragment = new dateFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(requireActivity()).get(PageViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_date, container, false);
        final CalendarView calendar = root.findViewById(R.id.calendarView);

        calendar.setOnDateChangeListener(this);
        return root;
    }


    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date d = df.parse(dayOfMonth+"-"+(month+1)+"-"+year);
            if(pageViewModel.getDate().getValue() != d) pageViewModel.setDate(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}