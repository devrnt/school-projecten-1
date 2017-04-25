/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import domein.DomeinController;
import fxml.Card;
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
public class uitest extends Application{
    private Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Card card = new Card("+2", "blue");
        Pane root = card.getContent();
        Scene scene = new Scene(root);
        
        stage.setTitle("Taalpicker test");
        stage.setScene(scene);
        stage.show();
        
        stage.show();
    }
    
    public static void next(){
        System.out.println("it gets done");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
