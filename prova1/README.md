## Prova

Esse projeto necessita do gradle 4+ instalado :)  Para executar com o `make` no seu computador basta:

```sh
# Terminal 1
make run

# Terminal 2
make start_client
```

Para executar em um computador sem o `make`  

```sh
#Terminal 1
gradle server:build
java -cp server/build/libs/server.jar:crud/build/libs/crud.jar server.CrudServer

# Terminal 2
gradle client:build
java -cp client/build/libs/client.jar:crud/build/libs/crud.jar client.CrudClient
```