primeiro projeto de 2026
# 🍅 LuiX-System Pomodoro

Um aplicativo de desktop desenvolvido em Java para utilizar a técnica Pomodoro e auxiliar na organização dos estudos e das tarefas do dia a dia.

## 📌 Sobre o projeto

O **LuiX-System Pomodoro** é um projeto pessoal desenvolvido para praticar os conceitos da linguagem Java e o desenvolvimento de aplicações com interface gráfica.

O aplicativo utiliza Java Swing para apresentar sua interface e possui identidade visual própria.

## ✨ Funcionalidades

- Interface gráfica para utilizar o temporizador Pomodoro.
- Temporizador para períodos de foco e pausa.
- Ícone personalizado do aplicativo.

## 🛠️ Tecnologias utilizadas

- **Java** — linguagem de programação.
- **Java Swing** — desenvolvimento da interface gráfica.
- **Git e GitHub** — versionamento e hospedagem do código-fonte.

## 📂 Estrutura do projeto

```text
Pomodoro/
├── src/
│   ├── Pomodoro.java
│   ├── PomodoroApp.java
│   └── logo.png
├── .gitignore
└── README.md
```

Os arquivos compilados e o JAR executável são gerados na pasta `build/` durante a compilação.

## ▶️ Como executar

### Requisitos

- JDK compatível com o código-fonte.
- Terminal para executar os comandos abaixo.

### Compilar o projeto

Na pasta principal do projeto, execute:

```bash
mkdir -p build/classes
javac -d build/classes src/*.java
```

### Copiar a imagem utilizada pelo aplicativo

```bash
cp src/logo.png build/classes/src/
```

### Gerar o arquivo JAR

```bash
jar --create --file build/LuiX-System-Pomodoro.jar --main-class src.PomodoroApp -C build/classes .
```

### Executar o aplicativo

```bash
java -jar build/LuiX-System-Pomodoro.jar
```

## 🎯 Objetivo

Este projeto faz parte do meu aprendizado em programação, com foco no desenvolvimento de aplicações Java e na criação de interfaces gráficas.

## 👨‍💻 Autor

**Rich Diniz**

---

Projeto pessoal desenvolvido para fins de aprendizado e prática de programação.

