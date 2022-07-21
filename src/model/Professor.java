package model;

import util.Contador;

import java.util.Date;

public class Professor extends Pessoa{
    public Professor(String nome, String cpf, String senha, String telefone, String nascimento, String dataCadastro, String ultimaAtualizacao) {
        super(Contador.proximoId(), nome, cpf, senha, telefone, nascimento, dataCadastro, ultimaAtualizacao);
    }
}
