import java.io.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
 
public class FileCoder 
{
    private static final String algorithm 		= "AES";
    private static final String transformation 	= algorithm + "/ECB/PKCS5Padding";
    private static final String strFileKey 		= "qwfwqflqwkkkdmmw";
 
    private Key key;
 
    public FileCoder(Key key) {
        this.key = key;
    }
 
    public void encrypt(File source, File dest) throws Exception {
        crypt(Cipher.ENCRYPT_MODE, source, dest);
    }
 
    public void decrypt(File source, File dest) throws Exception {
        crypt(Cipher.DECRYPT_MODE, source, dest);
    }

    private void crypt(int mode, File source, File dest) throws Exception 
    {
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(mode, key);
        InputStream input = null;
        OutputStream output = null;
         
        try {
            input = new BufferedInputStream(new FileInputStream(source));
            output = new BufferedOutputStream(new FileOutputStream(dest));
            
            byte[] buffer = new byte[1024];
            int read = -1;
            while ((read = input.read(buffer)) != -1) {
                output.write(cipher.update(buffer, 0, read));
            }
            output.write(cipher.doFinal());
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ie) {
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ie) {
                }
            }
        }
    }
     
 
    public static void main(String[] args) throws Exception 
    {
    	if(args.length == 0)
    	{
    		System.out.println("cmd error !!  java -jar FileCoder.jar < src-dir/ > < dest-dir/ >  형식을 맞춰주세요.");
    		return;
    	}

    	String ORG_PATH 	= args[0].replaceAll("\\\\\\\\", "/");
    	String COPY_PATH	= args[1].replaceAll("\\\\\\\\", "/");
    	
    	System.out.println(ORG_PATH);
    	System.out.println(COPY_PATH);
    	
    	File orgDir = new File(ORG_PATH);
    	File copyDir = new File(COPY_PATH);
    	
    	if(!orgDir.exists() || !orgDir.isDirectory())
    	{
    		System.out.println(ORG_PATH+"경로를 찾을 수 없습니다. 디렉토리를 생성해주세요.");
    		return;
    	}
    	
    	if(!copyDir.exists() || !copyDir.isDirectory())
    	{
    		System.out.println(COPY_PATH+"경로를 찾을 수 없습니다. 디렉토리를 생성해주세요.");
    		return;
    	}
    	
    	File[] orgFileList = orgDir.listFiles();
    	if(orgFileList == null || orgFileList.length == 0)
    	{
    		System.out.println(ORG_PATH + "에 파일이 없습니다. 변환할 파일을 해당 디렉토리에 넣어주세요..");
        	return;
    	}
    	
    	SecretKeySpec key = new SecretKeySpec(strFileKey.getBytes(), algorithm);
    	FileCoder coder = new FileCoder(key);
    	
    	for (File file : orgFileList) 
    	{
			coder.encrypt(new File(file.getPath()), new File(COPY_PATH + file.getName()));
		}
    }
}