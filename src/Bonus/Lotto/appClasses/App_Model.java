package Bonus.Lotto.appClasses;

import java.util.LinkedList;

import Bonus.Lotto.ServiceLocator;
import Bonus.Lotto.abstractClasses.Model;
import Bonus.Lotto.commonClasses.Configuration;
import Bonus.Lotto.commonClasses.Translator;
import javafx.concurrent.Task;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_Model extends Model {
	int count=0;
    ServiceLocator serviceLocator;
    
    public App_Model() {

        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application model initialized");
        serviceLocator.setJackpotProperty();

    }
    
    final Task<Void> initializer = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
        	 Integer i = 0;
             for (; i < 1000000000; i++) {
                 if ((i % 1000000) == 0){
                	 this.updateProgress(i, 1000000000);
                 }       
             }

            return null;
        }
    };
    
    public void initialize_goodluck() {
        new Thread(initializer).start();
    }
    
}
