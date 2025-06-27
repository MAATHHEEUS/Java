package com.app.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;

public class ScreenSearch extends AppCompatActivity {

    EditText name, phone, mail, birth;
    Button previous, next, back, edit, delete;

    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_search);

        name = (EditText) findViewById(R.id.SSeditText_name);
        phone = (EditText) findViewById(R.id.SSeditText_phone);
        mail = (EditText) findViewById(R.id.SSeditText_mail);
        birth = (EditText) findViewById(R.id.SSeditText_date);

        previous = (Button) findViewById(R.id.SSbutton_previous);
        next = (Button) findViewById(R.id.SSbutton_next);
        back = (Button) findViewById(R.id.SSbutton_back);
        edit = (Button) findViewById(R.id.SSbutton_edit);
        delete = (Button) findViewById(R.id.SSbutton_delete);

        cursor = DB.query(this);

        if(cursor.getCount() != 0){
            mostrarDados();
        }else {
            Mensagem.show("Ainda não há contatos salvos na sua agenda.", this);
        }
    }

    public void close(View v) {
        Intent ScreenSave = new Intent(this, com.app.agenda.MainActivity.class);
        startActivity(ScreenSave);
    }

    public void next(View v) {
        try{
            if(cursor.getCount() != 0){
                cursor.moveToNext();
                mostrarDados();
            }else {
                Mensagem.show("Ainda não há contatos salvos na sua agenda.", this);
            }
        }catch (Exception e){
            if(cursor.isAfterLast()){
                cursor.moveToFirst();
                mostrarDados();
            }else {
                Mensagem.show("Erro: navegação com cursor.", this);
            }
        }
    }

    public void previous(View v) {
        try{
            if(cursor.getCount() != 0){
                cursor.moveToPrevious();
                mostrarDados();
            }else {
                Mensagem.show("Ainda não há contatos salvos na sua agenda.", this);
            }
        }catch (Exception e){
            if(cursor.isBeforeFirst()){
                cursor.moveToLast();
                mostrarDados();
            }else {
                Mensagem.show("Erro: navegação com cursor.", this);
            }
        }
    }
    public void mostrarDados() {
        int inome = cursor.getColumnIndex("nome");
        int iphone = cursor.getColumnIndex("phone");
        int imail = cursor.getColumnIndex("email");
        int ibirth = cursor.getColumnIndex("birth");
        name.setText(cursor.getString(inome));
        phone.setText(cursor.getString(iphone));
        mail.setText(cursor.getString(imail));
        birth.setText(cursor.getString(ibirth));
    }

    public void delete(View v){
        if(cursor.getCount() != 0){
            int iid = cursor.getColumnIndex("id");
            DB.delete(this, cursor.getString(iid));
            cursor = DB.query(this);

            if(cursor.getCount() != 0){
                mostrarDados();
            }else {
                Mensagem.show("Ainda não há contatos salvos na sua agenda.", this);
            }
        }else {
            Mensagem.show("Ainda não há contatos salvos na sua agenda.", this);
        }
    }

    public void edit(View v) {
        if(cursor.getCount() != 0){
            Intent ScreenEdit = new Intent(this, com.app.agenda.ScreenEdit.class);

            int iid = cursor.getColumnIndex("id");
            int inome = cursor.getColumnIndex("nome");
            int iphone = cursor.getColumnIndex("phone");
            int imail = cursor.getColumnIndex("email");
            int ibirth = cursor.getColumnIndex("birth");
            ScreenEdit.putExtra("p_id", cursor.getString(iid));
            ScreenEdit.putExtra("p_nome", cursor.getString(inome));
            ScreenEdit.putExtra("p_phone", cursor.getString(iphone));
            ScreenEdit.putExtra("p_mail", cursor.getString(imail));
            ScreenEdit.putExtra("p_date", cursor.getString(ibirth));

            startActivity(ScreenEdit);
        }else {
            Mensagem.show("Ainda não há contatos salvos na sua agenda.", this);
        }
    }
}