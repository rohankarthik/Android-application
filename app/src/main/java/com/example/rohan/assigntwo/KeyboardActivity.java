package com.example.rohan.assigntwo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;


public class KeyboardActivity extends ActionBarActivity {
private EditText e1 , e2 , e3;
private Button b1;
private Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        e1 = (EditText)findViewById(R.id.editText);
        e2 = (EditText)findViewById(R.id.editText2);
        e3 = (EditText)findViewById(R.id.editText3);


        Intent intent = getIntent();
        String str = intent.getStringExtra("Msg");
        e1.setText(str);

        b1 = (Button)findViewById(R.id.button4);
        b2 = (Button)findViewById(R.id.button3);


        b1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                finish();
            }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                InputMethodManager manager;
                manager =(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(e1.getWindowToken(), 0);
                manager.hideSoftInputFromWindow(e2.getWindowToken(), 0);
                manager.hideSoftInputFromWindow(e3.getWindowToken(), 0);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_keyboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
