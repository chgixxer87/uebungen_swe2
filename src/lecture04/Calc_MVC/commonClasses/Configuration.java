package lecture04.Calc_MVC.commonClasses;

import lecture04.Calc_MVC.ServiceLocator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Logger;


public class Configuration {
                            // for easy reference

    private Properties defaultOptions;
    private Properties localOptions;
    
    //ServiceLocator initialisieren im construcot, nicht oben als instanzvariable
    public Configuration() {
    	//ServiceLocator.init();
    	Logger logger = ServiceLocator.getLogger();
    	
        // Load default properties from wherever the code is
        defaultOptions = new Properties();
        //mal eine falsche datei angeben, logger testen
        String defaultFilename = ServiceLocator.getAPP_NAME() + "_default.cfg";
        
        //suchen der config datei mittels class.getResource (suche am gleichen ort wie die klasse selbst)
        //--> alternativ mit FileInputStream (Datei im Dateisystem suchen, im workspace der entsprechenden app)
        InputStream inStream = ServiceLocator.getAPP_CLASS().getResourceAsStream(defaultFilename);
        try {
            defaultOptions.load(inStream);
            logger.config("Default configuration file found");
        } catch (Exception e) {
            logger.warning("No default configuration file found: " + defaultFilename);
        } finally {
            try {
                inStream.close();
            } catch (Exception ignore) {
            }
        }

        // Define locally-saved properties; link to the default values
        localOptions = new Properties(defaultOptions);

        // Load the local configuration file, if it exists (hier mit Dateisystem; file existiert nach 1. save)
        try {
            inStream = new FileInputStream(ServiceLocator.getAPP_NAME() + ".cfg");
            localOptions.load(inStream);
        } catch (FileNotFoundException e) { // from opening the properties file
            logger.config("No local configuration file found");
        } catch (IOException e) { // from loading the properties
            logger.warning("Error reading local options file: " + e.toString());
        } finally {
            try {
                inStream.close();
            } catch (Exception ignore) {
            }
        }
        
        //nur info, ausgabe von logger
        for (Enumeration<Object> i = localOptions.keys(); i.hasMoreElements();) {
            String key = (String) i.nextElement();
            logger.config("Option: " + key + " = " + localOptions.getProperty(key));
        }
    }
    
    public void save() {
        FileOutputStream propFile = null;
        try {
            propFile = new FileOutputStream(ServiceLocator.getAPP_NAME() + ".cfg");
            //effektive speicherung der geänderten config
            localOptions.store(propFile, null);
            ServiceLocator.getLogger().config("Local configuration file saved");
        } catch (Exception e) {
        	ServiceLocator.getLogger().warning("Unable to save local options: " + e.toString());
        } finally {
            if (propFile != null) {
                try {
                    propFile.close();
                } catch (Exception e) {
                }
            }
        }
    }
    
    public String getOption(String name) {
        return localOptions.getProperty(name);
    }
    
    public void setLocalOption(String name, String value) {
        localOptions.setProperty(name, value);
    }
}
