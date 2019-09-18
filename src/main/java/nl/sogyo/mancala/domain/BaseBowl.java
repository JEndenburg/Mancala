package nl.sogyo.mancala.domain;

public abstract class BaseBowl 
{
	private int stones;
	private BaseBowl neighbour;
	private Player player;
	
	protected void pass(int stones)
	{
		//TODO Implement
	}
	
	protected Kalaha getKalaha()
	{
		//TODO Implement
		return null;
	}
	
	private boolean isEmpty()
	{
		return stones == 0;
	}
	
	private BaseBowl getBowlAtDistance(int distance)
	{
		//TODO Implement
		return null;
	}
	
	public Player getWinner()
	{
		//TODO Implement
		return null;
	}
	
	public boolean isGameOver()
	{
		//TODO Implement
		return false;
	}
}
