package com.spittr.sys.config;

import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Configurator {

	protected Logger logger = LoggerFactory.getLogger(Configurator.class);
	
    public static final String PUBLIC_CONFIG = "/staticconfig.properties";

	protected Properties prop;

	public static Configurator getInstance(){
		return Inner.instance;
	}

	private static class Inner{
		public static Configurator instance = new Configurator();
	}

	private Configurator(){
		this.load();
	}

	public void load()
    {
        try
        {
            InputStreamReader is;

          // PRIVATE
            is = new InputStreamReader(
                this.getClass()
                    .getResourceAsStream(PUBLIC_CONFIG),
                "utf-8"
            );

            this.prop = new Properties(this.prop);
            this.prop.load(is);
            is.close();
        }
        catch (Exception ex)
        {
            throw(new RuntimeException(ex.getMessage(), ex));
        }
    }
	
	public Properties getProperties(){
		Properties properties = new Properties(this.prop);
		return properties;
	}
	
	public String get(String key){
		return this.prop.getProperty(key);
	}
	
	public Integer getInt(String key){
		String value = this.get(key);
		try{
			return Integer.valueOf(value);
		}catch (Exception e) {
			// TODO: handle exception
			logger.warn(e.toString());
			return null;
		}
	}
	
	public Long getLong(String key){
		String value = this.get(key);
		try{
			return Long.valueOf(value);
		}catch (Exception e) {
			// TODO: handle exception
			logger.warn(e.toString());
			return null;
		}
	}
	
	public  Boolean getBoolean(String key) {
		String value = this.get(key);
		try{
			return Boolean.valueOf(value);
		}catch (Exception e) {
			// TODO: handle exception
			logger.warn(e.toString());
			return null;
		}
	}
}
