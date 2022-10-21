package com.cassio.speedponto.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome")
    @Size(min = 5, max = 35, message = "O nome deve conter entre 5 a 35 caracteres")
    @NotEmpty(message = "O nome não pode ser vazio")
    private String nome;

    @Column(name = "cpf")
    @NotEmpty(message = "O campo CPF não pode ser vazio")
    @Min(10)
    private String cpf;

    @Column(name = "cargo")
    @NotEmpty(message = "O campo CPF não pode ser vazio")
    private String cargo;

    @Column(name = "acesso")
    private String acesso;

    @Column(name = "senha")
    private String senha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getAcesso() {
        return acesso;
    }

    public void setAcesso(String acesso) {
        this.acesso = acesso;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
