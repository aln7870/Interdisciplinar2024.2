CREATE DATABASE consultorio;

USE consultorio;

CREATE TABLE Paciente (
    CodPaciente INT UNSIGNED AUTO_INCREMENT,
    NomePaciente VARCHAR(100) NOT NULL,
    Sexo VARCHAR(20) DEFAULT 'NÃO INFORMADO', 
    DataNascimento DATE NOT NULL,
    CPF VARCHAR(14) UNIQUE NULL,  
    CPFResponsavel VARCHAR(14) UNIQUE NULL,
    Telefone VARCHAR(15) NOT NULL,  
    DataCadastro DATETIME DEFAULT CURRENT_TIMESTAMP,
    Status CHAR(1) DEFAULT 'A' CHECK (Status IN ('A', 'I')),
    PRIMARY KEY (CodPaciente)
    );

CREATE TABLE Prestador (
    CodPrestador TINYINT UNSIGNED AUTO_INCREMENT,
    NomePrestador VARCHAR(100) NOT NULL,
    DataCriacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    Status CHAR(1) DEFAULT 'A' CHECK (Status IN ('A', 'I')),
    CodTipoPrestador TINYINT UNSIGNED NOT NULL,
    PRIMARY KEY (CodPrestador)
);

CREATE TABLE TipoPrestador (
    CodTipoPrestador TINYINT UNSIGNED AUTO_INCREMENT,
    Funcao VARCHAR(100) NOT NULL,
    Status CHAR(1) DEFAULT 'A' CHECK (Status IN ('A', 'I')),
    PRIMARY KEY (CodTipoPrestador)
);

CREATE TABLE Usuario (
    CodUsuario TINYINT UNSIGNED AUTO_INCREMENT,
    Nome VARCHAR(100) UNIQUE NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    Senha VARCHAR(255) NOT NULL,
    DataCriacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    Status CHAR(1) DEFAULT 'A' CHECK (Status IN ('A', 'I')),
	CodPrestador TINYINT UNSIGNED NOT NULL,
    PRIMARY KEY (CodUsuario)
);

CREATE TABLE Atendimento (
    CodAtendimento INT UNSIGNED AUTO_INCREMENT,
    DataAtendimento DATETIME DEFAULT CURRENT_TIMESTAMP,
	TipoStatus ENUM('EM ANDAMENTO', 'CONCLUÍDO', 'CANCELADO', 'PENDENTE') DEFAULT 'EM ANDAMENTO',
    CodPrestador TINYINT UNSIGNED NOT NULL,
	CodPaciente INT UNSIGNED NOT NULL,
	CodImagensRadiologias INT UNSIGNED NOT NULL,
    PRIMARY KEY (CodAtendimento)
);

CREATE TABLE ImagensRadiologias(
CodImagensRadiologias INT UNSIGNED AUTO_INCREMENT,
Caminho LONGTEXT NOT NULL,
DataCriacao DATETIME DEFAULT CURRENT_TIMESTAMP,
Status CHAR(1) DEFAULT 'A' CHECK (Status IN ('A', 'I')),
PRIMARY KEY (CodImagensRadiologias)
);

CREATE TABLE Dente (
    CodDente TINYINT UNSIGNED AUTO_INCREMENT,
    NomeDente VARCHAR(100) NOT NULL,
    NumeroDente TINYINT UNSIGNED NOT NULL,
	Status CHAR(1) DEFAULT 'A' CHECK (Status IN ('A', 'I')),
    PRIMARY KEY (CodDente)
);

CREATE TABLE Procedimento(
CodProcedimento TINYINT UNSIGNED AUTO_INCREMENT,
DescricaoProcedimento VARCHAR(255) NOT NULL,
Status CHAR(1) DEFAULT 'A' CHECK (Status IN ('A', 'I')),
PRIMARY KEY (CodProcedimento)
);

CREATE TABLE ProMed(
CodProMed INT UNSIGNED AUTO_INCREMENT,
Observacao LONGTEXT,
CodDente TINYINT UNSIGNED NULL,
CodAtendimento INT UNSIGNED NOT NULL,
CodProcedimento TINYINT UNSIGNED NOT NULL,
PRIMARY KEY (CodProMed)
);

CREATE TABLE Faturamento (
    CodFaturamento INT UNSIGNED AUTO_INCREMENT,
    Valor DECIMAL(10, 5) NOT NULL CHECK (Valor >= 0), 
	CodAtendimento INT UNSIGNED NOT NULL,
    CodProMed INT UNSIGNED NOT NULL, 
    PRIMARY KEY (CodFaturamento)
);

ALTER TABLE Prestador
ADD CONSTRAINT FK_Prestador_TipoPrestador 
FOREIGN KEY (CodTipoPrestador)
REFERENCES TipoPrestador(CodTipoPrestador)
ON DELETE RESTRICT ON UPDATE CASCADE;
 
ALTER TABLE Usuario
ADD CONSTRAINT FK_Usuario_Prestador 
FOREIGN KEY (CodPrestador) 
REFERENCES Prestador(CodPrestador)
ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE Atendimento
ADD CONSTRAINT FK_Atendimento_Prestador 
FOREIGN KEY (CodPrestador) 
REFERENCES Prestador(CodPrestador)
ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE Atendimento
ADD CONSTRAINT FK_Atendimento_Paciente 
FOREIGN KEY (CodPaciente) 
REFERENCES Paciente(CodPaciente)
ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE Atendimento
ADD CONSTRAINT FK_Atendimento_ImagensRadiologias
FOREIGN KEY (CodImagensRadiologias) 
REFERENCES ImagensRadiologias(CodImagensRadiologias)
ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE ProMed
ADD CONSTRAINT FK_ProMed_Dente
FOREIGN KEY (CodDente) 
REFERENCES Dente(CodDente)
ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE ProMed
ADD CONSTRAINT FK_ProMed_Atendimento
FOREIGN KEY (CodAtendimento) 
REFERENCES Atendimento(CodAtendimento)
ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE ProMed
ADD CONSTRAINT FK_ProMed_Procedimento
FOREIGN KEY (CodProcedimento) 
REFERENCES Procedimento(CodProcedimento)
ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE Faturamento
ADD CONSTRAINT FK_Faturamento_Atendimento
FOREIGN KEY (CodAtendimento) 
REFERENCES Atendimento(CodAtendimento)
ON DELETE CASCADE ON UPDATE CASCADE;
    
ALTER TABLE Faturamento 
ADD CONSTRAINT FK_Faturamento_ProMed
FOREIGN KEY (CodProMed) 
REFERENCES ProMed(CodProMed)
ON DELETE RESTRICT ON UPDATE CASCADE;

-- PRESTADORES 
INSERT INTO TipoPrestador(CodTipoPrestador, Funcao) 
VALUES 
(1, 'DENTISTA'),
(2, 'RECEPCIONISTA');

-- SERVIÇOS OFERECIDOS
INSERT INTO Procedimento (CodProcedimento, DescricaoProcedimento)
VALUES
(1, 'LIMPEZA'),
(2, 'CLAREAMENTO'),
(3, 'APARELHOS ORTODÔNTICOS'),
(4, 'TRATAMENTO DE GENGIVAS'),
(5, 'FACETAS'),
(6, 'MANUTENÇÃO'),
(7, 'EXTRAÇÃO'),
(8, 'CANAL'),
(9, 'RESTAURAÇÃO'),
(10, 'PINO'),
(11, 'COROA');

-- ARCADA SUPERIOR (16 DENTES)
INSERT INTO Dente (CodDente, NomeDente, NumeroDente) 
VALUES 
(1, 'INCISIVO CENTRAL SUPERIOR DIREITO', 11),
(2, 'INCISIVO LATERAL SUPERIOR DIREITO', 12),
(3, 'CANINO SUPERIOR DIREITO', 13),
(4, 'PRIMEIRO PRÉ-MOLAR SUPERIOR DIREITO', 14),
(5, 'SEGUNDO PRÉ-MOLAR SUPERIOR DIREITO', 15),
(6, 'PRIMEIRO MOLAR SUPERIOR DIREITO', 16),
(7, 'SEGUNDO MOLAR SUPERIOR DIREITO', 17),
(8, 'TERCEIRO MOLAR SUPERIOR DIREITO (SISO)', 18),
(9, 'INCISIVO CENTRAL SUPERIOR ESQUERDO', 21),
(10, 'INCISIVO LATERAL SUPERIOR ESQUERDO', 22),
(11, 'CANINO SUPERIOR ESQUERDO', 23),
(12, 'PRIMEIRO PRÉ-MOLAR SUPERIOR ESQUERDO', 24),
(13, 'SEGUNDO PRÉ-MOLAR SUPERIOR ESQUERDO', 25),
(14, 'PRIMEIRO MOLAR SUPERIOR ESQUERDO', 26),
(15, 'SEGUNDO MOLAR SUPERIOR ESQUERDO', 27),
(16, 'TERCEIRO MOLAR SUPERIOR ESQUERDO (SISO)', 28);

-- ARCADA INFERIOR (16 DENTES)
INSERT INTO Dente (CodDente, NomeDente, NumeroDente) 
VALUES 
(17, 'INCISIVO CENTRAL INFERIOR DIREITO', 41),
(18, 'INCISIVO LATERAL INFERIOR DIREITO', 42),
(19, 'CANINO INFERIOR DIREITO', 43),
(20, 'PRIMEIRO PRÉ-MOLAR INFERIOR DIREITO', 44),
(21, 'SEGUNDO PRÉ-MOLAR INFERIOR DIREITO', 45),
(22, 'PRIMEIRO MOLAR INFERIOR DIREITO', 46),
(23, 'SEGUNDO MOLAR INFERIOR DIREITO', 47),
(24, 'TERCEIRO MOLAR INFERIOR DIREITO (SISO)', 48),
(25, 'INCISIVO CENTRAL INFERIOR ESQUERDO', 31),
(26, 'INCISIVO LATERAL INFERIOR ESQUERDO', 32),
(27, 'CANINO INFERIOR ESQUERDO', 33),
(28, 'PRIMEIRO PRÉ-MOLAR INFERIOR ESQUERDO', 34),
(29, 'SEGUNDO PRÉ-MOLAR INFERIOR ESQUERDO', 35),
(30, 'PRIMEIRO MOLAR INFERIOR ESQUERDO', 36),
(31, 'SEGUNDO MOLAR INFERIOR ESQUERDO', 37),
(32, 'TERCEIRO MOLAR INFERIOR ESQUERDO (SISO)', 38);
