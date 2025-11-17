# EXEMPLO DETALHADO - COMO ESTÁ CADA ARQUIVO

## 1. EXEMPLO: Pessoa.java (Classe Abstrata Base)

\`\`\`java
package com.educacao.cursos.models;

// ABSTRAÇÃO: Classe abstrata que não pode ser instanciada diretamente
public abstract class Pessoa {
    
    // ENCAPSULAMENTO: Atributos protected (acessíveis só pela família de classes)
    protected int id;
    protected String nome;
    protected String email;
    protected String telefone;

    // Construtor
    public Pessoa(int id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    // ENCAPSULAMENTO: Getters (apenas leitura)
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }

    // ENCAPSULAMENTO: Setters (apenas escrita controlada)
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    // POLIMORFISMO: toString() pode ser sobrescrito por subclasses
    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Email: " + email;
    }
}
\`\`\`

**Pilares OOP aqui:**
- ✓ **Abstração:** Classe abstrata não pode ser instanciada
- ✓ **Encapsulamento:** Atributos protected, getters/setters
- ✓ **Polimorfismo:** toString() para ser sobrescrito

---

## 2. EXEMPLO: Aluno.java (Herança)

\`\`\`java
package com.educacao.cursos.models;

import java.time.LocalDate;

// HERANÇA: Aluno estende Pessoa
public class Aluno extends Pessoa {
    
    // Atributo específico do Aluno
    private String matricula;
    private LocalDate dataCadastro;

    public Aluno(int id, String nome, String email, String telefone, String matricula) {
        // HERANÇA: Chama construtor da superclasse
        super(id, nome, email, telefone);
        this.matricula = matricula;
        this.dataCadastro = LocalDate.now();
    }

    // Getters específicos
    public String getMatricula() { return matricula; }
    public LocalDate getDataCadastro() { return dataCadastro; }

    // POLIMORFISMO: Sobrescreve toString() da superclasse
    @Override
    public String toString() {
        return super.toString() + " | Matrícula: " + matricula + " | Data: " + dataCadastro;
    }
}
\`\`\`

**Pilares OOP aqui:**
- ✓ **Herança:** extends Pessoa
- ✓ **Polimorfismo:** Sobrescreve toString()
- ✓ **Encapsulamento:** super() chama construtor pai

---

## 3. EXEMPLO: CursoService.java (Service com Lógica)

\`\`\`java
package com.educacao.cursos.services;

import com.educacao.cursos.models.Curso;
import com.educacao.cursos.models.Professor;
import java.util.ArrayList;
import java.util.List;

// SERVICE: Contém TODA lógica de negócio relacionada a Cursos
public class CursoService {
    
    // Armazena dados em memória (simulando banco de dados)
    private List<Curso> cursos;
    private int proximoId;

    public CursoService() {
        this.cursos = new ArrayList<>();
        this.proximoId = 1;
    }

    // RF001: CRIAR CURSO
    public void criarCurso(String nome, String descricao, Professor professor, 
                           int vagas, int cargaHoraria, String categoria) {
        
        // REGRA DE NEGÓCIO: Valida vagas
        if (vagas <= 0) {
            System.out.println("ERRO: Vagas deve ser maior que 0");
            return;
        }
        
        // Cria novo curso com ID automático
        Curso novoCurso = new Curso(proximoId++, nome, descricao, professor, vagas, cargaHoraria, categoria);
        
        // Armazena em memória
        cursos.add(novoCurso);
        
        // Atualiza relacionamento bidirecional
        professor.adicionarCurso(novoCurso);
        
        System.out.println("✓ Curso '" + nome + "' criado com sucesso!");
    }

    // BUSCAR CURSO POR ID
    public Curso buscarCursoPorId(int id) {
        // Usa stream() para busca funcional
        return cursos.stream()
            .filter(c -> c.getId() == id)
            .findFirst()
            .orElse(null);  // Retorna null se não encontrar
    }

    // LISTAR TODOS
    public List<Curso> listarTodosCursos() {
        return new ArrayList<>(cursos);  // Retorna cópia para evitar modificações
    }

    // FILTRAR POR CATEGORIA (Stream API)
    public List<Curso> listarCursosPorCategoria(String categoria) {
        return cursos.stream()
            .filter(c -> c.getCategoria().equalsIgnoreCase(categoria))
            .toList();
    }

    // CONTAR TOTAL
    public int getTotalCursos() {
        return cursos.size();
    }

    // EXIBIR TODOS (FOR EACH + LAMBDA)
    public void exibirTodosCursos() {
        System.out.println("\n=== TODOS OS CURSOS ===");
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado.");
        } else {
            cursos.forEach(System.out::println);  // Method reference
        }
    }
}
\`\`\`

**Conceitos aqui:**
- ✓ List<Curso> para persistência em memória
- ✓ Stream API e Lambda expressions (Java 8+)
- ✓ Regras de negócio validadas (vagas > 0)
- ✓ ID auto-incremento
- ✓ Relacionamentos (professor.adicionarCurso)

---

## 4. EXEMPLO: InscricaoService.java (Validações)

\`\`\`java
package com.educacao.cursos.services;

import com.educacao.cursos.models.Aluno;
import com.educacao.cursos.models.Curso;
import com.educacao.cursos.models.Inscricao;
import java.util.ArrayList;
import java.util.List;

// SERVICE: Gerencia inscrições com VALIDAÇÕES
public class InscricaoService {
    
    private List<Inscricao> inscricoes;
    private int proximoId;

    public InscricaoService() {
        this.inscricoes = new ArrayList<>();
        this.proximoId = 1;
    }

    // RF003: INSCREVER ALUNO COM VALIDAÇÕES
    public void inscreverAluno(Aluno aluno, Curso curso) {
        
        // VALIDAÇÃO 1: Aluno é nulo?
        if (aluno == null) {
            System.out.println("ERRO: Aluno inválido");
            return;
        }
        
        // VALIDAÇÃO 2: Curso é nulo?
        if (curso == null) {
            System.out.println("ERRO: Curso inválido");
            return;
        }
        
        // VALIDAÇÃO 3: Aluno já inscrito neste curso?
        boolean jaInscrito = inscricoes.stream()
            .anyMatch(i -> i.getAluno().getId() == aluno.getId() && 
                          i.getCurso().getId() == curso.getId());
        
        if (jaInscrito) {
            System.out.println("ERRO: " + aluno.getNome() + 
                             " já está inscrito em " + curso.getNome());
            return;
        }
        
        // VALIDAÇÃO 4: Vagas disponíveis?
        if (curso.getVagasDisponíveis() <= 0) {
            System.out.println("ERRO: Sem vagas disponíveis em " + curso.getNome());
            return;
        }
        
        // TODAS AS VALIDAÇÕES PASSARAM - Criar inscrição
        Inscricao novaInscricao = new Inscricao(proximoId++, aluno, curso);
        inscricoes.add(novaInscricao);
        
        // Atualizar vaga no curso
        curso.decrementarVagas();
        
        System.out.println("✓ " + aluno.getNome() + 
                         " inscrito em " + curso.getNome());
    }

    // BUSCAR INSCRIÇÃO
    public Inscricao buscarInscricaoPorId(int id) {
        return inscricoes.stream()
            .filter(i -> i.getId() == id)
            .findFirst()
            .orElse(null);
    }

    // LISTAR TODAS
    public List<Inscricao> listarTodas() {
        return new ArrayList<>(inscricoes);
    }

    // CONTAR TOTAL
    public int getTotalInscricoes() {
        return inscricoes.size();
    }
}
\`\`\`

**Conceitos aqui:**
- ✓ Múltiplas validações em cascata
- ✓ anyMatch() para verificar duplicata
- ✓ Retorno cedo (early return pattern)
- ✓ Atualização de estado relacionado (vagas)
- ✓ Mensagens de erro descritivas

---

## 5. EXEMPLO: Main.java (Orquestra)

\`\`\`java
package com.educacao.cursos;

import com.educacao.cursos.models.*;
import com.educacao.cursos.services.*;
import com.educacao.cursos.utils.Gerador;

public class Main {
    
    public static void main(String[] args) {
        // ETAPA 0: APRESENTAÇÃO
        Gerador.imprimirTitulo("SISTEMA DE GESTÃO DE CURSOS ONLINE");
        System.out.println("Iniciando simulação completa do sistema...\n");

        // ETAPA 1: INSTANCIAR TODOS OS SERVIÇOS
        CursoService cursoService = new CursoService();
        AlunoService alunoService = new AlunoService();
        InscricaoService inscricaoService = new InscricaoService();
        ProvaService provaService = new ProvaService();
        CertificadoService certificadoService = new CertificadoService();
        RelatorioService relatorioService = new RelatorioService(
            cursoService, alunoService, inscricaoService, certificadoService
        );

        // ETAPA 2: CADASTRAR PROFESSORES
        Gerador.imprimirTitulo("1. CADASTRANDO PROFESSORES");
        Professor prof1 = new Professor(1, "Dr. Carlos Silva", "carlos@edu.com", "11987654321", "OOP");
        Professor prof2 = new Professor(2, "Dra. Ana Costa", "ana@edu.com", "11987654322", "Web");
        System.out.println("Professor 1: " + prof1.getNome());
        System.out.println("Professor 2: " + prof2.getNome());

        // ETAPA 3: CRIAR CURSOS (chama CursoService)
        Gerador.imprimirTitulo("2. CADASTRANDO CURSOS");
        cursoService.criarCurso("Java Avançado", "...", prof1, 30, 40, "Programação");
        cursoService.criarCurso("React", "...", prof2, 25, 36, "Web");
        cursoService.exibirTodosCursos();

        // ... CONTINUA COM OUTRAS ETAPAS ...

        // ÚLTIMA ETAPA: RESUMO FINAL
        Gerador.imprimirTitulo("SIMULAÇÃO FINALIZADA");
        System.out.println("\nResumo:");
        System.out.println("- Total de Cursos: " + cursoService.getTotalCursos());
        System.out.println("- Total de Alunos: " + alunoService.getTotalAlunos());
        System.out.println("- Total de Certificados: " + certificadoService.getTotalCertificadosEmitidos());
    }
}
\`\`\`

**Conceitos aqui:**
- ✓ Orquestra os serviços
- ✓ Segue fluxo de negócio passo a passo
- ✓ Demonstra todos os RF001-RF010
- ✓ Exibe informações claras
- ✓ Sem lógica complexa (deixa para Services)

---

## RESUMO DA SEPARAÇÃO

| Camada | Responsabilidade | Exemplo |
|--------|------------------|---------|
| **models/** | Representar dados | Pessoa, Aluno, Curso |
| **services/** | Lógica de negócio | CursoService valida vagas |
| **utils/** | Funções auxiliares | ValidadorDados.validarEmail() |
| **Main.java** | Orquestração | Chama serviços em sequência |

---

## FLUXO TÍPICO

\`\`\`
Main.java
  ↓
alunoService.cadastrarAluno("João", "joao@mail.com", "1198765")
  ↓
ValidadorDados.validarEmail("joao@mail.com") ← Valida
  ↓
new Aluno(id, nome, email, telefone) ← Cria modelo
  ↓
alunos.add(novoAluno) ← Armazena
  ↓
Retorna sucesso para Main
  ↓
Main exibe mensagem
\`\`\`

---

## CONCLUSÃO

Cada arquivo segue um propósito específico e bem definido:

- **Models:** Apenas dados + getters/setters
- **Services:** Apenas lógica de negócio
- **Utils:** Apenas funções reutilizáveis
- **Main:** Apenas orquestra o fluxo

Isso torna o código:
- ✓ Fácil de entender
- ✓ Fácil de manter
- ✓ Fácil de estender
- ✓ Profissional
- ✓ Pronto para LP1
