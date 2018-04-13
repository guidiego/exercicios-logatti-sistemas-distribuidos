start_server:
	java -cp server/build/libs/server.jar:crud/build/libs/crud.jar server.CrudServer

start_client:
	java -cp client/build/libs/client.jar:crud/build/libs/crud.jar client.CrudClient

build:
	gradle server:build --debug
	gradle client:build --debug

run:
	make build
	make start_server

