package top.glkj.utils;

/**
 * @author : xgl
 * @version : 0.0.1
 * @date :2021/8/29 1:50
 */
public enum RequestMethod {
    GET("GET"),
    POST("POST");

    private String value;

    RequestMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "RequestMethod{" +
                "value='" + value + '\'' +
                "} " + super.toString();
    }
}
