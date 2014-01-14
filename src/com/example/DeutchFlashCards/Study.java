package com.example.DeutchFlashCards;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.*;

/**
 * Created by Alexander on 10.01.14.
 */
public class Study extends Activity {
    TextView tvLesson,tvProgress,tvWord;
    Button btnTranslate,btnSkip;
    ImageView image;
    LinkedHashMap lessonmap;
    Lesson lesson;
    int position;
    String name,namePic;
    ArrayList<String> dew,ruw,difficultWordsRu,difficultWordsDe;
    ArrayList<Integer> picRes;
    int trueAnswers;
    int currentWordNumber = 0;
    int skippedWords = 0;
    public static int wordsCount = 0;
    boolean r1 = true,r2 = true;

    public void initialize()
    {
        tvLesson = (TextView)findViewById(R.id.tvLesson);
        tvProgress = (TextView)findViewById(R.id.tvProgress);
        tvWord = (TextView)findViewById(R.id.tvWord);
        btnTranslate = (Button)findViewById(R.id.btnTranslate);
        btnSkip = (Button)findViewById(R.id.btnSkip);
        image = (ImageView)findViewById(R.id.imageView);
    }
    public void insertWords()
    {
        difficultWordsRu = new ArrayList<String>();
        difficultWordsDe = new ArrayList<String>();
        switch (position)
        {
            case 0://transport
                ruw = new ArrayList<String>(Arrays.asList("автомобиль","метро","круизный лайнер","поезд","автобус","трамвай","самолёт","мотоцикл","паром","такси"));
                dew = new ArrayList<String>(Arrays.asList("das Auto","die Metro","die Kreuzfahrt","den Zug","den Bus","die Straßenbahn","das Flugzeug","das Mottorrad","die Fahreschiff","die Fahreschiff","das Taxi"));
                picRes = new ArrayList<Integer>(Arrays.asList(R.drawable.transport2,R.drawable.transport1,R.drawable.transport3,R.drawable.transport4,R.drawable.transport5,R.drawable.transport6,R.drawable.transport7,R.drawable.transport8,R.drawable.transport9,R.drawable.transport10));
                break;

            case 1://animals
                ruw = new ArrayList<String>(Arrays.asList("хомяк","орёл","паук","лошадь","обезьяна","собака","белка","кошка","слон","корова"));
                dew = new ArrayList<String>(Arrays.asList("der Hamster","der Adler","die Spinne","das Pferd","der Affe","der Hund","das Eichhörnchen","die Katze","der Elefant","die Kuh"));
                picRes = new ArrayList<Integer>(Arrays.asList(R.drawable.animals1,R.drawable.animals2,R.drawable.animals3,R.drawable.animals4,R.drawable.animals5,R.drawable.animals6,R.drawable.animals7,R.drawable.animals8,R.drawable.animals9,R.drawable.animals10));
                break;

            case 2://entertainment
                ruw = new ArrayList<String>(Arrays.asList("рояль","велосипед","удочка","сноуборд","труба","дайвинг","каток","боулинг","бильярд","гитара"));
                dew = new ArrayList<String>(Arrays.asList("das Klavier","das Fahrrad","die Angel","das Snowboard","die Trompete","das Tauchen","die Eisbahn","das Bowling","das Billard","die Gitarre"));
                picRes = new ArrayList<Integer>(Arrays.asList(R.drawable.entertainment1,R.drawable.entertainment2,R.drawable.entertainment3,R.drawable.entertainment4,R.drawable.entertainment5,R.drawable.entertainment6,R.drawable.entertainment7,R.drawable.entertainment8,R.drawable.entertainment9,R.drawable.entertainment10));
                break;

            case 3://sport
                ruw = new ArrayList<String>(Arrays.asList("дартс","бег","бейсбол","волейбол","плавание","футбол","бокс","хоккей","дзюдо","баскетбол"));
                dew = new ArrayList<String>(Arrays.asList("die Darts","der Lauf","der Baseball","der Volleyball","das Schwimmen","der Fußball","das Boxen","das Eishockey","das Judo","der Basketball"));
                picRes = new ArrayList<Integer>(Arrays.asList(R.drawable.sport1,R.drawable.sport2,R.drawable.sport3,R.drawable.sport4,R.drawable.sport5,R.drawable.sport6,R.drawable.sport7,R.drawable.sport8,R.drawable.sport9,R.drawable.sport10));
                break;

            case 4://bodyparts
                ruw = new ArrayList<String>(Arrays.asList("рука","уши","нос","волосы","пальцы","живот","губы","колени","ноги","спина"));
                dew = new ArrayList<String>(Arrays.asList("der Arm","das Ohr","die Nase","das Haare","die Finger","der Bauch","die Lippen","die Knie","das Bein","der Rücken"));
                picRes = new ArrayList<Integer>(Arrays.asList(R.drawable.bodyparts1,R.drawable.bodyparts2,R.drawable.bodyparts3,R.drawable.bodyparts4,R.drawable.bodyparts5,R.drawable.bodyparts6,R.drawable.bodyparts7,R.drawable.bodyparts8,R.drawable.bodyparts9,R.drawable.bodyparts10));
                break;

            case 5://clothes
                ruw = new ArrayList<String>(Arrays.asList("куртка","шуба","сапог","трусы","футболка","шапка","часы","туфли","джинсы","платье"));
                dew = new ArrayList<String>(Arrays.asList("die Jacke","der Pelz","der Schaftstiefel","der Badehose","das Trikot","die Mütze","die Armbanduhr","der Schuhe","die Jeans","der Kleidung"));
                picRes = new ArrayList<Integer>(Arrays.asList(R.drawable.clothes1,R.drawable.clothes2,R.drawable.clothes3,R.drawable.clothes4,R.drawable.clothes5,R.drawable.clothes6,R.drawable.clothes7,R.drawable.clothes8,R.drawable.clothes9,R.drawable.clothes10));
                break;

            case 6://food
                ruw = new ArrayList<String>(Arrays.asList("пицца","пиво","суп","пельмени","макароны","курица","салат","яблоко","ананас","апельсин"));
                dew = new ArrayList<String>(Arrays.asList("die Pizza","das Bier","die Suppe","die Pelmeni","die Makkaroni","das Huhn","das Salat","der Apfel","die Ananas","die Orange"));
                picRes = new ArrayList<Integer>(Arrays.asList(R.drawable.food1,R.drawable.food2,R.drawable.food3,R.drawable.food4,R.drawable.food5,R.drawable.food6,R.drawable.food7,R.drawable.food8,R.drawable.food9,R.drawable.food10));
                break;

            case 7://inhouse
                ruw = new ArrayList<String>(Arrays.asList("полка","диван","кровать","ванна","стул","шкаф","раковина","окно","люстра","стол"));
                dew = new ArrayList<String>(Arrays.asList("das Regal","das Sofa","das Bett","die Badewanne","der Stuhl","der Schrank","die Muschel","das Fenster","der Kronleuchter","der Tisch"));
                picRes = new ArrayList<Integer>(Arrays.asList(R.drawable.inhouse1,R.drawable.inhouse2,R.drawable.inhouse3,R.drawable.inhouse4,R.drawable.inhouse5,R.drawable.inhouse6,R.drawable.inhouse7,R.drawable.inhouse8,R.drawable.inhouse9,R.drawable.inhouse10));
                break;

            case 8://inrestaurant
                ruw = new ArrayList<String>(Arrays.asList("поднос","стакан","салфетка","рюмка","вилка","тарелка","бокал","ложка","нож","скатерть"));
                dew = new ArrayList<String>(Arrays.asList("das Tablett","das Glas","die Serviette","das Schnapsglas","die Gabel","der Teller","das Weinglas","der Löffel","das Messer","die Tischdecke"));
                picRes = new ArrayList<Integer>(Arrays.asList(R.drawable.inrestaurant1,R.drawable.inrestaurant2,R.drawable.inrestaurant3,R.drawable.inrestaurant4,R.drawable.inrestaurant5,R.drawable.inrestaurant6,R.drawable.inrestaurant7,R.drawable.inrestaurant8,R.drawable.inrestaurant9,R.drawable.inrestaurant10));
                break;

            case 9://feelings
                ruw = new ArrayList<String>(Arrays.asList("восторг","испуг","плач","отвращение","волнение","радоость","одиночество","неприязнь","смех","зависть"));
                dew = new ArrayList<String>(Arrays.asList("die Begeisterung","das Schreck","das Weinen","die Abneigung","die Aufregung","die Freude","die Einsamkeit","die Feindseligkeit","dsa Lachen","der Neid"));
                picRes = new ArrayList<Integer>(Arrays.asList(R.drawable.feelings1,R.drawable.feelings2,R.drawable.feelings3,R.drawable.feelings4,R.drawable.feelings5,R.drawable.feelings6,R.drawable.feelings7,R.drawable.feelings8,R.drawable.feelings9,R.drawable.feelings10));
                break;
        }
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_layout);
        initialize();
        Intent intent = getIntent();
        position = Integer.parseInt(intent.getStringExtra("POSITION_OF_LESSON"));
        name = intent.getStringExtra("NAME_OF_LESSON");
        namePic = intent.getStringExtra("NAME_OF_PICTURE");
        insertWords();
        lesson = new Lesson(ruw,dew,0,name,namePic);
        tvLesson.setText(name);
        tvWord.setText(ruw.get(currentWordNumber));
        tvProgress.setText("0/10");
        image.setImageResource(picRes.get(currentWordNumber));
        trueAnswers = 0;
    }

    public void onClickTranslateOrKnow(View v)
    {
        if(r1){
            r1 = false;
            Log.d("MyLog","2");
            if (!(tvWord.getText().charAt(0) == 'd'))
            {
                tvWord.setText(dew.get(currentWordNumber));
                btnTranslate.setText(R.string.btnKnow);
                btnTranslate.setBackgroundColor(Color.GREEN);
                btnSkip.setText(R.string.btnDontKnow);
                btnSkip.setBackgroundColor(Color.RED);
            }
            else
            {
                //Знали
                trueAnswers++;
                if (currentWordNumber + 1 < ruw.size())
                    nextWord();
                else
                    finishLesson();
            }
            r1 = true;
         }
    }

    public void onClickSkipOrDontKnow(View v)
    {
        if (r2)
        {
            r2 = false;
            if (!(tvWord.getText().charAt(0) == 'd'))
            {
                //skip
                skippedWords++;
                int index =  ruw.indexOf(tvWord.getText());
                //Log.d("MyLogs",String.valueOf(index));
                ruw.add(ruw.get(index));
                //Log.d("MyLogs",ruw.get(ruw.size() - 1));
                dew.add(dew.get(index));
                picRes.add(picRes.get(index));
                nextWord();
            }
            else
            {
                //не Знали
                difficultWordsRu.add(ruw.get(currentWordNumber));
                difficultWordsRu.add(dew.get(currentWordNumber));
                if (currentWordNumber + 1 < ruw.size())
                    nextWord();
                else
                    finishLesson();
            }
            r2 = true;
        }
    }

    public void nextWord()
    {
        wordsCount++;
        String number = String.valueOf(currentWordNumber - skippedWords + 1);
        tvProgress.setText(number + "/10");
        currentWordNumber++;
        tvWord.setText(ruw.get(currentWordNumber));
        image.setImageResource(picRes.get(currentWordNumber));
        btnTranslate.setText(R.string.btnTranslate);
        btnSkip.setText(R.string.btnSkip);
        btnTranslate.setBackgroundResource(android.R.drawable.progress_horizontal);
        btnSkip.setBackgroundResource(android.R.drawable.progress_horizontal);
    }

    public void finishLesson()
    {
        Intent intent = new Intent(this,Result.class);
        if (trueAnswers > 10)
            trueAnswers = 10;
        intent.putExtra("TRUE_ANSWERS",String.valueOf(trueAnswers));
        intent.putExtra("POSITION_OF_LESSON",String.valueOf(position));
        intent.putExtra("CATEGORY",name);
        startActivity(intent);
        this.finish();
    }
}