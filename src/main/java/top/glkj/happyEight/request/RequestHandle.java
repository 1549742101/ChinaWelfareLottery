package top.glkj.happyEight.request;

import com.alibaba.fastjson.JSONObject;
import top.glkj.happyEight.entity.SSQQuery;
import top.glkj.happyEight.entity.SSQRequest;
import top.glkj.happyEight.entity.SSQResultResponse;
import top.glkj.happyEight.response.ResponseHandle;

import java.util.Date;
import java.util.HashMap;

/**
 * @author : xgl
 * @version : 0.0.1
 * @date :2021/8/29 1:39
 */
public final class RequestHandle {
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
    public static ResponseHandle systemDefineRun(){
        ResponseHandle responseHandle = new ResponseHandle();
        RequestParam param = new RequestParam();
        Date startTime = new Date("2017/01/01");
        Date endTime = new Date();
        new Thread(() -> {
            SSQQuery query = new SSQQuery();
            query.setDayStart(startTime);
            query.setDayEnd(endTime);
            query.setName("ssq");
            query.setPageNo(0);
            while (true) {
                query.setPageNo(query.getPageNo() + 1);
                SSQResultResponse resultResponse = new SSQResultResponse();
                String request = SSQRequest.getRequest(query);
                System.out.println(request);
                resultResponse = JSONObject.parseObject(request, SSQResultResponse.class);

                System.out.println(resultResponse);
                if (resultResponse.getPageCount().equals(query.getPageNo())) {
                    return;
                }
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).run();

        return responseHandle;
    }
}
