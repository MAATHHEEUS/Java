package com.app.agenda;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText name, phone, mail, birth;
    Button save, search, close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.editText_name);
        phone = (EditText) findViewById(R.id.editText_phone);
        mail = (EditText) findViewById(R.id.editText_mail);
        birth = (EditText) findViewById(R.id.editText_date);

        save = (Button) findViewById(R.id.button_save);
        search = (Button) findViewById(R.id.button_search);
        close = (Button) findViewById(R.id.button_close);
    }

    public void insert(View v) {
        String sname, sphone, semail, sbirth;
        sname = name.getText().toString().trim();
        sphone = phone.getText().toString().trim();
        semail = mail.getText().toString().trim();
        sbirth = birth.getText().toString().trim();
        if(sname.isEmpty() || sphone.isEmpty() || semail.isEmpty() || sbirth.isEmpty()){
            Mensagem.show("Campos não podem estar vazios", this);
            return;
        }
        DB.insert(this, sname, sphone, semail, sbirth);
        name.setText(null);
        phone.setText(null);
        mail.setText(null);
        birth.setText(null);
    }

    public void openSearch(View v) {
        Intent ScreenSearch = new Intent(this, com.app.agenda.ScreenSearch.class);
        startActivity(ScreenSearch);
    }

    public void close(View v) {
        finishAffinity();
    }
}