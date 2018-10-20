package com.example.karthikbk.contactsweeklychallengetry2;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewListContents extends AppCompatActivity {
    DatabaseHelper contactsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_contents);

        ListView listView=(ListView)findViewById(R.id.list1);
        contactsDB=new DatabaseHelper(this);
        final ArrayList<String> theList=new ArrayList<>();
        final Cursor data=contactsDB.showData();

        if(data.getCount()==0)
        {
            Toast.makeText(ViewListContents.this, "The Database was empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            data.moveToFirst();
            while(data.moveToNext())
            {
                theList.add(data.getString(0));
                ListAdapter listAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
            }

        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewListContents.this);
                builder.setCancelable(true);
                builder.setTitle("You told this while saving the contact.");
                builder.setMessage(data.getString(3));
                builder.show();
            }
        });

    }
}
