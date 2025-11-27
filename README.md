# Sistema de Gestão de Cursos Online

## Informações do Projeto

**Disciplina:** LP1 - Linguagem de Programação I  
**Professora:** Fernanda Dias  
**Tema:** Educação e Inovação  
**Equipe:** [Membro 1, Membro 2]  
**Líder:** [Nome do Líder]

---

## Descrição do Problema

A educação online cresce exponencialmente, porém muitos professores carecem de ferramentas para gerenciar seus cursos, alunos e avaliações de forma integrada. Este sistema foi desenvolvido para solucionar essa problemática, oferecendo uma plataforma completa de gestão acadêmica.

### Regras de Negócio Implementadas

1. **Cadastro de Cursos**: Professores podem criar cursos com descrição, carga horária e limite de vagas
2. **Gerenciamento de Alunos**: Sistema completo para cadastro e atualização de dados de alunos
3. **Inscrição em Cursos**: Alunos podem se inscrever em cursos respeitando limite de vagas
4. **Gestão de Aulas**: Organização de conteúdo por aula dentro de cada curso
5. **Sistema de Avaliação**: Criação de provas com questões e respostas automáticas
6. **Cálculo Automático de Notas**: Média calculada automaticamente a cada nova avaliação
7. **Geração de Certificados**: Certificados automáticos para alunos com média >= 7.0
8. **Relatórios de Desempenho**: Relatórios individuais e gerais do sistema
9. **Dashboard com Estatísticas**: Visão geral do desempenho dos cursos e alunos
10. **Simulação Completa**: Main com execução de todas as operações essenciais

---

## Arquitetura do Projeto

### Estrutura de Diretórios

\`\`\`
src/main/java/com/educacao/cursos/
├── models/
│   ├── Pessoa.java          (classe abstrata)
│   ├── Aluno.java
│   ├── Professor.java
│   ├── Curso.java
│   ├── Inscricao.java
│   ├── Aula.java
│   ├── Prova.java
│   ├── Questao.java
│   └── Certificado.java
├── services/
│   ├── CursoService.java
│   ├── AlunoService.java
│   ├── InscricaoService.java
│   ├── ProvaService.java
│   ├── CertificadoService.java
│   └── RelatorioService.java
├── utils/
│   ├── ValidadorDados.java
│   └── Gerador.java
└── Main.java
\`\`\`

### Camadas da Aplicação

1. **Models (Modelos)**: Entidades do domínio com lógica básica
2. **Services (Serviços)**: Lógica de negócio e orquestração
3. **Utils (Utilitários)**: Funções auxiliares e validações

---

## Pilares da Orientação a Objetos Implementados

### 1. Encapsulamento
- Uso de modificadores de acesso (private, public)
- Getters e setters para acesso controlado a atributos
- Isolamento de dados sensíveis

### 2. Herança
- Classe abstrata `Pessoa` com atributos comuns
- `Aluno` e `Professor` herdam de `Pessoa`
- Reutilização de código através da hierarquia

### 3. Polimorfismo
- Sobrescrita de métodos `toString()` em classes filhas
- Uso de interfaces implícitas através de métodos abstratos
- Tratamento polimórfico de objetos

### 4. Abstração
- Classe `Pessoa` abstrata define contrato
- Services abstraem a lógica complexa
- Usuário interage apenas com métodos públicos

---

## Funcionalidades Principais

### RF001: Cadastro de Cursos
\`\`\`java
cursoService.criarCurso(nome, descricao, professor, vagas, cargaHoraria, categoria);
\`\`\`

### RF002: Cadastro de Alunos
\`\`\`java
alunoService.cadastrarAluno(nome, email, telefone);
\`\`\`

### RF003: Inscrição em Cursos
\`\`\`java
inscricaoService.inscreverAluno(aluno, curso);
\`\`\`

### RF004: Gestão de Aulas
\`\`\`java
Aula aula = new Aula(id, curso, titulo, conteudo, duracao);
curso.adicionarAula(aula);
\`\`\`

### RF005: Criação de Provas
\`\`\`java
provaService.criarProva(curso, titulo);
provaService.adicionarQuestao(provaId, enunciado, resposta, opcoes);
\`\`\`

### RF006: Cálculo Automático de Notas
\`\`\`java
provaService.registrarNotaAluno(inscricao, nota);
// Média calculada automaticamente
\`\`\`

### RF007: Geração de Certificados
\`\`\`java
certificadoService.emitirCertificado(inscricao);
// Automático apenas para aprovados (média >= 7.0)
\`\`\`

### RF008: Relatório de Desempenho
\`\`\`java
relatorioService.gerarRelatorioDesempenhoAluno(alunoId);
\`\`\`

### RF009: Dashboard
\`\`\`java
relatorioService.gerarDashboard();
\`\`\`

### RF010: Simulação Completa
Execute `Main.java` para ver todas as operações em ação

---

## Como Compilar e Executar

### Compilação
\`\`\`bash
javac -d bin src/main/java/com/educacao/cursos/**/*.java
\`\`\`

### Execução
\`\`\`bash
java -cp bin com.educacao.cursos.Main
\`\`\`

### Ou com Maven
\`\`\`bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.educacao.cursos.Main"
\`\`\`

---

## Validações Implementadas

| Validação | Regra |
|-----------|-------|
| Email | Deve conter "@" e "." |
| Telefone | Mínimo 10 dígitos |
| Nome | Mínimo 3 caracteres |
| Nota | Valor entre 0 e 10 |
| Carga Horária | Entre 1 e 1000 horas |
| Vagas | Mínimo 1, máximo 500 |

---

## Relatórios Gerados

1. **Dashboard Geral**: Estatísticas do sistema
2. **Relatório Individual**: Desempenho por aluno
3. **Taxa de Aprovação**: Por curso
4. **Cursos com Maior Demanda**: Top 3

---

## Tecnologias Utilizadas

- Linguagem: Java 11+
- Paradigma: Orientado a Objetos
- Build: Maven (opcional)
- Padrões: MVC-like, Service Layer

---

## Equipe

- **Membro 1**: Responsável pelos requisitos RF001, RF002, RF005, RF006, RF009, RF010
- **Membro 2**: Responsável pelos requisitos RF003, RF004, RF007, RF008

---

## Observações Importantes

- Todas as operações essenciais do domínio foram implementadas
- O sistema é totalmente funcional de ponta a ponta
- A simulação no Main demonstra todos os recursos
- Segue os padrões da estrutura Spring Boot (mesmo sem framework)

---

## Licença

Projeto acadêmico - LP1

---

## Histórico de Versões

| Versão | Data | Desenvolvedor | Alterações |
|--------|------|---------------|-----------|
| 1.0.0 | 2024 | Membro 1 | Versão inicial com todas as funcionalidades |
| 1.0.0 | 2024 | Membro 2 | Integração dos módulos e testes |
