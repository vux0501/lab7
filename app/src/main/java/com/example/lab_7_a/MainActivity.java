package com.example.lab_7_a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    DataUser dataUser;
    Button add_btn;
    Button remove_btn;
    Button cancel_btn;
    EditText name_edt;
    ListView lvName;
    ArrayList nameList;
    ArrayAdapter adapter;
    ArrayList idList;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataUser = new DataUser(this,"userdb.sqlite",null,1);

        nameList = new ArrayList();
        idList = new ArrayList();

        name_edt = findViewById(R.id.name_edt);
        add_btn = findViewById(R.id.add_btn);
        remove_btn = findViewById(R.id.remove_btn);
        cancel_btn = findViewById(R.id.cancel_btn);
        lvName = findViewById(R.id.name_lv);

        getNameList();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,nameList);
        lvName.setAdapter(adapter);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataUser.addUser(new User(name_edt.getText().toString()));
                getNameList();
                adapter.notifyDataSetChanged();
                name_edt.setText("");
            }
        });

        remove_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataUser.removeUser((int)idList.get(index));
                getNameList();
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Thanh cong",Toast.LENGTH_SHORT).show();

            }
        });

        lvName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
            }
        });

    }

    private ArrayList getNameList(){
        nameList.clear();
        idList.clear();
        for (Iterator iterator = dataUser.getAll().iterator(); iterator.hasNext(); ) {
            User user = (User)  iterator.next();

            nameList.add(user.getName());
            idList.add(user.getId());
            
        }
        return nameList;
    }
}