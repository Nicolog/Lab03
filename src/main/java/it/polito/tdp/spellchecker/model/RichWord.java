package it.polito.tdp.spellchecker.model;

public class RichWord {

	private String word;
	private boolean correct;
	
	public RichWord(String word, boolean b) {
		this.word = word;
		this.correct = b;
		
	}

	public String getWord() {
		return word;
	}

	public boolean isCorrect() {
		return correct;
	}
	
}
