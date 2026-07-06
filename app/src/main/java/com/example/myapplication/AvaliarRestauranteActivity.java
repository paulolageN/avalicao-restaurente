package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityAvaliarRestauranteBinding;

import controller.AvaliacaoController;
import controller.RestauranteController;
import model.Avaliacao;
import model.Restaurante;
import model.Usuario;

public class AvaliarRestauranteActivity extends AppCompatActivity {
    ActivityAvaliarRestauranteBinding binding;
    RestauranteController restauranteController;
    AvaliacaoController avaliacaoController;
    Restaurante restaurante;
    Avaliacao avaliacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAvaliarRestauranteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        restauranteController = new RestauranteController(AvaliarRestauranteActivity.this);
        restaurante = (Restaurante) getIntent().getSerializableExtra("restaurante");
        binding.nomeRestauranteAvaliacao.setText(restaurante.getNomeRestaurante());
        binding.imgVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AvaliarRestauranteActivity.this, ListarRestaurantesActivity.class);
                startActivity(intent);
                finish();
            }
        });
        binding.btnAvaliarRestaurante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avaliacao = new Avaliacao();
                avaliacao.setTextoAvaliacao(binding.editAvalicaoRestaurante.getText().toString());
                avaliacao.setPontosAvaliacao(binding.ratingBar.getNumStars());

                if(avaliacaoController.inserir(avaliacao)){
                    Toast.makeText(AvaliarRestauranteActivity.this,"Sua avaliacao foi realizada com sucesso!", Toast.LENGTH_LONG).show();
                    //Chamar a proxima tela principal
                    Intent intent = new Intent(AvaliarRestauranteActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(AvaliarRestauranteActivity.this,"Erro ao realizar a sua avaliacao!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}