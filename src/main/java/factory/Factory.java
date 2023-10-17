package factory;

import entities.Aluno;
import entities.Empresa;
import entities.Estagio;
import entities.Orientador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Factory {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence.xml");
    private EntityManager em = emf.createEntityManager();

    public Factory(EntityManager em) {
        this.em = em;
    }

    public void inserirEstagio(Estagio estagio) {
        em.getTransaction().begin();
        em.persist(estagio);
        em.getTransaction().commit();
    }

    public void atualizarEstagio(Estagio estagio) {
        em.getTransaction().begin();
        em.merge(estagio);
        em.getTransaction().commit();
    }

    public void removerEstagio(Long id) {
        em.getTransaction().begin();
        Estagio estagio = em.find(Estagio.class, id);
        if (estagio != null) {
            em.remove(estagio);
        }
        em.getTransaction().commit();
    }

    public List<Aluno> listarAlunos() {
        TypedQuery<Aluno> query = em.createQuery("SELECT a FROM Aluno a", Aluno.class);
        return query.getResultList();
    }

    public List<Empresa> listarEmpresas() {
        TypedQuery<Empresa> query = em.createQuery("SELECT e FROM Empresa e", Empresa.class);
        return query.getResultList();
    }

    public List<Orientador> listarOrientadores() {
        TypedQuery<Orientador> query = em.createQuery("SELECT o FROM Orientador o", Orientador.class);
        return query.getResultList();
    }

    public List<Estagio> listarEstagios() {
        TypedQuery<Estagio> query = em.createQuery("SELECT e FROM Estagio e", Estagio.class);
        return query.getResultList();
    }

    public Estagio selecionarEstagioPorMatriculaAluno(String matricula) {
        TypedQuery<Estagio> query = em.createQuery("SELECT e FROM Estagio e WHERE e.aluno.matricula = :matricula", Estagio.class);
        query.setParameter("matricula", matricula);
        return query.getSingleResult();
    }

    public Aluno selecionarAlunoPorId(Long id) {
        return em.find(Aluno.class, id);
    }

    public Aluno selecionarAlunoPorMatricula(String matricula) {
        TypedQuery<Aluno> query = em.createQuery("SELECT a FROM Aluno a WHERE a.matricula = :matricula", Aluno.class);
        query.setParameter("matricula", matricula);
        return query.getSingleResult();
    }

    public void inserirAluno(Aluno aluno) {
        em.getTransaction().begin();
        em.persist(aluno);
        em.getTransaction().commit();
    }

    public void inserirEmpresa(Empresa empresa) {
        em.getTransaction().begin();
        em.persist(empresa);
        em.getTransaction().commit();
    }

    public void inserirOrientador(Orientador orientador) {
        em.getTransaction().begin();
        em.persist(orientador);
        em.getTransaction().commit();
    }

    public void atualizarAluno(Aluno aluno) {
        em.getTransaction().begin();
        em.merge(aluno);
        em.getTransaction().commit();
    }

    public void atualizarEmpresa(Empresa empresa) {
        em.getTransaction().begin();
        em.merge(empresa);
        em.getTransaction().commit();
    }

    public void atualizarOrientador(Orientador orientador) {
        em.getTransaction().begin();
        em.merge(orientador);
        em.getTransaction().commit();
    }

    public void removerAluno(Aluno id) {
        em.getTransaction().begin();
        Aluno aluno = em.find(Aluno.class, id);
        if (aluno != null) {
            em.remove(aluno);
        }
        em.getTransaction().commit();
    }

    public void removerEmpresa(Long id) {
        em.getTransaction().begin();
        Empresa empresa = em.find(Empresa.class, id);
        if (empresa != null) {
            em.remove(empresa);
        }
        em.getTransaction().commit();
    }

    public void removerOrientador(Long id) {
        em.getTransaction().begin();
        Orientador orientador = em.find(Orientador.class, id);
        if (orientador != null) {
            em.remove(orientador);
        }
        em.getTransaction().commit();
    }

    public static void main(String[] args) {
        Aluno aluno = new Aluno();
        aluno.setMatricula("1L");
        aluno.setNome("João");

        List<Estagio> estagios = new ArrayList<>();

        Estagio estagio1 = new Estagio();
        estagio1.setDataInicio(new Date(-2022)); // Defina a data de início conforme necessário
        estagio1.setDataFim(new Date(-2023)); // Defina a data de término conforme necessário
        estagio1.setCargaHoraria(30); // Defina a carga horária conforme necessário
        estagio1.setStatus("Em andamento"); // Defina o status conforme necessário

        aluno.setEstagios(estagios);
    }


    public void fechar() {
        em.close();
    }

}

