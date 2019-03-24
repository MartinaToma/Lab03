package it.polito.tdp.spellchechker.model;

public class RichWord {

	private String input;
	private boolean value=false;
	
	
	public RichWord(String input,boolean value) {
	
		this.input = input;
		this.value=value;
	}


	public String getInput() {
		return input;
	}


	public void setInput(String input) {
		this.input = input;
	}


	public boolean isValue() {
		return value;
	}


	public void setValue(boolean value) {
		this.value = value;
	}
	
	
	
	
	
}
