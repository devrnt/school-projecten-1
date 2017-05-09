/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import domein.DomeinController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Edward
 */
public class SpelbordController implements Initializable {

    @FXML
    public Label setCounter;
    public Label score1;
    public Label score2;
    public GridPane spelbord1;
    public GridPane spelbord2;
    public GridPane hand;
    public Button saveButton;
    public Button wisselButton;
    public Button endTurnButton;
    public Button passButton;

    private DomeinController dc;

    public SpelbordController(DomeinController dc) {
        this.dc = dc;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dc.bepaalSpelerAanDeBeurtEersteSet();

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //toDo venster maken waar ze naam vragen | L
                saveButton.getParent().setDisable(true);
                Label saveLabel = new Label(dc.getTaal().getVertaling("save_label")); //"Geef de naam om op te slaan"
                TextField saveInput = new TextField(); //input
                Button acceptButton = new Button(dc.getTaal().getVertaling("opslaan")); // button om het te bevestigen
                Stage popup = new Stage();
                acceptButton.disableProperty().bind(Bindings.isEmpty(saveInput.textProperty()));
                acceptButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (!dc.toonLijstWedstrijden().contains(saveInput.getText())) {
                            //dc.bewaarWedstrijd(saveInput.getText());
                            saveButton.getParent().setDisable(false);
                            popup.close();
                        }
                    }
                });
                HBox hbox = new HBox(saveLabel, saveInput, acceptButton);
                popup.setScene(new Scene(hbox));
                popup.showAndWait();
            }
        });

        wisselButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //hand herschrijven naar specifieke kaarten die gewisseld kunnen worden, dan terug schrijven naar de normale hand
            }
        });

        endTurnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //beëindig beurt, updaten setcounter en score van spelers, spelbord van spelers
            }
        });

        passButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //todo bevriezen van de speler, en beurt beeindig
            }
        });
    }

}
