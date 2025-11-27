package com.educacao.cursos.patterns;

public class DecoratorPattern {
    
    // Interface base
    public interface Componente {
        String obterDescricao();
        double obterPreco();
    }
    
    // Implementação concreta
    public static class CursoBasico implements Componente {
        @Override
        public String obterDescricao() {
            return "Curso Básico";
        }
        
        @Override
        public double obterPreco() {
            return 100.0;
        }
    }
    
    // Decoradores
    public abstract static class DecoradorCurso implements Componente {
        protected Componente componente;
        
        public DecoradorCurso(Componente componente) {
            this.componente = componente;
        }
    }
    
    public static class ComCertificado extends DecoradorCurso {
        public ComCertificado(Componente componente) {
            super(componente);
        }
        
        @Override
        public String obterDescricao() {
            return componente.obterDescricao() + " + Certificado";
        }
        
        @Override
        public double obterPreco() {
            return componente.obterPreco() + 50.0;
        }
    }
    
    public static class ComSuporte extends DecoradorCurso {
        public ComSuporte(Componente componente) {
            super(componente);
        }
        
        @Override
        public String obterDescricao() {
            return componente.obterDescricao() + " + Suporte";
        }
        
        @Override
        public double obterPreco() {
            return componente.obterPreco() + 30.0;
        }
    }
    
    public static class ComMateriais extends DecoradorCurso {
        public ComMateriais(Componente componente) {
            super(componente);
        }
        
        @Override
        public String obterDescricao() {
            return componente.obterDescricao() + " + Materiais";
        }
        
        @Override
        public double obterPreco() {
            return componente.obterPreco() + 25.0;
        }
    }
}
