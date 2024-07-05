package com.filemanager.textfiles;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.filemanager.BaseDataFile;

public class TextDataFile extends BaseDataFile{
	public TextDataFile(){
		this("");
	}

	public TextDataFile(String filePath){
		this(filePath,"");
	}
	public TextDataFile(String filePath,Charset encode){
		this(filePath,"",encode);
	}
	public TextDataFile(String filePath,String data){
		this(filePath,data,StandardCharsets.UTF_8);
	}
	public TextDataFile(String filePath,String data,Charset encode){
		super(filePath);
		this.data=data;
		this.encode=encode;
	}
	
	
	protected Charset encode = StandardCharsets.UTF_8;
	protected String data;
	
	public String getData()
	{
		return this.data;
	}
	public void setData(String data)
	{
		this.data=data;
	}
	
	@Override
	public boolean Save() {
		boolean ret=false;
		try (BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(this.filePath), this.encode))) {
			writer.write(data);
			ret=true;
		} catch (IOException e) {
			this.lastException=e;
		}
		return ret;
	}


	@Override
	public boolean Load() {
		boolean ret=false;
		try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(this.filePath),this.encode))) {
            String line;
            StringBuilder text=new StringBuilder();
            boolean fast=true;
            while ((line = reader.readLine()) != null) {
                if(!fast){
                	text.append(System.lineSeparator());
                }else{
                	fast=false;
                }
                text.append(line);
                
            }
            this.data=text.toString();
            ret=true;
        } catch (IOException e) {
            this.lastException=e;
            ret=false;
        }
		return ret;
	}
}
