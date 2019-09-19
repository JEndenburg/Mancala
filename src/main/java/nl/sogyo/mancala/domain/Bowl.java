package nl.sogyo.mancala.domain;

public class Bowl extends BaseBowl 
{
	public Bowl(BaseBowl neighbour, Player player) 
	{
		super(4, neighbour, player);
	}

	public void play()
	{
		neighbour.pass(this.stones);
		this.stones = 0;
	}
	
	@Override
	protected void pass(int stones)
	{
		super.pass(stones);
		if(stones == 1)
			player.switchTurn();
	}
}
