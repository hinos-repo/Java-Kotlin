import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.sql.*;

public class SQLiteJDBC {

	final static int nTableCount = 4;
	final static String strDbName = "asmrdb";

	static String CSV_DIR = "";
	static String DB_PATH = "";

	public static void main(String args[]) throws Exception {
		
    	if(args.length == 0)
    	{
    		System.out.println("cmd error !!  java -jar DbReader.jar < csv-dir/ > < db-filePath/ >  형식을 맞춰주세요.");
    		return;
    	}
    	
    	CSV_DIR 	= args[0].replaceAll("\\\\\\\\", "/");
    	DB_PATH		= args[1].replaceAll("\\\\\\\\", "/");

		File dbFile = getDbFile(DB_PATH);
		File[] orgFileList = getCsvFileList(CSV_DIR);

		if (dbFile == null)
			return;
		if (orgFileList == null)
			return;


		String arr_tableName[] = new String[nTableCount];
		arr_tableName[0] = "tb_sound_category.csv";
		arr_tableName[1] = "tb_sound.csv";
		arr_tableName[2] = "tb_theme.csv";
		arr_tableName[3] = "tb_theme_sound.csv";

		File arr_csvFile[] = new File[nTableCount];

		for (int i = 0; i < orgFileList.length; i++) {
			if (orgFileList[i].getName().equals(arr_tableName[0])) {
				arr_csvFile[0] = orgFileList[i];
			} else if (orgFileList[i].getName().equals(arr_tableName[1])) {
				arr_csvFile[1] = orgFileList[i];
			} else if (orgFileList[i].getName().equals(arr_tableName[2])) {
				arr_csvFile[2] = orgFileList[i];
			} else if (orgFileList[i].getName().equals(arr_tableName[3])) {
				arr_csvFile[3] = orgFileList[i];
			}
		}

		ArrayList<String> arr_category = new ArrayList<String>();
		ArrayList<String> arr_sound = new ArrayList<String>();
		ArrayList<String> arr_theme = new ArrayList<String>();
		ArrayList<String> arr_themeSound = new ArrayList<String>();

		for (int a = 0; a < arr_csvFile.length; a++) {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream(arr_csvFile[a].getPath()), "utf-8"));

			String st;

			while ((st = br.readLine()) != null) {
				if (a == 0) {
					arr_category.add(st);
				}

				else if (a == 1) {
					arr_sound.add(st);
				}

				else if (a == 2) {
					arr_theme.add(st);
				}

				else if (a == 3) {
					arr_themeSound.add(st);
				}
			}
		}

		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:/"+DB_PATH);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();

			for (int i = 1; i < arr_category.size(); i++) {
				String calum = arr_category.get(0);
				String tableName = arr_tableName[0].replaceAll(".csv", "");

				String result = "";
				String sp[] = arr_category.get(i).split(",",-1);
				for (int j = 0; j < sp.length; j++) {
					try {
						int nCheck = Integer.parseInt(sp[j]);
						result += sp[j];
					} catch (Exception e) {
						if (!sp[j].contains("\"")) {
							result += "'" + sp[j] + "'";
						}
						else {
							result += sp[j];
						}
					}
					if (j != sp.length - 1) {
						result += ",";
					}
				}

				String sql = "INSERT INTO " + tableName + "(" + calum + ")" + "VALUES(" + result + ")";
				stmt.executeUpdate(sql);
			}

			for (int i = 1; i < arr_sound.size(); i++) {
				String calum = arr_sound.get(0);
				String tableName = arr_tableName[1].replaceAll(".csv", "");

				String result = "";
				String sp[] = arr_sound.get(i).split(",",-1);
				for (int j = 0; j < sp.length; j++) {
					try {
						int nCheck = Integer.parseInt(sp[j]);
						result += sp[j];
					} catch (Exception e) {
						if (!sp[j].contains("\"")) {
							result += "'" + sp[j] + "'";
						}
						else {
							result += sp[j];
						}
					}
					if (j != sp.length - 1) {
						result += ",";
					}
				}

				String sql = "INSERT INTO " + tableName + "(" + calum + ")" + "VALUES(" + result + ")";
				stmt.executeUpdate(sql);
			}

			for (int i = 1; i < arr_theme.size(); i++) {
				String calum = arr_theme.get(0);
				String tableName = arr_tableName[2].replaceAll(".csv", "");

				String result = "";
				String sp[] = arr_theme.get(i).split(",", -1);

				for (int j = 0; j < sp.length; j++) {
					try {
						int nCheck = Integer.parseInt(sp[j]);
						result += sp[j];
					} catch (Exception e) {
						if (!sp[j].contains("\"")) {
							result += "'" + sp[j] + "'";
						} else {
							result += sp[j];
						}
					}
					if (j != sp.length - 1) {
						result += ",";
					}

				}

				String sql = "INSERT INTO " + tableName + "(" + calum + ")" + "VALUES(" + result + ")";

				stmt.executeUpdate(sql);
			}

			for (int i = 1; i < arr_themeSound.size(); i++) {
				String calum = arr_themeSound.get(0);
				String tableName = arr_tableName[3].replaceAll(".csv", "");

				String result = "";
				String sp[] = arr_themeSound.get(i).split(",",-1);
				for (int j = 0; j < sp.length; j++) {
					try {
						int nCheck = Integer.parseInt(sp[j]);
						result += sp[j];
					} catch (Exception e) {
						if (!sp[j].contains("\"")) {
							result += "'" + sp[j] + "'";
						} else {
							result += sp[j];
						}
					}
					if (j != sp.length - 1) {
						result += ",";
					}
				}

				String sql = "INSERT INTO " + tableName + "(" + calum + ")" + "VALUES(" + result + ")";

				stmt.executeUpdate(sql);
			}
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Insert Data successfully");
	}

	private static File[] getCsvFileList(String CSV_DIR) {
		File csvDir = new File(CSV_DIR);

		if (!csvDir.exists() || !csvDir.isDirectory()) {
			System.out.println(CSV_DIR + "경로를 찾을 수 없습니다. 디렉토리를 생성해주세요.");
			return null;
		}

		File[] orgFileList = csvDir.listFiles();
		if (orgFileList == null || orgFileList.length == 0) {
			System.out.println(CSV_DIR + "에 파일이 없습니다. 변환할 파일을 해당 디렉토리에 넣어주세요..");
			return null;
		}

		return orgFileList;
	}

	private static File getDbFile(String dbPath) 
	{
		File dbFile = new File(dbPath);

		if (!dbFile.exists()) {
			System.out.println(dbPath + "경로를 찾을 수 없습니다. 디렉토리를 생성해주세요.");
			return null;
		}

		return dbFile;
	}
}
