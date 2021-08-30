package top.glkj.happyEight.request;

import lombok.Data;
import top.glkj.utils.RequestMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import static top.glkj.utils.ParamConnect.connectUrl;
import static top.glkj.utils.ParamConnect.setHeader;

/**
 * @author : xgl
 * @version : 0.0.1
 * @date :2021/8/29 1:40
 */
@Data
public class RequestParam {
    private String requestUrl;
    private Map<String, String> requestHeader;
    private Map<String, String> requestParam;
    private RequestMethod requestMethod;
    private boolean useCaches;
    private int connectTimeOut;
    private int readTimeOut;
    private boolean doOutPut;
    private boolean doInPut;

    public String getConnectContent() throws IOException {

        if (this.equals(new RequestParam())) {
            return "";
        }

        HttpURLConnection connection = null;
        try {
            URL url = new URL(connectUrl(requestUrl, requestParam));
            connection = (HttpURLConnection) url.openConnection();
            setHeader(connection, requestHeader);
            connection.setRequestMethod(requestMethod.getValue());
            connection.setConnectTimeout(connectTimeOut);
            connection.setReadTimeout(readTimeOut);
            connection.setDoInput(doInPut);
            connection.setDoInput(doOutPut);
            connection.setUseCaches(useCaches);
            if (200 == connection.getResponseCode()) {
                InputStream inputStream = null;
                if (connection.getContentEncoding() != null
                        && !"".equals(connection.getContentEncoding())) {
                    String encode = connection.getContentEncoding()
                            .toLowerCase();
                    if (!"".equals(encode) && encode.contains("gzip")) {
                        inputStream = new GZIPInputStream(
                                connection.getInputStream());
                    }
                }

                if (null == inputStream) {
                    inputStream = connection.getInputStream();
                }

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append("\n");
                }
                return builder.toString();
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return "";
    }
}
