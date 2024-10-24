# 📚 Índice

* [Sobre o Projeto](#sobre-o-projeto)
* [Funcionalidades do Sistema](#funcionalidades-do-sistema)
* [Tecnologias utilizadas](#tecnologias-utilizadas)
* [Autores do Projeto](#autores)


## Sobre o Projeto

O ShopHub API foi desenvolvido com o intuito de criar uma plataforma eficiente e flexível para integrar diversos vendedores e compradores  em um único marketplace. Com a nossa API, os desenvolvedores podem facilmente acessar e gerenciar informações de produtos, categorias,  vendedores, pedidos e clientes, oferecendo uma experiência robusta para o desenvolvimento de e-commerces

### Funcionalidades do Sistema

1. *Sistema de Autenticação*
   - Permite criar contas de usuário e realizar login, com controle de tentativas e registro de novos usuários.

2. *Sistema de Autenticação com Login Social*
   - Suporta login através de redes sociais, gerenciado pela classe SocialLogin.

3. *Sistema de Autenticação com Tipo de Usuário*
   - Utiliza enumerações para definir diferentes tipos de usuários, com a classe TipoUsuarioEnum.

4. *Sistema de Autenticação Users*
   - Permite o gerenciamento de usuários, incluindo criação, atualização e recuperação de senha.

5. *Sistema de Finanças Imobiliárias*
   - Gerencia finanças, calcula parcelas e gera relatórios sobre imóveis, utilizando a classe ClFinances.

6. *Classe CheckEntries*
   - Verifica entradas em um arquivo de texto e calcula o próximo índice disponível.

7. *Classe Reader*
   - Lê e processa dados de arquivos de texto relacionados a imóveis e usuários.

8. *Classe Writer*
   - Grava informações de usuários em um arquivo de texto, mantendo um registro contínuo.

9. *Classe Favorite*
   - Permite gerenciar itens favoritos, salvando e visualizando imóveis desejados.

10. *Classe WishList*
    - Representa uma lista de desejos para imóveis de interesse dos usuários.

11. *Classe Scraper*
    - Realiza o scraping de dados de imóveis, permitindo buscas e salvamentos de informações.

12. *Classe AbstractScraper*
    - Base abstrata para scrapers de dados relacionados a imóveis.

13. *Classe SistemaInterno*
    - Entrada principal para o aplicativo, permitindo a interação do usuário por meio de um menu.

14. *Classe Menu*
    - Gerencia a interação do usuário com o sistema, apresentando opções e navegando entre elas.

15. *Classe Util*
    - Fornece utilitários gerais para a aplicação, incluindo configuração de logging e limpeza de tela.

O projeto foi realizado durante o período aproximado de 2 semanas, durante a disciplina de Programação Orientada a Objetos em TIC/Software do Serratec.

## Tecnologias utilizadas

    Java com Spring Boot
    dBeaver para o banco de dados relacional
    Swagger para documentação da API
    JWT para autenticação
    Docker para containerização
    Postman para testes de API
    Git para controle de versão
    Trello para gerenciamento de tarefas


Inicia o programa e segue o fluxo para uma barra de Join com cinco opções de estados. Nessa barra de join, encontra-se o estado de Login, nessa opção entra em uma caixa de representação da máquina de estado com uma condição para o login; caso falhe, o processo é repetido, e, se for bem-sucedido, o fluxo continua para o scraper e entra em outra barra de join com nove opções de estados. Nessa barra de join, encontra-se o estado com a opção de ver relatórios de finanças e o programa entra em outra caixa de representação da máquina de estados. Com a seguinte condição: se a resposta for negativa, o fluxo retorna ao menu; se a resposta for positiva, o programa vai para uma barra de join com dois estados sendo eles a simulação e a opção de ver mais imóveis. Caso clique mais de uma vez na opção de ver relatório de finanças durante a mesma aplicação, o fluxo é direcionado para outra caixa de representação da máquina de estados, para perguntar se deseja redefinir o prazo de pagamento se a resposta for negativa, simula-se o mesmo prazo de pagamento anterior. Se a resposta for positiva, o fluxo segue para outra barra de join, com dois estados sendo eles a nova simulação ou a opção de ver mais imóveis.


## Autores

* [Gabriel Toledo](https://github.com/gabrieltol7do)
* [Fred Fernandes](https://github.com/FFred-Fernandes)
* [Arthur Carreiro](https://github.com/arcarreiro)
* [Leticia Peixoto](https://github.com/leticiapzs)
* [Daniele Carius](https://github.com/Daniele-carius)
* [Lucas Schumacker](https://github.com/schumacker1)
* [João Moreira](https://github.com/joaogmmoreira)

