package model;

import util.Contador;

public class Diretor extends Pessoa{
    public Diretor(String nome, String cpf, String senha, String telefone, String nascimento, String dataCadastro, String ultimaAtualizacao) {
        super(Contador.proximoId(), nome, cpf, senha, telefone, nascimento, dataCadastro, ultimaAtualizacao);
    }
}
