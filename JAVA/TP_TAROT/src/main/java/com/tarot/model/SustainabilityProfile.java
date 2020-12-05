package com.tarot.model;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class SustainabilityProfile {

    Gson sustainabilityProfile;

    public SustainabilityProfile(){
        sustainabilityProfile = new Gson();
    }

    public void objToJson(Player p)
    {
        try{
            FileWriter fw = new FileWriter("../saving/profile.json");
            sustainabilityProfile.toJson(p,fw);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player jsonToObj()
    {
        Player resetPlayer = null;

        try{
            Reader fr = new FileReader("../saving/profile.json");
            resetPlayer = sustainabilityProfile.fromJson(fr,Player.class);
        }catch(IOException e){
            e.printStackTrace();
        }
        return resetPlayer;
    }
}
