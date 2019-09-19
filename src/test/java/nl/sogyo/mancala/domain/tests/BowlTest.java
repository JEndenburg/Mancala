package nl.sogyo.mancala.domain.tests;

import org.junit.*;

import nl.sogyo.mancala.domain.BaseBowl;
import nl.sogyo.mancala.domain.Bowl;
import nl.sogyo.mancala.domain.Kalaha;
import nl.sogyo.mancala.domain.Player;

public class BowlTest 
{	
	private Bowl bowl;
	Player player1;
	
	@Before
	public void setup()
	{
		player1 = new Player(true);
		bowl = new Bowl(player1);
	}
	
//	private BaseBowl[] createBoard()
//	{
//		Player p1 = null;
//		Player p2 = new Player(p1);
//		p1 = new Player(p2);
//		BaseBowl[] board = new BaseBowl[14];
//		for(int i = 0; i < board.length; i++)
//		{
//			Player selectedOwner = (i < board.length/2) ? p1 : p2;
//			BaseBowl neighbour = board[(i + 1) % board.length];
//			if((i + 1) % 7 == 0)
//				board[i] = new Kalaha(neighbour, selectedOwner);
//			else
//				board[i] = new Bowl(neighbour, selectedOwner);
//		}
//		
//		return board;
//	}

	@Test
	public void testHasFourStonesRightAfterBeingConstructed()
	{
		Assert.assertEquals(4, bowl.getStones());
	}
	
	@Test
	public void testHasNoStonesAfterPlay()
	{
		bowl.play();
		Assert.assertEquals(0, bowl.getStones());
	}
	
	@Test
	public void testNeigbourHasFiveStonesAfterPlay()
	{
		bowl.play();
		Assert.assertEquals(5, bowl.getBowlAtDistance(1).getStones());
	}
	
	@Test
	public void testAdjacentFourNeighboursHaveFiveStonesAfterPlay()
	{
		bowl.play();
		int[] expected = {5,5,5,5};
		int[] actual = {bowl.getBowlAtDistance(1).getStones(), bowl.getBowlAtDistance(2).getStones(), bowl.getBowlAtDistance(3).getStones(), bowl.getBowlAtDistance(4).getStones()};
		Assert.assertArrayEquals(expected, actual);
	}
	
	@Test
	public void testFifthNeighbourHasFourStonesAfterPlay()
	{
		bowl.play();
		Assert.assertEquals(bowl.getBowlAtDistance(5).getStones(), 4);
	}
	
	@Test
	public void testPlayerTurnSwitchedAfterPlayEndsInRegularNonEmptyBowl()
	{
		boolean playerIsTurn = player1.getIsMyTurn();
		bowl.play();
		Assert.assertNotEquals(playerIsTurn, player1.getIsMyTurn());
	}
	
	@Test
	public void testFourteenthNeighbourIsSelf()
	{
		BaseBowl obtainedBowl = bowl.getBowlAtDistance(14);
		Assert.assertEquals(bowl, obtainedBowl);
	}
	
	@Test
	public void testSeventhNeighbourIsKalaha()
	{
		BaseBowl obtainedBowl = bowl.getBowlAtDistance(BaseBowl.KALAHA_INTERVAL - 1);
		Assert.assertEquals(true, obtainedBowl instanceof Kalaha);
	}
	
	@Test
	public void testPlayerTurnNotSwitchedAfterPlayEndsInKalaha()
	{
		boolean playerIsTurn = player1.getIsMyTurn();
		((Bowl)bowl.getBowlAtDistance(BaseBowl.KALAHA_INTERVAL - 5)).play();
		Assert.assertEquals(playerIsTurn, player1.getIsMyTurn());
	}
	
	@Test
	public void testPlayerTurnSwitchedAfterPlayPassedButNotEndedInKalaha()
	{
		boolean playerIsTurn = player1.getIsMyTurn();
		((Bowl)bowl.getBowlAtDistance(BaseBowl.KALAHA_INTERVAL - 3)).play();
		Assert.assertNotEquals(playerIsTurn, player1.getIsMyTurn());
	}
	
	@Test
	public void testLatterHalfIsOwnedByOtherPlayer()
	{
		boolean[] expectedOwnership = {
				true, 	true, 	true, 	true, 	true, 	true, 	true,
				false, 	false, 	false, 	false, 	false, 	false,	false,
				};
		
		boolean[] obtainedOwnership = new boolean[BaseBowl.BOWL_COUNT];
		
		for(int i = 0; i < BaseBowl.BOWL_COUNT; i++)
			obtainedOwnership[i] = bowl.getBowlAtDistance(i).getPlayer() == player1;
		
		Assert.assertArrayEquals(expectedOwnership, obtainedOwnership);
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
	public void testIfBowlEmptyWhenNotEmpty()
	{
		Assert.assertEquals(false, bowl.isEmpty());
	}
	
	@Test
	public void testIfNoStonesAfterEndingInOwnEmptyBowl()
	{
		Bowl endingBowl = (Bowl)bowl.getBowlAtDistance(4);
		endingBowl.play();
		player1.switchTurn();
		bowl.play();
		
		Assert.assertEquals(0, endingBowl.getStones());
	}
	
	@Test
	public void testIfCanGetOppositeBowl()
	{
		BaseBowl[] expectedBowls = 
			{
				bowl.getBowlAtDistance(12), bowl.getBowlAtDistance(11), bowl.getBowlAtDistance(10),
				bowl.getBowlAtDistance(9), bowl.getBowlAtDistance(8), bowl.getBowlAtDistance(7),
				bowl.getBowlAtDistance(6), bowl.getBowlAtDistance(5), bowl.getBowlAtDistance(4),
				bowl.getBowlAtDistance(3), bowl.getBowlAtDistance(2), bowl.getBowlAtDistance(1),
				bowl.getBowlAtDistance(0)
			};
		BaseBowl[] obtainedBowls = new BaseBowl[expectedBowls.length];
		for(int i = 0; i < expectedBowls.length; i++)
			obtainedBowls[i] = bowl.getBowlAtDistance(i).getOpposite();
		
		Assert.assertArrayEquals(expectedBowls, obtainedBowls);
	}
	
	@Test
	public void testIfHasStolenStones()
	{
		((Bowl)bowl.getBowlAtDistance(4)).play();
		player1.switchTurn();
		bowl.play();
		
		int[] expectedDistribution = {0, 5, 5, 5, 0, 5, 7, 5, 0, 4};
		int[] obtainedDistribution = new int[expectedDistribution.length];
		
		for(int i = 0; i < expectedDistribution.length; i++)
			obtainedDistribution[i] = bowl.getBowlAtDistance(i).getStones();
		
		Assert.assertArrayEquals(expectedDistribution, obtainedDistribution);
	}
	
	@After
	public void tearDown()
	{
		bowl = null;
		player1 = null;
	}
}
