--
-- Sample dataset

-- bart simpson
-- password plaintext: hans
INSERT INTO customer(nickname, lastname, firstname, street, postCode, city, email, password, salt, otpkey, level, state) VALUES ('bart90', 'Simpson', 'Bart', 'ABC-Street', '86461', 'Springfield', 'bart@simpson.com', '$2a$10$F35NUXlsKrhIcZIZqR3myu0m9suN3PXp1GXKMZFMKAbStXFFJYT7G', '$2a$10$F35NUXlsKrhIcZIZqR3myu', '26MOCFUXZ5G6MJ2K', 'USER', 'ACTIVE')
INSERT INTO bankaccount(customer_id, account_number) VALUES (1, 980234)
INSERT INTO bankaccount(customer_id, account_number) VALUES (1, 516842)

-- lisa simpson
-- password plaintext: hans
INSERT INTO customer(nickname, lastname, firstname, street, postCode, city, email, password, salt, otpkey, level, state) VALUES ('lisa', 'Simpson', 'Lisa', 'Mapledrive 24', '86461', 'Springfield', 'lisa@simpson.com', '$2a$10$F35NUXlsKrhIcZIZqR3myu0m9suN3PXp1GXKMZFMKAbStXFFJYT7G', '$2a$10$F35NUXlsKrhIcZIZqR3myu', '26MOCFUXZ5G6MJ2K', 'USER', 'INACTIVE')
INSERT INTO bankaccount(customer_id, account_number) VALUES (2, 849544)

-- voucher account
INSERT INTO customer(nickname, lastname, firstname, street, postCode, city, email, password, salt, otpkey, level, state) VALUES ('voucher', 'Voucher', 'Bugcoin', '---', '---', '---', '---', '', '', '', 'SYSTEM', 'ACTIVE')
INSERT INTO bankaccount(customer_id, account_number) VALUES (3, 100000)

-- admin account
-- password plaintext: hans
INSERT INTO customer(nickname, lastname, firstname, street, postCode, city, email, password, salt, otpkey, level, state) VALUES ('kingOfTheHill', 'admin', 'admin', '---', '---', '---', 'admin@bugcoin.com', '$2a$10$F35NUXlsKrhIcZIZqR3myu0m9suN3PXp1GXKMZFMKAbStXFFJYT7G', '$2a$10$F35NUXlsKrhIcZIZqR3myu', '26MOCFUXZ5G6MJ2K', 'ADMIN', 'ACTIVE')
INSERT INTO bankaccount(customer_id, account_number) VALUES (4, 100010)

-- vouchers
INSERT INTO voucher(code, value, isreedemed) VALUES ('1234567890', '10', 'false')

-- initial account balance (voucher)
--INSERT INTO transaction(source_account_account_number, target_account_account_number, description, amount) VALUES (100000, 516842, 'GUTSCHEIN\nCode: 7098004047291733428', 500.00)
INSERT INTO transaction(source_account_account_number, target_account_account_number, description, amount) VALUES (100000, 849544, 'GUTSCHEIN\nCode: 9058297197314082204', 500.00)
--INSERT INTO transaction(source_account_account_number, target_account_account_number, description, amount) VALUES (100000, 980234, 'GUTSCHEIN\nCode: 2132592771375554407', 500.00)

-- transactions
--INSERT INTO transaction(source_account_account_number, target_account_account_number, description, amount) VALUES (980234, 516842, 'ÜBERWEISUNG\nDispoausgleich Kreditkarte', 47.50)
--INSERT INTO transaction(source_account_account_number, target_account_account_number, description, amount) VALUES (980234, 849544, 'ÜBERWEISUNG\nReparatur Malibu Stacy Kopf', 11.25)