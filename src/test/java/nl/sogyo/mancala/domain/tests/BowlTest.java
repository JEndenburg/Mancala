package nl.sogyo.mancala.domain.tests;

import org.junit.*;

import nl.sogyo.mancala.domain.BaseBowl;
import nl.sogyo.mancala.domain.Bowl;
import nl.sogyo.mancala.domain.Kalaha;
import nl.sogyo.mancala.domain.Player;

public class BowlTest 
{
	private Bowl bowl;
	Bowl[] neighbourBowls;
	Player player1;
	
	@Before
	public void setup()
	{
		player1 = new Player(true);
		neighbourBowls = new Bowl[5];
		neighbourBowls[neighbourBowls.length - 1] = new Bowl(null, player1);
		for(int i = neighbourBowls.length - 2; i >= 0; i--)
			neighbourBowls[i] = new Bowl(neighbourBowls[i + 1], player1);
		
		bowl = new Bowl(neighbourBowls[0], player1);
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
		Assert.assertEquals(5, neighbourBowls[0].getStones());
	}
	
	@Test
	public void testAdjacentFourNeighboursHaveFiveStonesAfterPlay()
	{
		bowl.play();
		int[] expected = {5,5,5,5};
		int[] actual = {neighbourBowls[0].getStones(), neighbourBowls[1].getStones(), neighbourBowls[2].getStones(), neighbourBowls[3].getStones()};
		Assert.assertArrayEquals(expected, actual);
	}
	
	@Test
	public void testFifthNeighbourHasFourStonesAfterPlay()
	{
		bowl.play();
		Assert.assertEquals(neighbourBowls[4].getStones(), 4);
	}
	
	@Test
	public void testPlayerTurnSwitchedAfterPlayEndsInRegularNonEmptyBowl()
	{
		bowl.play();
		Assert.assertEquals(false, player1.getIsMyTurn());
	}
	
	@After
	public void tearDown()
	{
		bowl = null;
		neighbourBowls = null;
	}
}
