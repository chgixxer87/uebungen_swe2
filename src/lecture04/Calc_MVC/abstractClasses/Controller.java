package lecture04.Calc_MVC.abstractClasses;

public abstract class Controller<M extends Model, V extends View<M>> {//achtung mit den generics
    protected M model;
    protected V view;
    
    protected Controller(M model, V view) {
        this.model = model;
        this.view = view;
    }
}
