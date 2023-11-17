package com.example.interfaceextension.sample;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//@SpringBootTest
@Slf4j
class Tests {
    private final String aesKey = "1234567890123456";
    AES aes = SecureUtil.aes(aesKey.getBytes(StandardCharsets.UTF_8));

    /**
     * 请求鉴权上传请求
     */
    @Test
    public void requestAuthEncryptParamUpload() {
        JSONObject paramJson = new JSONObject();
        JSONObject nameJson = new JSONObject();
        nameJson.put("firstName", "张");
        nameJson.put("lastName", "三");
        paramJson.put("name", nameJson);

        String encryptStr = aes.encryptHex(paramJson.toJSONString(JSONWriter.Feature.WriteMapNullValue)).toUpperCase();
        String ts = getTimestamp();

        List<String> itemList = new ArrayList<>();
        itemList.add(encryptStr);

        itemList.add("secret_key" + "=" + "access_secret");
        itemList.add("timestamp" + "=" + ts);

        String paramStr = CollUtil.join(itemList, "&");
        String sign = SecureUtil.md5().digestHex(paramStr, StandardCharsets.UTF_8);
        log.info(sign);

        String body = HttpUtil.createPost("http://localhost:8085/auth/encryptParameterUpload")
                .form("data", encryptStr)
                .form("file",new File("E:\\欣网卓信\\CRM系统-归档\\CRM平台使用情况统计需求.docx"))
                .header("X-Request-Signature", sign)
                .header("Access-Key", "access_key")
                .header("Timestamp", ts)
                .execute()
                .body();
        log.info(body);
    }

    /**
     * 请求上传请求
     */
    @Test
    public void requestEncryptParamUpload() {
        JSONObject paramJson = new JSONObject();
        JSONObject nameJson = new JSONObject();
        nameJson.put("firstName", "张");
        nameJson.put("lastName", "三");
        paramJson.put("name", nameJson);
        String encryptStr = aes.encryptHex(paramJson.toJSONString(JSONWriter.Feature.WriteMapNullValue)).toUpperCase();
        String body = HttpUtil.createPost("http://localhost:8085/encryptParameterUpload")
                .form("data", encryptStr)
                .form("file",new File("E:/欣网卓信/CRM系统-归档/CRM平台使用情况统计需求.docx"))
                .execute()
                .body();
        log.info(body);
    }

    /**
     * 请求不鉴权的表单参数接口
     */
    @Test
    public void requestEncryptParam() {
        JSONObject paramJson = new JSONObject();
        JSONObject nameJson = new JSONObject();
        nameJson.put("firstName", "张");
        nameJson.put("lastName", "三");
        paramJson.put("name", nameJson);
        String encryptStr = aes.encryptHex(paramJson.toJSONString(JSONWriter.Feature.WriteMapNullValue)).toUpperCase();
        String body = HttpUtil.createGet("http://localhost:8085/encryptParameter")
                .form("data", encryptStr)
                .execute()
                .body();
        log.info(body);
    }

    /**
     * 请求鉴权的表单参数接口
     */
    @Test
    public void requestAuthEncryptParam() {
        JSONObject paramJson = new JSONObject();
        JSONObject nameJson = new JSONObject();
        nameJson.put("firstName", "张");
        nameJson.put("lastName", "三");
        paramJson.put("name", nameJson);

        String encryptStr = aes.encryptHex(paramJson.toJSONString(JSONWriter.Feature.WriteMapNullValue)).toUpperCase();
        String ts = getTimestamp();

        List<String> itemList = new ArrayList<>();
        itemList.add(encryptStr);

        itemList.add("secret_key" + "=" + "access_secret");
        itemList.add("timestamp" + "=" + ts);

        String paramStr = CollUtil.join(itemList, "&");
        String sign = SecureUtil.md5().digestHex(paramStr, StandardCharsets.UTF_8);
        log.info(sign);

        String body = HttpUtil.createPost("http://localhost:8085/auth/encryptParameter")
                .form("data", encryptStr)
                .header("X-Request-Signature", sign)
                .header("Access-Key", "access_key")
                .header("Timestamp", ts)
                .execute()
                .body();
        log.info(body);
    }

    /**
     * 请求鉴权的json参数接口
     */
    @Test
    void requestAuthJsonArgumentApi() {
        JSONObject dataJson = new JSONObject();
        dataJson.put("username", "aaaaaa");
        dataJson.put("password", "psw");

        String encryptStr = aes.encryptHex(dataJson.toJSONString());
        String ts = getTimestamp();

        List<String> itemList = new ArrayList<>();
        itemList.add(encryptStr);

        itemList.add("secret_key" + "=" + "access_secret");
        itemList.add("timestamp" + "=" + ts);

        String paramStr = CollUtil.join(itemList, "&");
        String sign = SecureUtil.md5().digestHex(paramStr, StandardCharsets.UTF_8);
        log.info(sign);
        String body = HttpUtil.createPost("http://localhost:8085/auth/argument")
                .header("X-Request-Signature", sign)
                .header("Access-Key", "access_key")
                .header("Timestamp", ts)
                .body(encryptStr, MediaType.APPLICATION_JSON_VALUE)
                .execute()
                .body();
        log.info(body);
    }

    /**
     * 请求鉴权的无参数接口
     */
    @Test
    void requestAuthNoArgumentApi() {

        String ts = getTimestamp();

        List<String> itemList = new ArrayList<>();
        itemList.add("secret_key" + "=" + "access_secret");
        itemList.add("timestamp" + "=" + ts);

        String paramStr = CollUtil.join(itemList, "&");
        String sign = SecureUtil.md5().digestHex(paramStr, StandardCharsets.UTF_8);
        log.info(sign);
        String body = HttpUtil.createPost("http://localhost:8085/auth/noArgument")
                .header("X-Request-Signature", sign)
                .header("Access-Key", "access_key")
                .header("Timestamp", ts)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .execute()
                .body();
        log.info(body);
    }

    /**
     * 请求无鉴权的无参数接口
     */
    @Test
    void requestJsonArgumentApi() {
        JSONObject dataJson = new JSONObject();
        dataJson.put("username", "aaaaaa");
        dataJson.put("password", "psw");

        String encryptStr = aes.encryptHex(dataJson.toJSONString());

        String body = HttpUtil.createPost("http://localhost:8085/argument")
                .body(encryptStr, MediaType.APPLICATION_JSON_VALUE)
                .execute()
                .body();
        log.info(body);
    }

    /**
     * 请求无鉴权的无参数接口
     */
    @Test
    void requestNoArgumentApi() {
        JSONObject dataJson = new JSONObject();
        dataJson.put("username", "aaaaaa");
        dataJson.put("password", "psw");

        String encryptStr = aes.encryptHex(dataJson.toJSONString());
        String ts = getTimestamp();

        List<String> itemList = new ArrayList<>();
        itemList.add(encryptStr);

        itemList.add("secret_key" + "=" + "access_secret");
        itemList.add("timestamp" + "=" + ts);

        String paramStr = CollUtil.join(itemList, "&");
        String sign = SecureUtil.md5().digestHex(paramStr, StandardCharsets.UTF_8);
        log.info(sign);
        String body = HttpUtil.createPost("http://localhost:8085/argument")
                .header("X-Request-Signature", sign)
                .header("Access-Key", "access_key")
                .header("Timestamp", ts)
                .body(encryptStr, MediaType.APPLICATION_JSON_VALUE)
                .execute()
                .body();
        log.info(body);
    }

    @Test
    public void dec() {
        String data = "D0F0B2A4A76CE74C0B7BCC153D439B69D4A4D6D564551B505C7BE13C078B5CE489CCC75107AF3AFC4F60B5E43242D24B";
        log.info(SecureUtil.aes(aesKey.getBytes()).decryptStr(data));
    }

    private String getTimestamp() {
        return System.currentTimeMillis() + "";
    }

}
