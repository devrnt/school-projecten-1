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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
public class WedstrijdKiezerController implements Initializable {

    @FXML
    public VBox content;
    public Button maakButton;
    public Button exitButton;

    private DomeinController dc;
    private AnchorPane main;

    public WedstrijdKiezerController(DomeinController dc, AnchorPane main) {
        this.dc = dc;
        this.main = main;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> lijst = dc.toonLijstWedstrijden();
        StringProperty pick = new SimpleStringProperty();
        maakButton.disableProperty().bind(pick.isEqualTo(""));
        maakButton.textProperty().bind(Bindings.format(dc.getTaal().getVertaling("keuze_wedstrijd") + ": %s", pick));

        for (String text : lijst) {
            Label label = new Label(text);

            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    pick.setValue(label.getText());
                }
            });
            content.getChildren().add(label);
        }

        maakButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dc.laadWedstrijd(pick.getValue());
               
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/spelbord.fxml"));
                    loader.setResources(dc.getTaal().getBundle());
                    SpelbordController ctrl = new SpelbordController(dc);
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