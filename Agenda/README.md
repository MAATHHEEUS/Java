# 📱 Aplicativo Agenda de Contatos

## 📌 Descrição do Projeto

Este é um aplicativo Android desenvolvido com **Java** e **XML** no **Android Studio**, que simula uma **agenda de contatos**. O app permite **cadastrar, visualizar, editar e excluir contatos**, utilizando o banco de dados **SQLite** para armazenamento local.

O objetivo do projeto é demonstrar o uso de componentes visuais no Android, integração com banco de dados local e navegação entre telas em um aplicativo simples e funcional.

---

## 👨‍💻 Autores

- Matheus Carvalho Oliveira — Desenvolvedor Android

---

## 📲 Instalação no Celular Android

### ✅ Pré-requisitos

- Um celular Android com **Android 6.0** ou superior

### 🔧 Passos para Instalação

1. **Baixe o arquivo .apk no seu celular Android**:
   Se necessário, ative a opção "Instalar apps de fontes desconhecidas" nas configurações do dispositivo.

2. **Aceite todas as permissões de instalação**.
3. **Após a instalação o app está pronto para ser aberto**.

### ⚙️ Funcionalidades

O aplicativo possui quatro telas principais:

###🏠 Tela Inicial
- Tela com uma interface inicial com dois botões para cada página seguinte.

### ➕ Tela de Cadastro
- Campos para inserir:
    Nome, Telefone, E-mail, Data de aniversário.
- Botão "Salvar" grava os dados no banco SQLite.

### 🔍 Tela de Detalhes
- Mostra todas as informações do contato.

- Botões "Editar" e "Excluir".

### ✏️ Tela de Edição
- Mesma estrutura da tela de cadastro.

- Permite atualizar os dados de um contato já existente.

### 💾 Banco de Dados SQLite
- Implementado com uma classe que estende SQLiteOpenHelper.

- Contém uma tabela chamada contatos com os campos:
    id (INTEGER, chave primária, autoincremento), nome (TEXT), telefone (TEXT), email (TEXT), nascimento (TEXT)

- Métodos implementados:
    inserirContato(), listarContatos(), atualizarContato(), excluirContato()

- Persistência de dados local, sem necessidade de conexão com a internet.

### 🧰 Tecnologias Utilizadas
- Android Studio
- Java
- XML (interface de usuário)
- SQLite (banco de dados local)
- ConstraintLayout

### 📝 Licença
- Este projeto é de livre uso para fins educacionais.
- Para outros usos, entre em contato com o autor.