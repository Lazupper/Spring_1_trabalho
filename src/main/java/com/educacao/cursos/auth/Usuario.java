package com.educacao.cursos.auth;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String email;
    private String senha;
    private TipoUsuario tipo;
    
    public enum TipoUsuario {
        ALUNO, PROFESSOR, ADMIN
    }
    
    public Usuario(String email, String senha, TipoUsuario tipo) {
        this.email = email;
        this.senha = encriptarSenha(senha);
        this.tipo = tipo;
    }
    
    public Usuario(int id, String email, TipoUsuario tipo) {
        this.id = id;
        this.email = email;
        this.tipo = tipo;
    }
    
    private static String encriptarSenha(String senha) {
        // Implementação simplificada - em produção usar BCrypt
        return String.valueOf(senha.hashCode());
    }
    
    public boolean validarSenha(String senhaDigitada) {
        return this.senha.equals(encriptarSenha(senhaDigitada));
    }
    
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = encriptarSenha(senha); }
    
    public TipoUsuario getTipo() { return tipo; }
    public void setTipo(TipoUsuario tipo) { this.tipo = tipo; }
    
    @Override
    public String toString() {
        return "Usuario{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", tipo=" + tipo +
            '}';
    }
}
