package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import entities.Aluno;
import entities.Empresa;
import entities.Estagio;
import entities.Orientador;

public class Main {
    public static void main(String[] args) {

        String jdbcURL = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "123";


        try {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);

            // SQL para criar a tabela alunos (se não existir)
            String createTableSQL = "CREATE TABLE IF NOT EXISTS alunos (" +
                    "matricula VARCHAR(10), " +
                    "nome VARCHAR(100)" +
                    ')';
            PreparedStatement createTableStatement = connection.prepareStatement(createTableSQL);
            createTableStatement.execute();
            createTableStatement.close();

            // Agora você pode continuar com o código para inserir dados
            Aluno aluno = new Aluno();
            aluno.setMatricula("1L");
            aluno.setNome("João");

            List<Estagio> estagios = new ArrayList<>();

            Estagio estagio1 = new Estagio();
            estagio1.setDataInicio(new Date(-2022)); // Defina a data de início conforme necessário
            estagio1.setDataFim(new Date(-2023)); // Defina a data de término conforme necessário
            estagio1.setCargaHoraria(30); // Defina a carga horária conforme necessário
            estagio1.setStatus("Em andamento"); // Defina o status conforme necessário

            estagios.add(estagio1);

            aluno.setEstagios(estagios);

            // Continuar com o código de inserção no banco de dados

            String insertAlunoSQL = "INSERT INTO alunos (matricula, nome) VALUES (?, ?)";
            PreparedStatement insertAlunoStatement = connection.prepareStatement(insertAlunoSQL);

            // Defina os valores a serem inseridos
            insertAlunoStatement.setString(1, aluno.getMatricula());
            insertAlunoStatement.setString(2, aluno.getNome());

            // Execute a inserção
            int rowsAffected = insertAlunoStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Inserção do aluno bem-sucedida!");
            } else {
                System.out.println("Nenhuma linha afetada ao inserir aluno.");
            }

            insertAlunoStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
