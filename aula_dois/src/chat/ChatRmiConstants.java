package chat;

public enum ChatRmiConstants {
    REDUCED();
    private String localUriTemplate = "//%s:%n/%s";
    private Integer port = 1020;
    private String uri = "localhost";
    private String serviceName = "ChatService";

    public String getUri() {
        return String.format(
            this.localUriTemplate,
            this.uri, this.port, this.serviceName
        );
    }

    public int getPort() {
        return this.port;
    }
}