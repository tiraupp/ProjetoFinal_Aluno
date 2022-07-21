package service;

import model.Aluno;
import model.Diretor;
import model.Professor;
import repository.RepositoryImplementacao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ProfessorService {
    private RepositoryImplementacao<Integer, Professor> repository = new RepositoryImplementacao<>();
    private Scanner sc;
    LocalDate hoje = LocalDate.now();
    DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String hojeFormatado = hoje.format(formatoData);

    public ProfessorService(Scanner sc) {
        this.sc = sc;

        Professor professor1 = new Professor("ANTONIO MARTINS", "111", "111","48999799419", "19/12/1987", "01/07/2022", "01/07/2022");
        Professor professor2 = new Professor("CLARA MORAES", "222", "222","48999799419", "11/03/1982", "01/07/2022", "01/07/2022");
        Professor professor3 = new Professor("FRANCISCO PIRES", "333", "444","48999799419", "15/06/1976", "01/07/2022", "01/07/2022");
        Professor professor4 = new Professor("JOAO VITOR", "444", "444","48999799419", "04/01/1990", "01/07/2022", "01/07/2022");

        repository.salvar(professor1.getMatricula(), professor1);
        repository.salvar(professor2.getMatricula(), professor2);
        repository.salvar(professor3.getMatricula(), professor3);
        repository.salvar(professor4.getMatricula(), professor4);

    }
    public Professor tratarOpcaoProfessor(String opcao){
        sc.nextLine();
        if(opcao.equals("C")){
            return this.cadastrarProfessor();
        } else {
            return pesquisarPorCpf(opcao);
        }
    }

    public Professor cadastrarProfessor() {
        System.out.println(" ");
        System.out.println("Digite seu nome ");
        String nome = sc.next();
        System.out.println("Digite seu cpf ");
        String cpf = sc.next();
        System.out.println("Digite sua senha, somente números");
        String senha = sc.next();
        System.out.println("Digite seu telefone ");
        String telefone = sc.next();
        System.out.println("Digite sua data de nascimento 'dd/mm/aaaa'");
        String nascimento = sc.next();
        Professor professor = new Professor(nome, cpf, senha, telefone, nascimento, hojeFormatado, hojeFormatado);

        repository.salvar(professor.getMatricula(), professor);

        return professor;
    }

    public void mostrarTodos(){
        List<Professor> professores = repository.buscarTodos();
        for(Professor professor1: professores){
            System.out.println(professor1.getMatricula() + " - " + professor1.getNome());
        }
    }

    public Professor pesquisarPorCpf(String cpf) {
        List<Professor> professores = repository.buscarTodos();
        Professor professorcpf = null;
        for (Professor professor : professores) {
                if (professor.getCpf().equals(cpf)) {
                    boolean continua = true;
                    while (continua) {
                        System.out.println("Digite sua senha:");
                        String confereSenha = sc.next();
                        if (professor.getSenha().equals(confereSenha)) {
                            professorcpf = professor;
                            continua = false;
                        } else {
                            System.out.println("Senha Incorreta, verifique a senha digitada.");
                        }
                    }
            }
        }
        return professorcpf;
    }

    public void mostrarProfessorLogado(int id) {
        List<Professor> professores = repository.buscarTodos();
        for (Professor professor : professores) {
            if (professor.getMatricula() == id) {
                System.out.println(" ");
                System.out.println("Se necessario atualizar alguma informação escolha a opção: ");
                System.out.println("1 - Nome: "+professor.getNome());
                System.out.println("2 - CPF: "+professor.getCpf());
                System.out.println("3 - Senha: ");
                System.out.println("4 - Telefone: "+professor.getTelefone());
                System.out.println("5 - Data de Nacimento: 'dd/mm/aaaa'"+professor.getNascimento());
                System.out.println("0 - Dados estão corretos, voltar ao menu anteior.");
                System.out.println("Data última atualização do cadastro: "+professor.getUltimaAtualizacao());
            }
        }
    }

    public void atualizarProfessor(Professor professor){
        this.repository.salvar(professor.getMatricula(), professor);
    }

    public void diretorCadastraProfessor() {
        System.out.println(" ");
        System.out.println("Digite o nome: ");
        String nome = sc.next();
        System.out.println("Digite o cpf: ");
        String cpf = sc.next();
        System.out.println("Digite a senha: ");
        String senha = sc.next();
        System.out.println("Digite o telefone: ");
        String telefone = sc.next();
        System.out.println("Digite a data de nascimento: ");
        String nascimento = sc.next();
        Professor professor = new Professor(nome, cpf, senha, telefone, nascimento, hojeFormatado, hojeFormatado);

        repository.salvar(professor.getMatricula(), professor);
    }

    public void diretorAlteraCadastroProfessor() {
        System.out.println("Escolha na lista qual professor deseja alterar o cadastro: ");
        mostrarTodos();
        int id = sc.nextInt();
        List<Professor> professores = repository.buscarTodos();
        for (Professor professor : professores) {
            if (professor.getMatricula() == id) {
                boolean continuarCadastroProfessor = true;
                while (continuarCadastroProfessor) {
                    System.out.println(" ");
                    System.out.println("Selecione qual informação deseja atualizar: ");
                    System.out.println("1 - Nome: " + professor.getNome());
                    System.out.println("2 - CPF: " + professor.getCpf());
                    System.out.println("3 - Senha: ");
                    System.out.println("4 - Telefone: " + professor.getTelefone());
                    System.out.println("5 - Data de Nacimento: " + professor.getNascimento());
                    System.out.println("Data do cadastro: " + professor.getDataCadastro());
                    System.out.println("Data última atualização do cadastro: " + professor.getUltimaAtualizacao());
                    System.out.println("0 - Dados estão corretos, voltar ao menu anteior.");
                    int opcaoCadastro = sc.nextInt();
                    switch (opcaoCadastro) {
                        case 1: {
                            System.out.println(" ");
                            System.out.println("Digite o nome: ");
                            String nome = sc.next();
                            professor.setNome(nome);
                            professor.setUltimaAtualizacao(hojeFormatado);
                            atualizarProfessor(professor);
                            break;
                        }
                        case 2: {
                            System.out.println(" ");
                            System.out.println("Digite o cpf: ");
                            String cpf = sc.next();
                            professor.setCpf(cpf);
                            professor.setUltimaAtualizacao(hojeFormatado);
                            atualizarProfessor(professor);
                            break;
                        }
                        case 3: {
                            System.out.println(" ");
                            System.out.println("Digite a senha: ");
                            String senha = sc.next();
                            professor.setSenha(senha);
                            professor.setUltimaAtualizacao(hojeFormatado);
                            atualizarProfessor(professor);
                            break;
                        }
                        case 4: {
                            System.out.println(" ");
                            System.out.println("Digite o telefone: ");
                            String telefone = sc.next();
                            professor.setTelefone(telefone);
                            professor.setUltimaAtualizacao(hojeFormatado);
                            atualizarProfessor(professor);
                            break;
                        }
                        case 5: {
                            System.out.println(" ");
                            System.out.println("Digite a data de nascimento: ");
                            String nascimento = sc.next();
                            professor.setNascimento(nascimento);
                            professor.setUltimaAtualizacao(hojeFormatado);
                            atualizarProfessor(professor);
                            break;
                        }
                        case 0: {
                            continuarCadastroProfessor = false;
                            break;
                        }
                        default:{
                            System.out.println(" ");
                            System.out.println(" Opção invalida, tente novamente. ");
                        }
                    }
                }
            }
        }
    }
}
