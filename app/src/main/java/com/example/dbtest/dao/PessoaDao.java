package com.example.dbtest.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dbtest.models.Pessoa;

import java.util.List;

@Dao
public interface PessoaDao {
    @Insert
    void insertAll(Pessoa... pessoas);
    @Delete
    void delete(Pessoa pessoa);
    @Query("SELECT * FROM pessoa")
    List<Pessoa> getAllPessoas();
}
