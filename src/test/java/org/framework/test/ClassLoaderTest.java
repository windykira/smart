package org.framework.test;

import org.apache.log4j.Logger;
import org.framework.utils.ClassUtil;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

/**
 * Created by windy on 2016/9/9.
 */
public class ClassLoaderTest {

    private Logger log = Logger.getLogger(ClassLoaderTest.class);

    @Test
    public void test(){
        Set<Class<?>> classSet = ClassUtil.getClassSet("org.framework.helper");
        String str = "";
    }

    @Test
    public void test1()
    {
        File readFile = new File("F://photos");
        File[] files = readFile.listFiles();
        int length = files.length;
        File file;
        String fileName;
        String filePath;
        for(int i=0;i<length;i++)
        {
            file = files[i];
            fileName = file.getName();
            filePath = file.getPath();
            try {
                copyFile(file, new File(filePath.replaceAll(fileName, "CUPD_"+i+fileName+".jpg")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 复制文件
    private void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }
}
