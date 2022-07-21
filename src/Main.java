import model.Aluno;
import model.Diretor;
import model.Professor;
import service.AlunoService;
import service.DiretorService;
import service.ProfessorService;
import util.Menu;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AlunoService alunoService = new AlunoService(sc);
        ProfessorService professorService = new ProfessorService(sc);
        DiretorService diretorService = new DiretorService(sc);

        boolean continuaMenu = true;

        Menu.bemVindo();
        while (continuaMenu) {
            try {
                Menu.menuIdentificacao();
                String idenfifica = sc.next();
                switch (idenfifica) {
                    case "1": {
                        boolean menuAluno = true;
                        while (menuAluno) {
                            try {
                                Menu.menuAluno();
                                String opcaoAluno = sc.next().toUpperCase();
                                Aluno aluno = alunoService.tratarOpcaoAluno(opcaoAluno);
                                boolean continuaMenuAluno = true;
                                while (continuaMenuAluno) {
                                    Menu.menuAlunoLogado(aluno.getNome());
                                    String opcaoLogado = sc.next();
                                    switch (opcaoLogado) {
                                        case "1": {
                                            alunoService.verNotas(aluno.getMatricula());
                                            break;
                                        }
                                        case "2": {
                                            alunoService.mostrarAlunoLogado(aluno.getMatricula());
                                            break;
                                        }
                                        case "0": {
                                            continuaMenuAluno = false;
                                            menuAluno = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println(" ");
                                            System.out.println("Opcão inválida, tente novamente");
                                            break;
                                        }
                                    }
                                }
                            }
                         catch(Exception e){
                                    System.out.println(" ");
                                    System.out.println("CPF Incorreto, verifique o cpf digitado e digite novamente:");
                                }
                            }
                    menuAluno = false;
                    break;
                    }
                    case "2": {
                        boolean menuProfessor = true;
                        while (menuProfessor) {
                            try {
                                Menu.menuProfessor();
                                String opcaoProfessor = sc.next().toUpperCase();
                                Professor professor = professorService.tratarOpcaoProfessor(opcaoProfessor);
                                boolean continuaMenuProfessor = true;
                                while (continuaMenuProfessor) {
                                    Menu.menuProfessorLogado(professor.getNome());
                                    String opcaoLogado = sc.next();
                                    switch (opcaoLogado) {
                                        case "1": {
                                            alunoService.verNotasAlunos();
                                            break;
                                        }
                                        case "2": {
                                            System.out.println("Escolha o aluno para ver as notas");
                                            alunoService.mostrarTodos();
                                            int alunoNota = sc.nextInt();
                                            alunoService.verNotasProfessor(alunoNota);
                                            break;
                                        }
                                        case "3": {
                                            System.out.println("Escolha o aluno para cadastrar a nota:");
                                            alunoService.mostrarTodos();
                                            int alunoNota = sc.nextInt();
                                            alunoService.cadastrarNotas(alunoNota);
                                            break;
                                        }
                                        case "0": {
                                            continuaMenuProfessor = false;
                                            menuProfessor = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println(" ");
                                            System.out.println("Opcão inválida, tente novamente");
                                            break;
                                        }
                                    }
                                }
                            }
                            catch (Exception e) {
                                System.out.println(" ");
                                System.out.println("CPF Incorreto, verifique o cpf digitado e digite novamente:");
                            }
                        }
                        menuProfessor = false;
                        break;
                    }
                    case "3": {
                        boolean menuDiretor = true;
                        while (menuDiretor) {
                            try {
                                Menu.menuDiretor();
                                Diretor diretor = diretorService.pesquisarPorCpf(sc.next().toUpperCase());

                                boolean continuaMenuDiretor = true;
                                while (continuaMenuDiretor) {
                                    Menu.menuDiretorLogado(diretor.getNome());
                                    String opcaoDiretor = sc.next();
                                    switch (opcaoDiretor) {
                                        case "1": {
                                            alunoService.diretorCadastrarAluno();
                                            break;
                                        }
                                        case "2": {
                                            alunoService.diretorAlteraCadastroAluno();
                                            break;
                                        }
                                        case "3": {
                                            alunoService.verNotasAlunos();
                                            break;
                                        }
                                        case "4": {
                                            alunoService.diretorAlteraNotasAluno();
                                            break;
                                        }
                                        case "5": {
                                            professorService.diretorCadastraProfessor();
                                            break;
                                        }
                                        case "6": {
                                            professorService.diretorAlteraCadastroProfessor();
                                            break;
                                        }
                                        case "0": {
                                            continuaMenuDiretor = false;
                                            menuDiretor = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println(" ");
                                            System.out.println("Opcão inválida, tente novamente");
                                            break;
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println(" ");
                                System.out.println("CPF Incorreto, verifique o cpf digitado e digite novamente:");
                            }
                        }
                        menuDiretor = false;
                        break;
                    }
                   case "0": {
                        System.out.println(" ");
                        System.out.println("Encerrando sistema.... ");
                        continuaMenu = false;
                        break;
                    }
                    default: {
                        System.out.println(" ");
                        System.out.println("Opcão inválida, tente novamente");
                        break;
                    }
                }
            }catch (Exception e){
                System.out.println(" ");
                System.out.println("Opcão inválida, digite somente números conforme opção do menu");
            }
        }
    }
}