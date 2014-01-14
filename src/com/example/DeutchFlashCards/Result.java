package com.example.DeutchFlashCards;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by Alexander on 10.01.14.
 */
public class Result extends Activity {
    DB db;
    Lesson l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
    int full,empty;
    ListView lvSimple;
    final int easy = 1, normal = 2, hard = 3;
    ArrayList<Lesson> lessons;
    SimpleCursorAdapter scAdapter;
    Cursor cursor;


    public void prepareDataBase()
    {
        db = new DB(this);
        db.open();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);
        TextView tv = (TextView)findViewById(R.id.tvResult);
        Intent intent = getIntent();
        String s = intent.getStringExtra("TRUE_ANSWERS");
        int position = Integer.parseInt(intent.getStringExtra("POSITION_OF_LESSON"));
        tv.setText(s + " / 10");
        TextView categoryRes = (TextView)findViewById(R.id.tvCategoryResult);
        categoryRes.setText(intent.getStringExtra("CATEGORY"));
        prepareDataBase();
        if (s.equals("0"))
            db.updRec(String.valueOf(position+1),"0%");
        else
            db.updRec(String.valueOf(position+1),s + "0%");
    }

    public void onClickChooseLesson(View v)
    {
        db.close();
        this.finish();
    }

}