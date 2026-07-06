package controller;

import android.content.Context;
import android.util.Patterns;
import android.widget.Toast;

import java.util.ArrayList;

import dao.AvaliacaoDAO;
import dao.RestauranteDAO;
import dao.UsuarioDAO;
import model.Avaliacao;
import model.Restaurante;
import model.Usuario;


public class AvaliacaoController {
    private Context context;
    private RestauranteDAO restauranteDAO;
    private UsuarioDAO usuarioDAO;
    private AvaliacaoDAO avaliacaoDAO;

    //METODO CONSTRUTOR
    public AvaliacaoController(Context context){
        this.context = context;
        this.avaliacaoDAO = new AvaliacaoDAO(context);
    }

    public boolean inserir(Avaliacao avaliacao){
        //IMPLEMENTAR AS REGRAS DE NEGOCIO

        boolean result = avaliacaoDAO.inserir(avaliacao);
        return result;

    }

    public boolean alterar(Avaliacao avaliacao){
        //IMPLEMENTAR AS REGRAS DE NEGOCIO

        boolean result = avaliacaoDAO.alterar(avaliacao);
        return result;
    }

    public boolean excluir(Avaliacao avaliacao){
        return avaliacaoDAO.deletar(avaliacao);
    }

    public ArrayList<Restaurante> listarAvaliacoes(Restaurante restaurante){
        return avaliacaoDAO.listarAvaliacoes(restaurante);
    }

}
