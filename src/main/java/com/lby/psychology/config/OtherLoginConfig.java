package com.lby.psychology.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.easysdk.kernel.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.codec.Base64;

import java.security.KeyPair;

@Configuration
public class OtherLoginConfig {

    private final static String PROTOCOL = "https";

    private final static String HOST = "openapi.alipay.com";

    private final static String SIGN_TYPE = "RSA2";

    private final static String APP_ID = "2021002141656448";

    private final static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjcYRyaUaSQt1qRyThUQklxBvJDSsqJZyI6rsG4du/LTk3BF05d/+fusMO55mC50ZQmea3Iv6F/SKoeMSpvZ3xWU74EWtGrJ8IMY6AnZvB0KWnJjAXHi58vO8Ve29QFkNbhROd5DytlfOET/Iwz7hwfjdweHnoKtI3+TTSKLvGRfOtXY2Ybmpbw4/s19egnwqLkzgfKHs3cA+cAdSayhISFWG9IBqaWOULZoyYfCwsHhCznUi5CleQhDdrLqEMR9jXfyB117PWIKXlgk9mBLh/mkCdPP5v/+7GQzBU2WNFEicXK78NapMYtxU0pGuztgwJFWMQ8fHMKLjCHnVWJnTDwIDAQAB";

    @Autowired
    private KeyPair keyPair;



    public Config getAlipayOptions(){
        Config config = new Config();
        //协议
        config.protocol = PROTOCOL;
        //地址
        config.gatewayHost = HOST;
        //加密类型
        config.signType = SIGN_TYPE;
        //app id
        config.appId = APP_ID;

        //私钥
        config.merchantPrivateKey = new String(Base64.encode(keyPair.getPrivate().getEncoded()));
//        config.merchantPrivateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCehLi+K5wo27aIbzVbAExg9XqUScIggSgScoDrj9FoSD+Dx+BmpYaTDcTMl41MiyxJXD5GL5vpraCgE8b1+98L+acl2R9GS+p4J/CtTSHpGalE0bO+NwGXngawutcBkDK+xCIy9h3kIKtcQ7OrV3VQUlGgNkhuQqhrwEl7WmK+AoW+yANUk/asHTgbLxiK/3jHgmIXxQ9nj9JfcRk2OnluL6anSaAwmyDcz3+qkM6bP9stmEFYSw/FDw/8U597TBswJxcyS/eufI3wfzGR1EqyRbb8VkTN8s+zvFr+eOjJ4OorFMsEhpLHW9gpzirJfvk6VhRoWvJ5kGDp3sarvSpDAgMBAAECggEAXlHUZvsl4L3S311kXvBPggWuWRuGEbcn88oD4aXiUO1kNXIxmIH3D/rKVX37ikbpJAFp4HMljyZLg9Qgt26VqddzWDbXwAkdyz7gmw1hFwS4zgUEsTKjlOZnTrLtibWWLCIhwzbkVEAIFk8GUfYpDJDelriV4l1xPyPb1TTTugBwDulBYIbJVexTracLohyI9k7i4IyZDrjZZqIU0AQWMU6J+amdoJe8oGyvbR5D5aftyBIyZOuK/O2KOPSRVPgVr3uA5Ol5+PnHzc6V6pzJCnwoy2Lh19372+nPBe03uo6BYv6owxw2Pt83Drwv/K4naTZAgF/altf+qTp3TzeWAQKBgQDWCtemHmBQBjnvbBToiNVcy4PaLuXTOv3+LxrVwH2loFJgGyaSEGRHjbS4sScS5/nbcLp4KJV5hZxwIyHAlFnDSneAHhgRx3xbNLoulue1Dg5fWGCR2GTEiR3jd5+g5n4Jy8A1WKhwft83rC9vNBBE+j4HTKhcycm678ZBvspPQwKBgQC9l4599YeIPAlxJoONki22hi/1B4A8gBAu/EK62W76mAU8CswN/JIergFg4lUr9GzSORafQwl0ZVmriY6NfkkboJav1STddqMF+6MqI4YTQNq8Q6yBRwAtTguuheUvcR2C+eiFTMZQYATlW3e0uxTbDaheyP129vSxCZM4mNiJAQKBgAR2726YnZuttZGn6Hu9Nu4blKF9bu/MADZIF0A0JqrIwE8e4dwUGXNgok9j6pO7YPvRyyHsfsmtHsk8Al04+vJW4E0803WzeoWQgP2tyiF9rJESj9ka8WckEQqImTzK8gAUTusN8V1/V/qaTdb8+AXoTljqoWxmDR4oGDOQ3jlvAoGAfdmPtZ75E36+ycRGCQCipYlXZjBPeFmN5JKA/ST+HQwyVVPZdeNVX1YkBhrXbLD4V2eb06YdLY1lQ4+w981/W/5yaE91BCkPOBZ8jak9A6TGTbgiV0I5JNmsuXGZ268bZuja5zk6XvoN5e2cfXS9+FaNkDWcwo5ViPsRJb2X5wECgYBekwi6bJ1tSk8WfG3POGL/SWi0Cjmhiqs1EA2Odjgp//GqMXwkjiuX1A1h1t+ZugKfqMTw54C7YMoRM5qUjmtiVLLdLtdCX7ppe/WTe1MtnXVxQXLPZqG4HZnAyiGf21UsKbFsAXjEU1Zb1yebEcgWbzpWzrYM4/JNCqbRXXBgQw==";

        config.alipayPublicKey = ALIPAY_PUBLIC_KEY;
        return config;
    }


    @Bean
    public AlipayClient alipayClient (){
        return new DefaultAlipayClient(
                "https://openapi.alipay.com/gateway.do", APP_ID, getAlipayOptions().merchantPrivateKey, "json", "GBK", ALIPAY_PUBLIC_KEY,"RSA2");
    }




}
