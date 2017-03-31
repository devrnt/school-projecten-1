/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;

/**
 *
 * @author Edward
 */
public class TaalPickerController implements Initializable{
    @FXML
    public Button button_nl;
    public Button button_fr;
    public Button button_en;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_nl.setStyle("-fx-background-image: url('/resources/nl-flag.png')");
        
        button_nl.setOnAction((ActionEvent event) -> {
            System.out.println("Nederlands");
        });
        
        button_fr.setOnAction((ActionEvent event) -> {
            System.out.println("Français");
        });
        
        button_en.setOnAction((ActionEvent event) -> {
            System.out.println("English");
        });
    }
    
}
