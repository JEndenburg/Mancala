package nl.sogyo.mancala.domain;

public abstract class BaseBowl 
{
	public static final int BOWL_COUNT = 14;
	public static final int KALAHA_INTERVAL = 7;
	
	protected int stones = 4;
	protected BaseBowl neighbour;
	protected Player player;
	
	public int getStones() { return stones; }
	
	protected BaseBowl(int stones, Player player, int bowlsLeft)
	{
		this.stones = stones;
		this.player = player;
		
		bowlsLeft--;
		if(bowlsLeft > 0)
		{
			Player newBowlsPlayer = (bowlsLeft == KALAHA_INTERVAL) ? player.getOpponent() : player;
			
			if((bowlsLeft - 1) % KALAHA_INTERVAL == 0)
				this.neighbour = new Kalaha(newBowlsPlayer, bowlsLeft);
			else
				this.neighbour = new Bowl(newBowlsPlayer, bowlsLeft);
		}
	}
	
	public BaseBowl(int stones, Player player)
	{
		this(stones, player, BOWL_COUNT);
		this.getBowlAtDistance(BOWL_COUNT - 1).neighbour = this;
	}
	
	protected void pass(int stones)
	{
		this.stones++;
		if(stones > 1)
			neighbour.pass(stones - 1);
	}
	
	protected Kalaha getKalaha()
	{
		return neighbour.getKalaha();
	}
	
	public boolean isEmpty()
	{
		return stones == 0;
	}
	
	public BaseBowl getBowlAtDistance(int distance)
	{
		if(distance > 0)
			return neighbour.getBowlAtDistance(distance - 1);
		else
			return this;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public Player getWinner()
	{
		//TODO Implement
		return null;
	}
	
	protected abstract boolean isGameOver(int count);
	
	public boolean isGameOver()
	{
		return isGameOver(KALAHA_INTERVAL - 2);
	}
	
	public abstract BaseBowl getOpposite();
}
