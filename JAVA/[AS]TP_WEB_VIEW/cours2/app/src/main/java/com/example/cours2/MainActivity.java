package com.example.cours2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tail_internet, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        Button btn = (Button) findViewById(R.id.useRandom);
        btn.setOnClickListener(this);
        this.requestPermission();

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        EditText txt = (EditText) findViewById(R.id.editUrl);
        final String textUrl = txt.getText().toString();
        WebView wv = (WebView) findViewById(R.id.maWebPage);
        wv.setWebViewClient(new WebViewClient());

        String dom = sp.getSelectedItem().toString();
        String url = shuffleWord(textUrl);
        String fullUrl = "http://www."+url+"."+dom;
        wv.loadUrl(fullUrl);
        
        Context ctx = getApplicationContext();
        Toast.makeText(ctx, "Bien reçu : "+fullUrl,3000).show();
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.INTERNET}, 200);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 200 : {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }else {

                }
            }
        }
    }

    private String shuffleWord(String word) {
        List<Character> characters = new ArrayList<Character>();
        for(char c:word.toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(word.length());
        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }
}