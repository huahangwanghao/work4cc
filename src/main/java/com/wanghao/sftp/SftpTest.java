package com.wanghao.sftp; 
/** 
* @author WH 作者 E-mail: 
* @version 创建时间：2017年7月14日 下午6:25:35 
* 类说明 
*/
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class SftpTest {
    public static void main(String[] args) {
        testLogin();
  //      testMakeDir();
//        testDelFile();
//        testDelEmptyDir();
//        testDir();
//        testLs();
//        testParamLs();
//        testChangeDir();
//        testExist();
//        testParamExist();
        testUploadFile();
//        testUploadFile2();
//        testDownload();
    }
    
    public static void testLogin(){ //OK
        Sftp sftp = getSftp();
        
        sftp.login();
        sftp.logout();
    }
    
    public static void testMakeDir(){ //OK
        Sftp sftp = getSftp();
        sftp.login();
        sftp.makeDir("test2");
        sftp.changeDir("test2");
        sftp.makeDir("/test2/test2_1");
        sftp.logout();
    }
    
    public static void testDelFile(){ //OK
        Sftp sftp = getSftp();
        sftp.login();
        sftp.delFile("file1.txt");
        sftp.logout();
    }
    
    public static void testDelEmptyDir(){ //OK
        Sftp sftp = getSftp();
        sftp.login();
        sftp.delDir("test3");
        sftp.logout();
    }
    
    public static void testDir(){ //OK
        Sftp sftp = getSftp();
        sftp.login();
        sftp.delDir("test4");
        sftp.logout();
    }
    
    public static void testLs(){ //OK
        Sftp sftp = getSftp();
        sftp.login();
        System.out.println(Arrays.toString(sftp.ls()));
        System.out.println(Arrays.toString(sftp.lsFiles()));
        System.out.println(Arrays.toString(sftp.lsDirs()));
        sftp.logout();
    }
    
    public static void testParamLs(){ //OK
        Sftp sftp = getSftp();
        sftp.login();
        System.out.println(Arrays.toString(sftp.ls("test1/test4")));
        System.out.println(Arrays.toString(sftp.lsFiles("test1/test4")));
        System.out.println(Arrays.toString(sftp.lsDirs("test1/test4")));
        sftp.logout();
    }
    
    public static void testChangeDir(){ //OK
        Sftp sftp = getSftp();
        sftp.login();
        sftp.changeDir("test1");
        sftp.changeDir("/test1/test4");
        sftp.changeToParentDir();
        sftp.changeToHomeDir();
        sftp.logout();
    }
    
    public static void testExist(){ //OK
        Sftp sftp = getSftp();
        sftp.login();
        System.out.println(sftp.exist("2fs.docx"));
        System.out.println(sftp.exist("test1"));
        System.out.println(sftp.existDir("test2"));
        System.out.println(sftp.existDir("2sfs.txt"));
        System.out.println(sftp.existFile("2sfs.txt"));
        System.out.println(sftp.existFile("test2"));
        sftp.logout();
    }
    
    public static void testParamExist(){ //OK
        Sftp sftp = getSftp();
        sftp.login();
        System.out.println(sftp.exist("test1","test4"));
        System.out.println(sftp.exist("test1","test_bak.jpg"));
        System.out.println(sftp.existDir("/test1","test3"));
        System.out.println(sftp.existDir("/test1","test_bak.jpg"));
        System.out.println(sftp.existFile("test1","test_bak.jpg"));
        System.out.println(sftp.existFile("test1","test2"));
        sftp.logout();
    }
    
    
    public static void testUploadFile(){ //OK
        Sftp sftp = getSftp();
        sftp.login();
        sftp.uploadFile("/home/wanghao/www/images", "test_bak2.jpg", "D:\\test.jpg");
        try {
            sftp.uploadFile("/home/wanghao/www/images", "test_bak3.jpg", new FileInputStream("D:\\test.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sftp.logout();
    }
    
    public static void testUploadFile2(){ //OK
        Sftp sftp = getSftp();
        sftp.login();
        sftp.uploadFile("test1/test3", "test_bak2.jpg", "D:\\test.jpg");
        try {
            sftp.uploadFile("test1/test2", "test_bak3.jpg", new FileInputStream("D:\\test.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sftp.logout();
    }
    
    public static void testDownload(){ //OK
        Sftp sftp = getSftp();
        sftp.login();
        sftp.downloadFile("test1", "test_bak.jpg", "D:\\testdown");
        sftp.downloadFile("/", "2fs.docx", "D:\\testdown");
        sftp.logout();
    }
    
    private static Sftp getSftp(){
        
        String host = "59.110.215.202";
        int port = 22;
        int timeout = 60000;
        String username = "wanghao";
        String password = "wanghao@123.comfuckcao!@#";
        
        Sftp sftp = new Sftp(host, port, timeout, username, password);
        
        return sftp;
    }
}