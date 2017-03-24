package lecture04.Calc_MVC;

import java.util.Locale;
import java.util.logging.Logger;

import lecture04.Calc_MVC.commonClasses.Configuration;
import lecture04.Calc_MVC.commonClasses.Translator;


//methode 3: singleton mit einem service locator. resourcen in der main methode initialisieren mit den settern und new
public class ServiceLocator {
    private static ServiceLocator serviceLocator; // singleton

    // Application-global constants
    final private Class<?> APP_CLASS = Calculator_MVC.class;
    final private String APP_NAME = "Calculator_MVC";
    
    // Supported locales (for translations)
    final private Locale[] locales = new Locale[] { new Locale("en"), new Locale("de") };

    // Resources
    private Logger logger;
    private Configuration configuration;
    private Translator translator;

    
    //muss nicht in jeder klasse neu initialisiert werden, sondern nur 1x in der main methode?
    public static void init() {
        if (serviceLocator == null)
            serviceLocator = new ServiceLocator();
    }

    /**
     * Private constructor, because this class is a singleton
     * @param appName Name of the main class of this program
     */
    private ServiceLocator() {
        // Currently nothing to do here. We must define this constructor anyway,
        // because the default constructor is public
    }

    public static Class<?> getAPP_CLASS() {
        return serviceLocator.APP_CLASS;
    }
    
    public static String getAPP_NAME() {
        return serviceLocator.APP_NAME;
    }

    public static Logger getLogger() {
        return serviceLocator.logger;
    }

    public static void setLogger(Logger logger) {
    	serviceLocator.logger = logger;
    }

    public static Configuration getConfiguration() {
    	return serviceLocator.configuration;
    }

    public static void setConfiguration(Configuration configuration) {
    	serviceLocator.configuration = configuration;
    }

    public static Locale[] getLocales() {
    	return serviceLocator.locales;
    }

    public static Translator getTranslator() {
    	return serviceLocator.translator;
    }
    
    public static void setTranslator(Translator translator) {
    	serviceLocator.translator = translator;
    }
}
