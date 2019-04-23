db.properties default:
user=sa
password=
dburl=jdbc:hsqldb:hsql://localhost/
useSSL=false

Editar o caminho do SET PATH no StartDB.bat para para a pasta do arquivo runServer.bat do hsqldb
Adicionar ao projeto o drive hsqldb.jar que está na pasta lib
Na pasta tem o arquivo test.script com alguns comandos, DDL e DML...
Rodar o StartDB e depois a classe programa que está no pacote aplicação.
