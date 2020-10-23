package com.example.list;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class EditActivity extends AppCompatActivity {

    public static final int ADD = 1;
    public static final int EDIT = 2;
    public static final int SELECT = 3;
    public static final String POSITION = "POSITION";


    public static final String NAME = "NAME";
    public static final String PHONE = "PHONE";
    public static final String EMAIL = "EMAIL";
    public static final String PHOTO = "PHOTO";

    private EditText editName, editPhone, editEmail;
    private ImageView editPhoto;
    Uri photo;
    Intent result;
    int position;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initComponents();
    }

    private void initComponents() {
        initEditText();
        initEditPhoto();
        initIntent();
    }

    private void initEditPhoto() {
        editPhoto = findViewById(R.id.edit_photo);
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
            if(intent.getStringExtra(PHOTO) != null) {
                Glide.with(this).load(Uri.parse(intent.getStringExtra(PHOTO))).into(editPhoto);
            }
        }
    }

    private void initEditText() {
        editName = findViewById(R.id.edit_name);
        editPhone = findViewById(R.id.edit_phone);
        editEmail = findViewById(R.id.edit_email);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK  && data != null) {
            if (requestCode == SELECT){
                photo = data.getData();
                Glide.with(this).load(photo).into(editPhoto);
            }
        }
    }

    public void onSave(View view){
        result.putExtra(POSITION, position);
        result.putExtra(NAME, editName.getText().toString());
        result.putExtra(PHONE,  editPhone.getText().toString());
        result.putExtra(EMAIL, editEmail.getText().toString());
        result.putExtra(PHOTO, photo.toString());
        setResult(RESULT_OK, result);
        finish();
    }

    public void onSelect(View view){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT);
    }
}
