package com.example.DeutchFlashCards;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alexander on 09.01.14.
 */
public class Lesson{
    ArrayList<String> ruwords;
    ArrayList<String> dewords;
    int level;
    int success;
    String name;
    String namePic;
    Lesson(ArrayList<String> ruw,ArrayList<String> dew,int lev,String nam,String namPic)
    {
        this.ruwords = ruw;
        this.dewords = dew;
        this.level = lev;
        this.name = nam;
        this.namePic = namPic;
    }

    Lesson(int lev,String nam,String namPic)
    {
        this.level = lev;
        this.name = nam;
        this.namePic = namPic;
    }
}
