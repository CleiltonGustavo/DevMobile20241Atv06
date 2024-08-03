package com.example.dbtest.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pessoa")
public class Pessoa {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "nome")
    public String nome;
    @ColumnInfo(name = "curso")
    public String curso;
    @ColumnInfo(name = "idade")
    public int idade;

    public Pessoa(String nome, String curso, int idade){
        this.nome = nome;
        this.curso = curso;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @NonNull
    @Override
    public String toString() {
        String nomeRetorno = this.nome + " | " + this.curso + " | " + this.idade;
        return nomeRetorno;
    }
}
