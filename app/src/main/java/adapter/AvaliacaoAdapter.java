package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.databinding.ItemAvaliacaoBinding;

import java.util.ArrayList;

import model.Avaliacao;


public class AvaliacaoAdapter extends ArrayAdapter<Avaliacao> {

    public AvaliacaoAdapter(Context context, ArrayList<Avaliacao> avaliacoes) {
        super(context, 0, avaliacoes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Avaliacao avaliacao = getItem(position);

        ItemAvaliacaoBinding binding;

        if (convertView == null){
            binding = ItemAvaliacaoBinding.inflate(LayoutInflater.from(getContext()),parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else{
            binding = (ItemAvaliacaoBinding) convertView.getTag();
        }

        binding.txtUser.setText(avaliacao.getUsuario().getNomeUsuario());
        binding.txtAvaliacaoDoUser.setText(avaliacao.getTextoAvaliacao());
        binding.ratingBar3.setRating(avaliacao.getPontosAvaliacao());


        return convertView;

    }
}
