package com.cassio.speedponto.dao;

import com.cassio.speedponto.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FuncionarioDao extends JpaRepository<Funcionario, Integer> {

}
