/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import domein.DomeinController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import resources.Taal;
import startup.uitest;

/**
 *
 * @author Edward
 */
public class TaalPickerController implements Initializable{
    @FXML
    public Button button_nl;
    public Button button_fr;
    public Button button_en;
    
    public Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    private DomeinController dc;
    
    
    public TaalPickerController(DomeinController dc){
        this.dc = dc;
    }
    
    private void taalGekozen(){
        try {
            stage.hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            loader.setResources(dc.getTaal().getBundle());
            MainController ctrl = new MainController(dc);
            loader.setController(ctrl);
            Pane root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TaalPickerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_nl.setStyle(" -fx-background-image: url('/resources/nl-flag.png')");
        button_fr.setStyle(" -fx-background-image: url('/resources/fr-flag.png')");
        button_en.setStyle(" -fx-background-image: url('/resources/uk-flag.png')");
        
        
        button_nl.setOnAction((ActionEvent event) -> {
            System.out.println("Nederlands");
            dc.setTaal(new Taal("nl"));
            taalGekozen();
        });
        
        button_fr.setOnAction((ActionEvent event) -> {
            System.out.println("Français");
            dc.setTaal(new Taal("fr"));
            taalGekozen();
        });
        
        button_en.setOnAction((ActionEvent event) -> {
            System.out.println("English");
            dc.setTaal(new Taal("en"));
            taalGekozen();
        });
    }
    
}
