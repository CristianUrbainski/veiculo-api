-- tables

CREATE TABLE "marca"(
 "id" BigSerial NOT NULL,
 "nome" Character varying(100) NOT NULL
);

CREATE TABLE "modelo"(
 "id" BigSerial NOT NULL,
 "nome" Character varying(100) NOT NULL,
 "idmarca" Bigint NOT NULL
);

CREATE TABLE "veiculo"(
 "id" BigSerial NOT NULL,
 "nome" Character varying(100) NOT NULL,
 "ano_fabricacao" Integer NOT NULL,
 "consume_medio_cidade" Numeric(10,2) NOT NULL,
 "consumo_medio_rodovia" Numeric(10,2) NOT NULL,
 "idmodelo" Bigint NOT NULL
);

-- primary keys

ALTER TABLE "marca" ADD CONSTRAINT "PK_marca" PRIMARY KEY ("id");

ALTER TABLE "modelo" ADD CONSTRAINT "PK_modelo" PRIMARY KEY ("id");

ALTER TABLE "veiculo" ADD CONSTRAINT "PK_veiculo" PRIMARY KEY ("id");

-- indexes

CREATE INDEX "IX_rel_modelo_marca_idmarca" ON "modelo" ("idmarca");

CREATE INDEX "IX_rel_veiculo_modelo_idmodelo" ON "veiculo" ("idmodelo");

-- foreign keys

ALTER TABLE "modelo" ADD CONSTRAINT "rel_modelo_marca_idmarca" FOREIGN KEY ("idmarca") REFERENCES "marca" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "veiculo" ADD CONSTRAINT "rel_veiculo_modelo_idmodelo" FOREIGN KEY ("idmodelo") REFERENCES "modelo" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;