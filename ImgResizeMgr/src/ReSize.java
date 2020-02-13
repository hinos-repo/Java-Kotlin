import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ReSize 
{
	final static String hdpi_foler_name 	= "mipmap-hdpi";
	final static String xhdpi_foler_name 	= "mipmap-xhdpi";
	final static String xxhdpi_foler_name 	= "mipmap-xxhdpi";
	final static String xxxhdpi_foler_name 	= "mipmap-xxxhdpi";
	final static String splash_dir			= "drawable-xhdpi";
	
	final static int hdpi 		= 72;
	final static int xhdpi 		= 96;
	final static int xxhdpi 	= 144;
	final static int xxxhdpi 	= 192;
	
	final static int[] arr_nSize = {hdpi, xhdpi, xxhdpi, xxxhdpi};
	final static String[] arr_strFolderName = {hdpi_foler_name, xhdpi_foler_name, xxhdpi_foler_name, xxxhdpi_foler_name};
	
	public static void main(String [] args)
	{
//		String org_path = "D:\\org\\ic_launcher.png";
//		String copy_path = "D:\\copy";
		
		String org_path = null;
		String copy_path = null;
		String splash_path = null;
		
		switch(args.length)
		{
			case 2 : 
			{
				org_path = args[0].trim();
				copy_path = args[1];
				break;
			}

			case 3 :
			{
				org_path = args[0];
				copy_path = args[1];
				splash_path = args[2];
				break;
			}
		}

		if(org_path.isEmpty())
		{
			System.out.println("오리지널 폴더 경로를 입력해주세요.");
			return;
		}
		else if(copy_path.isEmpty())
		{
			System.out.println("카피 폴더 경로를 입력해주세요.");
			return;
		}
		
		File org = new File(org_path);
		String orgFileName = org.getName();
		
		Image img = null;
		try {
			img = ImageIO.read(new File(org_path));
		} catch (IOException e1) {
			System.out.println("이미지 읽기 실패");
			e1.printStackTrace();
			return;
		}
		
		if(img == null)
		{
			System.out.println("이미지 읽기 실패");
			return;
		}

		ArrayList<String> arr_strUrl = new ArrayList<String>();
		
		for(int i = 0; i<arr_strFolderName.length; i++)
		{
			File f = new File(copy_path + "/" + arr_strFolderName[i]);
			if(!f.isDirectory())
			{
				if(!f.mkdirs())
				{
					System.out.println(copy_path + "/" + arr_strFolderName[i] + "폴더 생성 실패");
					return;
				}
			}
			
			arr_strUrl.add(copy_path + "/" + arr_strFolderName[i]);
		}
		
		boolean bCheck = true;
		for(int i = 0; i<arr_nSize.length; i++)
		{
			try {
				int imgOrgWidth = img.getWidth(null);
				int imgOrgHeight = img.getHeight(null);
				
				double rataio = (double)arr_nSize[i] / (double)imgOrgWidth;
				
				int imgCopyWidth = (int)(imgOrgWidth * rataio);
				int imgCopyHeight = (int)(imgOrgHeight * rataio);
				
				Image resizeImage = img.getScaledInstance(imgCopyWidth, imgCopyHeight, Image.SCALE_SMOOTH);
				
				BufferedImage newImage = new BufferedImage(imgCopyWidth, imgCopyHeight, BufferedImage.TYPE_INT_ARGB);
				Graphics g = newImage.getGraphics();
				g.drawImage(resizeImage, 0, 0, null);
				g.dispose();
				ImageIO.write(newImage, "png", new File(arr_strUrl.get(i)+"/"+"ic_launcher.png"));
			} catch (Exception e) {
				e.printStackTrace();
				bCheck = false;
				break;
			}
		}
		
		if(!bCheck)
		{
			System.out.println("이미지 생성 실패!!");
			return;
		}
		
		if(splash_path != null)
		{
			File splashDir = new File(copy_path + "/" + splash_dir);
			if(!splashDir.isDirectory())
			{
				if(!splashDir.mkdirs())
				{
					System.out.println(copy_path + "/" + splash_dir + "폴더 생성 실패");
					return;
				}
			}
			
			File splashFile = new File(splash_path);
			String strSplashName = splashFile.getName();
			fileCopy(splash_path, splashDir+"/"+strSplashName);
		}
	}
	
	public static void fileCopy(String inFileName, String outFileName) 
	{
        try {
            
            FileInputStream fis = new FileInputStream(inFileName);
            FileOutputStream fos = new FileOutputStream(outFileName);
            
            int data = 0;
            
            while((data=fis.read())!=-1) {
                fos.write(data);
            }
            fis.close();
            fos.close();
            
        } catch (IOException e) {
        	System.out.println("스플래쉬 파일 복사 실패");
            e.printStackTrace();
        }
	 }
}
