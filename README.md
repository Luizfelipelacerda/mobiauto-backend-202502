
# mobiauto-backend-202502

Projeto teste para a empresa MOBIAUTO.

A Aplicação simula uma sistema de revenda de automoveis, com Assistentes que recebem as oportunidades de revendas, gerentes e proprietarios.
 
## &emsp; Tecnologias utilizadas
* Java 17
* Spring Boot
* Spring Data
* Spring Security
* MySQL
* Swagger

## Como rodar o projeto

Primeiramente clone o projeto

use git clone ********

então abra o projeto na sua IDE de preferencia (no meu caso usei o Intellij).

abra o projeto apartir do arquivo POM.xml.

depois que o maven baixar todas as dependencias, altere o arquivo application.properties com o seu banco de dados.

o projeto cria tabelas automaticamente usando o arquivo schema.sql e popula as tabelas com o data.sql.

todos os usuarios criados possuem a mesma senha "teste1".

na aplicação faz a sua autentificação de usuarios usando asymmetric public key pair, então sera necessario você criar suas proprias key.
no powershell use o comando "openssl genrsa -des3 -out private.pem 2048" para criar a chave privada, sera necessario digitar um PEM pass phrase duas vezes, a frase fica a seu criterio.
então use o segundo comando "openssl rsa -in private.pem -outform PEM -pubout -out public.pem" para criar a chave public, sera necessario digitar a mesma frase que usou na criação da chave privada.

pos criar as duas chaves, mova elas para o src/main/resources do projeto clonado.

com esse preparativos feitos, você ja podera rodar a aplicação.

para acessar a api utilize o Swagger pelo link 
http://localhost:8080/swagger-ui/index.html#/

ou se preferir o Postman ou Insomnia.




## Swagger

Este projeto utiliza o Swagger para mostrar seus Endpoints.

o link para o swagger é http://localhost:8080/swagger-ui/index.html#/

para utilizar os endpoints é necessario primeiro fazer o login com o endpoint Authentication/login, o endpoint ira retornar um json contendo o accessToken.

Com o accessToken va ate o botão de Authorize e insira apenas o valor do token, e clique no botão Authorize.

Agora você podera utilizar todas os endpoints livremente por 20 minutos.


## Autores

- [@Luizfelipelacerda](https://github.com/Luizfelipelacerda)


