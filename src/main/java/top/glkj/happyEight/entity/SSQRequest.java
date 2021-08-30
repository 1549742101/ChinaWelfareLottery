package top.glkj.happyEight.entity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;

/**
 * @author : xgl
 * @version : 0.0.1
 * @date :2021/8/28 1:44
 */
public class SSQRequest {
    private final static String REQUEST_URL = "http://www.cwl.gov.cn/cwl_admin/kjxx/findDrawNotice";
    private final static HashMap<String, String> REQUEST_HEAD = new HashMap<String,String>() {
        {
            put("Accept",
                    "application/json, text/javascript, */*; q=0.01");
            put("Accept-Encoding",
                    "gzip, deflate");
            put("Accept-Language", "zh-CN,zh;q=0.9");
            put("Cookie", "Sites=_21; UniqueID=BU4L2o19clVkQYXG1630085142366; _ga=GA1.3.339187880.1623588206; " +
                    "bdshare_firstime=1623588248097; _gid=GA1.3.1894942094.1630085142; 21_vq=24");
            put("Host", "www.cwl.gov.cn");
            put("Referer", "http://www.cwl.gov.cn/kjxx/ssq/kjgg/");
            put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                    "Chrome/92.0.4515.159 Safari/537.36");
            put("X-Requested-With", "XMLHttpRequest");
        }
    };

    public static String getRequest(SSQQuery ssqQuery) {
        String content = null;
        StringBuilder targetUrl = new StringBuilder(REQUEST_URL);
        HttpURLConnection connection = null;
        try {
            targetUrl.append("?");
            targetUrl.append("name=");
            targetUrl.append(ssqQuery.getName());
            targetUrl.append("&");
            targetUrl.append("issueCount=");
            targetUrl.append(ssqQuery.getIssueCount());
            targetUrl.append("&");
            targetUrl.append("issueStart=");
            targetUrl.append(ssqQuery.getIssueStart());
            targetUrl.append("&");
            targetUrl.append("issueEnd=");
            targetUrl.append(ssqQuery.getIssueEnd());
            targetUrl.append("&");
            targetUrl.append("dayStart=");
            String strDateFormat = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            targetUrl.append(sdf.format(ssqQuery.getDayStart()));
            targetUrl.append("&");
            targetUrl.append("dayEnd=");
            targetUrl.append(sdf.format(ssqQuery.getDayEnd()));
            targetUrl.append("&");
            targetUrl.append("pageNo=");
            targetUrl.append(ssqQuery.getPageNo());
            System.out.println(targetUrl);

            URL url = new URL(targetUrl.toString());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            for (String headKey : REQUEST_HEAD.keySet()) {
                connection.setRequestProperty(headKey, REQUEST_HEAD.get(headKey));
            }
            connection.setUseCaches(false);
            connection.setConnectTimeout(6 * 1000);
            connection.setReadTimeout(6 * 1000);
            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.connect();

            if (200 == connection.getResponseCode()) {
                InputStream inputStream = null;
                if (connection.getContentEncoding() != null
                        && !connection.getContentEncoding().equals("")) {
                    String encode = connection.getContentEncoding()
                            .toLowerCase();
                    if (encode != null && !encode.equals("")
                            && encode.indexOf("gzip") >= 0) {
                        inputStream = new GZIPInputStream(
                                connection.getInputStream());
                    }
                }

                if (null == inputStream) {
                    inputStream = connection.getInputStream();
                }

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream, "utf-8"));
                StringBuilder builder = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append("\n");
                }
                content = builder.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return content;
    }
}
