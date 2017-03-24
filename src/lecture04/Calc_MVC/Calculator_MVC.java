package lecture04.Calc_MVC;

import lecture04.Calc_MVC.appClasses.App_Controller;
import lecture04.Calc_MVC.appClasses.App_Model;
import lecture04.Calc_MVC.appClasses.App_View;
import lecture04.Calc_MVC.commonClasses.Configuration;
import lecture04.Calc_MVC.commonClasses.Translator;

import java.util.logging.Logger;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;


public class Calculator_MVC extends Application {
    private static Calculator_MVC mainProgram; // singleton
    private App_View view;


    public static void main(String[] args) {
        launch(args);
    }

    /** init() von der Java-Klasse Application
     * Note: This method is called on the main thread, not the JavaFX
     * Application Thread. This means that we cannot display anything to the
     * user at this point. Since we want to show a splash screen, this means
     * that we cannot do any real initialization here.
     * 
     * This implementation ensures that the application is a singleton; only one
     * per JVM-instance. On client installations this is not necessary (each
     * application runs in its own JVM). However, it can be important on server
     * installations.
     * -->
     * 
     * Why is it important that only one instance run in the JVM? Because our
     * initialized resources are a singleton - if two programs instances were
     * running, they would use (and overwrite) each other's resources!
     * --> ansonsten macht die methode NICHTS!
     */
    @Override
    public void init() {
        if (mainProgram == null) {
            mainProgram = this;
        } else {
            Platform.exit();
        }
    }

    /**
     * This method is called after init(), and is called on the JavaFX
     * Application Thread, so we can display a GUI. We have two GUIs: a splash
     * screen and the application. Both of these follow the MVC model.
     * 
     * We first display the splash screen. The model is where all initialization
     * for the application takes place. The controller updates a progress-bar in
     * the view, and (after initialization is finished) calls the startApp()
     * method in this class.
     */
    @Override
   /** public void start(Stage primaryStage) {
        // Create and display the splash screen and model
        Splash_Model splashModel = new Splash_Model();
        splashView = new Splash_View(primaryStage, splashModel);
        new Splash_Controller(this, splashModel, splashView);
        splashView.start();

        // Display the splash screen and begin the initialization
        splashModel.initialize();
    }*/

    /**
     * This method is called when the splash screen has finished initializing
     * the application. The initialized resources are in a ServiceLocator
     * singleton. Our task is to now create the application MVC components, to
     * hide the splash screen, and to display the application GUI.
     * 
     * Multitasking note: This method is called from an event-handler in the
     * Splash_Controller, which means that it is on the JavaFX Application
     * Thread, which means that it is allowed to work with GUI components.
     * http://docs.oracle.com/javafx/2/threads/jfxpub-threads.htm
     */
    public void start(Stage appStage) {
        appStage = new Stage();
        
        //resourcen initialisieren
        ServiceLocator.init();
        
        Logger log = Logger.getLogger("");
        ServiceLocator.setLogger(log);
        
        ServiceLocator.setConfiguration(new Configuration());//-> import nötig
        
        String language = ServiceLocator.getConfiguration().getOption("Language"); //--> default en
        
        ServiceLocator.setTranslator(new Translator(language));
        
        App_Model model = new App_Model();
        view = new App_View(appStage, model);
        new App_Controller(model, view);

        view.start();
    }

    /**
     * The stop method is the opposite of the start method. It provides an
     * opportunity to close down the program, including GUI components. If the
     * start method has never been called, the stop method may or may not be
     * called.
     * 
     * Make the GUI invisible first. This prevents the user from taking any
     * actions while the program is ending.
     */
    @Override
    public void stop() {
        ServiceLocator.getConfiguration().save();
        if (view != null) {
            // Make the view invisible
            view.stop();
        }

        // More cleanup code as needed

        ServiceLocator.getLogger().info("Application terminated");
    }

    // Static getter for a reference to the main program object
    protected static Calculator_MVC getMainProgram() {
        return mainProgram;
    }
}
