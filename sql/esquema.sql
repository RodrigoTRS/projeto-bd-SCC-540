CREATE TABLE ENTIDADE_PARCEIRA (
    NOME VARCHAR2(50),
    TELEFONE CHAR(11),
    EMAIL VARCHAR2(50),
    RUA VARCHAR2(50),
    NUMERO CHAR(4),
    CIDADE VARCHAR2(30),
    ESTADO CHAR(2),
    CEP CHAR(9),
    CONSTRAINT PK_ENTIDADE_PARCEIRA
        PRIMARY KEY (NOME)
);

CREATE TABLE VETERINARIO (
    CRVM CHAR(6),
    NOME VARCHAR2(50),
    CLINICA VARCHAR(50),
    EMAIL VARCHAR2(50),
    TELEFONE CHAR(11),
    CONSTRAINT  PK_VETERINARIO
        PRIMARY KEY (CRVM)    
);

CREATE TABLE CANIL (
    NOME VARCHAR2(50),
    NUM_ANIMAIS NUMBER,
    QUANT_RACAO NUMBER,
    RUA VARCHAR2(50),
    NUMERO CHAR(4),
    CIDADE VARCHAR2(30),
    ESTADO CHAR(2),
    CEP CHAR(9),
    CONSTRAINT PK_CANIL
        PRIMARY KEY (NOME),
    CONSTRAINT CK_CANIL_NUM_ANIMAIS
        CHECK (NUM_ANIMAIS > 0)
);

CREATE TABLE FUNCIONARIO (
    CPF CHAR(14),
    NOME VARCHAR2(50),
    EMAIL VARCHAR2(50),
    TELEFONE CHAR(11),
    VINCULO CHAR(20),
    RUA VARCHAR2(50),
    NUMERO CHAR(4),
    CIDADE VARCHAR2(30),
    ESTADO CHAR(2),
    CEP CHAR(9),
    CONSTRAINT PK_FUNCIONARIO
        PRIMARY KEY (CPF),
    CONSTRAINT UK_FUNCIONARIO
        UNIQUE(NOME),
    CONSTRAINT CK_FUNCIONARIO
        CHECK (VINCULO IN ('SUPERVISOR', 'ORGANIZADOR', 'RESPONSAVEL_CANIL'))
);

CREATE TABLE FEIRA (
    EDICAO CHAR(3),
    ORCAMENTO NUMBER,
    DATA DATE,
    NUM_ANIMAIS_ADOTADOS NUMBER,
    RUA VARCHAR2(50),
    NUMERO CHAR(4),
    CIDADE VARCHAR2(30),
    ESTADO CHAR(2),
    CEP CHAR(9),
    CONSTRAINT PK_FEIRA
        PRIMARY KEY (EDICAO)
);

CREATE TABLE MORADOR (
    CPF CHAR(14),
    NOME VARCHAR2(50),
    IDADE CHAR(3),
    TELEFONE CHAR(11),
    RENDA_FAMILIAR NUMBER,
    NUM_FILHOS NUMBER,
    NUM_PETS NUMBER,
    RUA VARCHAR2(50),
    NUMERO CHAR(4),
    CIDADE VARCHAR2(30),
    ESTADO CHAR(2),
    CEP CHAR(9),
    CONSTRAINT PK_MORADOR
        PRIMARY KEY (CPF),
    CONSTRAINT CK_IDADE
        CHECK (IDADE > 18)
);

CREATE TABLE CONTRIBUICAO (
    ENTIDADE VARCHAR2(50),
    DATA DATE,
    VALOR NUMBER NOT NULL,
    CONSTRAINT PK_CONTRIBUICAO
        PRIMARY KEY (ENTIDADE, DATA),
    CONSTRAINT FK_CONTRIBUICAO_ENTIDADE_PARCEIRA
        FOREIGN KEY (ENTIDADE)
        REFERENCES ENTIDADE_PARCEIRA(NOME)
        ON DELETE SET NULL
);

CREATE TABLE PARTICIPACAO_FEIRA (
    EDICAO CHAR (3),
    ENTIDADE VARCHAR2(50),
    CONSTRAINT PK_PARTICIPACAO_FEIRA
        PRIMARY KEY (EDICAO, ENTIDADE),
    CONSTRAINT FK_PARTICIPACAO_FEIRA
        FOREIGN KEY (EDICAO)
        REFERENCES FEIRA(EDICAO)
        ON DELETE SET NULL,
    CONSTRAINT FK_PARTICIPACAO_FEIRA_ENTIDADE_PARCEIRA
        FOREIGN KEY (ENTIDADE)
        REFERENCES ENTIDADE_PARCEIRA(NOME)
        ON DELETE CASCADE
);

CREATE TABLE ORGANIZADOR_FEIRA (
    ORGANIZADOR CHAR(14),
    EDICAO CHAR(3),
    CONSTRAINT PK_ORGANIZADOR_FEIRA
        PRIMARY KEY (ORGANIZADOR, EDICAO),
    CONSTRAINT FK_ORGANIZADOR_FEIRA_FEIRA
        FOREIGN KEY (EDICAO)
        REFERENCES FEIRA(EDICAO)
        ON DELETE SET NULL,
    CONSTRAINT FK_ORGANIZADOR_FEIRA_FUNCIONARIO
        FOREIGN KEY (ORGANIZADOR)
        REFERENCES FUNCIONARIO(CPF)
        ON DELETE CASCADE
);

CREATE TABLE PROJETO (
    NOME VARCHAR2(50),
    SUPERVISOR CHAR(14) NOT NULL,
    ORCAMENTO NUMBER,
    TEMPO_ESTIPULADO NUMBER,
    DATA_INICIO DATE,
    DATA_CONCLUSAO DATE,
    PROGRESSO CHAR(1),
    PRIORIDADE CHAR(5),
    RUA VARCHAR2(50),
    NUMERO CHAR(4),
    CIDADE VARCHAR(30),
    ESTADO CHAR(2),
    CEP CHAR(9),
    CONSTRAINT PK_PROJETO
        PRIMARY KEY (NOME),
    CONSTRAINT UK_PROJETO
        UNIQUE (SUPERVISOR),
    CONSTRAINT CK_PROJETO_PRIORIDADE
        CHECK (PRIORIDADE IN ('BAIXA', 'MEDIA', 'ALTA')),
    CONSTRAINT CK_PROJETO_PROGRESSO
        CHECK (PROGRESSO >= 0 AND PROGRESSO <= 5),
    CONSTRAINT FK_PROJETO_FUNCIONARIO
        FOREIGN KEY (SUPERVISOR)
        REFERENCES FUNCIONARIO(CPF)
        ON DELETE SET NULL
);

CREATE TABLE RESPONSAVEL_CANIL (
    RESPONSAVEL CHAR(14),
    CANIL VARCHAR2(50) NOT NULL,
    CONSTRAINT PK_RESPONSAVEL_CANIL
        PRIMARY KEY (RESPONSAVEL),
    CONSTRAINT FK_RESPONSAVEL_CANIL_FUNCIONARIO
        FOREIGN KEY (RESPONSAVEL)
        REFERENCES FUNCIONARIO(CPF)
        ON DELETE CASCADE,
    CONSTRAINT FK_RESPONSAVEL_CANIL_CANIL
        FOREIGN KEY (CANIL)
        REFERENCES CANIL(NOME)
        ON DELETE CASCADE  
);

CREATE TABLE ANIMAL (
    ID_COLEIRA CHAR(6),
    NOME VARCHAR2(50),
    CANIL VARCHAR2(50) NOT NULL,
    TIPO CHAR(8),
    IDADE NUMBER,
    RACA VARCHAR2(30),
    ISCASTRADO CHAR(1),
    ISVERMIFUGADO CHAR(1),
    CONSTRAINT PK_ANIMAL
        PRIMARY KEY (ID_COLEIRA),
    CONSTRAINT FK_ANIMAL
        FOREIGN KEY (CANIL)
        REFERENCES CANIL(NOME)
        ON DELETE SET NULL,
    CONSTRAINT CK_ANIMAL_IDADE
        CHECK (IDADE > 0),
    CONSTRAINT CK_ANIMAL_TIPO
        CHECK (TIPO IN ('CACHORRO', 'GATO'))
);

CREATE TABLE VACINA (
    ANIMAL CHAR(6),
    VETERINARIO CHAR(6),
    VACINA VARCHAR2(50),
    DATA DATE,
    CONSTRAINT PK_VACINA
        PRIMARY KEY (ANIMAL, VETERINARIO),
    CONSTRAINT FK_VACINA_ANIMAL
        FOREIGN KEY (ANIMAL)
        REFERENCES ANIMAL (ID_COLEIRA)
        ON DELETE CASCADE,
    CONSTRAINT FK_VACINA_VETERINARIO
        FOREIGN KEY (VETERINARIO)
        REFERENCES VETERINARIO(CRVM)
        ON DELETE SET NULL
);

CREATE TABLE CONSULTA (
    ANIMAL CHAR(6),
    VETERINARIO CHAR(6),
    DATA DATE,
    RESPONSAVEL VARCHAR2(50),
    TEL_RESPONSAVEL CHAR(11),
    CONSTRAINT PK_CONSULTA
        PRIMARY KEY (ANIMAL, VETERINARIO, DATA),
    CONSTRAINT FK_CONSULTA_ANIMAL
        FOREIGN KEY (ANIMAL)
        REFERENCES ANIMAL (ID_COLEIRA)
        ON DELETE CASCADE
);

CREATE TABLE ADOCAO (
    MORADOR CHAR(14),
    ANIMAL CHAR(6),
    DATA DATE,
    CONSTRAINT PK_ADOCAO
        PRIMARY KEY (MORADOR, ANIMAL),
    CONSTRAINT FK_ADOCAO_MORADOR
        FOREIGN KEY (MORADOR)
        REFERENCES MORADOR (CPF)
        ON DELETE SET NULL,
    CONSTRAINT FK_ADOCAO_ANIMAL
        FOREIGN KEY (ANIMAL)
        REFERENCES ANIMAL (ID_COLEIRA)
        ON DELETE CASCADE
);