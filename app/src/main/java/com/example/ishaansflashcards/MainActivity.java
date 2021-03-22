package com.example.ishaansflashcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> flashcardList;
    int index_curr = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        String s1 = getIntent().getStringExtra("string1");
        String s2 = getIntent().getStringExtra("string2");
        flashcardList = flashcardDatabase.getAllCards();

        //checking if list is empty
        if(flashcardList.size() > 0 && flashcardList != null) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(flashcardList.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(flashcardList.get(0).getAnswer());
        }
        //checking if list is empty

        findViewById(R.id.addingQ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 20);
            }
        });

        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);

            }
        });
        findViewById(R.id.myx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // make sure we have cards + increment
                if(flashcardList.size()  == 0) {
                    return;
                }
                index_curr++;
                // no index error
                if(index_curr >= flashcardList.size()) {
                    index_curr = 0;
                }
                // set data
                flashcardList = flashcardDatabase.getAllCards();
                Flashcard flashcard = flashcardList.get(index_curr);
                ((TextView) findViewById(R.id.flashcard_question)).setText(flashcard.getQuestion());
                ((TextView) findViewById(R.id.flashcard_answer)).setText(flashcard.getAnswer());
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(resultCode == RESULT_OK) {
                String string1 = data.getExtras().getString("string1");
                String string2 = data.getExtras().getString("string2");
                ((TextView) findViewById(R.id.flashcard_question)).setText(string1);
                ((TextView) findViewById(R.id.flashcard_answer)).setText(string2);
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                flashcardDatabase.insertCard(new Flashcard(string1, string2));
                flashcardList = flashcardDatabase.getAllCards();


            }


    }
}