package com.example.DeutchFlashCards;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.yandex.metrica.Counter;

import java.util.*;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    Lesson l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
    int full,empty;
    ListView lvSimple;
    final int easy = 1, normal = 2, hard = 3;
    ArrayList<Lesson> lessons;
    DB db;
    SimpleCursorAdapter scAdapter;
    Cursor cursor;

    public void prepareDataBase()
    {
        db = new DB(this);
        db.open();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        prepareDataBase();

        Counter.initialize(getApplicationContext());
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Counter.sharedInstance().reportEvent("Слов просмотрено: " + Study.wordsCount);
                Counter.sharedInstance().sendEventsBuffer();
                Study.wordsCount = 0;
            }
        }, 600L * 1500, 600L * 1500);
        //inserting data in lessons


        lessons = new ArrayList<Lesson>();

        l1 = new Lesson(easy, "транспорт","transport");
        l2 = new Lesson(easy, "животные","animals");
        l3 = new Lesson(easy, "развлечения","entertainment");
        l4 = new Lesson(easy, "спорт","sport");

        l5 = new Lesson(normal, "части тела","bodyparts");
        l6 = new Lesson(normal, "одежда","clothes");
        l7 = new Lesson(normal, "еда","food");
        l8 = new Lesson(normal, "в доме","inhouse");

        l9 = new Lesson(hard, "в ресторане","inrestaurant");
        l10 = new Lesson(hard, "чувства","feelings");

        lessons.add(l1);lessons.add(l2);lessons.add(l3);lessons.add(l4);lessons.add(l5);lessons.add(l6);lessons.add(l7);lessons.add(l8);lessons.add(l9);lessons.add(l10);

        //заполняем адаптер и делаем Listview
        full = R.drawable.fullstar;
        empty = R.drawable.emptystar;
        String[] texts = new String[lessons.size()];
        String[] progress = new String[lessons.size()];
        int[] level = new int[lessons.size()];
        for (int i = 0; i < lessons.size(); i++)
        {
            texts[i] = lessons.get(i).name;
            level[i] = lessons.get(i).level;
            progress[i]  = db.getRec(i);
        }
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(texts.length);
        Map<String, Object> m;
        for (int i = 0; i < texts.length; i++) {
            m = new HashMap<String, Object>();
            m.put("text", texts[i]);
            m.put("lastprogress", progress[i]);
            switch (level[i])
            {
                case easy:
                    m.put("image1",empty);m.put("image2",empty);m.put("image3",full); break;
                case normal:
                    m.put("image1",empty);m.put("image2",full);m.put("image3",full); break;
                case hard:
                    m.put("image1",full);m.put("image2",full);m.put("image3",full); break;
            }
            data.add(m);
        }
        String[] from = {"text","lastprogress","image1","image2","image3"};
        int[] to = {R.id.text,R.id.txtLastResult,R.id.image1,R.id.image2,R.id.image3};
        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.item,from, to);
        lvSimple = (ListView) findViewById(R.id.listView);
        lvSimple.setAdapter(sAdapter);
        //
        lvSimple.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                db.close();
                Intent intent = new Intent(view.getContext(), Study.class);
                intent.putExtra("POSITION_OF_LESSON",String.valueOf(position));
                intent.putExtra("NAME_OF_LESSON",lessons.get(position).name);
                intent.putExtra("NAME_OF_PICTURE",lessons.get(position).namePic);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        prepareDataBase();
        //inserting data in lessons

        lessons = new ArrayList<Lesson>();

        l1 = new Lesson(easy, "транспорт","transport");
        l2 = new Lesson(easy, "животные","animals");
        l3 = new Lesson(easy, "развлечения","entertainment");
        l4 = new Lesson(easy, "спорт","sport");

        l5 = new Lesson(normal, "части тела","bodyparts");
        l6 = new Lesson(normal, "одежда","clothes");
        l7 = new Lesson(normal, "еда","food");
        l8 = new Lesson(normal, "в доме","inhouse");

        l9 = new Lesson(hard, "в ресторане","inrestaurant");
        l10 = new Lesson(hard, "чувства","feelings");

        lessons.add(l1);lessons.add(l2);lessons.add(l3);lessons.add(l4);lessons.add(l5);lessons.add(l6);lessons.add(l7);lessons.add(l8);lessons.add(l9);lessons.add(l10);

        //заполняем адаптер и делаем Listview
        full = R.drawable.fullstar;
        empty = R.drawable.emptystar;
        String[] texts = new String[lessons.size()];
        String[] progress = new String[lessons.size()];
        int[] level = new int[lessons.size()];
        for (int i = 0; i < lessons.size(); i++)
        {
            texts[i] = lessons.get(i).name;
            level[i] = lessons.get(i).level;
            progress[i]  = db.getRec(i);
        }
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(texts.length);
        Map<String, Object> m;
        for (int i = 0; i < texts.length; i++) {
            m = new HashMap<String, Object>();
            m.put("text", texts[i]);
            m.put("lastprogress", progress[i]);
            switch (level[i])
            {
                case easy:
                    m.put("image1",empty);m.put("image2",empty);m.put("image3",full); break;
                case normal:
                    m.put("image1",empty);m.put("image2",full);m.put("image3",full); break;
                case hard:
                    m.put("image1",full);m.put("image2",full);m.put("image3",full); break;
            }
            data.add(m);
        }
        String[] from = {"text","lastprogress","image1","image2","image3"};
        int[] to = {R.id.text,R.id.txtLastResult,R.id.image1,R.id.image2,R.id.image3};
        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.item,from, to);
        lvSimple = (ListView) findViewById(R.id.listView);
        lvSimple.setAdapter(sAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    @Override
    protected void onPause() {
        super.onDestroy();
        db.close();
    }

}
