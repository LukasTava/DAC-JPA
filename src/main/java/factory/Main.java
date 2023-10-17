package factory;

import entities.Aluno;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EstagioPU");
        EntityManager em = emf.createEntityManager();

        Factory estagioFactory = new Factory(em);

        Aluno aluno = new Aluno();
        aluno.setMatricula("12345");
        aluno.setNome("Nome do Aluno");

        estagioFactory.inserirAluno(aluno);

        List<Aluno> alunos = estagioFactory.listarAlunos();
        for (Aluno a : alunos) {
            System.out.println(a.getNome());
        }

        Aluno alunoAtualizado = estagioFactory.selecionarAlunoPorMatricula("12345");
        alunoAtualizado.setNome("Novo Nome");
        estagioFactory.atualizarAluno(alunoAtualizado);

        // Exemplo de remoção de um aluno
        Aluno alunoParaRemover = estagioFactory.selecionarAlunoPorMatricula("12345");
        estagioFactory.removerAluno(alunoParaRemover);

        estagioFactory.fechar();
        emf.close();
    }
}
