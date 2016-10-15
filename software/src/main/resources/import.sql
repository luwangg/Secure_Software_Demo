--
-- Sample dataset

INSERT INTO customer(nickname, lastname, firstname, street, postCode, city, email, passwordhash) VALUES ('bart90', 'Simpson', 'Bart', 'ABC-Street', '86461', 'Springfield', 'bart@simpson.com', 'pwdHash123')
INSERT INTO bankaccount(customer_id, accountnumber, balance) VALUES (1, 1234, 0)
