UNISENAI
PROJETO APLICADO 2
2023-2
Aplicativo Web para gerenciar as reservas de uma pousada


Este é o repositório do Back-end do aplicativo Gerenciador de Reservas
O serviço de back-end é uma api REST composta de diversos end-points,
cada um responsável por uma determinada entidade do domínio da aplicação.
A idéia é validar um MVP que mostre a viabilidade do sistema.

Stack:
* IDE Eclipse
* Maven
* Java + Spring Boot
* flyway para migration
* MySql

25/10/2023
 Os endpoints de acomodação, hóspede e reserva estão em funcionamento.
 Analisar se ao invés de excluir seria melhor utilizar um flag boolean para identificar se o registro está ativo ou não.
 Falta incluir regras de negócio para o cadastro da reserva para evitar choque de datas.
 Falta incluir um endpoint para a listagem de reservas por acomodacao
 Melhorar as migrations incluindo as chaves estrangeiras nas tabelas (consistência).
 Falta incluir uma migration com o script para a auditoria da tabela de reservas.
 Falta incluir a entidade usuário e o esquema de segurança utilizando JWT (será a última classe a ser implementada).
 Falta gerar o Swagger da api
 Falta gerar os testes da api
 


