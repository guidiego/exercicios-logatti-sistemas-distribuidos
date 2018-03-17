package calculator;

public enum CalcRmiConstants {
    REDUCED();
    private String localUriTemplate = "//%s:%s/%s";
    private Integer port = 2020;
    private String uri = "localhost";
    private String serviceName = "CalculatorService";

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