package it.polito.tdp.spellchecker;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class FXMLController {

	private Dictionary model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btmClear;

    @FXML
    private Button btmSpell;

    @FXML
    private Label lblNumberErrors;
    
    @FXML
    private ComboBox<String> cmbLanguage;

    @FXML
    private TextArea txt;

    @FXML
    private TextArea txtErrors;

    @FXML
    private VBox txtText;

    @FXML
    void handleClear(ActionEvent event) {
    	this.txtText.setAccessibleText("");
    	this.txtErrors.clear();
    	this.lblNumberErrors.setText("");
    }

    @FXML
    void handleSpell(ActionEvent event) {
    	LinkedList<String> inputList = new  LinkedList<String>();
    	
    	String testo = txtText.getAccessibleText().toLowerCase();
    	testo.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    	
    	String array[] = testo.split(" ");
    	for(int i=0; i<array.length; i++) {
    		inputList.add(array[i]);
    	}
    	
    	model.loadDictionary(this.cmbLanguage.getValue());
    	LinkedList<RichWord> wrongWords = new  LinkedList<RichWord>(model.spellCheckText(inputList, this.cmbLanguage.getValue()));
    	
    	wrongWords= (LinkedList<RichWord>) model.wrongW(wrongWords);
    	
    	txtErrors.setText(model.toString(wrongWords));
    	this.lblNumberErrors.setText("The text contains "+wrongWords.size()+ " errors");
    	
    }
    
    public void setModel(Dictionary model) {
    	this.model = model;
    }

    @FXML
    void initialize() {
        assert btmClear != null : "fx:id=\"btmClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btmSpell != null : "fx:id=\"btmSpell\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbLanguage != null : "fx:id=\"cmbLanguage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txt != null : "fx:id=\"txt\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrors != null : "fx:id=\"txtErrors\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtText != null : "fx:id=\"txtText\" was not injected: check your FXML file 'Scene.fxml'.";

        
        cmbLanguage.getItems().clear();
        this.cmbLanguage.getItems().add("English");
        this.cmbLanguage.getItems().add("Italian");
        
        
    }

}
