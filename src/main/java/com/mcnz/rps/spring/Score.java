package com.mcnz.rps.spring;




public class Score {
	

	long id;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int wins, losses, ties;
	
	public void increaseWins(){
		System.out.println("Increasing wins in the bean");;
		wins++;
	}
	public void increaseLosses(){
		losses++;
	}
	public void increaseTies(){
		ties++;
	}
	
	public String toString(){
		String output = "Wins: " + wins + " Ties: " + ties + " Losses: " + losses;
		return output;
	}
	
	public int getWins()   {return wins;}
	public int getLosses() {return losses;}
	public int getTies()   {return ties;}
	
}