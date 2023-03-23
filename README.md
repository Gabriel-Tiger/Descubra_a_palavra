# Descubra a Palavra

Esse é um joguinho em Kotlin feito como uma tarefa para aula de programação Android. O Objetivo da tarefa era realizar um jogo para Android com o tema de "Descubra a Palavra", onde tem que ter 5 fases, nessas fases deve ser informado um texto desafio com "_" no lugar de algumas letras. Deve conter também um teclado com letras, corretas e erradas, como desafio do jogo. No final das 5 fases deve ser aberto uma nova tela com a pontuação do jogador.

## Características

Feito para ler um CSV com palavras e dicas das palavras, ele seleciona aleatoriamente 5 palavras(e dicas) paras as 5 fases. Então ao iniciar a fase ele gera o desafio, selecionando aleatoriamente os caracteres para ocultar, deixando apenas 3 visíveis(por conta da verificação de letras iguais ou palavras muito pequenas, se ele removeu uma letra e outra não, ele vai ocultar essa letra, podendo gerar um desafio sem letras liberadas). 

Então nessa etapa possui uma variável com a palavra verdadeira, uma com o desafio e um com o gabarito de letras para completar. Os botões são gerados quando a fase inicializa, cada um recebe uma letra do alfabeto (letras normais, sendo aceitas para completar suas equivalentes, por exemplo A para Ã). Os botões são gerados com o background padrão caso a letra não seja uma das letras visíveis, caso o desafio tenha letras visíveis o botão já iniciara com o background de letra correta.

O OnClickListener dos botões executam uma função que valida a jogada. Ela pega a letra do botão, verifica se a palavra desafio contem essa letra e retorna um código de validação (0-letra errada  1-letra certa  2-finalizo  3-nulo(para não somar erros ou pontos ao pressionar a mesma tecla inúmeras vezes)). O jogo também possui um cronometro que conta o tempo total em todas as 5 fases, sendo pausado ao finalizar uma frase e retomado quando clicado a primeira vez em uma botão de tentativa.

No final das 5 fases será aberto uma nova tela com os resultados: Total de acertos (numero de letras corretas que foram pressionadas); Total de erros (numero de letras erradas que foram pressionadas) e o tempo total.

## Conteúdo de terceiros utilizados

[Papel de Parede](https://br.pinterest.com/pin/78883430965332973/)