package com.example.anshulsharma.anshnotes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    ListView notes;
    static ArrayList<String> notesTitle=new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    SharedPreferences sharedPreferences;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        notesTitle.add("Add a new note.....");
        arrayAdapter.notifyDataSetChanged();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences=this.getSharedPreferences("package com.example.anshulsharma.anshnotes", Context.MODE_PRIVATE);
        HashSet<String>set=(HashSet<String>)sharedPreferences.getStringSet("note",null);


        notes=findViewById(R.id.notes);
        if(set.size()==0) {
            notesTitle.add("Add a new note.....");
        }else{
            notesTitle=new ArrayList(set);
        }

        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,notesTitle);
        notes.setAdapter(arrayAdapter);

        notes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are You Sure")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notesTitle.remove(position);
                                arrayAdapter.notifyDataSetChanged();

                                HashSet<String>set=new HashSet<>(MainActivity.notesTitle);
                                sharedPreferences.edit().putStringSet("note",set).apply();
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();


                return true;
            }
        });


        notes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(getApplicationContext(),notesSpace.class);
                intent.putExtra("placeNumber",position);
                startActivity(intent);
            }
        });

    }
}
