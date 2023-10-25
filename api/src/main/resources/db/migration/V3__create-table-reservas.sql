create table reservas(

  id bigint not null auto_increment,
  acomodacao_id bigint not null,
  hospede_id bigint not null,
  usuario_id bigint not null,
  data_reserva date not null,
  data_entrada date not null,
  data_saida date not null,
  quantidade_pessoas bigint not null,
  data_hora_entrada datetime,
  data_hora_saida datetime,
  valor_diaria decimal(10,2) not null,
  valor_pago_total decimal(10,2),
  forma_pagamento varchar(20),
  observacao varchar(255),
  
  primary key(id)

  );