package com.cassio.speedponto.service;
import com.cassio.speedponto.dao.FuncionarioDao;
import com.cassio.speedponto.exceptions.CpfExistsException;
import com.cassio.speedponto.exceptions.CriptoExistsException;
import com.cassio.speedponto.model.Funcionario;
import com.cassio.speedponto.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class ServiceFuncionario {

    @Autowired
    private FuncionarioDao repositorioFuncionario;

    public void salvarFuncionario(Funcionario funcionario) throws Exception{

        try{

            if(repositorioFuncionario.findByCpf(funcionario.getCpf()) != null){
                throw new CpfExistsException("Já existe um CPF cadastrado para" + funcionario.getCpf());
            }

            funcionario.setSenha(Util.md5(funcionario.getSenha()));

        }catch (NoSuchAlgorithmException e){
            throw new CriptoExistsException("Erro na criptografia da senha");
        }

        repositorioFuncionario.save(funcionario);

    }

}
