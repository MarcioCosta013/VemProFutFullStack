# VemProFut Programa

A ideia é fazer um programa para organizar o futebol nosso de cada dia, e dentro disso fazer um ranqueamento <br>
por notas de atuação, ter historico de cartão amarelo, ter cronometro e sorteio de times, deve ser um programa <br>
de PC, um Web e um app que acessem ao mesmo banco de dados e sincronizam.

## Protótipos ou Wireframe

#### Web:


#### Desktop:


#### Mobile:



## Entender o objetivo do sistema:

- Qual problema ou necessidade esse sistema resolve?
    - fazer um ranqueamento por notas de atuação, ter historico de cartão amarelo, ter cronometro e sorteio de <br> times.

- Quem vai usar o programa ou app? (usuário final)
    - de inicio ADMs, mas depois escalar para todos os participantes da paleda.

- Quais benefícios ele deve trazer?
    - Melhor organizaçao e rapides com os sorteios.

## Definir Prioridades e Cronograma:

- Quais requisitos são essenciais para a primeira versão (MVP)?
    - Criar Partida, com cronometro e Peladeiros e armazenar informacoes do jogo.

## Identificar os stakeholders:

- Clientes : Peladeiros.
- Equipe de desenvolvimento: Marcio Costa
- Donos ou responsáveis pelo projeto: Marcio Costa
- Parceiros ou fornecedores: XXX


## Levantamento de Requisitos:

### Requisitos Funcionais:

- Login Peladeiro com Google, facebook ou Email.
- Cadastro de Peladeiro com Google, facebook ou Email.
    - Nome
    - Apelido
    - Pé que chuta: Canhoto, Destro ou Ambidestro.
    - Foto
    - Descrição
    - WhatsApp
- Fut: 
    - Criar Fut.
        - ADM:
            - Nome do Fut.
            - Numero de Jogadores por time.
            - Tempo de cada partida.
            - Numero de Gols Para Terminar a Partida.
            - Adicionar Peladeiros
                - Via Peladeiros Cadastrados
                - Via Lista em Texto
            - ADM pode transformar outros Peladeiros em Editor do Fut também.
            - Só o ADM pode tornar outras pessoas Editor.
            - Banimento de futuros jogos.
                

    - Futs cadastrado:
        - Iniciar e Encerrar Fut.
            - Com ou sem reservas?
            - Adicionar Peladeiros do dia:
                - Via Cadastro
                - Via Lista em Texto
            - Com Sorteio dos 8 primeiros a chegarem.(indicar quem chegou)

        - Ciclos de Partidas:
            - Novo placar zerado.
            - Sorteio dos próximos times
            - Novo Cronometro
                - Se terminar empatado, pênalte - 4X4.
            - Historico de Cartão
            - Historico e Dados do dia das Ultimas Partidas:
                - Artilheiro
                - Melhor Nota
                - Time que mais Ganhou mais Partidas
                - Pessoa que mais Ganhou mais Partidas
        - Historico geral de Todas as Partidas do Fut:
            - Ranking Artilheiro
            - Ranking Melhor Nota
            - Ranking Time que mais Ganhou
            - Ranking Pessoa que mais Ganhou Partidas
- Historico do Peladeiro
    - Gols
    - Nota
    - Partidas Jogadas
    - Cartãos Azuis, Amarelos e Vermelhos

    #### Tipos de Conta e Níveis de acesso dentro do Fut

    1 - Convidado/Peladeiro: 

        - Só Leitura
        - Solicitar para entrar no Fut.

    2 - Editor: 

        - Todas as atribuições de Convidado/Peladeiro.
        - Iniciar e Encerrar o Fut.
        - Aceitar Solicitação de Peladeiro para participar do Fut
        - Define numero de jogadores por time no inicio do Fut
        - Adiciona e excluir os peladeiros do dia
        - Define ordem de chegada dos Peladeiros
        - Com ou sem reservas?

    3 - Administrador: 

        - Todas as atribuições de Editor
        - Dar e tirar nivel Editor;
        - Dar Banimento.
            - Quantos jogos?
                    - 1
                    - 3 
                    - 5
                    - Permanente
        - Tirar o Banimento.
        - Exclui o Fut


### Requisitos Não funcionais:

- Interface intuitiva e responsiva.
- Deve funcionar Windows 10+, Web e Android 14+.
- A Web deve ser só para consulta.
- Desktop e Mobile que podem açoes como iniciar partidas, adicionar dados e etc...


## Plataformas e Integrações

- Será um aplicação Web, Descktop e Mobile.
- Sincroinização em Nuvem entre PC, Web e Mobile.
- Quais tecnologias ou frameworks serão usados?
    - Web:

    - Desktop:

    - Mobile: 

