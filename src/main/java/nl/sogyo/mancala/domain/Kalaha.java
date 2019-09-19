package nl.sogyo.mancala.domain;

public class Kalaha extends BaseBowl 
{
	public Kalaha(Player player) 
	{
		super(0, player);
	}
	
	protected Kalaha(Player player, int bowlsLeft)
	{
		super(0, player, bowlsLeft);
	}

	@Override
	protected void pass(int stones)
	{
		if(player.getIsMyTurn())
			super.pass(stones);
		else
			neighbour.pass(stones);
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

	@Override
	public BaseBowl getOpposite() 
	{
		return this;
	}
}
