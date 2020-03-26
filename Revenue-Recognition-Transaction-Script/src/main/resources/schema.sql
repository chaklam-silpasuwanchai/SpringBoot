CREATE TABLE products (ID int primary key AUTO_INCREMENT, name varchar(255), type varchar(255)) 
CREATE TABLE contracts (ID int primary key AUTO_INCREMENT, product_id int, revenue decimal, dateSigned date)
CREATE TABLE revenueRecognitions (contract_id int, amount decimal, recognizedOn date, PRIMARY KEY (contract_id, recognizedOn))