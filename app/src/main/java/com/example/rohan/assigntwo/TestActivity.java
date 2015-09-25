package com.example.rohan.assigntwo;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class TestActivity extends ActionBarActivity implements ListFragmentActivity.ListSelectedListenerTest    {
    ListFragmentActivity resultFragment;
    private Button tbutton;
    private int newItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        tbutton = (Button) findViewById(R.id.backbutton2);

        tbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }



    private void pushButton(int new1) {
        Bundle dataBundle = new Bundle();
        dataBundle.putInt("Ivalue", new1);
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.putExtras(dataBundle);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_test, menu);
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


    public void setnewItem(int newItem) {
        this.newItem = newItem;
    }

    @Override
    public void onListSelectedTest(int newItem) {
        Toast.makeText(this, "List Activity ", Toast.LENGTH_LONG).show();

    }
}
