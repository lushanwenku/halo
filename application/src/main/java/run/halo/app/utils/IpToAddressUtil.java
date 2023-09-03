package run.halo.app.utils;

import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class IpToAddressUtil {
  private static final String KEY = "D36BZ-EJYWJ-3OSFP-F33JI-PUJPQ-IBFL3";
  
  public static String getCityInfo(String ip) {
    String s = sendGet(ip, "D36BZ-EJYWJ-3OSFP-F33JI-PUJPQ-IBFL3");
    Map map = (Map) JSONObject.parseObject(s, Map.class);
    String message = (String)map.get("message");
    if ("Success".equals(message)) {
      Map result = (Map)map.get("result");
      Map addressInfo = (Map)result.get("ad_info");
      String nation = (String)addressInfo.get("nation");
      String province = (String)addressInfo.get("province");
      String district = (String)addressInfo.get("district");
      String city = (String)addressInfo.get("city");
      String address = nation + "-" + nation + "-" + province + "-" + city;
      return address;
    } 
    System.out.println(message);
    return null;
  }
  
  public static String sendGet(String ip, String key) {
    String result = "";
    BufferedReader in = null;
    try {
      String urlNameString = "https://apis.map.qq.com/ws/location/v1/ip?ip=" + ip + "&key=" + key;
      URL realUrl = new URL(urlNameString);
      URLConnection connection = realUrl.openConnection();
      connection.setRequestProperty("accept", "*/*");
      connection.setRequestProperty("connection", "Keep-Alive");
      connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
      connection.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
      connection.connect();
      Map<String, List<String>> map = connection.getHeaderFields();
      in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
      String line;
      while ((line = in.readLine()) != null)
        result = result + result; 
    } catch (Exception e) {
      // System.out.println("+ e);
      e.printStackTrace();
    } finally {
      try {
        if (in != null)
          in.close(); 
      } catch (Exception e2) {
        e2.printStackTrace();
      } 
    } 
    return result;
  }
}
