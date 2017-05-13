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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
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
    public ImageView matchpoints1;
    public ImageView matchpoints2;
    public Label setWinner;
    public Button startSet;

    private DomeinController dc;

    public SpelbordController(DomeinController dc) {
        this.dc = dc;
    }

    private void endTurn() {
        dc.beeindigBeurt();
        if (dc.setEinde()) {
            dc.geefUitslag();   //identificatie van winnaar set geven?
            if (dc.geefSetScore(dc.geefGeregistreerdeSpelers().get(0)) >= 3 || dc.geefSetScore(dc.geefGeregistreerdeSpelers().get(1)) >= 3) {
                updateScherm();
                dc.verhoogKrediet();
                toonWinnaarScherm();
            } else {
                //winnaar tonen
                endTurnButton.setDisable(true);
                passButton.setDisable(true);
                startSet.setDisable(false);
                setWinner.setText(dc.getTaal().getVertaling("set_winnaar") + dc.geefNaamSetWinnaar());
                setWinner.setOpacity(1);
                startSet.setOpacity(1);
            }
        } else {
            dc.voegBovensteKaartVanSetStapelToeAanSpelbord();
            updateScherm();
        }

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

        //updaten match points
        matchpoints1.setImage(new Image(String.format("/resources/%d_points.png", dc.geefSetScore(dc.geefGeregistreerdeSpelers().get(0)))));
        matchpoints2.setImage(new Image(String.format("/resources/%d_points.png", dc.geefSetScore(dc.geefGeregistreerdeSpelers().get(1)))));
        //updaten setscores
        score1.setText(String.valueOf(dc.geefGeregistreerdeSpelers().get(0) + ": " + Integer.toString(dc.geefSpeler(dc.geefGeregistreerdeSpelers().get(0)).getSpelbordScore())));
        score2.setText(String.valueOf(dc.geefGeregistreerdeSpelers().get(1) + ": " + Integer.toString(dc.geefSpeler(dc.geefGeregistreerdeSpelers().get(1)).getSpelbordScore())));

        //score actieve speler border geven
        if (dc.geefActieveSpeler().equals(dc.geefGeregistreerdeSpelers().get(0))) {
            score1.setStyle("-fx-border-color: black;");
            score2.setStyle("");
        } else if (dc.geefActieveSpeler().equals(dc.geefGeregistreerdeSpelers().get(1))) {
            score2.setStyle("-fx-border-color: black;");
            score1.setStyle("");
        } else {  //for debug
            System.out.println("Mag niet");
        }

        //updaten spelborden
        spelbord1.getChildren().clear();
        spelbord2.getChildren().clear();
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
        hand.getChildren().clear();
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
                        updateScherm();
                    } else {
                        dc.legWedstrijdkaart(card.getOmschrijving(), 3);
                        updateScherm();
                    }
                }
            });
            hand.add(card.getContent(), 0, i);

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        matchpoints2.setScaleX(-1);
        dc.maakSetStapel();
        dc.bepaalSpelerAanDeBeurtEersteSet();
        dc.voegBovensteKaartVanSetStapelToeAanSpelbord();
        updateScherm();
        //setWinner.setOpacity(0);
        setWinner.setTextAlignment(TextAlignment.CENTER);
        startSet.setOpacity(0);
        startSet.setDisable(true);

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
                endTurn();
            }
        });

        passButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //todo bevriezen van de speler, en beurt beeindig
                dc.bevriesSpelbord();
                endTurn();
            }
        });
        
        startSet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //oude set clearen
                dc.geefSpeler(dc.geefGeregistreerdeSpelers().get(0)).setSpelbordBevroren(false);
                dc.geefSpeler(dc.geefGeregistreerdeSpelers().get(0)).setSpelbordScore(0);
                dc.geefSpeler(dc.geefGeregistreerdeSpelers().get(0)).getSpelbord().clear();
                dc.geefSpeler(dc.geefGeregistreerdeSpelers().get(1)).setSpelbordBevroren(false);
                dc.geefSpeler(dc.geefGeregistreerdeSpelers().get(1)).setSpelbordScore(0);
                dc.geefSpeler(dc.geefGeregistreerdeSpelers().get(1)).getSpelbord().clear();                         
                
                //nieuwe set starten
                dc.maakSetStapel();
                dc.bepaalSpelerAanDeBeurtEersteSet();
                dc.voegBovensteKaartVanSetStapelToeAanSpelbord();
                updateScherm();
                setWinner.setOpacity(0);
                endTurnButton.setDisable(false);
                passButton.setDisable(false);
                startSet.setOpacity(0);
                startSet.setDisable(true);
            }
        });
    }

}
