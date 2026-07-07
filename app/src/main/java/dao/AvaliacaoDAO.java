package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import database.BDHelper;
import model.Avaliacao;
import model.Restaurante;
import model.Usuario;

public class AvaliacaoDAO {
    // atributo para realizar operacoes no db
    private SQLiteDatabase database;
    // atributo da classe DBHelper
    private BDHelper dbHelper;

    public AvaliacaoDAO(Context context){
        dbHelper = new BDHelper(context);
    }

    // metodo para inserir dados na tabela avaliacao
    public boolean inserir(Avaliacao avaliacao){
        try {
            // abrir banco de dados para salvar dados na tabela avaliacao
            database = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            // nome do campo da tabela, valor
            values.put("idRestaurante", avaliacao.getRestaurante().getIdRestaurante());
            values.put("idUsuario", avaliacao.getUsuario().getIdUsuario());
            values.put("pontosAvaliacao",avaliacao.getPontosAvaliacao());
            values.put("textoAvaliacao",avaliacao.getTextoAvaliacao());


            long result = database.insert("avaliacaoRestaurante", null,values);

            // fechar a conexao com o banco de dados
            database.close();


            if (result == -1){
                Log.i("INFO DB", "ERRO AO INSERIR A AVALIACAO");
                return false;
            } else{
                Log.i("INFO DB", "SUCESSO AO INSERIR A AVALIACAO");
                return true;
            }
        } catch (Exception e){
            Log.e("AvaliacaoDAO", "erro ao inserir avaliacao"+e.getMessage());
            return false;
        }
    }

    // metodo para alterar dados na tabela avaliacao
    public boolean alterar(Avaliacao avaliacao){
        try {
            // abrir banco de dados para salvar dados na tabela avaliacao
            database = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            // nome do campo da tabel, valor
            values.put("pontosAvaliacao",avaliacao.getPontosAvaliacao());
            values.put("textoAvaliacao",avaliacao.getTextoAvaliacao());
            values.put("idRestaurante", avaliacao.getRestaurante().getIdRestaurante());
            values.put("idUsuario", avaliacao.getUsuario().getIdUsuario());

            long result = database.update("avaliacaoRestaurante", values, "idAvaliacao = ?", new String[]{String.valueOf(avaliacao.getIdAvaliacao())});

            // fechar a conexao com o banco de dados
            database.close();


            if (result <= 0){
                Log.i("INFO DB", "ERRO AO ALTERAR OS DADOS DA AVALIACAO");
                return false;
            } else{
                Log.i("INFO DB", "SUCESSO AO ALTERAR OS DADOS DA AVALIACAO");
                return true;
            }
        } catch (Exception e){
            Log.e("AvaliacaoDAO", "erro ao alterar os dados da avaliacao"+e.getMessage());
            return false;
        }
    }

    // metodo para excluir avaliacao
    public boolean deletar(Avaliacao avaliacao){
        try {
            // abrir banco de dados para deletar restaurante
            database = dbHelper.getWritableDatabase();
            // deleta um restaurante
            long result = database.delete("avaliacaoRestaurante", "idAvaliacao = ?", new String[]{String.valueOf(avaliacao.getIdAvaliacao())});

            // fechar a conexao com o banco de dados
            database.close();


            if (result == -1){
                Log.i("INFO DB", "ERRO AO DELETAR A AVALIACAO");
                return false;
            } else{
                Log.i("INFO DB", "SUCESSO AO DELETAR A AVALIACAO");
                return true;
            }
        } catch (Exception e){
            Log.e("AvaliacaoDAO", "erro ao DELETAR avaliacao"+e.getMessage());
            return false;
        }
    }

    // listar todos as avaliacoes
    public ArrayList<Avaliacao> listarAvaliacoes(Restaurante restaurante){
        ArrayList<Avaliacao> avaliacoes = new ArrayList<>();

        try {
            // abrir banco de dados para leitura
            database = dbHelper.getReadableDatabase();
            Cursor cursor = database.rawQuery(
                    "SELECT * FROM avaliacaoRestaurante " +
                            "INNER JOIN restaurante ON avaliacaoRestaurante.idRestaurante = restaurante.idRestaurante " +
                            "INNER JOIN usuario ON avaliacaoRestaurante.idUsuario = usuario.idUsuario " +
                            "WHERE avaliacaoRestaurante.idRestaurante = ?",
                    new String[]{String.valueOf(restaurante.getIdRestaurante())}
            );

            if (cursor.moveToFirst()){
                do {
                    Restaurante restauranteCusor  = new Restaurante();
                    restauranteCusor.setIdRestaurante(cursor.getInt(cursor.getColumnIndexOrThrow("idRestaurante")));
                    restauranteCusor.setNomeRestaurante(cursor.getString(cursor.getColumnIndexOrThrow("nomeRestaurante")));
                    restauranteCusor.setEnderecoRestaurante(cursor.getString(cursor.getColumnIndexOrThrow("enderecoRestaurante")));
                    restauranteCusor.setTelefoneRestaurante(cursor.getString(cursor.getColumnIndexOrThrow("telefoneRestaurante")));
                    restauranteCusor.setDescricaoRestaurante(cursor.getString(cursor.getColumnIndexOrThrow("descricaoRestaurante")));
                    restauranteCusor.setHorarioFuncionamento(cursor.getString(cursor.getColumnIndexOrThrow("horarioFuncionamento")));

                    Usuario usuarioCursor = new Usuario();
                    usuarioCursor.setIdUsuario(cursor.getInt(cursor.getColumnIndexOrThrow("idUsuario")));
                    usuarioCursor.setNomeUsuario(cursor.getString(cursor.getColumnIndexOrThrow("nomeUsuario")));
                    usuarioCursor.setEmailUsuario(cursor.getString(cursor.getColumnIndexOrThrow("emailUsuario")));
                    usuarioCursor.setSenhaUsuario(cursor.getString(cursor.getColumnIndexOrThrow("senhaUsuario")));
                    usuarioCursor.setFotoUsuario(cursor.getString(cursor.getColumnIndexOrThrow("fotoUsuario")));

                    Avaliacao avaliacaoCusor = new Avaliacao();
                    avaliacaoCusor.setIdAvaliacao(cursor.getInt(cursor.getColumnIndexOrThrow("idAvaliacao")));
                    avaliacaoCusor.setPontosAvaliacao(cursor.getFloat(cursor.getColumnIndexOrThrow("pontosAvaliacao")));
                    avaliacaoCusor.setTextoAvaliacao(cursor.getString(cursor.getColumnIndexOrThrow("textoAvaliacao")));

                    avaliacaoCusor.setRestaurante(restauranteCusor);
                    avaliacaoCusor.setUsuario(usuarioCursor);
                    avaliacoes.add(avaliacaoCusor);
                } while (cursor.moveToNext());
            }

            // fechar o cursor
            cursor.close();
            // fechar a conexao com o banco de dados
            database.close();

        } catch (Exception e){
            Log.e("AvaliacaoDAO", "erro ao listar todos as avaliacoes"+e.getMessage());
        }
        return avaliacoes;
    }
}

