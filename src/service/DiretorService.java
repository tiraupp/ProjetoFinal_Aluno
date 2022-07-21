package service;

import model.Diretor;
import model.Professor;
import repository.RepositoryImplementacao;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class DiretorService {
    private RepositoryImplementacao<Integer, Diretor> repository = new RepositoryImplementacao<>();
    private Scanner sc;

    public DiretorService(Scanner sc) {
        this.sc = sc;

        Diretor diretor = new Diretor("LUIZ GILBERTO", "111", "111","48999001122", "01/02/1970", "17/07/2022", "18/07/2022");
        Diretor diretor2 = new Diretor("ELIANA OLIVEIRA", "222", "222","48999001122", "01/02/1970", "17/07/2022", "18/07/2022");
        Diretor diretor3 = new Diretor("AUGUSTO PORTO", "333", "333","48999001122", "01/02/1970", "17/07/2022", "18/07/2022");
        Diretor diretor4 = new Diretor("THEO PIRES", "444", "444","48999001122", "01/02/1970", "17/07/2022", "18/07/2022");

        repository.salvar(diretor.getMatricula(), diretor);
        repository.salvar(diretor2.getMatricula(), diretor2);
        repository.salvar(diretor3.getMatricula(), diretor3);
        repository.salvar(diretor4.getMatricula(), diretor4);


    }

    public Diretor pesquisarPorCpf(String cpf) {
        List<Diretor> diretores = repository.buscarTodos();
        Diretor diretorcpf = null;
            for (Diretor diretor : diretores) {
                if (diretor.getCpf().equals(cpf)) {
                    boolean continua = true;
                    while (continua) {
                        System.out.println("Digite sua senha:");
                        String confereSenha = sc.next();
                        if (diretor.getSenha().equals(confereSenha)) {
                            diretorcpf = diretor;
                            continua = false;
                        } else {
                            System.out.println("Senha Incorreta, verifique a senha digitada.");
                        }
                    }
                }
            }
        return diretorcpf;
    }
}
