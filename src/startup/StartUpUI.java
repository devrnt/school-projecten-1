/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import domein.DomeinController;
import fxml.TaalPickerController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Edward
 */
public class StartUpUI extends Application{
    private Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        DomeinController dc = new DomeinController();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/taalpicker.fxml"));
        TaalPickerController ctrl = new TaalPickerController(dc);
        loader.setController(ctrl);
        Pane root = loader.load();
        Scene scene = new Scene(root);
        
        stage.setTitle("PAZAAK");
        stage.setScene(scene);
        stage.show();
        
        stage.show();
        System.out.println("done");
        this.stage = stage;
        ctrl.setStage(stage);
    }
    
    public static void next(){
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}