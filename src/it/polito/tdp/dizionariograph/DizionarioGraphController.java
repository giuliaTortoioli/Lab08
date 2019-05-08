package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DizionarioGraphController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TextArea txtResult;


    @FXML
    private TextField txtNumeroLettere;

    @FXML
    private TextField txtParolaDaCercare;

    @FXML
    private Button btnGeneraGrafo;

    @FXML
    private Button btnVicini;

    @FXML
    private Button btnGradoMax;

    @FXML
    private Button btnReset;

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	int lettere = 0;
    	try {
    	lettere = Integer.parseInt(txtNumeroLettere.getText());
    	
    	model.createGraph(lettere);
    	
    	txtResult.appendText(String.format("**Grafo creato**\n"));

    	}catch(Exception e) {
    		txtResult.appendText("ERRORE! Inserisci un numero\n");
    	}
    	
    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	txtResult.clear();
    	txtNumeroLettere.clear();
    	txtParolaDaCercare.clear();

    }

    @FXML
    void doTrovaGradoMax(ActionEvent event) {
    	
    	txtResult.appendText("Grado massimo: "+model.findMaxDegree()+"\n");
    	txtResult.appendText("Vicini: "+model.getVicini().toString()+"\n");

    }

    @FXML
    void doTrovaVicini(ActionEvent event) {
    	
    	String parola; 
    	
    	try {
    	
    		parola = txtParolaDaCercare.getText();
    	txtResult.appendText(model.displayNeighbours(parola).toString()+"\n");
    	
    	
    	}
    	catch(Exception e) {
    		txtResult.appendText(("ERRORE! Inserisci la parola da cercare ("+txtNumeroLettere.getText()+" lettere)\n"));
    	}
    	

    }

    @FXML
    void initialize() {
        assert txtNumeroLettere != null : "fx:id=\"txtNumeroLettere\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtParolaDaCercare != null : "fx:id=\"txtParolaDaCercare\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnVicini != null : "fx:id=\"btnVicini\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGradoMax != null : "fx:id=\"btnGradoMax\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
    
    
    
    
}
