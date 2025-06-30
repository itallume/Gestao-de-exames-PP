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
- ✅ Logs da aplicação (`R10`)

---

## 🧩 Padrões de Projeto Utilizados

| Requisito | Padrão | Aplicação |
|----------|--------|-----------|
| `R1` | **Template Method** | Utilizado em `CarregadorDeDados`, define o esqueleto da leitura de arquivos CSV. Subclasses implementam o método `extrairObjeto(dados)` para pacientes, médicos etc. |
| `R2` | **Singleton** | `GeradorDeID` garante que a geração de identificadores de exames seja única e centralizada. |
| `R3 & R4` | **Bridge** | Separação entre abstração (`AExame`) e implementação (`IGerador`) dos formatos de laudo (PDF, HTML, texto etc). Permite adicionar novos exames ou formatos sem alterar o código existente. |
| `R5` | **Chain of Responsibility** | Encadeamento de validadores (`ValidadorHemograma`, `ValidadorUltrassonografia`, `ValidadorRessonancia`) através da interface `IValidador`. Cada validador aplica regras específicas de exame. |
| `R6` | **Observer + State** | Padrão Observer notifica o paciente quando o laudo estiver pronto, e o State gerencia o estado da notificação (pendente, enviada, falhou). |
| `R7` | **Strategy** | Aplicação de políticas de desconto configuráveis (idoso, convênio, campanhas). Estratégias podem ser adicionadas sem alterar a lógica principal. |
| `R8` | **Priority Queue (Comportamental)** | Fila de prioridade organiza os exames com base em rótulos: URGENTE, POUCO URGENTE e ROTINA. |
| `R10` | **Singleton** | Armazena os logs da aplicação |

---

## 📌 Diagrama de Classes

![Diagrama de Classes](./sistema-clinica.svg)
