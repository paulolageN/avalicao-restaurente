package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityDetalhesDoRestauranteBinding;

import java.util.ArrayList;

import adapter.AvaliacaoAdapter;
import adapter.AvaliacaoAdapter;
import adapter.RestauranteAdapter;
import controller.AvaliacaoController;
import controller.RestauranteController;
import model.Avaliacao;
import model.Restaurante;

public class DetalhesDoRestauranteActivity extends AppCompatActivity {

    ActivityDetalhesDoRestauranteBinding binding;
    AvaliacaoController controller;
    ArrayList<Avaliacao> avaliacoes;
    Restaurante restaurante;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetalhesDoRestauranteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        controller = new AvaliacaoController(this);
        restaurante = (Restaurante) getIntent().getSerializableExtra("restaurante");
        avaliacoes = controller.listarAvaliacoes(restaurante);

        binding.txtNomeRestaurante.setText("Nome: "+restaurante.getNomeRestaurante());
        binding.txtEnderecoRestaurante.setText("Endereco: "+restaurante.getEnderecoRestaurante());
        binding.txtTelefone.setText("Telefone: "+restaurante.getTelefoneRestaurante());
        binding.txtDescricaoRestaurante.setText("Descricao: "+restaurante.getDescricaoRestaurnate());
        binding.txtHorarioRestaurante.setText("Horario de funcionamento: "+restaurante.getHorarioFuncionamento());

        AvaliacaoAdapter adapter = new AvaliacaoAdapter(this, avaliacoes);
        binding.Avaliacoes.setAdapter(adapter);

        binding.imgVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalhesDoRestauranteActivity.this, ListarRestaurantesActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}