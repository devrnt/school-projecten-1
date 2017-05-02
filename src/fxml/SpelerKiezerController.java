/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import domein.DomeinController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Edward
 */
public class SpelerKiezerController implements Initializable {

    @FXML
    public VBox content;
    public Button maakButton;
    public Button exitButton;

    private DomeinController dc;
    private AnchorPane main;

    public SpelerKiezerController(DomeinController dc, AnchorPane main) {
        this.dc = dc;
        this.main = main;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> lijst = dc.geefLijstBeschikbareSpelers();
        ObservableList<String> picked = FXCollections.observableArrayList();
        maakButton.disableProperty().bind(Bindings.size(picked).isNotEqualTo(2));

        for (String text : lijst) {
            Label label = new Label(text);

            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (label.getStyle().equals("")) {
                        label.setStyle("-fx-background-color: #f25a5a;");
                        picked.add(label.getText());
                    } else {
                        label.setStyle("");
                        picked.remove(label.getText());
                    }
                }
            });
            content.getChildren().add(label);
        }

        maakButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                dc.maakWedstrijd();
                for (String naam : picked) {
                    dc.registreerSpeler(naam);
                }
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/wedstrijdstapelKiezer.fxml"));
                    loader.setResources(dc.getTaal().getBundle());
                    WedstrijdStapelKiezerController ctrl = new WedstrijdStapelKiezerController(dc, main);
                    loader.setController(ctrl);
                    AnchorPane content = loader.load();
                    main.getChildren().clear();
                    main.getChildren().add(content);
                } catch (IOException ex) {
                    Logger.getLogger(SpelerKiezerController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
    }

}
