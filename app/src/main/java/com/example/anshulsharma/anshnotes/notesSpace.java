package com.example.anshulsharma.anshnotes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.util.HashSet;

public class notesSpace extends AppCompatActivity {

    EditText editText;
    Intent intent;
    SharedPreferences sharedPreferences;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sharedPreferences.edit().putString("newNote",editText.getText().toString()).apply();
        MainActivity.notesTitle.set(intent.getIntExtra("placeNumber",0),sharedPreferences.getString("newNote",""));
        MainActivity.arrayAdapter.notifyDataSetChanged();

        HashSet<String>set=new HashSet<>(MainActivity.notesTitle);
        sharedPreferences.edit().putStringSet("note",set).apply();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_space);

        editText=findViewById(R.id.addNotes);
        intent=getIntent();
        sharedPreferences=this.getSharedPreferences("package com.example.anshulsharma.anshnotes", Context.MODE_PRIVATE);
    }
}
