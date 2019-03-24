	package it.polito.tdp.spellchecker.controller;

	import java.net.URL;
    import java.util.LinkedList;
    import java.util.List;
    import java.util.ResourceBundle;
    import javafx.scene.control.ComboBox;
    import javafx.scene.control.Label;
    import it.polito.tdp.spellchechker.model.Dictionary;
    import it.polito.tdp.spellchechker.model.RichWord;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.scene.control.Button;	
    import javafx.scene.control.TextArea;

	
	public class SpellCheckerController {		
		
		private Dictionary model;
		
		@FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private ComboBox<String> comboId;

	    @FXML
	    private TextArea textArea1;

	    @FXML
	    private Button buttonSpellCheck;

	    @FXML
	    private TextArea textArea2;  
	    
	    
	    @FXML
	    private Label textErrori;	    

	    @FXML
	    private Label textSeconds;	    
	    @FXML
	    private Button buttonClearText;	  
	    

	    @FXML
	    void chooseLanguage(ActionEvent event) {
	    	
	    	if(comboId.getValue()!=null) {
	    	textArea1.setDisable(false);
			textArea2.setDisable(false);
	    	}
	    	else {
	    		textArea1.setDisable(true);
				textArea2.setDisable(true);
	    	}	    	
	    	
	    }    
	    
	    
	    @FXML
	    void doSpellCheck(ActionEvent event) {
	    textArea2.clear();
	    if(comboId.getValue()==null) {
	    	textArea2.appendText("Errore: seleziona lingua");
	    	return;
	    }
	    long timeInizio=System.nanoTime();
	    model.loadDictionary(comboId.getValue());
	    //System.out.println(comboId.getValue());
	    String string=textArea1.getText();
	    
	    List<String> tot=new LinkedList<String>();
	    
	    string = string.replaceAll("\n", " ");
		string = string.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]]", "");
	    
	    String s[]=string.split(" ");
	    for(String st:s) {	    	
	    	tot.add(st);
	    }
	   
	   List<RichWord> list=new LinkedList<RichWord>(model.spellCheckText(tot)); 
	   int conta=0;
	   for(RichWord r:list)	{
		   if(r.isValue()==false) {
			   conta++;
		   textArea2.appendText(r.getInput()+" ");
		   }
	   }
	   long timeFine=System.nanoTime();
	   long diff=timeFine-timeInizio;
	   textErrori.setText("The text contains "+conta +"errors");
	   textSeconds.setText("Spell check completed in "+diff*0.000000001+" seconds");
	    
	    
	    }


	    @FXML
	    void doClearText(ActionEvent event) {
	    	textArea1.clear();
	    	textArea2.clear();
	    	model.resetDictionary();
	    	textErrori.setText("");
	    	textSeconds.setText("");
	    

	    }

	   
	    @FXML
	    void initialize() {
	        assert comboId != null : "fx:id=\"comboId\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert textArea1 != null : "fx:id=\"textArea1\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert buttonSpellCheck != null : "fx:id=\"buttonSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert textArea2 != null : "fx:id=\"textArea2\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert textErrori != null : "fx:id=\"textErrori\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert buttonClearText != null : "fx:id=\"buttonClearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert textSeconds != null : "fx:id=\"textSeconds\" was not injected: check your FXML file 'SpellChecker.fxml'.";

	    }
	    public Dictionary getModel() {
			return model;
		}


		public void setModel(Dictionary model) {			
			comboId.getItems().addAll("English","Italian");
			this.model = model;
			textArea1.setDisable(true);
			textArea2.setDisable(true);
		}


	}


