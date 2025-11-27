package com.educacao.cursos.repositories;

import com.educacao.cursos.database.ConnectionFactory;
import com.educacao.cursos.models.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoRepository {
    
    public static void salvar(Curso curso, int professorId) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO cursos (professor_id, titulo, descricao, carga_horaria, vagas_disponiveis) " +
                "VALUES (?, ?, ?, ?, ?)")) {
            
            ps.setInt(1, professorId);
            ps.setString(2, curso.getTitulo());
            ps.setString(3, curso.getDescricao());
            ps.setInt(4, curso.getCargaHoraria());
            ps.setInt(5, curso.getVagasDisponiveis());
            
            ps.executeUpdate();
            System.out.println("Curso salvo no banco de dados: " + curso.getTitulo());
            
        } catch (SQLException e) {
            System.err.println("Erro ao salvar curso: " + e.getMessage());
        }
    }
    
    public static List<Curso> buscarTodos() {
        List<Curso> cursos = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM cursos")) {
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso(
                    rs.getString("titulo"),
                    rs.getString("descricao"),
                    rs.getInt("carga_horaria"),
                    rs.getInt("vagas_disponiveis")
                );
                cursos.add(curso);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cursos: " + e.getMessage());
        }
        
        return cursos;
    }
}
