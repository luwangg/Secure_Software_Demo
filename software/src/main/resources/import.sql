--
-- Sample dataset

-- bart simpson
-- password plaintext: hans
INSERT INTO customer(nickname, lastname, firstname, street, postCode, city, email, password, salt, otpkey) VALUES ('bart90', 'Simpson', 'Bart', 'ABC-Street', '86461', 'Springfield', 'bart@simpson.com', '$2a$10$F35NUXlsKrhIcZIZqR3myu0m9suN3PXp1GXKMZFMKAbStXFFJYT7G', '$2a$10$F35NUXlsKrhIcZIZqR3myu', '26MOCFUXZ5G6MJ2K')
INSERT INTO bankaccount(customer_id, accountnumber, balance) VALUES (1, 980234, 7518)
INSERT INTO bankaccount(customer_id, accountnumber, balance) VALUES (1, 516842, 47.50)

-- lisa simpson
-- password plaintext: hans
INSERT INTO customer(nickname, lastname, firstname, street, postCode, city, email, password, salt, otpkey) VALUES ('lisa', 'Simpson', 'Lisa', 'Mapledrive 24', '86461', 'Springfield', 'lisa@simpson.com', '$2a$10$F35NUXlsKrhIcZIZqR3myu0m9suN3PXp1GXKMZFMKAbStXFFJYT7G', '$2a$10$F35NUXlsKrhIcZIZqR3myu', '26MOCFUXZ5G6MJ2K')
INSERT INTO bankaccount(customer_id, accountnumber, balance) VALUES (2, 849544, 11.25)


-- transactions
INSERT INTO transaction(source_account_accountnumber, target_account_accountnumber, description, amount) VALUES (980234, 516842, 'ÜBERWEISUNG\nDispoausgleich Kreditkarte', 47.50)
INSERT INTO transaction(source_account_accountnumber, target_account_accountnumber, description, amount) VALUES (980234, 849544, 'ÜBERWEISUNG\nReparatur Malibu Stacy Kopf', 11.25)