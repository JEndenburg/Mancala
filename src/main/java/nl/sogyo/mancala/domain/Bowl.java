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
		int stonesToPass = this.stones;
		this.stones = 0;
		neighbour.pass(stonesToPass);
	}
	
	@Override
	protected void pass(int stones)
	{
		super.pass(stones);
		if(stones == 1)
			player.switchTurn();
	}
}
