package com.educacao.cursos.auth;

import com.educacao.cursos.database.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {
    private static Usuario usuarioLogado = null;
    
    public static boolean registrar(String email, String senha, Usuario.TipoUsuario tipo) {
        if (!validarEmail(email) || senha.length() < 6) {
            return false;
        }
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO usuarios (email, senha, tipo) VALUES (?, ?, ?)")) {
            
            Usuario usuario = new Usuario(email, senha, tipo);
            ps.setString(1, email);
            ps.setString(2, usuario.getSenha());
            ps.setString(3, tipo.toString());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao registrar usuário: " + e.getMessage());
            return false;
        }
    }
    
    public static Usuario login(String email, String senha) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                "SELECT id, email, tipo FROM usuarios WHERE email = ? AND senha = ?")) {
            
            Usuario temp = new Usuario(email, senha, Usuario.TipoUsuario.ALUNO);
            ps.setString(1, email);
            ps.setString(2, temp.getSenha());
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuarioLogado = new Usuario(
                    rs.getInt("id"),
                    rs.getString("email"),
                    Usuario.TipoUsuario.valueOf(rs.getString("tipo"))
                );
                System.out.println("Login bem-sucedido: " + usuarioLogado.getEmail());
                return usuarioLogado;
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao fazer login: " + e.getMessage());
        }
        
        return null;
    }
    
    public static void logout() {
        usuarioLogado = null;
        System.out.println("Logout realizado");
    }
    
    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
    
    public static boolean estaLogado() {
        return usuarioLogado != null;
    }
    
    private static boolean validarEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
}
