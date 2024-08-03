package com.example.dbtest.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.dbtest.dao.PessoaDao;
import com.example.dbtest.models.Pessoa;


@Database(entities = {Pessoa.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract PessoaDao pessoaDao();
}
