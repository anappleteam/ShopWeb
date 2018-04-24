package bupt.sse.shop.order.action;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016091600522386";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCwCqnT0Pq2bON54AeEuj9N7z/RdLkh8N0+76EFTgtpsQ3xQgX5RRNX91S7lPKwkyFztxx39lfeBK5GbOTfEyS1JasoFQagGBlHf08ExeO9sNacXeHg+b/lrWxj8kbbpUrJOQI/0yChsNm1qU7DdCvlPQlVwUpdCv6fIxL+tmwhwcJSdd8x0A6l1hvMurEgKSsGHBN+K4GytQx4QrLI4jCfrwGFrv6c0okqj8++HQzS3a0JMpYk9otFLtu2emXTzEZJJaHuLY3z+WX9KrnSJxdwUG0OmA+XKLR42v7ImRhLbj6L6ZplrV7YLSAM4tRxyVAB3T9lDUd04oOO1BksGtmtAgMBAAECggEBAK4I/LfxA8rRf1l/hy9KSUcs3XSe5l+NlcygbhmmfFXyugvm76ztAyCV3WjM6dhyvEF/p19ZZKiTDA+HnASFEmcbZEft8WzYJjWuwFnBoVJovuI7VhFqFrJjbqKoZDDFClhASd77RGlVQwX66WkFMlggrVfgDTpKokqFDERP6Dq9hS7G6woiSR+6nBxXlnGGSbe3TW7Ujc/jOGRcOtKmuXvMlQxgKpcBqgN0ThABt2YbQEVlE/H5zR2w+7SESAKgDvIBQLUenaGjyeot3HItJXrALX2ojkvNcwwSRfi03fxN3GZ6tu/LRdyKYVX6Hn1cSwwp1+wnhvjhjneG3/m0+QECgYEA1r9e1nOfR3iJrrJIoHoDF+W1H39L+lPLEuj4kfIJW9tOPTjlSNRjk6XZXxLN2zTzUTBWWj01ls6bZqD0NP0WCxm6y6s9Rhn+8W+ml55y/mFQtEFf/uzW8HnEpKtuGUOj+26nWuVgTC9mS59VivFfK0NsUrXnpKLK7ULsixYJ0yECgYEA0dva+ens5h1welICSB6ula7ZB0Tnnk1LmcSQwXgNCsc/DzosP/fxJeCDoxv7wRAadaNfA6rRo/6XuBtVIVio1pY0KjtClO5WXNJlExGRBzUVCrAA4eey4idypQR2KWADgTfkv58kvXi4jPDghZYGVXwEeI6/xt675cJhddLIAQ0CgYB75mYdbRBU+CLX/F0S2dK3Ev1xcsyvXXEpM+lWq0LjEKEPiaggYQoiwemshPjopKBOE4l+/xnQLNxUVkEP4YW6WdoAcIglzhHd0wFXMUA/rH4XwjBUsOlCTb87Ev2u7evKwYklVHo++ooWz5NXWvQBziDNh85imtpiEd7QUKLSgQKBgC1FmpWzSzHRRAR/UJKyW8NatuMEkFrsHdUMPJYgCeA7X3UvfYBHQrSDHKLBVKwvhyW9mq+12Afi0mQbMWBCOhogyRwbMJ/kUqkvkjJs5baCUwm9MlOJomN7UxYaQktmtRrZLfXQpSJiup1dEXKADNnAKU7ArMqAHlTQfCno0NC5AoGASgNlGrsfDLhIAwAgeRgDBoMGJathALIriddmduhKr6H0xLrrhXgMgGBHAvA7z6JVhdFIKIfdeMLFWBt1aBWxPcA1aabMdMBn+MG27bObzYJXV5ICHdJfNMsXynO25sEiLa0JJhJO1EEc1IspTfM7sl2wDv4KtCtWt7kqwqx7fyQ=";
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzccG7B3sy7EIkU2uArNhJUZD/KQ//s6/04J2s7vAKf2BOauKoy+ZMvhneZVFmcnRxFv1Oys2FhGd7yH0DHCxdYf0mYSSCNVMfgy0ZBn2U9TjydDO8Nby2a+SrIEjgpRvHrYqo0VGDSXLXKtlp3Gtwpa9nyJtwsEsnVbxBXcHkFIVPYvDReKU1JqAXvG8AXezHuBHewcYDo8cOguCr9z3Vmy2zwNcP1HNyoLSlfd/6JgRTox+rQZPsr8JrnsBgTdWvt0B2wkedD6aA0T9rDum1myPea95YGJCqP+hKjXCgfLIS6OKBaIaJd+2dqH4fhhSpbdov/7PfTV+nSwl4I7fFQIDAQAB";
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/4396Shop/order_returnorder.action";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

