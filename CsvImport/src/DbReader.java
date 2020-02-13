import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DbReader 
{
	final static int nTableCount = 4;
	final static String strDbName = "asmrdb";
	
	static String CSV_DIR = "D:\\yk\\csv";
	static String DB_DIR  = "D:\\yk\\db";
	
	
	public static void main(String [] args) throws Exception
	{
		
//    	if(args.length == 0)
//    	{
//    		System.out.println("cmd error !!  java -jar DbReader.jar < csv-dir/ > < db-dir/ >  형식을 맞춰주세요.");
//    		return;
//    	}
    
		
    	File dbFile = getDbFile(DB_DIR);
    	File[] orgFileList = getCsvFileList(CSV_DIR);
    	
    	if(orgFileList == null) return;
    	if(dbFile == null) return;
    	
    	
    	String arr_tableName [] = new String [nTableCount];
    	arr_tableName[0] = "tb_sound_category.csv";
    	arr_tableName[1] = "tb_sound.csv";
    	arr_tableName[2] = "tb_theme.csv";
    	arr_tableName[3] = "tb_theme_sound.csv";
    	
    	File arr_csvFile[] = new File[nTableCount];
    	
    	for(int i = 0; i<orgFileList.length; i++)
    	{
    		if(orgFileList[i].getName().equals(arr_tableName[0]))
    		{
    			arr_csvFile[0] = orgFileList[i];
    		}
    		else if(orgFileList[i].getName().equals(arr_tableName[1]))
    		{
    			arr_csvFile[1] = orgFileList[i];
    		}
    		else if(orgFileList[i].getName().equals(arr_tableName[2]))
    		{
    			arr_csvFile[2] = orgFileList[i];
    		}
    		else if(orgFileList[i].getName().equals(arr_tableName[3]))
    		{
    			arr_csvFile[3] = orgFileList[i];
    		}
    	}
    	
    	ArrayList<String> arr_category 		= new ArrayList<String>();
    	ArrayList<String> arr_sound 		= new ArrayList<String>();
    	ArrayList<String> arr_theme 		= new ArrayList<String>();
    	ArrayList<String> arr_themeSound 	= new ArrayList<String>();
    	
    	for(int a = 0; a<arr_csvFile.length; a++)
    	{
    		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arr_csvFile[a].getPath()), "utf-8"));
        	
        	String st;
        	
        	while((st = br.readLine()) != null)
        	{
//        		if(a == 0)
//        		{
//        			arr_category.add(st);
//        		}
        		
          		if(a == 0)
          		{
          			String result = "";
          			String sp[] = st.split(",");
          			for(int i = 0; i<sp.length; i++)
          			{
          				try {
    						int nCheck = Integer.parseInt(sp[i]);
    						result += sp[i];
    					} catch (Exception e) {
    						result += "'"+sp[i]+"'";
    					}
          				if(i != sp.length-1)
          				{
          					result += ",";
          				}
          			}
          			System.out.println(result);
          			arr_category.add(result);
          		}
        		
        		else if(a == 1)
        		{
        			arr_sound.add(st);
        		}
        		
        		else if(a == 2)
        		{
        			arr_theme.add(st);
        		}
        		
        		else if(a == 3)
        		{
        			arr_themeSound.add(st);
        		}
        	}   
    	}
    	
//    	for(int i = 0; i<arr_category.size(); i++)
//    	{
//    		System.out.println(arr_category.get(i));
//    	}
    	
//    	String tableName = arr_tableName[0].replaceAll(".csv", "");
//    	String calum = arr_category.get(0);
//    	
//    	String sql = "INSERT INTO " + tableName + "(" + calum +")" + "VALUES(" + arr_category.get(1) + ")";
//    	System.out.println(sql);

    	
    	
    	
//    	for(int i = 0; i<arr_category.size(); i++)
//    	{
//    		System.out.println(arr_category.get(i));
//    	}
//    	
//    	System.out.println();
//    	System.out.println();
//    	
//    	for(int i = 0; i<arr_sound.size(); i++)
//    	{
//    		System.out.println(arr_sound.get(i));
//    	}
//    	
//    	System.out.println();
//    	System.out.println();
//    	
//    	for(int i = 0; i<arr_theme.size(); i++)
//    	{
//    		System.out.println(arr_theme.get(i));
//    	}
//    	
//    	System.out.println();
//    	System.out.println();
//    	
//    	for(int i = 0; i<arr_themeSound.size(); i++)
//    	{
//    		System.out.println(arr_themeSound.get(i));
//    	}


    	
//    	BufferedReader br = null;
//    	String line;
//    	String cvsSplitBy  = "\n";
//    	String[] field = null;
//    	
//    	try {
//            br = new BufferedReader(new InputStreamReader(new FileInputStream(arr_csvFile[0].getPath()), "utf-8"));
//            System.out.println(br.lines().count());
//            while ((line = br.readLine()) != null) {
//                field = line.split(cvsSplitBy);
//                break;
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    	
//    	for(int i = 0; i<field.length; i++)
//    	{
//    		System.out.println(field[i]);
//    	}
    	
    	
    

	}

	
	private static File[] getCsvFileList(String CSV_DIR)
	{
    	File csvDir = new File(CSV_DIR);
    	
    	if(!csvDir.exists() || !csvDir.isDirectory())
    	{
    		System.out.println(CSV_DIR+"경로를 찾을 수 없습니다. 디렉토리를 생성해주세요.");
    		return null;
    	}

    	File[] orgFileList = csvDir.listFiles();
    	if(orgFileList == null || orgFileList.length == 0)
    	{
    		System.out.println(CSV_DIR + "에 파일이 없습니다. 변환할 파일을 해당 디렉토리에 넣어주세요..");
        	return null;
    	}
    	
    	return orgFileList;
	}
	
	private static File getDbFile(String DB_DIR)
	{
		String strDbPath = DB_DIR + "\\"+strDbName;
		
    	File dbFile = new File(strDbPath);
    	
    	if(!dbFile.exists())
    	{
    		System.out.println(strDbPath+"경로를 찾을 수 없습니다. 디렉토리를 생성해주세요.");
    		return null;
    	}
    	
    	return dbFile;
	}

}
