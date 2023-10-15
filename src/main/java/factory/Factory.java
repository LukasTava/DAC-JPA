package factory;
import entities.Aluno;
import entities.Empresa;
import entities.Estagio;
import entities.Orientador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Factory {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence.xml");
    private EntityManager em = emf.createEntityManager();

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
}
