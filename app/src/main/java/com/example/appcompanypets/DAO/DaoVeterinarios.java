package com.example.appcompanypets.DAO;

// herdando da classe sqlite...
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.appcompanypets.DTO.DtoVeterinarios;

import java.util.ArrayList;

public class DaoVeterinarios extends SQLiteOpenHelper
{
    private final String TABELA = "TB_CONTATO";

    public DaoVeterinarios(@Nullable Context context)
    {
        super(context, "DB_CONTATO", null, 1);
    }

    // neste primeiro método colocamos a criação de nossa tabela.
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String comando = "CREATE TABLE " + TABELA + "(" +
                "ID INTEGER PRIMARY KEY," +
                "NOME VARCHAR(100) NOT NULL," +
                "EMAIL VARCHAR(50) NOT NULL," +
                "TELEFONE VARCHAR(10) NOT NULL," +
                "DS_IMAGEM VARCHAR(120) NOT NULL)";
        db.execSQL(comando);
        comando = "INSERT INTO "+TABELA+" VALUES" +
                "(1, 'Marcia Ribeiro', 'marcia.beirosantos@gmail.com', '1140028922', 'https://appcompanypetsapi.000webhostapp.com/Veterinarios/2.png')," +
                "(2, 'Domingues Carvalho', 'carvalhodomingues@outlook.com', '1143235678', 'https://appcompanypetsapi.000webhostapp.com/Veterinarios/22.png')," +
                "(3, 'Michele Gomes', 'michel.go@yahooo.com', '1148728923', 'https://appcompanypetsapi.000webhostapp.com/Veterinarios/4.png')," +
                "(4, 'Aline Sthepanie', 'sthepanie.Al@gmail.com', '1543889038', 'https://appcompanypetsapi.000webhostapp.com/Veterinarios/3.png')," +
                "(4, 'Camila Cristina', 'camilacrristina@gmail.com', '1543889422', 'https://appcompanypetsapi.000webhostapp.com/Veterinarios/8.png')";
        db.execSQL(comando);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    public ArrayList<DtoVeterinarios> consultarTodos()
    {
        String comando = "SELECT * FROM " + TABELA;
        Cursor cursor = getReadableDatabase().rawQuery(comando, null);
        ArrayList<DtoVeterinarios> arrayListContato = new ArrayList<>();

        while(cursor.moveToNext())
        {
            DtoVeterinarios dtoContato = new DtoVeterinarios();
            dtoContato.setId(cursor.getInt(0));
            dtoContato.setNome(cursor.getString(1));
            dtoContato.setEmail(cursor.getString(2));
            dtoContato.setTelefone(cursor.getString(3));
            dtoContato.setDs_Imagem(cursor.getString(4));

            arrayListContato.add(dtoContato);
        }
        return arrayListContato;
    }
}