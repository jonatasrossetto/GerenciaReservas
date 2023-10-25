create table acomodacoes(

  id bigint not null auto_increment,
  numero bigint not null UNIQUE,
  capacidade_Pessoas bigint not null,
  quantidade_camas bigint not null,
  valor_diaria decimal(10,2) not null,

  primary key(id)

  );