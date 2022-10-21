package com.cassio.speedponto.model;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuário {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String cpf;

    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
