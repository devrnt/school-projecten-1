/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import domein.DomeinController;
import domein.Kaart;
import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    public Button endTurnButton;
    public Button passButton;

    private DomeinController dc;

    public SpelbordController(DomeinController dc) {
        this.dc = dc;
    }

    private void toonWinnaarScherm() {
        try {
            AnchorPane win = FXMLLoader.load(SpelbordController.class.getResource("/fxml/winnaar.fxml"));
            ((Label) win.getChildren().get(1)).setText(dc.getTaal().getVertaling("ui_winner"));
            ((Label) win.getChildren().get(1)).setText(dc.getTaal().getVertaling("ui_credit"));
            Stage lastStage = new Stage();
            lastStage.setScene(new Scene(win));
            lastStage.showAndWait();
            //nu of het programma sluiten of teruggaan naar menu (via een van de components het hoofdpaneel opvragen en dan menu enablen en main disablen)
            //spelers updaten
            for (String naam : dc.geefGeregistreerdeSpelers()) {
                dc.updateSpeler(naam);
            }
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(SpelbordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateScherm() {
        //updaten setNummer
        setCounter.setText(MessageFormat.format(dc.getTaal().getVertaling("setcounter"), dc.getAantalSets()));

        //updaten scores
        score1.setText(String.valueOf(dc.geefGeregistreerdeSpelers().get(0) + ": " + dc.geefSetScore(dc.geefGeregistreerdeSpelers().get(0))));
        score2.setText(String.valueOf(dc.geefGeregistreerdeSpelers().get(1) + ": " + dc.geefSetScore(dc.geefGeregistreerdeSpelers().get(1))));

        //score actieve speler border geven
        if(dc.geefActieveSpeler().equals(dc.geefGeregistreerdeSpelers().get(0))){
            score1.setStyle("-fx-border-color: black;");
            score2.setStyle("");
        }else if(dc.geefActieveSpeler().equals(dc.geefGeregistreerdeSpelers().get(1))){
            score2.setStyle("-fx-border-color: black;");
            score1.setStyle("");
        }else{  //for debug
            System.out.println("Mag niet");
        }
        
        //updaten spelborden
        List<String> bord1 = dc.geefSpelbord(dc.geefGeregistreerdeSpelers().get(0));
        for (int i = 0; i < bord1.size(); i++) {
            Card card;
            if (bord1.get(i).charAt(0) == '-') {
                card = new Card(bord1.get(i), "red");
            } else {
                card = new Card(bord1.get(i), "blue");
            }
            spelbord1.add(card.getContent(), i % 3, (int) Math.floor(i / 3));
        }
        List<String> bord2 = dc.geefSpelbord(dc.geefGeregistreerdeSpelers().get(1));
        for (int i = 0; i < bord2.size(); i++) {
            Card card;
            if (bord2.get(i).charAt(0) == '-') {
                card = new Card(bord2.get(i), "red");
            } else {
                card = new Card(bord2.get(i), "blue");
            }
            spelbord2.add(card.getContent(), i % 3, (int) Math.floor(i / 3));
        }
        //updaten hand
        List<Kaart> handKaarten = dc.geefSpeler(dc.geefActieveSpeler()).getWedstrijdStapel();
        for (int i = 0; i < handKaarten.size(); i++) {
            Card card;
            if (handKaarten.get(i).getOmschrijving().charAt(0) == '-') {
                card = new Card(handKaarten.get(i).getOmschrijving(), "red");
            } else {
                card = new Card(handKaarten.get(i).getOmschrijving(), "blue");
            }
            //hand.add(card.getContent().getChildren().get(0), 0, i);
            card.getContent().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (dc.geefKaartType(card.getOmschrijving()).equals("+/-")) {
                        //wissel dialoog
                        saveButton.getParent().setDisable(true);
                        Stage wisselStage = new Stage();
                        Button posBut = new Button("+");
                        Button negBut = new Button("-");
                        Label omschrijving = new Label(dc.getTaal().getVertaling("+/-"));
                        posBut.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                dc.legWedstrijdkaart(card.getOmschrijving(), 1);
                                wisselStage.close();
                            }
                        });
                        negBut.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                dc.legWedstrijdkaart(card.getOmschrijving(), 2);
                                wisselStage.close();
                            }
                        });
                        HBox hbox = new HBox(posBut, omschrijving, negBut);
                        hbox.setPadding(new Insets(10));
                        wisselStage.setScene(new Scene(hbox));
                        wisselStage.showAndWait();
                        saveButton.getParent().setDisable(false);
                    } else {
                        dc.legWedstrijdkaart(card.getOmschrijving(), 3);
                    }
                }
            });
            hand.add(card.getContent(), 0, i);

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dc.bepaalSpelerAanDeBeurtEersteSet();
        updateScherm();

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
                            dc.bewaarWedstrijd(saveInput.getText());
                            saveButton.getParent().setDisable(false);
                            popup.close();
                        }
                    }
                });
                HBox hbox = new HBox(saveLabel, saveInput, acceptButton);
                hbox.setPadding(new Insets(10));
                popup.setScene(new Scene(hbox));
                popup.showAndWait();
                saveButton.getParent().setDisable(false);
            }
        });

        endTurnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //beëindig beurt, updaten setcounter en score van spelers, spelbord van spelers
                dc.beeindigBeurt();
                updateScherm();
            }
        });

        passButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //todo bevriezen van de speler, en beurt beeindig
                dc.bevriesSpelbord();
                dc.beeindigBeurt();
                updateScherm();
            }
        });
    }

}
