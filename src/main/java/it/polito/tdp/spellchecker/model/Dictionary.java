package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {

	private Set<String> DEng;
	private Set<String> DIta;
	
	public Dictionary() {
		DEng = new HashSet<String>();
		DIta = new HashSet<String>();
	}
	
	public void loadDictionary(String language) {
		
		try {
			if(language.equals("English")) {	
				FileReader fr = new FileReader("English.txt");
				BufferedReader br = new BufferedReader(fr); 
				String word;
				
				while ((word = br.readLine()) != null) {
				   // Aggiungere parola alla struttura dati
					this.DEng.add(word);
				}
				
				br.close();
			}
			else {
				FileReader fr = new FileReader("Italian.txt");
				BufferedReader br = new BufferedReader(fr); 
				String word;
				
				while ((word = br.readLine()) != null) {
				   // Aggiungere parola alla struttura dati
					this.DIta.add(word);
				}
				
				br.close();
			}
			} catch (IOException e){
			System.out.println("Errore nella lettura del file");
			}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList, String language){
		List<RichWord> l = new LinkedList<RichWord>();
		if(language.equals("English"))
				for(String s : inputTextList) {
					if(DEng.contains(s))
						l.add(new RichWord(s, true));
					else
						l.add(new RichWord(s, false));
				}
		
		if(language.equals("Italian"))
			for(String s : inputTextList) {
				if(DIta.contains(s))
					l.add(new RichWord(s, true));
				else
					l.add(new RichWord(s, false));
			}
		
		return l;
	}
	
	public List<RichWord> wrongW(List<RichWord> w){
		
		for(RichWord r : w)
			if(r.isCorrect()==true)
				w.remove(r);
		
		return w;
	}
	
	public String toString(List<RichWord> w) {
		String s= "";
		
		for(RichWord r : w)
			s+=r.getWord()+"\n";
		
		return s;
	}

	public Set<String> getDEng() {
		return DEng;
	}

	public Set<String> getDIta() {
		return DIta;
	}
	
	
}
