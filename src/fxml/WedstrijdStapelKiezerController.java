/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import domein.DomeinController;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author Edward
 */
public class WedstrijdStapelKiezerController implements Initializable {

    @FXML
    public ListView spelerView;
    public ScrollPane scrollPane;
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
        ObservableList<String> geselecteerd = FXCollections.observableArrayList();

        selectButton.disableProperty().bind(Bindings.size(geselecteerd).isNotEqualTo(6));
        nextButton.disableProperty().bind(Bindings.size(spelerLijst).isNotEqualTo(0));

        spelerView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                buyButton.setDisable(false);
                GridPane grid = new GridPane();
                for (int i = 0; i < 4; i++) {
                    grid.getColumnConstraints().add(new ColumnConstraints(84));
                }
                List<String> kaarten = dc.toonStartStapel(spelerView.getSelectionModel().getSelectedItem().toString());
                for (int i = 0; i < Math.ceil(kaarten.size() / 4); i++) {
                    grid.getRowConstraints().add(new RowConstraints(104));
                }
                for (int i = 0; i < kaarten.size(); i++) {
                    if (kaarten.get(i).charAt(0) == '-') {
                        Card card = new Card(kaarten.get(i), "red");
                        card.getContent().setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                card.selectPane();
                                if (geselecteerd.contains(card.getOmschrijving())) {
                                    geselecteerd.remove(card.getOmschrijving());
                                } else {
                                    geselecteerd.add(card.getOmschrijving());
                                }
                                System.out.println(geselecteerd.toString());
                            }
                        });
                        grid.add(card.getContent(), i % 4, (int) Math.floor(i / 4));
                    } else {
                        Card card = new Card(kaarten.get(i), "blue");
                        card.getContent().setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                card.selectPane();
                                if (geselecteerd.contains(card.getOmschrijving())) {
                                    geselecteerd.remove(card.getOmschrijving());
                                } else {
                                    geselecteerd.add(card.getOmschrijving());
                                }
                                System.out.println(geselecteerd.toString());
                            }
                        });
                        grid.add(card.getContent(), i % 4, (int) Math.floor(i / 4));
                    }

                }

                scrollPane.setContent(grid);

            }
        });

        if (!spelerLijst.isEmpty()) {
            buyButton.setDisable(true);
        }

        selectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dc.maakWedstrijdStapel(spelerView.getSelectionModel().getSelectedItem().toString(), geselecteerd);
                geselecteerd.clear();
                spelerLijst.remove(spelerView.getSelectionModel().getSelectedItem().toString());
            }
        });

        buyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GridPane grid = new GridPane();
                for (int i = 0; i < 4; i++) {
                    grid.getColumnConstraints().add(new ColumnConstraints(84));
                }
                List<String> startStapel = dc.toonStartStapel(spelerView.getSelectionModel().getSelectedItem().toString());
                List<String> kaarten = dc.toonBetalendeKaarten();

                for (int i = 0; i < kaarten.size(); i++) {
                    String omschrijving = kaarten.get(i).split(" ")[0];
                    for (String startStapelKaart : startStapel) {
                        if (startStapelKaart.equals(omschrijving)) {
                            kaarten.remove(i);
                        }
                    }
                }
                for (int i = 0; i < Math.ceil(kaarten.size() / 4); i++) {
                    grid.getRowConstraints().add(new RowConstraints(124));
                }

                int krediet = (int)dc.geefKredietSpeler(spelerView.getSelectionModel().getSelectedItem().toString());
                Pane pane = new StackPane();
                Label label = new Label(String.valueOf(dc.geefKredietSpeler(spelerView.getSelectionModel().getSelectedItem().toString())));
                label.textProperty().bind(Bindings.format("Krediet: %d", krediet));
                pane.getChildren().add(label);
                grid.add(pane, 0, 0);

                //nog prijs splitten van description
                for (int i = 1; i < kaarten.size(); i++) {
                    if (kaarten.get(i).charAt(0) == '-') {
                        Card card = new Card(kaarten.get(i).split(" ")[0], "red");
                        card.getContent().setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                card.selectPane();
                                if (geselecteerd.contains(card.getOmschrijving())) {
                                    geselecteerd.remove(card.getOmschrijving());
                                } else {
                                    geselecteerd.add(card.getOmschrijving());
                                }
                                System.out.println(geselecteerd.toString());
                            }
                        });        
                        Label prijs = new Label(kaarten.get(i).split(" ")[1]);
                        VBox vbox = new VBox(card.getContent(), prijs);
                        grid.add(vbox, i % 4, (int) Math.floor(i / 4));
                    } else {
                        Card card = new Card(kaarten.get(i).split(" ")[0], "blue");
                        card.getContent().setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                card.selectPane();
                                if (geselecteerd.contains(card.getOmschrijving())) {
                                    geselecteerd.remove(card.getOmschrijving());
                                } else {
                                    geselecteerd.add(card.getOmschrijving());
                                }
                                System.out.println(geselecteerd.toString());
                            }
                        });       
                        Label prijs = new Label(kaarten.get(i).split(" ")[1]);
                        VBox vbox = new VBox(card.getContent(), prijs);
                        grid.add(vbox, i % 4, (int) Math.floor(i / 4));
                    }

                }
                
                scrollPane.setContent(grid);
            }
        });

        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //volgende scherm
            }
        });
    }
}
