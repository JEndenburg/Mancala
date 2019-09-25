package nl.sogyo.mancala.domain;

public class Bowl extends BaseBowl 
{
	public Bowl(int stones, Player player)
	{
		super(stones, player);
	}
	
	public Bowl(Player player) 
	{
		this(4, player);
	}
	
	protected Bowl(Player player, int bowlsLeft)
	{
		super(4, player, bowlsLeft);
	}

	public void play()
	{
		if(!player.getIsMyTurn())
			throw new InvalidPlayException("You can't play when it's not your turn!");
		if(isEmpty())
			throw new InvalidPlayException("You can't play an empty bowl!");
		
		int stonesToPass = this.stones;
		this.stones = 0;
		neighbour.pass(stonesToPass);
	}
	
	@Override
	protected void onGetLastStone()
	{
		if(stones == 1 && player.getIsMyTurn())
		{
			Kalaha nearestKalaha = getKalaha();
			Bowl oppositeBowl = (Bowl)getOpposite();
			nearestKalaha.stones += this.stones + oppositeBowl.stones;
			oppositeBowl.stones = this.stones = 0;
		}
		player.switchTurn();
	}
	
	@Override
	public BaseBowl getOpposite() 
	{
		return (Bowl)neighbour.getOpposite().neighbour;
	}
	
	@Override
	protected boolean isGameOver(int count)
	{
		if(player.getIsMyTurn())
		{
			if(isEmpty())
				return count <= 0 ? true : neighbour.isGameOver(count - 1);
			else
				return false;
		}
		else
			return neighbour.isGameOver(count);
	}
}
