package com.example.appcompanypets.DTO;

// Classe de transporte de objetos
public class DtoVeterinarios
{
    private int id;
    private String nome;
    private String email;
    private String telefone;

    public String getDs_Imagem() {
        return ds_Imagem;
    }

    public void setDs_Imagem(String ds_Imagem) {
        this.ds_Imagem = ds_Imagem;
    }

    private String ds_Imagem;

    public DtoVeterinarios(String nome, String email, String telefone)
    {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public DtoVeterinarios()
    {
    }

    @Override
    public String toString()
    {
        return nome + " - " + telefone;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
