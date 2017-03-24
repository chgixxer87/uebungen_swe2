package lecture04.Calc_MVC.commonClasses;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import lecture04.Calc_MVC.ServiceLocator; //import wie von einer java klasse, hier ist es aber eine eigene klasse


public class Translator {
	//ServiceLocator.init(); --> nur 1x initialisieren in der main methode? --> wahrscheinlich ja, da nicht
	//für jede klasse ein ServiceLocator objekt erstellt wird
    private Logger logger = ServiceLocator.getLogger();
    
    protected Locale currentLocale;
    private ResourceBundle resourceBundle;

    public Translator(String localeString) {//übergabe des locale.getLanguage java-methode (String übergabe)
        // Can we find the language in our supported locales?
        // If not, use VM default locale
        Locale locale = Locale.getDefault();//default der JVM
        if (localeString != null) {
            Locale[] availableLocales = ServiceLocator.getLocales(); //methode liefert ein array zurück [de, en]
            for (int i = 0; i < availableLocales.length; i++) { // in der schleife wird gesucht, ob der String localeString
            	//einer locale unseres arrays entspricht
                String tmpLang = availableLocales[i].getLanguage(); //java methode get für locales [de , en]
                if (localeString.substring(0, tmpLang.length()).equals(tmpLang)) {
                    locale = availableLocales[i];
                    break; //wenn locale aus dem array gefunden bzw. dem localeString entspricht -> break
                }
            }
        }
        
        // Load the resource strings (von den en und de properties files)
        //wir sind immer noch im translator constr. es wird z.b. für de ein transl obj erstellt mit locale = de
        resourceBundle = ResourceBundle.getBundle(ServiceLocator.getAPP_CLASS().getName(), locale);
        //wir sind immer noch im translator constr. es wird z.b. für de ein transl obj erstellt
        Locale.setDefault(locale); // Change VM default (for dialogs, etc.)
        currentLocale = locale;
        
        logger.info("Loaded resources for " + locale.getLanguage());
    }
    
    /**
     * Return the current locale; this is useful for formatters, etc.
     */
    public Locale getCurrentLocale() {
        return currentLocale;
    }

    /**
     * Public method to get string resources, default to "--" *
     */
    public String getString(String key) {
        try {
            return resourceBundle.getString(key);
        } catch (MissingResourceException e) {
            logger.warning("Missing string: " + key);
            return "--";
        }
    }
}
