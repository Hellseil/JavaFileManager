package com.filemanager.inifile;
import java.io.File;
import java.lang.reflect.Field;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.FileBased;
import org.apache.commons.configuration2.io.FileHandler;

import com.filemanager.BaseDataFile;

public abstract class BaseIniDataFile extends BaseDataFile {
	
	public BaseIniDataFile(){
		this("");
	}
	public BaseIniDataFile( String filePath){
		
		super(filePath);
	}
	private Configurations configs;
	private Configuration config;
	
	@Override
	public void setFilePath(String filePath)  {
		this.filePath=filePath;
		if(this.configs==null)
		{
			this.configs = new Configurations();
		}
		try
		{
			File iniFile = new File(this.filePath);
			this.config = this.configs.ini(iniFile);
		}catch (ConfigurationException e) {
	        // ConfigurationException をキャッチして適切に処理する
	        this.lastException = e;
	        //System.err.println("INIファイルの読み込み中にエラーが発生しました: " + e.getMessage());
		}catch (Exception e) {
			this.lastException=e;
		}
		
	}
	protected String getKeyName(String fieldName) throws NoSuchFieldException {
		String ret="";
		try {
	        Field field = this.getClass().getDeclaredField(fieldName);
	        if (field.isAnnotationPresent(KeyNameAnnotation.class)) {
	        	KeyNameAnnotation annotation = field.getAnnotation(KeyNameAnnotation.class);
	            ret=annotation.value();
	        }
	   } catch (NoSuchFieldException e) {
	       	this.lastException=e;
	       	throw e;
	   }
		return ret;
	}
	// <editor-fold desc=" WriteMethod">
    protected boolean writeData(String section,String key,String value) {
    	boolean ret=false;
    	try {
    		//PropertiesConfiguration iniConfig = (PropertiesConfiguration) config;
    		FileHandler fileHandler = new FileHandler((FileBased) config);
    		this.config.setProperty(section+"."+key,value);
			fileHandler.save(this.filePath);
			ret=true;
		} catch (org.apache.commons.configuration2.ex.ConfigurationException e) {
			this.lastException=e;
			ret=false;
		}
    	return ret;
    }

    protected boolean writeData(String section,String key,int value) {
    	return this.writeData(section, key,String.valueOf(value));
    }
    protected boolean writeData(String section,String key,long value) {
    	return this.writeData(section, key,String.valueOf(value));
    }
    protected boolean writeData(String section,String key,short value) {
    	return this.writeData(section, key,String.valueOf(value));
    }

    protected boolean writeData(String section,String key,float value) {
    	return this.writeData(section, key,String.valueOf(value));
    }
    protected boolean writeData(String section,String key,double value) {
    	return this.writeData(section, key,String.valueOf(value));
    }

    protected <E extends Enum<E>> boolean writeData(String section,String key,E value) {
    	return this.writeData(section, key,value.name());
    }
    
    // </editor-fold>

	 // <editor-fold desc=" ReadMethod">
    protected String readData(String section,String key,String defaultValue) {
    	String ret=defaultValue;
    	try {
    		ret= this.config.getString(section+"."+key);
		} catch (Exception e) {
			this.lastException=e;
			ret=defaultValue;
		}
    	return ret;
    }
    protected int readData(String section,String key,int defaultValue) {
    	int ret=defaultValue;
    	try {
    		ret=Integer.parseInt( this.readData(section,key,String.valueOf(defaultValue)));
		} catch (Exception e) {
			this.lastException=e;
			ret=defaultValue;
		}
    	return ret;
    }
    protected long readData(String section,String key,long defaultValue) {
    	long ret=defaultValue;
    	try {
    		ret=Long.parseLong( this.readData(section,key,String.valueOf(defaultValue)));
		} catch (Exception e) {
			this.lastException=e;
			ret=defaultValue;
		}
    	return ret;
    }
    protected short readData(String section,String key,short defaultValue) {
    	short ret=defaultValue;
    	try {
    		ret=Short.parseShort( this.readData(section,key,String.valueOf(defaultValue)));
		} catch (Exception e) {
			this.lastException=e;
			ret=defaultValue;
		}
    	return ret;
    }
    protected float readData(String section,String key,float defaultValue) {
    	float ret=defaultValue;
    	try {
    		ret=Float.parseFloat( this.readData(section,key,String.valueOf(defaultValue)));
		} catch (Exception e) {
			this.lastException=e;
			ret=defaultValue;
		}
    	return ret;
    }
    protected double readData(String section,String key,double defaultValue) {
    	double ret=defaultValue;
    	try {
    		ret=Double.parseDouble( this.readData(section,key,String.valueOf(defaultValue)));
		} catch (Exception e) {
			this.lastException=e;
			ret=defaultValue;
		}
    	return ret;
    }
    protected <E extends Enum<E>> E readData(String section,String key,Class<E> enumClass,E defaultValue) {
    	E ret=defaultValue;
    	try {
    		ret=Enum.valueOf( enumClass,this.readData(section,key,String.valueOf(defaultValue)));
		} catch (Exception e) {
			this.lastException=e;
			ret=defaultValue;
		}
    	return ret;
    }
    // </editor-fold>

}
