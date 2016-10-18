--
-- Sample dataset

-- bart simpson
INSERT INTO customer(nickname, lastname, firstname, street, postCode, city, email, password, salt, otpkey) VALUES ('bart90', 'Simpson', 'Bart', 'ABC-Street', '86461', 'Springfield', 'bart@simpson.com', '$2a$10$vSyrIKTVv7YEMctYHr2wzuWhXS7/UtBJcoW0DzXZfnokaohI5iu62', '', '26MOCFUXZ5G6MJ2K')
INSERT INTO bankaccount(customer_id, account_number) VALUES (1, 980234)
INSERT INTO bankaccount(customer_id, account_number) VALUES (1, 516842)

-- lisa simpson
INSERT INTO customer(nickname, lastname, firstname, street, postCode, city, email, password, salt, otpkey) VALUES ('lisa', 'Simpson', 'Lisa', 'Mapledrive 24', '86461', 'Springfield', 'lisa@simpson.com', '$2a$10$vSyrIKTVv7YEMctYHr2wzuWhXS7/UtBJcoW0DzXZfnokaohI5iu62', '', '26MOCFUXZ5G6MJ2K')
INSERT INTO bankaccount(customer_id, account_number) VALUES (2, 849544)

-- voucher accounter
INSERT INTO customer(nickname, lastname, firstname, street, postCode, city, email, password, salt, otpkey) VALUES ('voucher', 'Account', 'Voucher', '---', '---', '---', '---', '', '', '')
INSERT INTO bankaccount(customer_id, account_number) VALUES (3, 100000)

-- initial account balance (voucher)
INSERT INTO transaction(source_account_account_number, target_account_account_number, description, amount) VALUES (100000, 516842, 'GUTSCHEIN\nCode: 902340vn8e7wr98n23hr', 500.00)
INSERT INTO transaction(source_account_account_number, target_account_account_number, description, amount) VALUES (100000, 849544, 'GUTSCHEIN\nCode: mv8t32vnt89324hf8943', 500.00)
INSERT INTO transaction(source_account_account_number, target_account_account_number, description, amount) VALUES (100000, 980234, 'GUTSCHEIN\nCode: 859nbztrzhfuqhe16785', 500.00)

-- transactions
INSERT INTO transaction(source_account_account_number, target_account_account_number, description, amount) VALUES (980234, 516842, 'ÜBERWEISUNG\nDispoausgleich Kreditkarte', 47.50)
INSERT INTO transaction(source_account_account_number, target_account_account_number, description, amount) VALUES (980234, 849544, 'ÜBERWEISUNG\nReparatur Malibu Stacy Kopf', 11.25)