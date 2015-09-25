package com.example.rohan.assigntwo;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ListFragmentActivity extends Fragment {
    private ListView lv;
    private Activity activity;

    public interface ListSelectedListenerTest {
        public void onListSelectedTest(int newItem);
    }

    public interface ListSelectedListenerMain {
        public void onListSelectedMain(int newItem);
    }


    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        this.activity = getActivity();
    }

    String[] deserts = new String[] {
            "Cupcake",
            "Donut",
            "Gingerbread",
            "Ice Cream",
            "Jelly Bean"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.frag_layout, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1,deserts);

        final ListView lv = (ListView)rootView.findViewById(R.id.list);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {

                for (int j = 0; j < adapterView.getChildCount(); j++)
                    adapterView.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);
                view.setBackgroundColor(Color.LTGRAY);

                Toast.makeText(activity, "In Fragment ", Toast.LENGTH_LONG).show();
                try{
                    ((ListSelectedListenerMain) activity).onListSelectedMain(i);
                }catch (ClassCastException cce){

                }
            }

        });
        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Desert Description");
        menu.add(0, v.getId(), 0, "Platform");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="Platform"){
            Toast.makeText(activity,"Android",Toast.LENGTH_LONG).show();
        }
        else{
            return false;
        }
        return true;
    }

}
