/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.finder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author Keons
 * @see https://stackoverflow.com/questions/54035976/javafx-with-fxml-adding-action-events-for-buttons
 * @version 08K10A
 */
public class Controller {
    @FXML
    private void sayHi(ActionEvent event){
        event.consume();
        System.out.println("HI");
    }
}
