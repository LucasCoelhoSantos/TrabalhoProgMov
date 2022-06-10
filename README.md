# Trabalho de Programação para Dispositivos Móveis

O objetivo deste trabalho é a criação de um aplicativo de quizzes com login para o Sistema Android com o foco em testes e recursos (fragmentos, strings, cores, imagens, sons, notificações, menu, alarme, câmera, mapas).

## Visão Geral da aplicação

A aplicação “UFMS QUIZ” trata da realização de quizzes. A realização dos quizzes é feita pelo usuário diretamente, através do aplicativo móvel. Nessa aplicação, é necessário que o usuário se registre para acessar o conteúdo e as funcionalidades do App, onde os quizzes serão exibidos.

## Classes de Usuários

**Usuário:** Tem a permissão para responder os quizzes, criar sua conta e gerenciar seus dados, realizar login, checar o resultado de quizzes anteriores e alterar as configurações pré-disponibilizadas no aplicativo.

## Definição de Conceitos

- Quiz: Conjunto de perguntas cujas respostas levarão a resultados diferentes baseados em configurações determinadas pelos desenvolvedores.
- Usuário: Pessoa que utilizará o app.
- Login: Ação realizada pelo usuário que permite o acesso à sua conta dentro da aplicação.

## Requisitos funcionais

### Lançamentos diversos:

- RF-1. A aplicação deve permitir o cadastro do usuário com os seguintes atributos: nome de usuário,  e-mail e senha.
- RF-2. A aplicação deve permitir a alteração e exclusão da conta no app por parte do usuário, com os seguintes atributos: nome de usuário, email e senha.
- RF-3. A aplicação deverá disponibilizar diferentes quizzes para o usuário.
- RF-4. A aplicação deverá permitir ao usuário logado, a ação de responder os quizzes disponíveis.
- RF-5. A aplicação deverá retornar para o usuário que respondeu um quiz, uma resposta baseada em suas escolhas.
- RF-6. A aplicação deve permitir ao usuário visualizar os quizzes concluídos e a sua pontuação.

### Controle e nível de acesso:

- RF-7. A aplicação deve exigir que os usuários façam login.
- RF-8. A aplicação deve emitir uma mensagem de erro em caso das credenciais de acesso, no momento de login não forem condizentes com nenhuma conta já criada.
- RF-9. A aplicação deve emitir uma mensagem de erro em caso das credenciais de acesso, no momento de cadastro forem condizentes com alguma conta já criada.

## Requisitos não-funcionais

### Disponibilidade:

- RNF-1. A aplicação deve estar disponível 24hs por dia, 7 dias por semana, com ressalvas em momentos de manutenção previamente avisados.

### Segurança:

- RNF-2. Todas as senhas da aplicação devem ser criptografadas.

### Manutenibilidade:

- RNF-3. A aplicação deve ser implantada em módulos, permitindo a adição, exclusão ou alteração de partes da aplicação sem afetar o seu funcionamento total.

### Portabilidade:

- RNF-4. A aplicação deve ser compatível com Android 5.1 ou superior.
- RNF-5. A aplicação deve ser capaz de armazenar os dados em base de dados SQLite.

## Histórico de versões do documento

|Versão|Publicação   |Autor(es)|Ações realizadas|
|:----:|:-----------:|:-------:|:---------------|
|1.0   |09/Junho/2022|Lucas de Carvalho Vilela Welter<br>Lucas Coelho Santos<br>Christian Nilles|Elaboração inicial do documento de requisitos, com criação de introdução, visão geral, definição de conceitos e requisitos, funcionais, não funcionais e histórico de versões;|
|1.1|10/Junho/2022|Lucas de Carvalho Vilela Welter<br>Lucas Coelho Santos<br>Christian Nilles|Alterações finais em diversas partes do documento;<br>Ajuste, detalhamento e revisão dos requisitos finais;|

## Alunos

- [Christian Nilles](https://github.com/ChristianNilles)
- [Lucas Coelho Santos](https://github.com/LucasCoelhoSantos)
- [Lucas de Carvalho Vilela Welter](https://github.com/boltwelter123)

## Professora

- [Ana Karina](https://www.github.com/anakarinafacom)
