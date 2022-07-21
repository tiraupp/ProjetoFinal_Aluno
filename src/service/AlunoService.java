package service;

import model.Aluno;
import model.Diretor;
import repository.RepositoryImplementacao;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class AlunoService {
    private RepositoryImplementacao<Integer, Aluno> repository = new RepositoryImplementacao<>();
    private Scanner sc;
    LocalDate hoje = LocalDate.now();
    DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String hojeFormatado = hoje.format(formatoData);

    public AlunoService(Scanner sc) {
        this.sc = sc;

        Aluno aluno1 = new Aluno("MELISSA NOGUEIRA", "111", "111","48999799419", "08/04/2004", "01/07/2022", "01/07/2022");
        Aluno aluno2 = new Aluno("LUIZA PORTO", "222", "222","48999799419", "19/02/2000", "01/07/2022", "01/07/2022");
        Aluno aluno3 = new Aluno("ENZO GABRIEL", "333", "333","48999799419", "07/08/2002", "01/07/2022", "01/07/2022");
        Aluno aluno4 = new Aluno("FRANCISCO PIRES", "444", "444","48999799419", "22/04/2003", "01/07/2022", "01/07/2022");

        repository.salvar(aluno1.getMatricula(), aluno1);
        repository.salvar(aluno2.getMatricula(), aluno2);
        repository.salvar(aluno3.getMatricula(), aluno3);
        repository.salvar(aluno4.getMatricula(), aluno4);

        aluno1.setNota1(7.0);aluno1.setNota2(8.0);aluno1.setNota3(9.0);aluno1.setNota4(9.0);
        aluno2.setNota1(7.0);aluno2.setNota2(8.0);
        aluno3.setNota1(7.0);aluno3.setNota2(6.0);aluno3.setNota3(9.0);aluno3.setNota4(5.0);
        aluno4.setNota1(7.0);aluno4.setNota2(8.0);aluno4.setNota3(9.0);
    }
    public Aluno tratarOpcaoAluno(String opcao){
        sc.nextLine();
        if(opcao.equals("C")){
            return this.cadastrarAluno();
        } else {
            return pesquisarPorCpf(opcao);
        }
    }

    public Aluno cadastrarAluno() {
        System.out.println(" ");
        System.out.println("Digite seu nome ");
        String nome = sc.nextLine().toUpperCase();
        System.out.println("Digite seu cpf ");
        String cpf = sc.next();
        System.out.println("Digite sua senha ");
        String senha = sc.next();
        System.out.println("Digite seu telefone ");
        String telefone = sc.next();
        System.out.println("Digite sua data de nascimento ");
        String nascimento = sc.next();
        Aluno aluno = new Aluno(nome, cpf, senha, telefone, nascimento, hojeFormatado, hojeFormatado);

        repository.salvar(aluno.getMatricula(), aluno);

        return aluno;
    }

    public void mostrarTodos(){
        List<Aluno> alunos = repository.buscarTodos();
        for(Aluno alunos1: alunos){
            System.out.println(alunos1.getMatricula() + " - " + alunos1.getNome());
        }
    }

    public Aluno pesquisarPorCpf(String cpf) {
        List<Aluno> alunos = repository.buscarTodos();
        Aluno alunocpf = null;
        for (Aluno aluno : alunos) {
                if (aluno.getCpf().equals(cpf)) {
                    boolean continua = true;
                    while (continua) {
                        System.out.println("Digite sua senha:");
                        String confereSenha = sc.next();
                        if (aluno.getSenha().equals(confereSenha)) {
                            alunocpf = aluno;
                            continua = false;
                        } else {
                            System.out.println("Senha Incorreta, verifique a senha digitada.");
                        }
                }
            }
        }
        return alunocpf;
    }

    public void mostrarAlunoLogado(int id) {
        List<Aluno> alunos = repository.buscarTodos();
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula() == id) {
                boolean continuarCadastroAluno = true;
                while (continuarCadastroAluno) {
                    System.out.println(" ");
                    System.out.println("Se necessario atualizar alguma informação escolha a opção: ");
                    System.out.println("1 - Nome: " + aluno.getNome());
                    System.out.println("2 - CPF: " + aluno.getCpf());
                    System.out.println("3 - Senha: ");
                    System.out.println("4 - Telefone: " + aluno.getTelefone());
                    System.out.println("5 - Data de Nacimento: " + aluno.getNascimento());
                    System.out.println("Data última atualização do cadastro: " + aluno.getUltimaAtualizacao());
                    System.out.println(" ");
                    System.out.println("0 - Dados estão corretos, voltar ao menu anteior.");
                    System.out.println(" ");
                    int opcaoCadastro = sc.nextInt();
                    switch (opcaoCadastro) {
                        case 1: {
                            System.out.println(" ");
                            System.out.println("Não é possível alterar o nome, contate a direção! ");
                            break;
                        }
                        case 2: {
                            System.out.println(" ");
                            System.out.println("Não é possível alterar o cpf, contate a direção!");
                            break;
                        }
                        case 3: {
                            System.out.println(" ");
                            System.out.println("Digite sua nova senha: ");
                            String senha = sc.next();
                            aluno.setSenha(senha);
                            aluno.setUltimaAtualizacao(hojeFormatado);
                            atualizarAluno(aluno);
                            System.out.println(" ");
                            System.out.println("Nova senha atualizada! ");
                            break;
                        }
                        case 4: {
                            System.out.println(" ");
                            System.out.println("Digite seu novo telefone: ");
                            String telefone = sc.next();
                            aluno.setTelefone(telefone);
                            aluno.setUltimaAtualizacao(hojeFormatado);
                            atualizarAluno(aluno);
                            System.out.println(" ");
                            System.out.println("Telefone atualizado! ");
                            break;
                        }
                        case 5: {
                            System.out.println(" ");
                            System.out.println("Não é possível alterar a data de nascimento, contate a direção! ");
                            break;
                        }
                        case 0: {
                            continuarCadastroAluno = false;
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

    public void atualizarAluno(Aluno aluno){
        this.repository.salvar(aluno.getMatricula(), aluno);
    }
    public void verNotas(int id){
        List<Aluno> alunos = repository.buscarTodos();
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula() == id) {
                if(aluno.getNota1()==null && aluno.getNota2()==null && aluno.getNota3()==null && aluno.getNota3()==null){
                    System.out.println("Você ainda não possui notas postadas.");
                }else {
                    if (aluno.getNota1() != null) {
                        System.out.println("Nota 1: " + aluno.getNota1());
                    }
                    if (aluno.getNota2() != null) {
                        System.out.println("Nota 2: " + aluno.getNota2());
                    }
                    if (aluno.getNota3() != null) {
                        System.out.println("Nota 3: " + aluno.getNota3());
                    }
                    if (aluno.getNota4() != null) {
                        System.out.println("Nota 4: " + aluno.getNota4());
                    }
                }
                if(aluno.getNota1()!=null && aluno.getNota2()!=null && aluno.getNota3()!=null && aluno.getNota3()!=null){
                    Double media = (aluno.getNota1()+ aluno.getNota2()+ aluno.getNota3()+ aluno.getNota4())/4;
                    String aprovacao;
                    if (media>=7.0){
                        aprovacao = "Aprovado";
                    }else {
                        aprovacao  = "Reprovado";
                    }
                    System.out.println("Sua média final é: "+media +" - "+aprovacao);
                }
            }
        }
    }
    public void verNotasProfessor(int id){
        List<Aluno> alunos = repository.buscarTodos();
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula() == id) {
                if(aluno.getNota1()==null && aluno.getNota2()==null && aluno.getNota3()==null && aluno.getNota3()==null){
                    System.out.println(aluno.getNome() + ", ainda não possui notas postadas.");
                }else {
                    if (aluno.getNota1() != null) {
                        System.out.println("Nota 1: " + aluno.getNota1());
                    }
                    if (aluno.getNota2() != null) {
                        System.out.println("Nota 2: " + aluno.getNota2());
                    }
                    if (aluno.getNota3() != null) {
                        System.out.println("Nota 3: " + aluno.getNota3());
                    }
                    if (aluno.getNota4() != null) {
                        System.out.println("Nota 4: " + aluno.getNota4());
                    }
                }
                if(aluno.getNota1()!=null && aluno.getNota2()!=null && aluno.getNota3()!=null && aluno.getNota4()!=null){
                    Double media = (aluno.getNota1()+ aluno.getNota2()+ aluno.getNota3()+ aluno.getNota4())/4;
                    String aprovacao;
                    if (media>=7.0){
                        aprovacao = "Aprovado";
                    }else {
                        aprovacao  = "Reprovado";
                    }
                    System.out.println("Média final é: "+media +" - "+aprovacao);
                }
            }
        }
    }
    public void cadastrarNotas(int id) {
        List<Aluno> alunos = repository.buscarTodos();
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula() == id) {
            boolean cadastraNota = true;
            while (cadastraNota){
                if(aluno.getNota1()==null) {
                    System.out.println("Digite a Nota 1 do aluno "+aluno.getNome());
                    Double nota1 = sc.nextDouble();
                    aluno.setNota1(nota1);
                } else if(aluno.getNota2()==null){
                        System.out.println("Digite a Nota 2 do aluno "+aluno.getNome());
                        Double nota2 = sc.nextDouble();
                        aluno.setNota2(nota2);
                    } else if(aluno.getNota3()==null){
                            System.out.println("Digite a Nota 3 do aluno "+aluno.getNome());
                            Double nota3 = sc.nextDouble();
                            aluno.setNota3(nota3);
                        } else if(aluno.getNota4()==null){
                            System.out.println("Digite a Nota 4 do aluno "+aluno.getNome());
                            Double nota4 = sc.nextDouble();
                            aluno.setNota4(nota4);
                        } else{
                        System.out.println("Todas as notas para o aluno "+aluno.getNome()+" já foram cadastradas!");
                        cadastraNota = false;
                        break;
                    }
                System.out.println("Deseja cadastrar mais notas para o aluno "+aluno.getNome()+" ? S/N");
                String continua = sc.next().toUpperCase();
                if(continua.equals("N")){
                    cadastraNota = false;
                }
            }

            }
        }
    }
    public void verNotasAlunos(){
        System.out.println(" ");
        List<Aluno> alunos = repository.buscarTodos();
        for (Aluno aluno : alunos) {
            String aprovacao;
            if(aluno.getNota1()==null && aluno.getNota2()==null && aluno.getNota3()==null && aluno.getNota4()==null){
                System.out.println("Aluno " + aluno.getNome()+" não possui notas postadas");
            }else if(aluno.getNota1()!=null && aluno.getNota2()!=null && aluno.getNota3()!=null && aluno.getNota4()!=null){
                Double media = (aluno.getNota1()+ aluno.getNota2()+ aluno.getNota3()+ aluno.getNota4())/4;
                if (media>=7.0){
                    aprovacao = "Aprovado";
                }else {
                    aprovacao = "Reprovado";
                }
                System.out.println("Aluno " + aluno.getNome()+", Nota 1: "+ aluno.getNota1()+", Nota 2: "+ aluno.getNota2()+", Nota 3: "+ aluno.getNota3()+", Nota 4: "+ aluno.getNota4()+", média final: "+media+" " +aprovacao);

            } else {
                String nota1 = "";
                String nota2 = "";
                String nota3 = "";
                String nota4 = "";
                if(aluno.getNota1()!=null){
                    nota1 = (", Nota 1: " +aluno.getNota1().toString());
                }
                if(aluno.getNota2()!=null) {
                    nota2 = (", Nota 2: " + aluno.getNota2().toString());
                }
                if(aluno.getNota3()!=null){
                    nota3 = (", Nota 3: " + aluno.getNota3().toString());
                }
                if(aluno.getNota4()!=null) {
                    nota4 = (", Nota 4: " + aluno.getNota4().toString());
                }
                System.out.println("Aluno " + aluno.getNome() + nota1 + nota2+ nota3 + nota4);
            }
        }
    }
    public Aluno diretorCadastrarAluno() {
        sc.nextLine();
        System.out.println(" ");
        System.out.println("Digite o nome do aluno ");
        String nome = sc.nextLine().toUpperCase();
        System.out.println("Digite cpf do aluno ");
        String cpf = sc.next();
        System.out.println("Digite a senha do aluno");
        String senha = sc.next();
        System.out.println("Digite o telefone do aluno ");
        String telefone = sc.next();
        System.out.println("Digite a data de nascimento do aluno 'dd/mm/aaaa' ");
        String nascimento = sc.next();
        Aluno aluno = new Aluno(nome, cpf, senha, telefone, nascimento, hojeFormatado, hojeFormatado);

        repository.salvar(aluno.getMatricula(), aluno);
        System.out.println("Aluno " +nome+ " cadastrado com sucesso!");
        return aluno;
    }
    public void diretorAlteraCadastroAluno() {
        System.out.println("Escolha na lista qual aluno deseja alterar o cadastro: ");
        mostrarTodos();
        int id = sc.nextInt();
        List<Aluno> alunos = repository.buscarTodos();
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula() == id) {
                boolean continuarCadastroAluno = true;
                while (continuarCadastroAluno) {
                    System.out.println(" ");
                    System.out.println("Selecione qual informação deseja atualizar: ");
                    System.out.println("1 - Nome: " + aluno.getNome());
                    System.out.println("2 - CPF: " + aluno.getCpf());
                    System.out.println("3 - Senha: ");
                    System.out.println("4 - Telefone: " + aluno.getTelefone());
                    System.out.println("5 - Data de Nacimento: " + aluno.getNascimento());
                    System.out.println("Data do cadastro: " + aluno.getDataCadastro());
                    System.out.println("Data última atualização do cadastro: " + aluno.getUltimaAtualizacao());
                    System.out.println("0 - Dados estão corretos, voltar ao menu anteior.");
                    int opcaoCadastro = sc.nextInt();
                    switch (opcaoCadastro) {
                        case 1: {
                            System.out.println(" ");
                            System.out.println("Digite o nome: ");
                            String nome = sc.nextLine().toUpperCase();
                            aluno.setNome(nome);
                            aluno.setUltimaAtualizacao(hojeFormatado);
                            atualizarAluno(aluno);
                            System.out.println(" ");
                            System.out.println("Nome atualizado! ");
                            break;
                        }
                        case 2: {
                            System.out.println(" ");
                            System.out.println("Digite o cpf: ");
                            String cpf = sc.next();
                            aluno.setCpf(cpf);
                            aluno.setUltimaAtualizacao(hojeFormatado);
                            atualizarAluno(aluno);
                            System.out.println(" ");
                            System.out.println("Cpf atualizado! ");
                            break;
                        }
                        case 3: {
                            System.out.println(" ");
                            System.out.println("Digite a senha: ");
                            String senha = sc.next();
                            aluno.setSenha(senha);
                            aluno.setUltimaAtualizacao(hojeFormatado);
                            atualizarAluno(aluno);
                            System.out.println(" ");
                            System.out.println("Senha atualizada! ");
                            break;
                        }
                        case 4: {
                            System.out.println(" ");
                            System.out.println("Digite o telefone: ");
                            String telefone = sc.next();
                            aluno.setTelefone(telefone);
                            aluno.setUltimaAtualizacao(hojeFormatado);
                            atualizarAluno(aluno);
                            System.out.println(" ");
                            System.out.println("Telefone atualizado! ");
                            break;
                        }
                        case 5: {
                            System.out.println(" ");
                            System.out.println("Digite a data de nascimento 'dd/mm/aaaa' : ");
                            String nascimento = sc.next();
                            aluno.setNascimento(nascimento);
                            aluno.setUltimaAtualizacao(hojeFormatado);
                            atualizarAluno(aluno);
                            System.out.println(" ");
                            System.out.println("Data de nascimento atualizada! ");
                            break;
                        }
                        case 0: {
                            continuarCadastroAluno = false;
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
    public void diretorAlteraNotasAluno() {
        System.out.println("Escolha na lista qual aluno deseja alterar a nota: ");
        mostrarTodos();
        int id = sc.nextInt();
        List<Aluno> alunos = repository.buscarTodos();
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula() == id) {
                boolean continuarCadastroAluno = true;
                while (continuarCadastroAluno) {
                    System.out.println(" ");
                    System.out.println("Selecione qual informação deseja atualizar: ");
                    System.out.println("Nome: " + aluno.getNome());
                    System.out.println("1 - Nota 1: " + aluno.getNota1());
                    System.out.println("2 - Nota 2: " + aluno.getNota2());
                    System.out.println("3 - Nota 3: " + aluno.getNota3());
                    System.out.println("4 - Nota 4: " + aluno.getNota4());
                    System.out.println("0 - Dados estão corretos, voltar ao menu anteior.");
                    int opcaoCadastro = sc.nextInt();
                    switch (opcaoCadastro) {
                        case 1: {
                            System.out.println(" ");
                            System.out.println("Digite a nova nota: ");
                            Double nota1 = sc.nextDouble();
                            aluno.setNota1(nota1);
                            break;
                        }
                        case 2: {
                            System.out.println(" ");
                            System.out.println("Digite a nova nota: ");
                            Double nota2 = sc.nextDouble();
                            aluno.setNota2(nota2);
                            break;
                        }
                        case 3: {
                            System.out.println(" ");
                            System.out.println("Digite a nova nota: ");
                            Double nota3 = sc.nextDouble();
                            aluno.setNota3(nota3);
                            break;
                        }
                        case 4: {
                            System.out.println(" ");
                            System.out.println("Digite a nova nota: ");
                            Double nota4 = sc.nextDouble();
                            aluno.setNota4(nota4);
                            break;
                        }
                        case 0: {
                            continuarCadastroAluno = false;
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
