package com.app.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ScreenEdit extends AppCompatActivity {

    EditText name, phone, mail, birth;
    String id;
    Button save, close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_edit);

        name = (EditText) findViewById(R.id.SEeditText_name);
        phone = (EditText) findViewById(R.id.SEeditText_phone);
        mail = (EditText) findViewById(R.id.SEeditText_mail);
        birth = (EditText) findViewById(R.id.SEeditText_date);

        save = (Button) findViewById(R.id.SEbutton_save);
        close = (Button) findViewById(R.id.SEbutton_close);

        Intent it = getIntent();
        name.setText(it.getStringExtra("p_nome"));
        phone.setText(it.getStringExtra("p_phone"));
        mail.setText(it.getStringExtra("p_mail"));
        birth.setText(it.getStringExtra("p_date"));
        id = it.getStringExtra("p_id");
    }

    public void save(View v) {
        String sname, sphone, semail, sbirth;
        sname = name.getText().toString().trim();
        sphone = phone.getText().toString().trim();
        semail = mail.getText().toString().trim();
        sbirth = birth.getText().toString().trim();
        if(sname.isEmpty() || sphone.isEmpty() || semail.isEmpty() || sbirth.isEmpty()){
            Mensagem.show("Campos não podem estar vazios", this);
            return;
        }
        DB.edit(this, id, sname, sphone, semail, sbirth);
        Intent ScreenSearch = new Intent(this, com.app.agenda.ScreenSearch.class);
        startActivity(ScreenSearch);
    }

    public void close(View v) {
        this.finish();
    }
}