package com.tester;

import com.filemanager.inifile.BaseIniDataFile;

public class TestIni extends BaseIniDataFile{
	public TestIni(String filePath)
	{
		super(filePath);
	}
	enum TestEnum{
		TEST1,
		TEST2,
	}
	private String strData="TESTSTR";
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
		this.writeData(section, "intdata", intData);
		this.writeData(section, "strdata", strData);
		this.writeData(section, "testenum", testEnum);
		return true;
	}

	@Override
	public boolean Load() {
		String section="test";
		intData=this.readData(section, "intdata", intData);
		strData=this.readData(section, "strdata", strData);
		testEnum=this.readData(section, "testenum",TestEnum.class, testEnum);
		return true;
	}
	
}
