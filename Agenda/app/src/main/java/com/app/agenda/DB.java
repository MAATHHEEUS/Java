package com.app.agenda;


import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.ContentValues;
import android.content.ContextWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

public class DB {
    static SQLiteDatabase db = null;
    static Cursor cursor = null;

    public static void dropDB(Activity act){
        try {
            ContextWrapper cw = new ContextWrapper(act);
            cw.deleteDatabase("Agenda");
        } catch (Exception e){
            Mensagem.show("Erro: drop do banco.", act);
        }
    }

    public static void initDB(Activity act){
        try {
            ContextWrapper cw = new ContextWrapper(act);
            db = cw.openOrCreateDatabase("Agenda", MODE_PRIVATE, null);
        } catch (Exception e){
            Mensagem.show("Erro: criação do banco.", act);
        }
    }

    public static void initTable(Activity act){
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS contatos (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, phone TEXT, email TEXT, birth TEXT);");
        } catch (Exception e){
            Mensagem.show("Erro: criação da tabela.", act);
        }
    }

    public static void closeDB(){
        db.close();
    }

    public static void insert(Activity act, String snome, String sphone, String smail, String sbirth) {
        try {
            initDB(act);
            db.execSQL("INSERT INTO contatos (nome, phone, email, birth) VALUES ('"+snome+"', '"+sphone+"', '"+smail+"', '"+sbirth+"');");
        } catch (Exception e){
            Mensagem.show("Erro: inserir contato.", act);
        }finally {
            Mensagem.show("Contato inserido com sucesso.", act);
            closeDB();
        }
    }

    public static void delete(Activity act, String id){
        try{
            initDB(act);
            db.delete(
                    "contatos",
                    "id = ?",
                    new String[]{id}
            );
        } catch (Exception e){
            Mensagem.show("Erro: deletar contato.", act);
        }finally {
            Mensagem.show("Contato excluído com sucesso.", act);
            closeDB();
        }
    };

    public static void edit(Activity act, String id, String snome, String sphone, String smail, String sbirth) {
        try{
            initDB(act);
            ContentValues cv = new ContentValues();
            cv.put("nome",snome);
            cv.put("phone",sphone);
            cv.put("email",smail);
            cv.put("birth",sbirth);
            db.update(
                    "contatos",
                    cv,
                    "id = ?",
                    new String[]{id}
            );
        }catch (Exception e) {
            Mensagem.show("Erro: editar contato.", act);
        } finally {
            closeDB();
        }
    }

    public static Cursor query(Activity act) {
        initDB(act);
        cursor = db.query(
                "contatos",
                new String[]{"id", "nome", "phone", "email", "birth"},
                null,
                null,
                null,
                null,
                "nome",
                null
        );
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
        }
        closeDB();
        return cursor;
    }

}
