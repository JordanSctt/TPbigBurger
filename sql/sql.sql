CREATE TABLE _user (
    _user_id SERIAL NOT NULL PRIMARY KEY,
    _name character varying(20),
    _password character varying(20),
    _phone character varying(20)
);

CREATE TABLE _burger (
    _burger_id SERIAL NOT NULL PRIMARY KEY,
    _label character varying(20),
    _price NUMERIC(4, 2)
);

CREATE TABLE _commande (
    _commande_id SERIAL NOT NULL PRIMARY KEY,
    _user_id integer,
    _startDatePrep timestamp,
    _EndDatePrep timestamp
);

CREATE TABLE _commandeItems (
    _burger_id integer,
    _commande_id integer,
    _quantity integer
);

ALTER TABLE _commande
    ADD CONSTRAINT _commande_fk__user foreign key (_user_id)  REFERENCES _user(_user_id);

ALTER TABLE _commandeItems
    ADD CONSTRAINT _commandeItems_pk_burgcomm primary key (_burger_id, _commande_id);

    ALTER TABLE _commandeitems
        ADD CONSTRAINT _commandeitems_fk__commande foreign key (_commande_id)  REFERENCES _commande(_commande_id);
    ALTER TABLE _commandeitems
        ADD CONSTRAINT _commandeitems_fk__burger foreign key (_burger_id)  REFERENCES _burger(_burger_id);

        ALTER TABLE _commande
        ADD _etatCommande character varying(100);

        ALTER TABLE _user
                ADD _role character varying(20);


