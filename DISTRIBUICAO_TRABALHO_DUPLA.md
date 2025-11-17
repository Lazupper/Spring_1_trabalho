# DISTRIBUIÇÃO DO TRABALHO - DUPLA

## PROPOSTA DE SEPARAÇÃO

Para maximizar a produtividade e evitar conflitos, sugerimos dividir assim:

---

## LAURENT: CAMADA DE MODELOS + ALGUNS SERVICES

### Responsabilidades:

#### Models (100% - 6 arquivos):
- ✓ Pessoa.java (classe abstrata base)
- ✓ Aluno.java (extends Pessoa)
- ✓ Professor.java (extends Pessoa)
- ✓ Curso.java
- ✓ Aula.java
- ✓ Certificado.java

**Tempo estimado:** 1-2 horas

#### Services (50% - 3 arquivos):
- ✓ CursoService.java
- ✓ AlunoService.java
- ✓ CertificadoService.java

**Tempo estimado:** 2-3 horas

#### Utils:
- ✓ Gerador.java

**Tempo estimado:** 30 minutos

#### Main (Primeira metade):
- ✓ Passos 1-5 (Professores, Cursos, Alunos, Inscrições, Aulas)

**Tempo estimado:** 1 hora

---

## Nathan: MODELOS DE AVALIAÇÃO + SERVICES RESTANTES

### Responsabilidades:

#### Models (40% - 4 arquivos):
- ✓ Inscricao.java
- ✓ Prova.java
- ✓ Questao.java
- ✓ (Colaboração em Certificado.java)

**Tempo estimado:** 1-2 horas

#### Services (50% - 3 arquivos):
- ✓ InscricaoService.java
- ✓ ProvaService.java
- ✓ RelatorioService.java

**Tempo estimado:** 3-4 horas

#### Utils:
- ✓ ValidadorDados.java

**Tempo estimado:** 1 hora

#### Main (Segunda metade):
- ✓ Passos 6-13 (Provas, Notas, Certificados, Relatórios, Validações)

**Tempo estimado:** 1-2 horas

---

## CRONOGRAMA SUGERIDO

| Fase | Tarefa | Membro 1 | Membro 2 | Tempo |
|------|--------|----------|----------|-------|
| 1 | Criar Pessoa.java | ✓ | - | 30 min |
| 1 | Criar Aluno.java | ✓ | - | 30 min |
| 1 | Criar Professor.java | ✓ | - | 30 min |
| 2 | Criar Curso.java | ✓ | - | 30 min |
| 2 | Criar Aula.java | ✓ | - | 20 min |
| 2 | Criar Inscricao.java | - | ✓ | 20 min |
| 3 | Criar Prova.java | - | ✓ | 30 min |
| 3 | Criar Questao.java | - | ✓ | 20 min |
| 3 | Criar Certificado.java | ✓ | ✓ | 20 min |
| 4 | CursoService.java | ✓ | - | 45 min |
| 4 | AlunoService.java | ✓ | - | 45 min |
| 5 | InscricaoService.java | - | ✓ | 45 min |
| 5 | ProvaService.java | - | ✓ | 1h 30min |
| 6 | CertificadoService.java | ✓ | - | 30 min |
| 6 | RelatorioService.java | - | ✓ | 1h 30min |
| 7 | ValidadorDados.java | - | ✓ | 45 min |
| 7 | Gerador.java | ✓ | - | 30 min |
| 8 | Main.java (merge) | ✓ | ✓ | 2h |
| 9 | Testes + Ajustes | ✓ | ✓ | 1h |

**Total estimado:** 15-18 horas

---

## FLUXO DE TRABALHO COM GIT

### Setup Inicial (Juntos)
\`\`\`bash
# Membro 1 (Líder) cria repositório no GitHub
git init
git add .
git commit -m "Estrutura inicial do projeto"
git push origin main

# Membro 2 faz clone
git clone <link-do-repo>
\`\`\`

### Durante o Desenvolvimento

**Membro 1:**
\`\`\`bash
git checkout -b feature/models-e-services-1
# Desenvolve seus arquivos
git add src/main/java/com/educacao/cursos/models/
git add src/main/java/com/educacao/cursos/services/CursoService.java
git add src/main/java/com/educacao/cursos/services/AlunoService.java
git add src/main/java/com/educacao/cursos/services/CertificadoService.java
git add src/main/java/com/educacao/cursos/utils/Gerador.java
git commit -m "Implementar models e services 1"
git push origin feature/models-e-services-1
\`\`\`

**Membro 2:**
\`\`\`bash
git checkout -b feature/models-e-services-2
# Desenvolve seus arquivos
git add src/main/java/com/educacao/cursos/models/Inscricao.java
git add src/main/java/com/educacao/cursos/models/Prova.java
git add src/main/java/com/educacao/cursos/models/Questao.java
git add src/main/java/com/educacao/cursos/services/InscricaoService.java
git add src/main/java/com/educacao/cursos/services/ProvaService.java
git add src/main/java/com/educacao/cursos/services/RelatorioService.java
git add src/main/java/com/educacao/cursos/utils/ValidadorDados.java
git commit -m "Implementar models e services 2"
git push origin feature/models-e-services-2
\`\`\`

### Merge das Features
\`\`\`bash
# Membro 1 faz pull da main
git checkout main
git pull origin main

# Merge da feature 1
git merge feature/models-e-services-1

# Merge da feature 2 (sem conflitos pois arquivos diferentes)
git merge feature/models-e-services-2

# Ambos trabalham no Main.java
# Membro 1 adiciona passos 1-5
# Membro 2 adiciona passos 6-13
# Merge manual no Main.java

git add .
git commit -m "Merge: Main.java completo"
git push origin main
\`\`\`

---

## PONTOS DE INTEGRAÇÃO (CUIDADO!)

### Áreas que podem gerar conflito:

1. **Main.java** - Ambos mexem
   - Solução: Um faz 1-5, outro faz 6-13

2. **Relacionamentos entre Models**
   - Prova usa Questao
   - Inscricao usa Aluno + Curso
   - Solução: Definir interfaces bem clara

3. **Services que usam Services**
   - RelatorioService usa CursoService, AlunoService, InscricaoService
   - Solução: Implementar depois que os outros estão prontos

---

## TESTE DE INTEGRAÇÃO

Depois de fazer merge:

\`\`\`bash
# Compile tudo
javac -d bin src/main/java/com/educacao/cursos/**/*.java

# Execute
java -cp bin com.educacao.cursos.Main

# Verifique saída completa (13 etapas)
\`\`\`

Se passou em todas as 13 etapas = **INTEGRAÇÃO BEM-SUCEDIDA**

---

## HISTÓRICO DE COMMITS

Para fins de correção, o professor poderá ver:

\`\`\`bash
git log --oneline

Exemplo de saída esperada:
a1b2c3d Merge: Main.java completo - Membro 1 + Membro 2
e4f5g6h Implementar models e services 2 - Membro 2
i7j8k9l Implementar models e services 1 - Membro 1
m0n1o2p Estrutura inicial do projeto - Membro 1
\`\`\`

Cada membro terá seus commits individuais visíveis.

---

## VERIFICAÇÃO FINAL

Antes de entregar, confirme:

- ✓ Main.java executa completo
- ✓ Todas as 13 etapas de simulação funcionam
- ✓ Não há erros de compilação
- ✓ Não há NPE (NullPointerException)
- ✓ Histórico Git mostra commits de AMBOS membros
- ✓ README.md documentado
- ✓ Todos os RF001-RF010 funcionam

---

## SUGESTÕES FINAIS

1. **Comuniquem-se** - Combine interfaces dos Services antes de implementar
2. **Testes diários** - Façam merge e teste a cada fim de dia
3. **Documentem** - Adicione comentários no código
4. **GitHub do líder** - Apenas o link do líder é enviado
5. **Mas ambos devem programar** - A nota é individual (git log comprova)
