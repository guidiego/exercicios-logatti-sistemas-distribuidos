start_server:
	java -cp server/build/libs/server.jar:chat/build/libs/chat.jar server.ChatServer

start_client:
	java -cp client/build/libs/client.jar:chat/build/libs/chat.jar client.ChatClient

build:
	gradle server:build --debug
	gradle client:build --debug

run:
	make build
	make start_server

