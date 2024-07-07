package com.tester;

import com.filemanager.inifile.BaseIniDataFile;
import com.filemanager.inifile.KeyNameAnnotation;

public class TestIni extends BaseIniDataFile{
	public TestIni(String filePath)
	{
		super(filePath);
	}
	enum TestEnum{
		TEST1,
		TEST2,
	}
	@KeyNameAnnotation(value="strdata")
	private String strData="TESTSTR";
	@KeyNameAnnotation(value="intdata")
	private int intData=2;
	private TestEnum testEnum=TestEnum.TEST1;
	public int getIntData()
	{
		return this.intData;
	}
	public void setIntData(int intData)
	{
		this.intData=intData;
	}
	@Override
	public boolean Save() {
		String section="test";
		try
		{
			this.writeData(section,this.getKeyName("intData"), intData);
			this.writeData(section, "strdata", strData);
			this.writeData(section, "testenum", testEnum);
		}
		catch(Exception e){
			this.lastException=e;
		}
		return true;
	}

	@Override
	public boolean Load() {
		String section="test";
		try {
			intData=this.readData(section,this.getKeyName("intData"), intData);
			strData=this.readData(section, "strdata", strData);
			testEnum=this.readData(section, "testenum",TestEnum.class, testEnum);
		}
		catch(Exception e){
			this.lastException=e;
		}
		return true;
	}
	
}
