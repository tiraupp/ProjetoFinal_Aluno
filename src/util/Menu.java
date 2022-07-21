package util;

import model.Aluno;
import service.AlunoService;

import java.util.Scanner;

public class Menu {
    private Scanner sc;
    AlunoService alunoService = new AlunoService(sc);

    public static void bemVindo(){
        System.out.println(" ");
        System.out.println("para efeito de teste, o sistema possui alunos, professores e diretores já cadastrados");
        System.out.println(" utilizar cpf e senhas iguais, '111', '222', '333', '444' ");
        System.out.println(" ");
        System.out.println("*************************************************************************");
        System.out.println("******************Seja bem vindo(a) ao sistema de Notas******************");
        System.out.println("*************************************************************************");

    }

    public static void menuIdentificacao(){
        System.out.println(" ");
        System.out.println("Escolha a opção desejada: ");
        System.out.println("1 - Aluno");
        System.out.println("2 - Professor");
        System.out.println("3 - Diretor");
        System.out.println("0 - Sair");
    }

    public static void menuAluno(){
        System.out.println(" ");
        System.out.println("Você já possui login? ");
        System.out.println("Sim - Digite seu CPF para logar");
        System.out.println("Não - digite 'C' para cadastrar");
    }
    public static void menuAlunoLogado(String nome){
        System.out.println(" ");
        System.out.println(nome + " escolha a opção: ");
        System.out.println("1 - Ver notas");
        System.out.println("2 - Ver dados cadastrais");
        System.out.println("0 - Voltar ao menu anterior");
    }
    public static void menuProfessor(){
        System.out.println(" ");
        System.out.println("Você já possui login? ");
        System.out.println("Sim - Digite seu CPF para logar");
        System.out.println("Não - digite 'C' para cadastrar");
    }
    public static void menuProfessorLogado(String nome){
        System.out.println(" ");
        System.out.println(nome + " escolha a opção: ");
        System.out.println("1 - Ver notas já cadastradas de todos os alunos");
        System.out.println("2 - Ver notas já cadastradas por aluno");
        System.out.println("3 - Cadastrar nota por aluno");
        System.out.println("0 - Voltar ao menu anterior");
    }
    public static void menuDiretor(){
        System.out.println(" ");
        System.out.println("Digite seu CPF: ");
    }

    public static void menuDiretorLogado(String nome){
        System.out.println(" ");
        System.out.println(nome + " escolha a opção: ");
        System.out.println("1 - Cadastrar aluno");
        System.out.println("2 - Atualizar cadastro do aluno");
        System.out.println("3 - Ver notas por aluno");
        System.out.println("4 - Alterar nota por aluno");
        System.out.println("5 - Cadastrar professor");
        System.out.println("6 - Atualizar cadastro do professor");
        System.out.println("0 - Voltar ao menu anterior");
    }
}
