# Sprint 2 - Banco de Dados, Autenticação e Interface Gráfica

## Objetivo
Transformar o Sistema de Gestão de Cursos de um programa console para uma aplicação com persistência de dados e interface gráfica profissional.

## Requisitos Implementados

### 1. Camada de Banco de Dados
- **ConnectionFactory.java**: Gerencia conexões com MySQL
  - Configuração centralizada de credenciais
  - Método estático para obter conexões
  - Tratamento de exceções de conexão

- **DatabaseInitializer.java**: Cria schema inicial
  - Tabelas: usuarios, professores, alunos, cursos, inscricoes
  - Relacionamentos com Foreign Keys
  - Constraints de integridade

- **CursoRepository.java**: Padrão DAO para Cursos
  - CRUD básico (Create, Read)
  - Queries preparadas (PreparedStatement)
  - Proteção contra SQL Injection

### 2. Sistema de Autenticação
- **Usuario.java**: Modelo de usuário com tipos
  - Enum TipoUsuario: ALUNO, PROFESSOR, ADMIN
  - Encriptação de senha com hashCode
  - Validação de credenciais

- **AuthService.java**: Service de autenticação
  - Registro de novos usuários
  - Login com validação de email/senha
  - Rastreamento de usuário logado
  - Validação de email

### 3. Interface Gráfica Swing
- **LoginFrame.java**: Tela de login
  - Campo de email e senha
  - Seleção de tipo de usuário
  - Registro e login
  - Redirecionamento pós-login

- **MenuPrincipalFrame.java**: Menu pós-autenticação
  - Layout responsivo com GridLayout
  - Menus diferentes por tipo de usuário
  - Botões funcionais para diferentes operações
  - Logout com retorno ao login

## Padrões de Design Utilizados

### 1. Singleton Pattern
\`\`\`java
// ConnectionFactory usa padrão singleton implícito
public static Connection getConnection()
\`\`\`

### 2. DAO (Data Access Object)
\`\`\`java
// CursoRepository encapsula acesso aos dados
public class CursoRepository
\`\`\`

### 3. Service Pattern
\`\`\`java
// AuthService centraliza lógica de autenticação
public class AuthService
\`\`\`

## Estrutura de Diretórios

\`\`\`
src/main/java/com/educacao/cursos/
├── database/
│   ├── ConnectionFactory.java
│   └── DatabaseInitializer.java
├── auth/
│   ├── Usuario.java
│   └── AuthService.java
├── gui/
│   ├── LoginFrame.java
│   └── MenuPrincipalFrame.java
└── repositories/
    └── CursoRepository.java
\`\`\`

## Fluxo de Execução

1. **Inicialização**
   - ConnectionFactory estabelece conexão com MySQL
   - DatabaseInitializer cria tabelas se não existirem

2. **Login**
   - LoginFrame exibe interface
   - Usuário insere email, senha e tipo
   - AuthService valida credenciais no banco
   - MenuPrincipalFrame abre se sucesso

3. **Operações**
   - Usuário interage com menu baseado em seu tipo
   - Dados salvos no banco via Repositories

## Instruções de Configuração

### Requisitos
- MySQL instalado e rodando
- Driver MySQL JDBC (adicionar ao pom.xml)

### Configuração MySQL
\`\`\`sql
CREATE DATABASE sistema_cursos;
USE sistema_cursos;
\`\`\`

### Arquivo pom.xml
\`\`\`xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
\`\`\`

### ConnectionFactory
Ajustar credenciais:
\`\`\`java
private static final String URL = "jdbc:mysql://localhost:3306/sistema_cursos";
private static final String USER = "root";
private static final String PASSWORD = "sua_senha";
\`\`\`

## Como Executar Sprint 2

\`\`\`bash
# 1. Compilar
mvn clean compile

# 2. Executar
mvn exec:java -Dexec.mainClass="com.educacao.cursos.Main"

# Ou com GUI iniciada automaticamente
# new LoginFrame().setVisible(true);
\`\`\`

## Novos Padrões OOP

### Encapsulamento Avançado
- Modificadores de acesso (private, protected, public)
- Getters e Setters com validação
- Classe Usuario com métodos de segurança

### Herança e Polimorfismo
- TipoUsuario (enum com valores polimórficos)
- DecoradorCurso (preparado para Sprint 3)

## Desafios Resolvidos

1. **Segurança**: Encriptação de senhas com hashCode
2. **SQL Injection**: PreparedStatement para queries
3. **Conexões**: Factory pattern para gerenciar conexões
4. **UI**: Swing com layouts profissionais

## Próximas Melhorias (Sprint 3)

- Dashboard com gráficos
- Relatórios em PDF
- Sistema de notificações
- Padrões avançados (Observer, Decorator)
