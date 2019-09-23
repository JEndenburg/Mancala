package nl.sogyo.mancala.domain.tests;

import org.junit.*;

import nl.sogyo.mancala.domain.*;

public class KalahaTest 
{
	private Bowl bowl;
	Player player1;
	
	@Before
	public void setup()
	{
		player1 = new Player(true);
		bowl = new Bowl(player1);
	}
	
	@Test
	public void testSkipKalahaWhenNotMyTurn()
	{
		bowl = new Bowl(15, player1);
		bowl.play();
		
		int[] expectedDistribution = {1, 6, 6, 5, 5, 5, 1, 5, 5, 5, 5, 5, 5, 0};
		int[] obtainedDistribution = new int[BaseBowl.BOWL_COUNT];
		for(int i = 0; i < BaseBowl.BOWL_COUNT; i++)
			obtainedDistribution[i] = bowl.getBowlAtDistance(i).getStones();
		
		Assert.assertArrayEquals(expectedDistribution, obtainedDistribution);
	}
	
	@Test
	public void testKalahaReturnsSelfOnBeingAskedItsOpposite()
	{
		Kalaha kalaha = (Kalaha) bowl.getBowlAtDistance(BaseBowl.KALAHA_INTERVAL - 1);
		
		Assert.assertEquals(kalaha, kalaha.getOpposite());
	}
	
	@After
	public void tearDown()
	{
		bowl = null;
		player1 = null;
	}
}
