package model;

import java.io.Serializable;

public class Avaliacao {
    private int idAvaliacao;
    private  Restaurante restaurante;

    private Usuario usuario;
    private float pontosAvaliacao;

    public int getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(int idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public float getPontosAvaliacao() {
        return pontosAvaliacao;
    }

    public void setPontosAvaliacao(float pontosAvaliacao) {
        this.pontosAvaliacao = pontosAvaliacao;
    }

    public String getTextoAvaliacao() {
        return textoAvaliacao;
    }

    public void setTextoAvaliacao(String textoAvaliacao) {
        this.textoAvaliacao = textoAvaliacao;
    }

    private String textoAvaliacao;



}