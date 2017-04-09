package Bonus.Lotto;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Locale;
import java.util.logging.Logger;

import Bonus.Lotto.appClasses.Bets;
import Bonus.Lotto.commonClasses.Configuration;
import Bonus.Lotto.commonClasses.Translator;
import javafx.beans.property.SimpleDoubleProperty;


/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * The singleton instance of this class provide central storage for resources
 * used by the program. It also defines application-global constants, such as
 * the application name.
 * 
 * @author Brad Richards
 */
public class ServiceLocator {
    private static ServiceLocator serviceLocator; // singleton

    // Application-global constants
    final private Class<?> APP_CLASS = Lotto.class;
    final private String APP_NAME = "Lotto";
    
    // Supported locales (for translations)
    final private Locale[] locales = new Locale[] { new Locale("en"), new Locale("de") };

    // Resources
    private Logger logger;
    private Configuration configuration;
    private Translator translator;
    //saves our bets
    private LinkedList <Bets>betList;
    //saves the bets of the simulator
    private LinkedList <Bets>betList_Sim = new LinkedList <Bets>();
    private int betCounter =0;
    private double jackpot = 12000000;
    private SimpleDoubleProperty jackpot_property = new SimpleDoubleProperty();

    /**
     * Factory method for returning the singleton
     * @param mainClass The main class of this program
     * @return The singleton resource locator
     */
    public static ServiceLocator getServiceLocator() {
        if (serviceLocator == null)
            serviceLocator = new ServiceLocator();
        return serviceLocator;
    }

    /**
     * Private constructor, because this class is a singleton
     * @param appName Name of the main class of this program
     */
    private ServiceLocator() {
        // Currently nothing to do here. We must define this constructor anyway,
        // because the default constructor is public
    }

    public Class<?> getAPP_CLASS() {
        return APP_CLASS;
    }
    
    public String getAPP_NAME() {
        return APP_NAME;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Locale[] getLocales() {
        return locales;
    }

    public Translator getTranslator() {
        return translator;
    }
    
    public void setTranslator(Translator translator) {
        this.translator = translator;
    }
    
    public void setBetList(LinkedList <Bets>betList){
    	this.betList = betList;
    }
    
    //sets a bet of the current user
    public void setBet (Bets bet){
    	this.betList.add(bet);
    	betCounter++;
    }
    
    //sets a bet of the simulator
    public void setBetForSim (Bets bet){
    	this.betList_Sim.add(bet);
    }
    
    public int getBetCounter(){
    	return this.betCounter;
    }
    
    public LinkedList <Bets>  getBetList(){
    	return this.betList;
    }
    
    public LinkedList <Bets>  getBetListForSim(){
    	return this.betList_Sim;
    }
    
    public void increaseJackpot(){
    	jackpot= jackpot+2.5;
    	jackpot_property.set(jackpot);
    	
    }
    
    public String getJackpot(){
    	NumberFormat df = NumberFormat.getCurrencyInstance();
    	DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    	dfs.setCurrencySymbol("CHF ");
    	dfs.setGroupingSeparator(',');
    	dfs.setMonetaryDecimalSeparator('.');
    	((DecimalFormat) df).setDecimalFormatSymbols(dfs);
    	return df.format(jackpot_property.get());
    }
    
    public String getTotalCHF(){
    	NumberFormat df = NumberFormat.getCurrencyInstance();
    	DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    	dfs.setCurrencySymbol("CHF ");
    	dfs.setGroupingSeparator(',');
    	dfs.setMonetaryDecimalSeparator('.');
    	((DecimalFormat) df).setDecimalFormatSymbols(dfs);
    	double total = betCounter*2.5;
    	return df.format(total);
    }
    
    public void setJackpotProperty(){
    	this.jackpot_property.set(jackpot);
    }
    
	public SimpleDoubleProperty getValueProperty() {
		return jackpot_property;
	}
}
