package nl.sogyo.mancala.domain.tests;

import org.junit.*;

import nl.sogyo.mancala.domain.Player;

public class PlayerTest 
{
	private Player player1;
	private Player player2;
	
	@Before
	public void setup()
	{
		player1 = new Player(true);
		player2 = player1.getOpponent();
	}
	
	@Test
	public void testIfPlayerOneHasTurnAfterConstruction()
	{
		Assert.assertEquals(true, player1.getIsMyTurn());
	}
	
	@Test
	public void testIfPlayerOneDoesNotHaveTurnAfterSwitchTurnWasCalled()
	{
		player1.switchTurn();
		Assert.assertNotEquals(true, player1.getIsMyTurn());
	}
	
	@Test
	public void testIfPlayerOneDoesHaveTurnAfterSwitchTurnWasCalledTwice()
	{
		player1.switchTurn();
		player1.switchTurn();
		Assert.assertEquals(true, player1.getIsMyTurn());
	}
	
	@Test
	public void testIfPlayerTwoDoesNotHaveTurnAfterConstruction()
	{
		Assert.assertNotEquals(true, player2.getIsMyTurn());
	}
	
	@Test
	public void testIfPlayerTwoDoesHaveTurnAfterSwitchTurnWasCalled()
	{
		player2.switchTurn();
		Assert.assertEquals(true, player2.getIsMyTurn());
	}
	
	@Test
	public void testIfPlayerTwoDoesHaveTurnAfterSwitchTurnWasCalledOnPlayerOne()
	{
		player1.switchTurn();
		Assert.assertEquals(true, player2.getIsMyTurn());
	}
}
