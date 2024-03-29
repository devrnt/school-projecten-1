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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Edward
 */
public class MainController implements Initializable {
    
    @FXML
    public AnchorPane menu;
    public AnchorPane main;
    public Button maakButton;
    public Button startButton;
    public Button laadButton;
    public Button sluitButton;
    
    private DomeinController dc;
    
    public MainController(DomeinController dc) {
        this.dc = dc;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        maakButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/maakSpeler.fxml"));
                    loader.setResources(dc.getTaal().getBundle());
                    MaakSpelerController ctrl = new MaakSpelerController(dc);
                    loader.setController(ctrl);
                    AnchorPane content = loader.load();
                    main.getChildren().clear();
                    main.getChildren().add(content);
                    
                    ((Button)content.getChildren().get(6)).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            main.setDisable(true);
                            menu.setDisable(false);
                        }
                    });
                    
                    menu.setDisable(true);
                    main.setDisable(false);
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/kiezer.fxml"));
                    loader.setResources(dc.getTaal().getBundle());
                    SpelerKiezerController ctrl = new SpelerKiezerController(dc, main);
                    loader.setController(ctrl);
                    AnchorPane content = loader.load();
                    main.getChildren().clear();
                    main.getChildren().add(content);
                    
                    ((Button)content.getChildren().get(2)).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            main.setDisable(true);
                            menu.setDisable(false);
                        }
                    });
                    
                    menu.setDisable(true);
                    main.setDisable(false);
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        laadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/kiezer.fxml"));
                    loader.setResources(dc.getTaal().getBundle());
                    WedstrijdKiezerController ctrl = new WedstrijdKiezerController(dc, main);
                    loader.setController(ctrl);
                    AnchorPane content = loader.load();
                    main.getChildren().clear();
                    main.getChildren().add(content);
                    
                    ((Button)content.getChildren().get(2)).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            main.setDisable(true);
                            menu.setDisable(false);
                        }
                    });
                    
                    menu.setDisable(true);
                    main.setDisable(false);
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        sluitButton.setOnAction((ActionEvent event) -> {
            Node node = (Node) event.getSource();
            ((Stage) node.getScene().getWindow()).close();
            System.exit(0);
        });
        
    }
    
}
