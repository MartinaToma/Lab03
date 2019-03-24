package it.polito.tdp.spellchechker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	private String language;
	private List<String> setLista;
	
	
	
	public Dictionary() {		
		setLista=new LinkedList<String>();		
	}
	


	public void loadDictionary(String language) {
		this.language=language.toLowerCase();
		
		if(this.language.equals("english")) {
			try {
				FileReader fr =new FileReader("rsc/English.txt");
				BufferedReader br=new BufferedReader(fr);
				String word;
				while((word=br.readLine())!=null) {
					setLista.add(word);
					
				}
				br.close();				
			}
			catch(IOException e) {
				System.out.println("Errore nella lettura del file English.txt");
			}
		}
		
		else 
			if(this.language.equals("italian")) {
				try {
					FileReader fr2 =new FileReader("rsc/Italian.txt");
					BufferedReader br2=new BufferedReader(fr2);
					String word2;
					while((word2=br2.readLine())!=null) {
						setLista.add(word2);
						
					}
					br2.close();				
				}
				catch(IOException e) {
					System.out.println("Errore nella lettura del file Italian.txt");
				}
			}
		
		
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
			
		 List<RichWord> rich=new LinkedList<RichWord>();
		for (String string : inputTextList) {
			
			boolean a=false;
			for (String string2 : setLista) {
				if(string.toLowerCase().equals(string2)) {
					a=true;
					RichWord r=new RichWord(string,true);
					rich.add(r);
					break;
			}				
				
		}
				if(a==false){
					RichWord ri=new RichWord(string,false);
					//System.out.println(ri.getInput());
					rich.add(ri);
				}
				
		}			
		 
		return rich;
	}
	
	public void resetDictionary() {
		this.setLista.clear();
		
	}
	
	
}
