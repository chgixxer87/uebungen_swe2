package lecture03.LogginTest;

import java.util.logging.Logger;

public class ServiceLocator {
	
	//singleton
	private static ServiceLocator servicelocator;
	
	//resources, here just a logger
	
	private Logger logger;

	
	private ServiceLocator() {
        // Currently nothing to do here. We must define this constructor anyway,
        // because the default constructor is public
    }
	
	//return the singleton
	
	 public static ServiceLocator getServiceLocator() {
	        if (servicelocator == null)
	            servicelocator = new ServiceLocator();
	        return servicelocator;
	    }
	 
	 //getter and setter for the resources
	 public Logger getLogger() {
	        return logger;
	    }

	    public void setLogger(Logger logger) {
	        this.logger = logger;
	    }
}
