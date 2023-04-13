# API - FastService
Backend App Fast Service - É um Sofwate para controle e automoção comercial para restaurantes. 

Desenvolvido com as seguintes tecnologias:
- Conceitos de boas práticas e qualidade no código, usando Design Patterns, Clean Architecture, Domain Driven Design (DDD) e Princípios SOLID
- Backend **API REST** com o framework **Spring Boot** 3.0.3 e **Java** 19
- Autenticação e autorização de usuários com **JWT** (JSON Web Token)
- Banco de dados relacional **PostgreSQL**
- Banco de dados não relacional **MongoDB**
- **Flyway** migrações de banco de dados
- Apache **Maven** gerenciamentos de depêndencias
- Testes automatizados com **JUnit 5**
- **Swagger** para documentação do projeto
- **Docker**

 ### Funcionalidades do software
 - Categorias
    - Buscar todas as categorias
    - Buscar uma categoria pelo ID
    - Cadastra uma categoria (Somente o usuário ADMIN)
    - Atuailiza uma categoria pelo ID (Somente o usuário ADMIN)
    - Remove uma categoria pelo ID (Somente o usuário ADMIN)
    
- Produtos
    - Buscar todas os produtos
    - Buscar um produto pelo ID
    - Criar um produto (Somente o usuário ADMIN)
    - Atuailizar um produto pelo ID (Somente o usuário ADMIN)
    - Remover um produto pelo ID (Somente o usuário ADMIN)
    
- Pedidos
    - Buscar todas os pedidos
    - Buscar um pedido pelo ID
    - Fechar o pedido da mesa pelo ID
    - Cadastrar uma mesa
    - Enviar pedido para a cozinha
    - Concluir pedido
    
- Items
    - Adicionar um item
    - Remover um item
    
- Usuários Garçom
    - Criar usuário garçom (Somente usuário ADMIN)
    - Upload de avatar 
    
- Autenticação
    - Autenticação de usuário
    
- Envio de e-mail na criação de um novo usuário

- Notificação de cada pedido enviado para a cozinha
 
### Permissões de usuário (ROLES)
    - Admin
    - Garçom
    - Usuário comum
    
# Endpoints
    
|  URL |  Método | Descrição |
|----------|--------------|--------------|
|`http://localhost:8080/auth/signin`                             | POST | Solicitar Access Token |
|`http://localhost:8080/api/categories/v1/1`                             | GET | Retornar uma categoria baseado no ID |
|`http://localhost:8080/api/categories/v1/`                             | GET | Retornar todas as categorias |
|`http://localhost:8080/api/categories/v1/`                             | POST | Registrar uma categoria |
|`http://localhost:8080/api/categories/v1/`                             | PATCH | Atualizar uma categoria |
|`http://localhost:8080/api/categories/v1/`                             | DELETE | Deletar uma categoria |
|`http://localhost:8080/api/products/v1/1`                             | GET | Retornar um produto baseado no ID |
|`http://localhost:8080/api/products/v1/`                             | GET | Retornar todas os produtos |
|`http://localhost:8080/api/products/v1/`                             | POST | Registrar um produto |
|`http://localhost:8080/api/products/v1/`                             | PATCH | Atualizar um produto |
|`http://localhost:8080/api/products/v1/`                             | DELETE | Deletar um produto |
|`http://localhost:8080/api/orders/v1/1`                             | GET | Retornar um pedido baseado no ID |
|`http://localhost:8080/api/orders/v1/`                             | GET | Retornar todas os pedidos |
|`http://localhost:8080/api/orders/v1/`                             | POST | Registrar uma mesa |
|`http://localhost:8080/api/orders/v1/send/1`                             | PUT | Enviar pedido para a cozinha pelo ID |
|`http://localhost:8080/api/orders/v1/close/1`                             | DELETE | Fechar pedido da mesa pelo ID |
|`http://localhost:8080/api/orders/v1/conclude/1`                             | PUT | Concluir pedido pelo ID |
|`http://localhost:8080/api/items/v1/add`                             | POST | Adicionar produtos no pedido |
|`http://localhost:8080/api/items/v1/remove/1`                             | DELETE | Remover produto do pedido |
|`http://localhost:8080/api/users/v1/`                             | POST | Criar usuário garçom |
|`http://localhost:8080/api/users/v1/upload/avatar`                             | PUT | Fazer upload de avatar do garçom |

---
## Como executar o projeto
Edite o arquivo `application.properties` em `api-fastservice/src/main/resources` com as informações necessárias correspondentes às configurações da sua máquina (usuário/senha do banco de dados e também do provedor de email para envio automático do sistema).
O projeto foi construído com a IDE Eclipse. Para executá-lo:

1. Baixe e instale o Docker Desktop
2. Faça o Download do zip do projeto ou clone o repositório Git e extraia o conteúdo do arquivado compactado
3. Navegue até a pasta do projeto e abra o Prompt de Comando do Windows ou Terminal do GNU/Linux
4. Execuete o comando `mvn clean package -DskipTests`. Ele irá gerar os target jar
5. Execute o comando `docker-compose up -d --build`. Ele irá criar um container chamado api-fastservice e db_fastservice-postgres contendo a imagem do banco de dados PostgreSQL e Jdk19.

![Captura de tela 2023-04-12 203236](https://user-images.githubusercontent.com/43589505/231607980-d6ce2108-7ed0-4e8e-b681-4b9d2b0a6603.png)

6.  Abra o Eclipse ou IDE de sua preferência.
7.  Importe o projeto baixado: Vá em File > Open Projects from File System. Selecione a pasta pela opção "Directory" e pressione Finish.
8.  Espere o Maven baixar todas as dependências.
9.  Abra a classe java "api-fastservice" e execute o método main.
10.  O projeto irá ser executado.
11.  Para testar os recursos das URLs acima, use alguma ferramenta de testes de API, como o **Postman**; 

### Autor
Feito por Cícero Júnior. 👋🏽 Entre em contato! <br>
<a href="https://www.linkedin.com/in/juniiorsilva-fullstack/" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a> 




