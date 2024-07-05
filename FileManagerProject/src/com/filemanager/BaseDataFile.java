package com.filemanager;

public abstract class BaseDataFile {
	
	public BaseDataFile()
	{
		this("");
	}
	public BaseDataFile(String filePath)
	{
		this.setFilePath(filePath);
	}
	protected String filePath;
	protected Exception lastException;
	
	
	public void setFilePath(String filePath)
	{
		this.filePath=filePath;
	}
	public String getFilePath()
	{
		return this.filePath;
	}
	public Exception getLastException()
	{
		return this.lastException;
	}
	public abstract boolean Save();
	public abstract boolean Load();
}