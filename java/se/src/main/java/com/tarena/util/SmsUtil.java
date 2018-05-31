package com.tarena.util;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;

import java.time.Instant;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2017/3/27
 * Time: 22:14
 * CopyRight:Zhouji
 */
public class SmsUtil {

    private static final String AccessKeyId = "LTAIWyEHL17sxCU3";
    private static final String Action = "SingleSendSms";
    private static final String Format = "JSON";
    private static final String ParamString = "{\"customer\":\"ddd\"}";
    private static final String RecNum = "17071400493";
    private static final String RegionId = "cn-hangzhou";
    private static final String SignName = "骆驼金融";
    private static final String SignatureMethod = "HMAC-SHA1";
    private static final String SignatureNonce = "9e030f6b-03a2-40f0-a6ba-157d44532fd0";
    private static final String SignatureVersion = "1.0";
    private static final String TemplateCode = "SMS_57250018";
    private static final String Timestamp = Instant.now().toString();
    private static final String Version = "2016-09-27";

    /**
     *
     * @param recNum 短信接收号码
     * @param templateCode 模板code
     * @param paramString 对应于模板的参数
     * @return
     */
    public static Integer smsSend(String recNum, String templateCode, String paramString){


        try {
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIWyEHL17sxCU3", "5Tw5pQvqbXFb0g4JcEiLIsubnXWimF");
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms",  "sms.aliyuncs.com");
            IAcsClient client = new DefaultAcsClient(profile);
            SingleSendSmsRequest request = new SingleSendSmsRequest();
            request.setSignName("骆驼金融");
            request.setTemplateCode(templateCode);
            request.setParamString(paramString);

            request.setActionName("SingleSendSms");
            request.setRecNum(recNum);//接收号码
            request.setAcceptFormat(FormatType.JSON);
            SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
            System.out.println("httpResponse: " + JSON.toJSONString(httpResponse));
            return 1;
        } catch (ServerException e) {
            e.printStackTrace();
        }
        catch (ClientException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static void main(String[] args) {
        String recNum = "17071400493";
        String temCode = "SMS_57250018";
        String paramString = "{\"customer\":\"ddd\"}";
        smsSend(recNum, temCode, paramString);
    }
}
