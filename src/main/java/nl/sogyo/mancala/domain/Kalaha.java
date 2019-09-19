package nl.sogyo.mancala.domain;

public class Kalaha extends BaseBowl 
{
	public Kalaha(BaseBowl neighbour, Player player) 
	{
		super(0, neighbour, player);
	}

	@Override
	protected void pass(int stones)
	{
		//TODO Implement
	}
	
	@Override
	protected Kalaha getKalaha()
	{
		return this;
	}
	
	@Override
	public boolean isGameOver()
	{
		//TODO Implement
		return false;
	}
	
	@Override
	public Player getWinner()
	{
		//TODO Implement
		return null;
	}
}
