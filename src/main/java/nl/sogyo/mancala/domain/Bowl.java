package nl.sogyo.mancala.domain;

public class Bowl extends BaseBowl 
{
	public Bowl(BaseBowl neighbour) 
	{
		super(4, neighbour);
	}

	public void play()
	{
		neighbour.pass(this.stones);
		this.stones = 0;
	}
}
