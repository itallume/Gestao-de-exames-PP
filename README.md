# 🏥 Sistema de Gerenciamento de Exames Médicos - IF Diagnósticos

## 👥 Equipe

- **Itallo Oliveira** - 20231370014  
- **João Paulo** - 20231370018  
- **Fernando Júlio** - 20231370025
- **Lauro Stephan** - 20231370026  


## 📚 Descrição do Projeto

Este projeto foi desenvolvido para a disciplina Padrões de projeto, que tem como objetivo de atender às necessidades da clínica **IF Diagnósticos** (Fictícia), com foco na automatização da geração de laudos médicos, notificações de pacientes e aplicação de políticas flexíveis como descontos e gerenciamento de prioridades. O sistema foi arquitetado com **padrões de projeto** para garantir **extensibilidade**, **manutenção simplificada** e **reusabilidade de código**.

---

## 🎯 Requisitos Atendidos

O sistema cumpre os requisitos funcionais descritos no enunciado da atividade, tais como:

- ✅ Leitura de dados de arquivos CSV (`R1`)
- ✅ Geração única e sequencial de exames (`R2`)
- ✅ Emissão de diferentes tipos de exames (`R3`)
- ✅ Suporte a múltiplos formatos de laudos (`R4`)
- ✅ Validação específica para cada exame (`R5`)
- ✅ Notificação automática do paciente (`R6`)
- ✅ Aplicação de políticas de desconto dinâmicas (`R7`)
- ✅ Priorização de exames por urgência (`R8`)
- ✅ Programa principal que simula a execução (`R9`)
- ✅ Logs da aplicação (`R10`)

---

## 🧩 Padrões de Projeto Utilizados

| Requisito | Padrão | Aplicação |
|-----------|--------|-----------|
| `R1` | **Template Method** | Utilizado em `CarregadorDeDados`, define o esqueleto da leitura de arquivos CSV. Subclasses implementam o método `extrairObjeto(dados)` para pacientes, médicos etc. |
| `R2` | **Singleton** | `GeradorDeID` garante que a geração de identificadores de exames seja única e centralizada. |
| `R3 & R4` | **Bridge** | Separação entre abstração (`ExameProcedimento`) e implementação (`ILaudo`) dos formatos de laudo (PDF, HTML, texto etc). Permite adicionar novos exames ou formatos sem alterar o código existente. |
| `R5` | **Chain of Responsibility** | Encadeamento de validadores através da interface `IValidador`. `ValidadorFachada` cria chains específicos para cada tipo de exame (endoscópicos, imagem, laboratorial). |
| `R6` | **Observer** | Notifica o paciente quando o laudo estiver pronto através da interface `INotificador`. Diferentes implementações (Email, SMS) podem ser adicionadas. |
| `R7` | **Visitor + Decorator** | **Visitor**: `PrecoVisitor` calcula preços baseados no tipo de exame. **Decorator**: `DescontoBase` e subclasses aplicam descontos combináveis (idoso, convênio, campanhas). |
| `R8` | **Strategy** | Fila de prioridade (`FilaPrioridadeExame`) usa estratégias de inserção (`InserirExameStrategy`) diferentes para cada nível de prioridade (URGENTE, POUCO URGENTE, ROTINA). |
| `R9` | **Facade** | `LaboratorioFachada` fornece interface unificada para todas as operações do sistema, simplificando a interação com os subsistemas complexos. |
| `R10` | **Singleton** | Armazena os logs da aplicação de forma centralizada. |
---

## 📌 Diagrama de Classes

![Diagrama de Classes](./sistema-clinica.svg)
