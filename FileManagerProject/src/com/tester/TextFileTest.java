package com.tester;

import com.filemanager.textfiles.TextDataFile;

public class TextFileTest {
	public static void main(String[] args)
	{
		try {
		TextDataFile file1=new TextDataFile("../../test1.txt","test1");
		file1.Save();
		TextDataFile file2=new TextDataFile("../../test2.txt");
		file2.Load();
		System.out.println(file2.getData());
		TestIni ini=new TestIni("./test.ini");
		ini.Load();
		System.out.println(ini.getIntData());
		if(ini.getIntData()>10)
		{
			ini.setIntData(0);
		}
		else
		{
			ini.setIntData(ini.getIntData()+1);
		}
		ini.Save();
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
	}
	
}
