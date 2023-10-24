create table hospedes(

  id bigint not null auto_increment,
  usuario_id bigint not null,
  numero_documento varchar(100) not null,
  tipo_documento varchar(100) not null,
  nome varchar(100) not null,
  telefone bigint not null,
  email varchar(100) not null,
  endereco varchar(100) not null,
  data_nascimento date not null,
  data_cadastro date not null,

  primary key(id)

  );