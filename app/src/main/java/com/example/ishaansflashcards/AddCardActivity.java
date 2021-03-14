package com.example.ishaansflashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        findViewById(R.id.myx).setVisibility(View.VISIBLE);
        findViewById(R.id.addingQ).setVisibility(View.INVISIBLE);
        findViewById(R.id.save).setVisibility(View.VISIBLE);

        findViewById(R.id.myx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("string1", ((EditText) findViewById(R.id.Question)).getText().toString());
                data.putExtra("string2", ((EditText) findViewById(R.id.Answer)).getText().toString());
                setResult(RESULT_OK, data);
                finish();
            }
        });



    }



}