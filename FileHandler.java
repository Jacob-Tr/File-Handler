package simcar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileHandler extends SimCar
{
	static int MAX_ACCOUNTS = 20000;
	static int MAX_STATS = 500;
	static String test;
	static String tag[] = new String[500];
	
	public void FileSave(File file, Map<String, String> stat) throws IOException
	{
		FileWriter writer = new FileWriter(file);
		FileReader read = new FileReader(file);
		BufferedReader reader = new BufferedReader(read);
		
		stat.keySet().toArray(tag);
		
		System.out.println(tag[0] + tag[1]);
		
		for(int i = 0; i < stat.size(); i++)
		{
			writer.write(tag[i] + ":" + stat.get(tag[i]) + "\n");
		}
		writer.close();
		reader.close();
		return;
	}
	
	public Map FileLoad(File file, int fileLines) throws IOException
	{
		Map<String, String> map = new HashMap<String, String>();
		FileReader read = new FileReader(file);
		BufferedReader reader = new BufferedReader(read);
		
		String str = null;
		
		for(int i = 0; i < fileLines; i++)
		{
			if(keyValue(i) == null) break;
			str = reader.readLine();
			
			for(int ii = 0; ii < str.length(); ii++)
			{
				if(ii == str.length() - 1) str = "<NO VALUE>";
				if(str.charAt(ii) == ':')
				{
					str = str.substring(ii + 1, str.length());
					break;
				}
				continue;
			}
			
			map.put(keyValue(i), str);
		}
		return map;
	}
	
	public String keyValue(int key)
	{
		switch(key)
		{
			case 0: return "Password";
			case 1: return "Account";
			default: return null;
		}
	}
	
	
	/*{
		if(!file.exists())
		{
			System.out.println("Error: Could not find file to read from.");
			return;
		}
		
		FileReader reader = new FileReader(file);
		BufferedReader read = new BufferedReader(reader);
		
		Outer: for(int i = 0; i < MAX_STATS; i++)
		{
			test = read.readLine();
			System.out.println(test);
			if(test == null) break;
			
			for(int ii = 0; ii  < 500; ii++)
			{
				if(test.charAt(ii) == ':' && test.length() >= ii)
				{
					tags[i] = test.substring(0, ii);
					if(test.length() > ii)
					{
						stat[playerid][i] = "" + value;
					}
					break;
				}
				else if(test.length() <= ii) break;
				else continue;
			}
			continue;
		}
		
		Outer: for(int i = 0; i < MAX_STATS; i++)
		{
			test = read.readLine();
			if(test == null) break;
			
			Inner: for(int ii = 0; ii < 500; ii++)
			{
				if(test.length() <= ii) break Outer;
				if(test.charAt(ii) == ':')
				{
					if(test.substring(0, ii - 1) == tag)
					{
						if(IsStrNumeric(test.substring(ii + 1, test.length())))
						{
							stat[playerid][i] = test.substring(ii + 1, test.length());
							for(int iii = 0; iii <= stat[playerid][i].length(); iii++)
							{
								if(stat[playerid][i].charAt(iii) == '.')
								{
									stat[playerid][i] = stat[playerid][i].substring(0, iii - 1);
									break Inner;
								}
								else if(iii >= stat[playerid][i].length())
								{
									stat[playerid][i] = stat[playerid][i].substring(0, stat[playerid][i].length());
									break Inner;
								}
								else continue;
							}
						}
						else break Inner;
					}
					else
					{
						tags[i] = test.substring(0,  ii);
						if(test.length() > ii)
						{
							stat[playerid][i] = test.substring(ii + 1, test.length());
						}
					}
					continue;
				}
				else continue;
			}
			continue;
		}
		
		reader.close();
		read.close();
		
		FileWriter writer = new FileWriter(file);
		
		for(int i = 0; i < MAX_STATS; i++)
		{
			if(tags[i] == null) break;
			System.out.println(tags[i]  + stat[playerid][i] + i);
			writer.write(String.format("%s:%s", tags[i], stat[playerid][i]));
			writer.write(System.lineSeparator());
			//else writer.write(String.format("\n%s:%s", tags[i], stat[playerid][i]));
		}
		
		writer.close();
		return;
	}
	
		if(!file.exists())
		{
			System.out.println("Error: Could not find file to read from.");
			return;
		}
		
		FileReader reader = new FileReader(file);
		BufferedReader read = new BufferedReader(reader);
		
		Outer: for(int i = 0; i < MAX_STATS; i++)
		{
			test = read.readLine();
			System.out.println(test);
			if(test == null) break;
			
			for(int ii = 0; ii  < 500; ii++)
			{
				if(test.charAt(ii) == ':' && test.length() >= ii)
				{
					tags[i] = test.substring(0, ii);
					if(test.length() > ii)
					{
						stat[playerid][i] = "" + value;
					}
					break;
				}
				else if(test.length() <= ii) break;
				else continue;
			}
			continue;
		}
		
		Outer: for(int i = 0; i < MAX_STATS; i++)
		{
			test = read.readLine();
			if(test == null) break;
			
			Inner: for(int ii = 0; ii < 500; ii++)
			{
				if(test.length() <= ii) break Outer;
				if(test.charAt(ii) == ':')
				{
					if(test.substring(0, ii - 1) == tag)
					{
						if(IsStrNumeric(test.substring(ii + 1, test.length())))
						{
							stat[playerid][i] = test.substring(ii + 1, test.length());
							for(int iii = 0; iii <= stat[playerid][i].length(); iii++)
							{
								if(stat[playerid][i].charAt(iii) == '.')
								{
									stat[playerid][i] = stat[playerid][i].substring(0, iii - 1);
									break Inner;
								}
								else if(iii >= stat[playerid][i].length())
								{
									stat[playerid][i] = stat[playerid][i].substring(0, stat[playerid][i].length());
									break Inner;
								}
								else continue;
							}
						}
						else break Inner;
					}
					else
					{
						tags[i] = test.substring(0,  ii);
						if(test.length() > ii)
						{
							stat[playerid][i] = test.substring(ii + 1, test.length());
						}
					}
					continue;
				}
				else continue;
			}
			continue;
		}
		
		reader.close();
		read.close();
		
		FileWriter writer = new FileWriter(file);
		
		for(int i = 0; i < MAX_STATS; i++)
		{
			if(tags[i] == null) break;
			System.out.println(tags[i]  + stat[playerid][i] + i);
			writer.write(String.format("%s:%s", tags[i], stat[playerid][i]));
			writer.write(System.lineSeparator());
			//else writer.write(String.format("\n%s:%s", tags[i], stat[playerid][i]));
		}
		
		writer.close();
		return;
	}

	public void FileSaveDouble(String name, String tag, double value, int playerid, int line) throws IOException
	{	
		boolean checks = false;
		
		File file = new File("C:\\Users\\jacob\\eclipse-workspace\\SIMCAR\\Local_Saves" + name + "\\" + name + ".ini");
		
		if(!file.exists())
		{
			System.out.println("Error: Could not find file to read from.");
			return;
		}
		
		FileReader reader = new FileReader(file);
		BufferedReader read = new BufferedReader(reader);
		
		for(int i = 0; i < MAX_STATS; i++)
		{
			test = read.readLine();
			if(test == null) break;
			
			for(int ii = 0; ii  < 500; i++)
			{
				if(test.charAt(ii) == ':')
				{
					tags[i] = test.substring(0, ii -1);
					stat[playerid][i] = test.substring(ii + 1, test.length());
					break;
				}
				if(ii >= test.length()) break;
				else continue;
			}
			continue;
		}
		
		for(int i = 0; i < MAX_STATS; i++)
		{
			test = read.readLine();
			if(test == null) break;
			
			for(int ii = 0; ii < 500; ii++)
			{
				if(test.charAt(ii) == ':')
				{
					if(test.substring(0, ii - 1) == tag)
					{
						if(IsStrNumeric(test.substring(ii + 1, test.length())))
						{
							stat[playerid][line] = test.substring(ii + 1, test.length());
							for(int iii = 0; iii <= stat[playerid][line].length(); iii++)
							{
								if(stat[playerid][line].charAt(iii) == '.')
								checks = true;
								else continue;
							}
							break;
						}
						else continue;
					}
					break;
				}
				else continue;
			}
		}
		
		if(checks == false)
		{
			System.out.println("* Error: Value is not a double and cannot be saved here.");
			return;
		}
		
		reader.close();
		read.close();
		
		FileWriter writer = new FileWriter(file);
		
		writer.write("");
		
		for(int i = 0; i < MAX_STATS; i ++)
		{
			if(tags[i] == null) break;
	        writer.write(String.format("%s:%s", tags[i], stat[playerid][i]));
	        writer.write(System.lineSeparator());
	        continue;
		}
		
		writer.close();
		return;
	}
	
	public void FileSaveString(String name, String tag, String value, int playerid, int line) throws IOException
	{
		File file = new File("C:\\Users\\jacob\\eclipse-workspace\\SIMCAR\\Local_Saves" + name + "\\" + name + ".ini");
		
		if(!file.exists())
		{
			System.out.println("Error: Could not find file to read from.");
			return;
		}
		
		FileReader reader = new FileReader(file);
		BufferedReader read = new BufferedReader(reader);
		
		
		for(int i = 0; i < MAX_STATS; i++)
		{
			test = read.readLine();
			if(test == null) break;
			
			for(int ii = 0; ii  < 500; ii++)
			{
				try
				{
					if(test.charAt(ii) == ':')
					{
						tags[i] = test.substring(0, ii -1);
						stat[playerid][i] = test.substring(ii + 1, test.length());
						break;
					}
					else continue;
				}
				catch(IndexOutOfBoundsException e)
				{
					System.out.println("Error: FileHandler/FileSaveString <Index Out Of Bounds Exception #1>");
					break;
				}
			}
			continue;
		}
		
		for(int i = 0; i < MAX_STATS; i++)
		{
			test = read.readLine();
			if(test == null) break;
			for(int ii = 0; ii < 500; ii++)
			{
				try
				{
					if(test.charAt(ii) == ':')
					{
						if(test.substring(0, ii - 1) == tag)
						{
							if(!IsStrNumeric(test.substring(ii + 1, test.length())))
							{
								stat[playerid][line] = test.substring(ii + 1, test.length());
								break;
							}
							break;
						}
						else continue;
					}
					else if(test.length() > ii) break;
				}
				catch(IndexOutOfBoundsException e)
				{
					System.out.println("Error: FileHandler/FileSaveString <Index Out Of Bounds Exception #2>");
					continue;
				}
			}
			continue;
		}
		
		reader.close();
		read.close();
		
		FileWriter writer = new FileWriter(file);
		
		writer.write("");
		
		for(int i = 0; i < MAX_STATS; i ++)
		{
			if(tags[i] == null) break;
	        writer.write(String.format("%s:%s", tags[i], stat[playerid][i]));
	        writer.write(System.lineSeparator());
	        continue;
		}
		
		writer.close();
		return;
	}*/

	/*public void LoadFileStats(String name, int playerid) throws IOException
	{
		File file = new File("C:\\Users\\jacob\\eclipse-workspace\\SIMCAR\\Local_Saves" + name + "\\" + name + ".ini");
		
		if(!file.exists())
		{
			System.out.println("\nError: Could not find file to read from.");
			return;
		}
		
		FileReader reader = new FileReader(file);
		BufferedReader read = new BufferedReader(reader);
		
		for(int i = 0; i <= MAX_STATS; i++)
		{
			test = read.readLine();
			if(test == null) break;
			
			for(int ii = 0; ii  < 500; ii++)
			{
				try
				{
					if(test.charAt(ii) == ':')
					{
						tags[i] = test.substring(0, ii -1);
						stat[playerid][i] = test.substring(ii + 1, test.length());
						break;
					}
					else continue;
				}
				catch(IndexOutOfBoundsException e)
				{
					System.out.println("Error: FileHandler/LoadFileStats <Index Out Of Bounds Exception #1>");
					break;
				}
			}
			continue;
		}
		
		
		for(int i = 0; i <= MAX_STATS; i++)
		{
			test = read.readLine();
			if(test == null) break;
			for(int ii = 0; ii < 500; ii++)
			{
				try
				{
					if(test.charAt(ii) == ':')
					{
						if(IsStrNumeric(test.substring(ii + 1, test.length())))
						{
							stat[playerid][i] = test.substring(ii + 1, test.length());
							break;
						}
						else
						{
							charString[playerid][i] = stat[playerid][i].substring(stat[playerid][i].charAt(ii), stat[playerid][i].length());
						}
					}
					else if(test.length() < ii) break;
					continue;
				}
				catch(IndexOutOfBoundsException e)
				{
					System.out.println("Error: FileHandler/LoadFileStats <Index Out Of Bounds Exception #2>");
					continue;
				}
				catch(Exception ee)
				{
					System.out.println("Error: FileHandler/LoadFileStats <Generic Exception #1>");
					continue;
				}
			}
			for(int iii = 0; iii <= stat[playerid][i].length(); iii++)
			{
				System.out.println("" + iii);
				try
				{
					if(stat[playerid][i].charAt(iii) == '.')
					{
						charDouble[playerid][i] = Double.parseDouble(stat[playerid][i].substring(stat[playerid][i].charAt(iii), stat[playerid][i].length()));
						break;
					}
				}
				catch(IndexOutOfBoundsException e)
				{
					charInt[playerid][i] = Integer.parseInt(stat[playerid][i].substring(stat[playerid][i].charAt(i), stat[playerid][i].length()));
					break;
				}
			}
		}
		
		reader.close();
		read.close();
	}
	
	public int getIntStat(String tag, int id, int statId)
	{
		int type = -1;
		
		try
		{
			type = Integer.parseInt(stat[id][statId]);
		}
		catch(NumberFormatException e) 
		{
			return -1;
		}
		catch(Exception eee)
		{
			return -1;
		}
		return type;
	}
	
	public double getDoubleStat(String tag, int id, int statId)
	{
		double type = -1;
		
		try
		{
			type = Double.parseDouble(stat[id][statId]);
		}
		catch(NumberFormatException e) 
		{
			return -1;
		}
		catch(Exception eee)
		{
			return -1;
		}
		return type;
	}
	
	public String getStringStat(String tag, int id, int statId)
	{
		double type = -1;
		int typeInt = -1;
		
		try
		{
			type = Double.parseDouble(stat[id][statId]);
			typeInt = Integer.parseInt(stat[id][statId]);
		}
		catch(NumberFormatException e) 
		{
			return null;
		}
		catch(Exception eee)
		{
			return null;
		}
		
		if(type != -1 || typeInt != -1) return null;
		
		return stat[id][statId];
	}
	
	public int getStatID(String tag)
	{
		if(tag == null) return -1;
		if(tag == "Password") return 0;
		if(tag == "ID") return 1;
		return -1;
	}*/
}
