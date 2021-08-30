package top.glkj.utils;

import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : xgl
 * @version : 0.0.1
 * @date :2021/8/29 1:59
 */
public final class ParamConnect {
    public static String connect(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        params.forEach((k, v) -> {
            sb.append(k);
            sb.append("=");
            sb.append(v);
            sb.append("&");
        });
        return sb.substring(0, sb.length() - 1);
    }

    public static String connectUrl(String requestUrl, Map<String, String> params) {
        return requestUrl + "?" + connect(params);
    }

    public static void setHeader(HttpURLConnection connection, Map<String, String> header) {
        header.forEach(connection::setRequestProperty);
    }

    public <T> Map<String,String> paramToMap(Class<T> clazz, T t){
        HashMap<String, String> map = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields){
           if (field.getDeclaringClass().equals(RequestParam.class)){
               field.setAccessible(true);
               try {
                   map.put(field.getName(),field.get(t).toString());
               } catch (IllegalAccessException e) {
                   e.printStackTrace();
               }
           }
        }
        return map;
    }
}
