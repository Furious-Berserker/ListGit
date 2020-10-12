package com.example.list;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private List<Person> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        Person person = new Person();
        person.setName("Ivan ivanov");
        person.setPhone("+79246547821");
        person.setEmail("ivan@mail.ru");
        list.add(person);

        adapter = new ListAdapter(this, list);
        recyclerView = findViewById(R.id.list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void onAdd(View view){
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        startActivityForResult(intent, EditActivity.ADD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getExtras() != null){
            if (requestCode == EditActivity.ADD){
                Person person = new Person();
                person.setName(data.getStringExtra(EditActivity.NAME));
                person.setPhone(data.getStringExtra(EditActivity.PHONE));
                person.setEmail(data.getStringExtra(EditActivity.EMAIL));

                list.add(person);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
