# ALG
Projeto de Compiladores 

2.2. Atividade a ser desenvolvida
Desenvolver um programa computacional na linguagem escolhida que implemente:
1. Um analisador léxico que reconheça a tabela de tokens diponíveis na Tabela1 para
linguagem ALG.
2. Para tal, desenvolver um autômato finito determinístico em forma de tabela que
reconheça a linguagem regular expressa através da Tabela1.
3. Desenvolver uma tabela de Símbolos, utilizar estrutura de dados (sugestão – Tabela
hash) para armazenar as palavras-chave (Tabela 2) e identificadores da linguagem.
4. Para cada token identificado deverá ser armazenado o seu tipo e lexema.
5. O programa deverá ler como entrada o programa fonte apresentado na Figura 1 e
mostrar na tela o token reconhecido seguido de seus atributos. Qualquer elemento
diferente dos tokens definidos não será reconhecido pelo programa e este retornará
na tela ERRO.
6. Algumas definições:
a. Token – Classe de palavra identificada.
b. Lexema – é a palavra lida do texto do programa
c. Atributo – Característica identificada do token.
d. ERRO – Um token indicando que o analisador léxico encontrou um lexema
fora do padrão especificado.
