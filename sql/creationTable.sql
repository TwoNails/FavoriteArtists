#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: ARTISTE
#------------------------------------------------------------

CREATE TABLE ARTISTE(
        ID     Int NOT NULL ,
        NOM    Varchar (50) NOT NULL ,
        NB_FAN Int NOT NULL
    ,CONSTRAINT ARTISTE_PK PRIMARY KEY (ID)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: TITRE
#------------------------------------------------------------

CREATE TABLE TITRE(
        PK        Int NOT NULL ,
        NOM       Varchar (50) NOT NULL ,
        DUREE     Int NOT NULL ,
        NB_ECOUTE Int NOT NULL ,
        NOTE      Bool NOT NULL ,
        ID        Int NOT NULL
    ,CONSTRAINT TITRE_PK PRIMARY KEY (PK)

    ,CONSTRAINT TITRE_ARTISTE_FK FOREIGN KEY (ID) REFERENCES ARTISTE(ID)
)ENGINE=InnoDB;