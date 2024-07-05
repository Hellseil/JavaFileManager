package com.tester;

import com.filemanager.textfiles.TextDataFile;

public class TextFileTest {
	public static void main(String[] args)
	{
		TextDataFile file1=new TextDataFile("../../test1.txt","test1");
		file1.Save();
		TextDataFile file2=new TextDataFile("../../test2.txt");
		file2.Load();
		System.out.println(file2.getData());
		
	}
}
