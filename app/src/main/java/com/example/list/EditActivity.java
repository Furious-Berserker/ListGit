package com.example.list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    public static final int ADD = 1;
    public static final int EDIT = 2;
    public static final String POSITION = "POSITION";


    public static final String NAME = "NAME";
    public static final String PHONE = "PHONE";
    public static final String EMAIL = "EMAIL";

    private EditText editName, editPhone, editEmail;
    Intent result;
    int position;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initComponents();
    }

    private void initComponents() {
        initIntent();
        initEditText();
    }

    private void initIntent() {
        result = new Intent();
        setResult(RESULT_CANCELED, result);
        Intent intent = getIntent();
        if (intent.getExtras() == null){
            position = -1;
        } else {
            position = intent.getIntExtra(POSITION, -1);
            editName.setText(intent.getStringExtra(NAME));
            editPhone.setText(intent.getStringExtra(PHONE));
            editEmail.setText(intent.getStringExtra(EMAIL));

        }
    }

    private void initEditText() {
        editName = findViewById(R.id.edit_name);
        editPhone = findViewById(R.id.edit_phone);
        editEmail = findViewById(R.id.edit_email);
    }

    public void onSave(View view){
        result.putExtra(POSITION, position);
        result.putExtra(NAME, editName.getText().toString());
        result.putExtra(PHONE,  editPhone.getText().toString());
        result.putExtra(EMAIL, editEmail.getText().toString());
        setResult(RESULT_OK, result);
        finish();
    }

}
