/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import domein.DomeinController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
    private AnchorPane main;

    public WedstrijdStapelKiezerController(DomeinController dc, AnchorPane main) {
        this.dc = dc;
        this.main = main;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> spelerLijst = FXCollections.observableArrayList(dc.geefSpelerZonderWedstrijdStapel());
        spelerView.setItems(spelerLijst);
        ObservableList<String> geselecteerd = FXCollections.observableArrayList();

        selectButton.disableProperty().bind(Bindings.size(geselecteerd).isNotEqualTo(6));
        nextButton.disableProperty().bind(Bindings.size(spelerLijst).isNotEqualTo(0));
        buyButton.disableProperty().bind(Bindings.isNull(spelerView.getSelectionModel().selectedItemProperty()));
        returnButton.disableProperty().bind(buyButton.disableProperty());

        spelerView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (spelerView.getSelectionModel().getSelectedItem() != null) {
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
                                }
                            });
                            grid.add(card.getContent(), i % 4, (int) Math.floor(i / 4));
                        }

                    }

                    scrollPane.setContent(grid);
                }
            }
        });

        selectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dc.maakWedstrijdStapel(spelerView.getSelectionModel().getSelectedItem().toString(), geselecteerd);
                geselecteerd.clear();
                spelerLijst.remove(spelerView.getSelectionModel().getSelectedItem().toString());
                scrollPane.setContent(new Pane());
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

                Pane pane = new StackPane();

                Label label = new Label(String.valueOf(dc.geefKredietSpeler(spelerView.getSelectionModel().getSelectedItem().toString())));

                pane.getChildren().add(label);
                grid.add(pane, 0, 0);

                for (int i = 1; i < kaarten.size(); i++) {
                    if (kaarten.get(i).charAt(0) == '-') {
                        Card card = new Card(kaarten.get(i).split(" ")[0], "red");
                        Double kost = Double.parseDouble(kaarten.get(i).split(" ")[1]);
                        card.getContent().setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                dc.voegBetaaldeKaartToeAanStartStapel(spelerView.getSelectionModel().getSelectedItem().toString(), card.getOmschrijving());
                                dc.verminderKrediet(kost, spelerView.getSelectionModel().getSelectedItem().toString());
                                grid.getChildren().remove(((StackPane) event.getSource()).getParent());
                                label.setText(String.valueOf(dc.geefKredietSpeler(spelerView.getSelectionModel().getSelectedItem().toString())));
                            }
                        });
                        Label prijs = new Label(kost.toString() + "krediet");       //nog vertaald worden
                        VBox vbox = new VBox(card.getContent(), prijs);
                        grid.add(vbox, i % 4, (int) Math.floor(i / 4));
                    } else {
                        Card card = new Card(kaarten.get(i).split(" ")[0], "blue");
                        Double kost = Double.parseDouble(kaarten.get(i).split(" ")[1]);
                        card.getContent().setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                if (dc.geefSpeler(spelerView.getSelectionModel().getSelectedItem().toString()).getKrediet() >= kost) {
                                    dc.voegBetaaldeKaartToeAanStartStapel(spelerView.getSelectionModel().getSelectedItem().toString(), card.getOmschrijving());
                                    dc.verminderKrediet(kost, spelerView.getSelectionModel().getSelectedItem().toString());
                                    grid.getChildren().remove(((StackPane) event.getSource()).getParent());
                                    label.setText(String.valueOf(dc.geefKredietSpeler(spelerView.getSelectionModel().getSelectedItem().toString())));
                                }
                            }
                        });
                        Label prijs = new Label(kost.toString());
                        VBox vbox = new VBox(card.getContent(), prijs);
                        grid.add(vbox, i % 4, (int) Math.floor(i / 4));
                    }

                }

                scrollPane.setContent(grid);
            }
        });

        returnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MouseEvent mEvent = new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.NONE, 0, false, false, false, false, false, false, false, true, false, true, null);
                Event.fireEvent(spelerView, mEvent);
            }
        });

        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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
