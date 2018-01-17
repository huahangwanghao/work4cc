package com.wanghao.ftp;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/** 
* @author WH 作者 E-mail: 
* @version 创建时间：2017年7月14日 下午6:10:07 
* 类说明 
*/
public class FTPUtil {
    public static FTPClient getFTPClient(String ftpHost, String ftpPassword,  
            String ftpUserName, int ftpPort) {  
        FTPClient ftpClient = null;  
        try {  
            ftpClient = new FTPClient();  
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器  
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器  
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {  
                System.out.println("未连接到FTP，用户名或密码错误。");  
                ftpClient.disconnect();  
            } else {  
                System.out.println("FTP连接成功。");  
            }  
        } catch (SocketException e) {  
            e.printStackTrace();  
            System.out.println("FTP的IP地址可能错误，请正确配置。");  
        } catch (IOException e) {  
            e.printStackTrace();  
            System.out.println("FTP的端口错误,请正确配置。");  
        }  
        return ftpClient;  
    }  
}
 