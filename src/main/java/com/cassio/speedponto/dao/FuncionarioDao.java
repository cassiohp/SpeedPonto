package com.cassio.speedponto.dao;

import com.cassio.speedponto.controllers.FuncionarioController;
import com.cassio.speedponto.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FuncionarioDao extends JpaRepository<Funcionario, Integer> {

    @Query("select i from Funcionario i where i.cpf = :cpf")
    public Funcionario findByCpf(String cpf);
    @Query("select j from Funcionario j where j.cpf = :cpf and j.senha = :senha")
    public Funcionario buscarLogin(String cpf, String senha);

}
