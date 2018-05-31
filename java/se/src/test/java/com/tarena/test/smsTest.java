package com.tarena.test;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;
import com.tarena.util.Signature;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import static com.aliyuncs.auth.AcsURLEncoder.percentEncode;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2017/3/24
 * Time: 17:22
 * CopyRight:HuiMei Engine
 */
public class smsTest {

    @Test
    public void smsSendTest(){
        final String AccessSecret = "5Tw5pQvqbXFb0g4JcEiLIsubnXWimF";
        final String AccessKeyId = "LTAIWyEHL17sxCU3";
        final String Action = "SingleSendSms";
        final String Format = "JSON";
        final String ParamString = "{\"customer\":\"ddd\"}";
        final String RecNum = "17071400493";
        final String RegionId = "cn-hangzhou";
        final String SignName = "骆驼金融";
        final String SignatureMethod = "HMAC-SHA1";
        final String SignatureNonce = "9e030f6b-03a2-40f0-a6ba-157d44532fd0";
        final String SignatureVersion = "1.0";
        final String TemplateCode = "SMS_57250018";
        final String Timestamp = Instant.now().toString();
        final String Version = "2016-09-27";


        try {
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIWyEHL17sxCU3", "5Tw5pQvqbXFb0g4JcEiLIsubnXWimF");
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms",  "sms.aliyuncs.com");
            IAcsClient client = new DefaultAcsClient(profile);
            SingleSendSmsRequest request = new SingleSendSmsRequest();
            request.setSignName("骆驼金融");//控制台创建的签名名称
            request.setTemplateCode("SMS_57250018");//控制台创建的模板CODE
            request.setParamString("{\"customer\":\"ddd\"}");//短信模板中的变量；数字需要转换为字符串；个人用户每个变量长度必须小于15个字符。"
            //request.setParamString("{}");

            request.setActionName("SingleSendSms");
            String canonicalizedQueeryString  = "AccessKeyId=" + AccessKeyId +
                    "&Action=" + Action +
                    "&Format=" + Format +
                    "&ParamString=" + ParamString +
                    "&RecNum=" + RecNum +
                    "&RegionId=" + RegionId +
                    "&SignName=" + SignName +
                    "&SignatureMethod=" + SignatureMethod +
                    "&SignatureNonce=" + SignatureNonce +
                    "&SignatureVersion=" + SignatureVersion +
                    "&TemplateCode=" + TemplateCode +
                    "&Timestamp=" + Timestamp +
                    "&Version=" + Version;

//            String StringToSign=
//                    "POST" + "&" +
//                    percentEncode("/") + "&" +
//                    percentEncode(canonicalizedQueeryString);
//            String encodeHmac= Signature.calculateRFCHMAC(AccessSecret, StringToSign);
//            String sign = Base64.getEncoder().encodeToString(encodeHmac.getBytes("utf-8"));
//            System.out.println("StringToSign: " + StringToSign);
//            System.out.println("encodeHmac: " + encodeHmac);
//            System.out.println("sign: " + sign);
            request.setSignName(SignName);
            request.setRecNum("17071400493");//接收号码
            request.setAcceptFormat(FormatType.JSON);
            SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
            System.out.println("httpResponse: " + JSON.toJSONString(httpResponse));
        } catch (ServerException e) {
            e.printStackTrace();
        }
        catch (ClientException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
