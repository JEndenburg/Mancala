package nl.sogyo.mancala.domain;

public abstract class BaseBowl 
{
	protected int stones = 4;
	protected BaseBowl neighbour;
	protected Player player;
	
	public int getStones() { return stones; }
	
	public BaseBowl(int stones, BaseBowl neighbour, Player player)
	{
		this.stones = stones;
		this.neighbour = neighbour;
		this.player = player;
	}
	
	protected void pass(int stones)
	{
		this.stones++;
		if(stones > 1)
			neighbour.pass(stones - 1);
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
