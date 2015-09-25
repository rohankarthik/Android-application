package com.example.rohan.assigntwo;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener ,ListFragmentActivity.ListSelectedListenerMain {

    private static final String[] items={"Home","Date Selection","Keypad","Deserts Selection"};
    ListFragmentActivity resultFragment;
    private EditText editfield;
    private TextView tvmain;
    private Spinner spin;
    int newone;
    private Button go;
    int itemno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editfield = (EditText) findViewById(R.id.editText4);
        spin = (Spinner) findViewById(R.id.spinner);
        go = (Button)findViewById(R.id.button);

        spin.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        go.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                if(itemno == 1)
                {
                    Intent i = new Intent(getApplicationContext(), DateActivity.class);
                    startActivity(i);
                }
                if(itemno == 2)
                {
                    Intent i = new Intent(getApplicationContext(), KeyboardActivity.class);
                    i.putExtra("Msg",editfield.getText().toString());
                    startActivity(i);
                }
                if(itemno == 3)
                {
                    Intent i = new Intent(getApplicationContext(), TestActivity.class);
                    startActivity(i);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        switch (id) {
            case R.id.Dateid:
                Intent i = new Intent(getApplicationContext(), DateActivity.class);
                startActivity(i);
                break;

            case R.id.keyid:
                i = new Intent(getApplicationContext(), KeyboardActivity.class);
                startActivity(i);
                break;

            case R.id.listid:
                i = new Intent(getApplicationContext(), TestActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
          return  true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if(parent.getItemAtPosition(pos).toString() == "Date Selection")
        {
            itemno = 1;
        }

        if(parent.getItemAtPosition(pos).toString() == "Keypad")
        {
            itemno = 2;
        }
        if(parent.getItemAtPosition(pos).toString() == "Deserts Selection")
        {
            itemno = 3;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onListSelectedMain(int newItem) {
        Toast.makeText(this, "Main Activity ", Toast.LENGTH_LONG).show();


    }
}
