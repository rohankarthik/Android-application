package com.example.rohan.assigntwo;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class DateActivity extends ActionBarActivity {

    final Context context = this;
    private TextView DisplayDate;
    private Button PickDate;
    private Button button;
    private DatePicker dpick;

    private int yeardp;
    private int monthdp;
    private int daydp;

    static final int DATE_DIALOG_ID = 0;

    private DatePickerDialog.OnDateSetListener DateSetListenerdp =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    yeardp = year;
                    monthdp = monthOfYear;
                    daydp = dayOfMonth;
                }
            };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        DisplayDate = (TextView) findViewById(R.id.displayDate);
        PickDate    = (Button) findViewById(R.id.pickDate);
        button       = (Button) findViewById(R.id.confirm);
        dpick        = (DatePicker)findViewById(R.id.datePicker);

        loadSPreferences();


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                alertDialogBuilder.setTitle("Confirm the Date?");
                alertDialogBuilder.setMessage("Click Yes to Save!").setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                String strdate = (monthdp+1)+"/"+daydp + "/" + yeardp;
                                savePreferences("storeDate",strdate);
                                DisplayDate.setText(new StringBuilder()
                                                .append(monthdp + 1).append("/")
                                                .append(daydp).append("/")
                                                .append(yeardp).append(" "));
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                 dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        PickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        final Calendar cal = Calendar.getInstance();
        yeardp = cal.get(Calendar.YEAR);
        monthdp = cal.get(Calendar.MONTH);
        daydp = cal.get(Calendar.DAY_OF_MONTH);

    }

    private void loadSPreferences() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences((this));
        String date = sp.getString("storeDate","Value");
        DisplayDate.setText(date);
    }

    private void savePreferences(String key, String value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,value);
        editor.commit();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        DateSetListenerdp,
                        yeardp, monthdp, daydp);
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_date, menu);
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
