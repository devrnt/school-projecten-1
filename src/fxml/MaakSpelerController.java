/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import domein.DomeinController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 *
 * @author Edward
 */
public class MaakSpelerController implements Initializable {

    @FXML
    public Button maakButton;
    public Button exitButton;
    public TextField naamInput;
    public TextField jaarInput;
    public Text error;
    public ImageView correct;
    public ImageView wrong;

    private DomeinController dc;

    public MaakSpelerController(DomeinController dc) {
        this.dc = dc;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        error.setText("");
        maakButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!(naamInput.getText().equals("") || jaarInput.getText().equals(""))){
                    try{
                        dc.maakSpeler(naamInput.getText(), Integer.parseInt(jaarInput.getText()));
                        correct.setOpacity(1);
                        wrong.setOpacity(0);
                        naamInput.setText("");
                        jaarInput.setText("");
                    }catch(IllegalArgumentException e){
                        error.setText(dc.getTaal().getVertaling(e.getMessage()));
                        correct.setOpacity(0);
                        wrong.setOpacity(1);
                    }
                }
            }
        });
    }

}
