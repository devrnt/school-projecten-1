/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import domein.DomeinController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Edward
 */
public class WedstrijdStapelKiezerController implements Initializable {

    @FXML
    public ListView spelerView;
    public GridPane gridPane;
    public Button selectButton;
    public Button buyButton;
    public Button nextButton;
    public Button returnButton;
    
    private DomeinController dc;

    public WedstrijdStapelKiezerController(DomeinController dc) {
        this.dc = dc;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> spelerLijst = FXCollections.observableArrayList(dc.geefSpelerZonderWedstrijdStapel());
        spelerView.setItems(spelerLijst);
        spelerView.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                System.out.println(spelerView.getSelectionModel().getSelectedItem());
            }
        });
        
        if(!spelerLijst.isEmpty()){
            selectButton.setDisable(true);
            buyButton.setDisable(true);
        }
        
        selectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            }
        });
        
        buyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(spelerLijst.isEmpty()){
                    
                }
            }
        });
    }
}
