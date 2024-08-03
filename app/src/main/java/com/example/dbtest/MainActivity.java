package com.example.dbtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dbtest.database.AppDataBase;
import com.example.dbtest.models.Pessoa;
import com.example.dbtest.dao.PessoaDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Pessoa> pessoasList;
    private AppDataBase appDatabase;
    ItemAdapter itensAdapter;
    EditText et_nome, et_curso, et_idade;
    FloatingActionButton botaoAddPessoa;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nome = findViewById(R.id.et_nome);
        et_curso = findViewById(R.id.et_curso);
        et_idade = findViewById(R.id.et_idade);
        botaoAddPessoa = findViewById(R.id.botaoAddPessoa);

        recyclerView = findViewById(R.id.rv_itens);


        pessoasList = new ArrayList<>(); //Faço um array para as minhas pessoas

        //Inserindo manualmente
        Pessoa cleilton = new Pessoa("Cleilton", "Ciência da Computação", 23);
        Pessoa pedro = new Pessoa("Pedro", "Goleador do Mengão", 27);


        appDatabase = Room.databaseBuilder(this, AppDataBase.class, "db_pessoas")
                .enableMultiInstanceInvalidation()
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

                PessoaDao pessoaDao = appDatabase.pessoaDao();
                pessoaDao.insertAll(cleilton, pedro);

        List<Pessoa> pessoasDoBd = pessoaDao.getAllPessoas();
                for(Pessoa p : pessoasDoBd){
                    Log.d("sid-tag", p.toString());
                }

                //Continuando a povoação do RecyclerView:

                pessoasList.add(cleilton);
                pessoasList.add(pedro);
                //pessoasList.add(new Pessoa("Maria", "História", 51));



        botaoAddPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etNome;
                String etCurso;
                int etIdade;

                //Verifico se algum campo está vazio, se estiver, aviso ao usuário para preenche-lo, se não estiver prossigo inserindo o objeto Pessoa a partir dos dados dentro do banco.
                if (TextUtils.isEmpty(et_nome.getText()) || TextUtils.isEmpty(et_curso.getText()) || TextUtils.isEmpty(et_idade.getText())) {
                    Toast.makeText(v.getContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                } else {
                    etNome = et_nome.getText().toString();
                    etCurso = et_curso.getText().toString();
                    etIdade = Integer.parseInt(et_idade.getText().toString());
                    pessoasList.add(new Pessoa(etNome, etCurso, etIdade));
                    Toast.makeText(v.getContext(), "Pessoa " + et_nome.getText() + " adicionada!", Toast.LENGTH_SHORT).show();
                }
            }
        });

                itensAdapter = new ItemAdapter(pessoasList);

                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(itensAdapter);

                swipeToDelete();
    }

    private void swipeToDelete() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Pessoa pessoaParaDeletar = pessoasList.get(position);
                appDatabase.pessoaDao().delete(pessoaParaDeletar);
                pessoasList.remove(position);
                itensAdapter.notifyItemRemoved(position);
            }
        }).attachToRecyclerView(recyclerView);
    }

}