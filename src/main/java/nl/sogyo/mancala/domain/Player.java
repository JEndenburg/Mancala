package nl.sogyo.mancala.domain;

public class Player 
{
	private boolean isMyTurn = true;
	private Player opponent;
	
	public Player getOpponent() { return opponent; }
	
	public Player(boolean isStarter)
	{
		this.opponent = new Player();
		this.opponent.isMyTurn = !isStarter;
		this.opponent.opponent = this;
		this.isMyTurn = isStarter;
	}
	
	private Player(){}
	
	public boolean getIsMyTurn()
	{
		return isMyTurn;
	}
	
	public void switchTurn()
	{
		isMyTurn = !isMyTurn;
		opponent.isMyTurn = !isMyTurn;
	}
}
