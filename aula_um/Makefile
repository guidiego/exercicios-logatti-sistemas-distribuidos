start_server:
	rmic -classpath bin/main server.CalculatorServer -nowarn
	start rmiregistry
	start java -jar server/build/libs/server.jar

drop_server:
	stop rmiregistry
	stop java -jar server/build/libs/server.jar

start_client:
	start java -jar client/build/libs/client.jar

drop_client:
	stop java -jar client/build/libs/client.jar

build:
	gradle server:build
	gradle client:build

clear:
	rm -rf bin client server

run:
	make clear
	make build
	make start_server
	make start_client

	java -jar client/build/libs/client.jar

	make drop_server
	make drop_client
