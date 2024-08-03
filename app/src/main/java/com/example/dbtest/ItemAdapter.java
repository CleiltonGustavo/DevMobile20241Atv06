package com.example.dbtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dbtest.models.Pessoa;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItensViewHolder> {
    private List<Pessoa> pessoasList;

    public ItemAdapter(List<Pessoa> pessoasList) { this.pessoasList = pessoasList; } //Construtor do Adapter

    public ItensViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItensViewHolder(itemView);
    }

    public void onBindViewHolder(ItensViewHolder holder, int position){
        Pessoa pessoas = pessoasList.get(position); //Pego um item da minha lista de entradas, e abaixo associarei valores aos campos de texto da tela de itens com a ajuda dos gets
        holder.tv_nome.setText(pessoas.getNome());
        holder.tv_curso.setText(pessoas.getCurso());
        holder.tv_idade.setText(String.valueOf(pessoas.getIdade()));
    }

    public int getItemCount() { return pessoasList.size(); }

    public static class ItensViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_nome, tv_curso, tv_idade;

        public ItensViewHolder(View itemView) {
            super(itemView);

            tv_nome = itemView.findViewById(R.id.tv_nome);
            tv_curso = itemView.findViewById(R.id.tv_curso);
            tv_idade = itemView.findViewById(R.id.tv_idade);


            tv_nome.setOnClickListener(this);
            tv_curso.setOnClickListener(this);
            tv_idade.setOnClickListener(this);
        }

        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Você clicou no item: " + tv_nome.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}
