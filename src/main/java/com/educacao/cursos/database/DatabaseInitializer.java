package com.educacao.cursos.database;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    
    public static void initializeDatabase() {
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Criar tabela de usuários
            String sqlUsuarios = "CREATE TABLE IF NOT EXISTS usuarios (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "email VARCHAR(100) UNIQUE NOT NULL, " +
                "senha VARCHAR(255) NOT NULL, " +
                "tipo ENUM('ALUNO', 'PROFESSOR', 'ADMIN') NOT NULL, " +
                "criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
            
            stmt.execute(sqlUsuarios);
            
            // Criar tabela de professores
            String sqlProfessores = "CREATE TABLE IF NOT EXISTS professores (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "usuario_id INT NOT NULL, " +
                "nome VARCHAR(100) NOT NULL, " +
                "especializacao VARCHAR(100), " +
                "FOREIGN KEY (usuario_id) REFERENCES usuarios(id))";
            
            stmt.execute(sqlProfessores);
            
            // Criar tabela de alunos
            String sqlAlunos = "CREATE TABLE IF NOT EXISTS alunos (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "usuario_id INT NOT NULL, " +
                "nome VARCHAR(100) NOT NULL, " +
                "matricula VARCHAR(20) UNIQUE NOT NULL, " +
                "FOREIGN KEY (usuario_id) REFERENCES usuarios(id))";
            
            stmt.execute(sqlAlunos);
            
            // Criar tabela de cursos
            String sqlCursos = "CREATE TABLE IF NOT EXISTS cursos (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "professor_id INT NOT NULL, " +
                "titulo VARCHAR(150) NOT NULL, " +
                "descricao TEXT, " +
                "carga_horaria INT, " +
                "vagas_disponiveis INT, " +
                "data_inicio DATE, " +
                "FOREIGN KEY (professor_id) REFERENCES professores(id))";
            
            stmt.execute(sqlCursos);
            
            // Criar tabela de inscrições
            String sqlInscricoes = "CREATE TABLE IF NOT EXISTS inscricoes (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "aluno_id INT NOT NULL, " +
                "curso_id INT NOT NULL, " +
                "data_inscricao TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "status ENUM('ATIVA', 'CONCLUIDA', 'CANCELADA'), " +
                "FOREIGN KEY (aluno_id) REFERENCES alunos(id), " +
                "FOREIGN KEY (curso_id) REFERENCES cursos(id))";
            
            stmt.execute(sqlInscricoes);
            
            System.out.println("Banco de dados inicializado com sucesso!");
            
        } catch (Exception e) {
            System.err.println("Erro ao inicializar banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
