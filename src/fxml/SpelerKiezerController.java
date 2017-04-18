/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import domein.DomeinController;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 *
 * @author Edward
 */
public class SpelerKiezerController implements Initializable {

    @FXML
    public VBox content;
    public Button kiesButton;
    public Button exitButton;

    private DomeinController dc;

    public SpelerKiezerController(DomeinController dc) {
        this.dc = dc;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> lijst = dc.geefLijstBeschikbareSpelers();
        List<String> picked = new ArrayList<>();
        for(String text: lijst){
            Label label = new Label(text);
            label.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    if(label.getStyle().equals("") && picked.size() < 2){
                        label.setStyle("-fx-background-color: #f25a5a;");
                        picked.add(label.getText());
                    }else{
                        label.setStyle("");
                        picked.remove(label.getText());
                    }
                }                
            });
            content.getChildren().add(label);
        }
        
        kiesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dc.maakWedstrijd();
                if(picked.size() == 2){
                    for(String naam: picked){
                        dc.registreerSpeler(naam);
                    }
                }
            }
        });
    }

}
