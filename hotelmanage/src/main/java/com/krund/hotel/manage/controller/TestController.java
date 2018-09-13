package com.krund.hotel.manage.controller;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.krund.hotel.manage.annotation.RequestLimit;
import com.krund.hotel.manage.dto.Result;
import com.krund.hotel.manage.entity.Room;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;

@Controller
@RequestMapping("/test/")
public class TestController
{

    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = "LTAI4vdSsMbIb6rD";
    private static String accessKeySecret = "Y2cfwlbzrkv8N4wanABInFaj7y4VAe";
    private static String bucketName = "krundtest";


    public static void putObject(String bucketName, String key, String filePath) throws FileNotFoundException
    {
        try
        {
            // 初始化OSSClient
            OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

            // 获取指定文件的输入流
            File file = new File(filePath);
            InputStream content = new FileInputStream(file);

            // 创建上传Object的Metadata
            ObjectMetadata meta = new ObjectMetadata();

            // 必须设置ContentLength
            meta.setContentLength(file.length());

            Date expire = new Date(new Date().getTime() + 30 * 1000);
            meta.setExpirationTime(expire);

            // 上传Object.

            PutObjectResult result = client.putObject(bucketName, key, content, meta);
            // 打印ETag

            System.out.println("etag--------------->" + result.getETag());
        } catch (Exception es)
        {
        }
    }

    public static String getFileMD5(File file)
    {
        if (!file.isFile())
        {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try
        {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1)
            {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16).toUpperCase();
    }

    @RequestMapping(value = "oos", method = RequestMethod.GET)
    public static void oos() throws FileNotFoundException
    {

        putObject(bucketName, "e.xaml", "D:\\e.xaml");
        File file = new File("D:\\e.xaml");
        String md5 = getFileMD5(file);

        System.out.println("md5---------------->" + md5);
    }

    @RequestMapping(value = "t1", method = RequestMethod.GET)
    @ResponseBody
    public static String test() throws FileNotFoundException
    {
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        try
        {
            /*
             * Create an empty folder without request body, note that the key must be
             * suffixed with a slash
             */
            final String keySuffixWithSlash = "MyObjectKey/";
            client.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            System.out.println("Creating an empty folder " + keySuffixWithSlash + "\n");

            /*
             * Verify whether the size of the empty folder is zero
             */
            OSSObject object = client.getObject(bucketName, keySuffixWithSlash);
            System.out.println("Size of the empty folder '" + object.getKey() + "' is " +
                    object.getObjectMetadata().getContentLength());
            object.getObjectContent().close();

        } catch (OSSException oe)
        {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce)
        {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            client.shutdown();
        }

        return "ee";

    }

    @RequestLimit //请求频率限制
    @RequestMapping(value = "testput",method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "获得Room对象")
    public Result<Room> getById(HttpServletRequest request) throws Exception
    {
        return Result.success("hello");
    }
}