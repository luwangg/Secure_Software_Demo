--
-- Sample dataset

INSERT INTO customer(nickname, lastname, firstname, street, postCode, city, email, password, otpkey) VALUES ('bart90', 'Simpson', 'Bart', 'ABC-Street', '86461', 'Springfield', 'bart@simpson.com', 'test', '26MOCFUXZ5G6MJ2K')
INSERT INTO bankaccount(customer_id, accountnumber, balance) VALUES (1, 980234, 7518)
INSERT INTO bankaccount(customer_id, accountnumber, balance) VALUES (1, 516842, 47.50)
INSERT INTO transaction(source_account_accountnumber, target_account_accountnumber, description, amount) VALUES (980234, 516842, 'ÜBERWEISUNG\nMVG AUTOMAT HEIMERANPL. III\n2016-10-01T12:24:14', 47.50)