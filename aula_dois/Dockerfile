FROM gradle:4.6.0-jdk8

COPY . /chat
WORKDIR /chat

RUN cd /chat \
    && gradle server:build

CMD ["java", "-cp", "server/build/libs/server.jar:chat/build/libs/chat.jar", "server.ChatServer"]
