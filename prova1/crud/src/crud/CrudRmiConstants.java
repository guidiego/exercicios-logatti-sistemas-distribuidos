package crud;

public enum CrudRmiConstants {
    REDUCED();
    private String localUriTemplate = "//%s:%s/%s";
    private Integer port = 1030;
    private String uri = "localhost";
    private String serviceName = "CrudService";

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