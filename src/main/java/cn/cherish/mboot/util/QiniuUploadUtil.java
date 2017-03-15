package cn.cherish.mboot.util;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.IOException;

/**
 * 七牛上传工具
 * Created by Cherish on 2017/2/11.
 */
public class QiniuUploadUtil {

    //设置好账号的ACCESS_KEY和SECRET_KEY
    private static final String ACCESS_KEY = "blwSothQH2Kqv86Zuzs1H_CUtB_LmWNqurc5rgqp";
    private static final String SECRET_KEY = "4SwYbPDC4op52h3N6wl0a5bNyBEZNeSr96CDJgrl";
    //要上传的空间
    private static final String BUCKET_NAME = "cherish-mboot";
    // cherish-mboot下载文件的链接
    private static final String DOMAIN = "http://ol6z72uko.bkt.clouddn.com/";
    //密钥配置
    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    ///////////////////////指定上传的Zone的信息//////////////////
    //第一种方式: 指定具体的要上传的zone
    //注：该具体指定的方式和以下自动识别的方式选择其一即可
    //要上传的空间(bucket)的存储区域为华东时
    // Zone z = Zone.zone0();
    //要上传的空间(bucket)的存储区域为华北时
    // Zone z = Zone.zone1();
    //要上传的空间(bucket)的存储区域为华南时
    // Zone z = Zone.zone2();

    //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
    private static final Zone z = Zone.autoZone();
    private static final Configuration c = new Configuration(z);
    //创建上传对象
    private static final UploadManager uploadManager = new UploadManager(c);

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public static String getUpToken() {
        return auth.uploadToken(BUCKET_NAME);
    }

    /**
     * 上传文件到七牛
     * @param filePath 上传文件的本地路径
     * @param key 上传到七牛后保存的文件名
     * @throws IOException IO异常，本地文件不存在等
     */
    public static void upload(String filePath, String key) throws IOException {
        try {
            //调用put方法上传
            Response res = uploadManager.put(filePath, key, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException ignore) {
                //ignore
            }
        }
    }

    /*public static void main(String args[]) throws IOException {
        upload("", "");
    }*/

}
