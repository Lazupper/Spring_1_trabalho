# Sprint 3 - Dashboard, Relatórios e Padrões Avançados

## Objetivo
Adicionar camada de apresentação avançada com dashboard, relatórios profissionais e implementar padrões de design sofisticados.

## Requisitos Implementados

### 1. Dashboard e Visualização
- **DashboardFrame.java**: Interface visual com estatísticas
  - Cards de estatísticas (Total Alunos, Cursos, Taxa Aprovação, Professores)
  - Layout GridLayout 2x2
  - Cores diferenciadas por métrica
  - Header com tema azul profissional

- **GraficoDesempenho.java**: Gráfico de barras customizado
  - Herda de JPanel
  - Renderização com Graphics2D
  - Suporte a múltiplas barras
  - Escalas automáticas

### 2. Relatórios e Exportação
- **RelatorioExporter.java**: Exportação de dados
  - Exportar para CSV (delimitado por vírgula)
  - Exportar para HTML (tabelas formatadas)
  - Timestamps automáticos
  - Estrutura profissional

- **PDFGenerator.java**: Geração de documentos
  - Certificados de conclusão
  - Relatórios de desempenho por aluno
  - Formato visual com bordas e formatação
  - Integração com LaTeX (conversível para PDF)

### 3. Sistema de Notificações
- **NotificacaoService.java**: Central de notificações
  - Classe Notificacao com ID, título, mensagem
  - Enum TipoNotificacao (INSCRICAO, NOTA, CERTIFICADO, AVISO, ATUALIZACAO)
  - Interface NotificacaoListener (padrão Observer)
  - Marca como lida/não lida
  - Histórico de notificações

### 4. Padrões de Design Avançados

#### Observer Pattern
- **ObserverPattern.java**: Implementação completa
  - Interface Observer com método update()
  - Classe Observable com lista de observadores
  - MonitoradorCursos que notifica eventos
  - ProfessorObserver e AdministradorObserver

#### Decorator Pattern
- **DecoratorPattern.java**: Composição de funcionalidades
  - Interface Componente
  - CursoBasico como implementação concreta
  - DecoradorCurso abstrato
  - Decoradores: ComCertificado, ComSuporte, ComMateriais
  - Preços dinâmicos por combinação

## Padrões de Design Utilizados

### 1. Observer Pattern
\`\`\`
Observable (sujeito)
    ↓
    ├→ Observer 1 (Professor)
    ├→ Observer 2 (Administrador)
    └→ Observer N
\`\`\`

### 2. Decorator Pattern
\`\`\`
Curso Básico (100)
    ↓
    + Certificado (50)
    ↓
    + Suporte (30)
    ↓
    + Materiais (25)
    = Preço Total: 205
\`\`\`

### 3. Listener Pattern
\`\`\`
NotificacaoService
    ↓
    └→ AdicionarListener()
        ├→ Listener 1
        ├→ Listener 2
        └→ Listener N
\`\`\`

## Estrutura de Diretórios

\`\`\`
src/main/java/com/educacao/cursos/
├── gui/
│   └── DashboardFrame.java
├── graphics/
│   └── GraficoDesempenho.java
├── reports/
│   ├── RelatorioExporter.java
│   └── PDFGenerator.java
├── notifications/
│   └── NotificacaoService.java
└── patterns/
    ├── ObserverPattern.java
    └── DecoratorPattern.java
\`\`\`

## Exemplos de Uso

### Criar Notificação
\`\`\`java
NotificacaoService.criarNotificacao(
    "Nota Publicada",
    "Sua nota de Programação foi divulgada",
    NotificacaoService.TipoNotificacao.NOTA
);
\`\`\`

### Exportar Relatório
\`\`\`java
String[][] dados = {
    {"João Silva", "8.5", "Aprovado"},
    {"Maria Santos", "9.2", "Aprovado"}
};
String[] headers = {"Aluno", "Nota", "Status"};

RelatorioExporter.exportarHTML("relatorio.html", "Relatório Final", dados, headers);
\`\`\`

### Usar Decorator
\`\`\`java
Componente curso = new CursoBasico();
curso = new ComCertificado(curso);
curso = new ComSuporte(curso);
curso = new ComMateriais(curso);

System.out.println(curso.obterDescricao()); // Curso Básico + Certificado + Suporte + Materiais
System.out.println(curso.obterPreco());    // 205.0
\`\`\`

### Implementar Observer
\`\`\`java
MonitoradorCursos monitor = new MonitoradorCursos();
monitor.adicionarObservador(new ProfessorObserver("Dr. Silva"));
monitor.adicionarObservador(new AdministradorObserver());

monitor.inscreverAluno("Programação I", "João");
// [PROFESSOR Dr. Silva] Notificado: Novo aluno inscrito em Programação I: João
// [ADMINISTRADOR] Notificado: Novo aluno inscrito em Programação I: João
\`\`\`

## Como Executar Sprint 3

\`\`\`bash
# 1. Compilar
mvn clean compile

# 2. Executar
mvn exec:java -Dexec.mainClass="com.educacao.cursos.Main"

# 3. Abrir Dashboard
# new DashboardFrame().setVisible(true);

# 4. Gerar Relatórios
# RelatorioExporter.exportarHTML("resultado.html", dados, headers);

# 5. Gerar Certificados
# PDFGenerator.gerarCertificadoPDF("João Silva", "Programação I", "2025-01-20", "certificado");
\`\`\`

## Novos Conceitos OOP

### Design Patterns
1. **Observer**: Desacoplamento entre objetos
2. **Decorator**: Composição sobre herança
3. **Listener**: Event-driven programming

### SOLID Principles
- **Single Responsibility**: Cada classe tem uma responsabilidade
- **Open/Closed**: Aberto para extensão, fechado para modificação
- **Liskov Substitution**: Substitibilidade de tipos
- **Interface Segregation**: Interfaces específicas
- **Dependency Inversion**: Depender de abstrações

## Melhorias Técnicas

### Rendering Gráfico
- Graphics2D com antialiasing
- RenderingHints para qualidade
- Cálculo dinâmico de escalas

### Exportação de Dados
- Múltiplos formatos (CSV, HTML)
- Geração automática de timestamps
- Estrutura formatada profissionalmente

### Notificações
- Padrão Observer implementado
- Listeners customizáveis
- Histórico completo de notificações

## Arquitetura Completa (Sprint 1 + 2 + 3)

\`\`\`
Apresentação (Sprint 3)
    ├── DashboardFrame
    ├── GraficoDesempenho
    ├── Relatórios (PDF, CSV, HTML)
    └── Notificações

Aplicação (Sprint 1 + 2)
    ├── Services (Cursos, Alunos, Provas)
    ├── Padrões (Observer, Decorator)
    └── Autenticação (AuthService)

Dados (Sprint 2)
    ├── Banco de Dados (MySQL)
    ├── Repositories (DAO)
    └── ConnectionFactory

Models (Sprint 1)
    ├── Entidades (Pessoa, Aluno, Curso)
    └── Utilitários
\`\`\`

## Próximos Passos

- Integração completa de Dashboard com dados reais
- Implementação de mais Padrões (Factory, Strategy, State)
- Testes unitários (JUnit)
- Deploy em servidor aplicação
