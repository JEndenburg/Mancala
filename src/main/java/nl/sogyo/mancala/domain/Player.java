package nl.sogyo.mancala.domain;

public class Player 
{
	private boolean isMyTurn = false;
	private Player opponent;
	
	public Player(Player opponent)
	{
		this.opponent = opponent;
	}
	
	public boolean getIsMyTurn()
	{
		return isMyTurn;
	}
	
	public void switchTurn()
	{
		//TODO Implement
	}
}
