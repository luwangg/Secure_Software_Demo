--
-- Sample dataset

INSERT INTO customer(nickname, lastname, firstname, street, postCode, city, email, password, otpkey) VALUES ('bart90', 'Simpson', 'Bart', 'ABC-Street', '86461', 'Springfield', 'bart@simpson.com', 'test', '26MOCFUXZ5G6MJ2K')
INSERT INTO bankaccount(customer_id, accountnumber, balance) VALUES (1, 1234, 0)
