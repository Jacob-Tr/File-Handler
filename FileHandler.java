import java.nio.file.Paths;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileHandler
{	
	private static const int MAX_ACCOUNTS = 20000;
	private static const int MAX_STATS = 500;
	
	private String test;
	private String tag[] = new String[500];
	
	private Map<Integer, String> keyValue = new HashMap<Integer, String>(MAX_STATS);
	private keyValues = 0;
	
	private static List<HashMap<String, String>> value = new ArrayList<HashMap<String, String>>();
	private static int handles = 0;
	
	private handle = 0;
	
	private char driveLetter = 'C';
	
	private File directory = () ->
	{
		String path = System.getEnv("Path");
		this.driveLetter = envPath[0];
		
		return new File("" + this.driveLetter + () -> {return (isWindows() ? '\\:' : '/:');});
	};
	
	public FileHandler(String args)
	{
		this.handle = ++handles;
		boolean invalidPath = false;
		
		if(args[1] != ':') args = ("" + driveLetter + () -> {return (isWindows() ? '\\' : '/');} + args);
		
		try {Paths.get(directory.toString())}
		catch(InvalidPathException e) 
		{
			invalidPath = true;
			
			do
			{
				InputStreamReader input = new InputStreamReader(System.in);
				BufferedReader reader = new BufferedReader(input);
				
				System.out.println("" + "<Handle #" + handles "> no valid directory.\nSpecify one manually?:\n");
				
				String input = "";
				try {input = reader.readLine();}
				catch(IOException e) {e.printStackTrace()};
				
				try {Paths.get(input)}
				catch(InvalidPathException e) 
				{
					System.out.println("~ Invalid directory.\n");
					continue;
				}
				
				this.directory = input;
				
				invalidPath = false;
			} while(invalidPath);
		}
	}
	
	public int getMaxAccounts() {return MAX_ACCOUNTS;}
	public int getMaxStats() {return MAX_STATS;}
	
	public void fileCreate(String fileName)
	{
		if(!this.directory.isDirectory()) this.directory.mkdirs();
		
		File file = new File("" + this.directory + fileName);
		
		try {file.createNewFile();}
		catch(Exception e) {e.printStackTrace();}
	}
	public boolean fileExists(String fileName)
	{
		File file = new File("" + this.directory + fileName);	
		return file.exists();
	}
	public void fileDestroy(String fileName)
	{
		File file = new File("" + this.directory + fileName);
		if(file.exists()) {file.delete();}
	}
	
	public void fileSave(String fileName, Map<String, String> stat) throws IOException
	{
		File file = new File("" + this.directory + fileName);
		
		FileWriter writer = new FileWriter(file);
		FileReader read = new FileReader(file);
		
		BufferedReader reader = new BufferedReader(read);
		
		stat.keySet().toArray(tag);
		
		System.out.println(tag[0] + tag[1]);
		
		for(int i = 0; i < stat.size(); i++) {writer.write(tag[i] + ":" + stat.get(tag[i]) + "\n");}
	
		writer.close();
		reader.close();
		return;
	}
	
	public HashMap<String, String> fileLoad(String fileName, int fileLines) throws IOException
	{
		File file = new File("" + this.directory + fileName);
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		FileReader read = new FileReader(file);
		BufferedReader reader = new BufferedReader(read);
		
		String str = null;
		
		for(int i = 0; i < fileLines; i++)
		{
			if(this.getKeyValue(i) == null) break;
			str = reader.readLine();
			
			for(int ii = 0; ii < str.length(); ii++)
			{
				if(ii == str.length() - 1) str = "<NO VALUE>";
				
				if(str.charAt(ii) == ':' && ii < str.length()) {str = str.substring(ii + 1, str.length());}
				else if(ii == str.length() - 1) break;
				continue;
			}
			
			map.put(this.getKeyValue(i), str);
		}
		reader.close();
		return map;
	}
	
	public String getKeyValue(int index) {return this.keyValues.get(index);}
	
	public void addKeyValue(String key) {this.keyValue.put(++this.keyValues, key);}
	public void removeKeyValue(String key)
	{
		boolean removed = false;
		for(int i = 1; i < MAX_STATS; i++)
		{
			if(this.keyValue.get(i).equals(null)) return;
			
			if(removed)
			{
				this.keyValue.put(i - 1, this.keyValue.get(i));
				if(this.keyValue.get(i - 1).equals(null) break;
			}
			
			if(this.keyValue.get(i).equals(key) {removed = true;}
		}
	}
	public void setKeyValues(String[] args) 
	{
		for(int i = 1; i < MAX_STATS; i++
		{
			if(args[i] = null) break;
			this.addKeyValue(args[i]);
		}
	}
}
