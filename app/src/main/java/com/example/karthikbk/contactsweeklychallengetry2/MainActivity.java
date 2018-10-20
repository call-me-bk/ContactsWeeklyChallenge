package com.example.karthikbk.contactsweeklychallengetry2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper contactsDB;

    Button btnSave,btnShow;
    EditText etName,etPhone,etEmail,etExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsDB=new DatabaseHelper(this);
        etName=(EditText) findViewById(R.id.editName);
        etPhone=(EditText) findViewById(R.id.editPhone);
        etEmail=(EditText) findViewById(R.id.editEmail);
        etExtra=(EditText) findViewById(R.id.editExtra);
        btnSave=(Button) findViewById(R.id.buttonSave);
        btnShow=(Button) findViewById(R.id.buttonView);

        AddData();
        viewData();

    }

    public void AddData()
    {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etName.getText().toString();
                String phone=etPhone.getText().toString();
                String email=etEmail.getText().toString();
                String extra=etExtra.getText().toString();

                    boolean insertData = contactsDB.addData(name, phone, email, extra);

                if(insertData)
                {
                    Toast.makeText(MainActivity.this, "Contact details Saved Successfully", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Umm...there was a problem. try again", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void viewData(){
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,ViewListContents.class);
                startActivity(intent);
            }
        });
    }

}
