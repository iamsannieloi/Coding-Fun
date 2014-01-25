import java.util.Scanner;

public class Poker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Deck deck = new Deck();
		deck.shuffle();
		Hand player1 = new Hand();
		Hand player2 = new Hand();
		int initialMoney = 100; 
		int player1Money = initialMoney;
		int player2Money = initialMoney;
		boolean continueGame = true;
		boolean player1Win = false;
		boolean player2Win = false;
		Scanner sc = new Scanner(System.in);

		//prompt the players
		System.out.println("Please enter player 1 name:");
		String player1Name = sc.next();
		System.out.println("Please enter player 2 name:");
		String player2Name = sc.next();
		
		while (continueGame == true)
		{
			//Initialize betting values
			int bet1 = 0;
			int bet12 = 0;
			int bet2 = 0;
			int bet22 = 0;
			int pot = 0;
			int calledAmount = 0;
			
			//Deal the cards
			for (int i = 0; i < 5; i++)
			{
				player1.replace(deck.dealCard(), i);
				player2.replace(deck.dealCard(), i);
			}

			//Player 1's turn
			System.out.println( player1Name + " Cards : " + player1.toString());
			System.out.println("You have: $" + player1Money);
			System.out.println("Enter amount to bet, or type fold to fold:");
			String player1Choice = sc.next();
			String player2Choice = "";

			//If player 1 does not fold
			if (!player1Choice.equalsIgnoreCase("fold"))
			{
				//Parse string as int, remove from amount and add to pot
				bet1 = Integer.parseInt(player1Choice);
				player1Money = player1Money - bet1;
				pot = pot + bet1;
			}

			//If player 1 folds
			else if(player1Choice.equalsIgnoreCase("fold"))
			{
				//End game
				player2Win = true;
				continueGame = false;
			}
			
			System.out.println("========================================================================");
			if (continueGame == true)
			{
				//Player 2's turn
				System.out.println( player2Name + " Cards: " + player2.toString());

				//Player 2 must call, raise, or fold
				System.out.println("You have: $" + player2Money);
				System.out.println( player1Name + " bet: $" + bet1);
				System.out.println("Enter same amount to call, a greater amount to raise, or fold to fold:");
				player2Choice = sc.next();

				//if player 2 does not fold
				if (!player2Choice.equalsIgnoreCase("fold"))
				{
					//Parse string as int, check if Player 2 is calling or raising, and remove from amount and add to pot
					bet2 = Integer.parseInt(player2Choice);
					player2Money = player2Money - bet2;
					pot = pot + bet2;

					//If player 2 raises player 1 must call or fold
					if (bet2 > bet1)
					{
						//Player 1 must call or fold
						System.out.println( player1Name + " Card: " + player1.toString());
						System.out.println("You have: $" + player1Money);
						System.out.println( player2Name + " raised bet to: $" + bet2);
						System.out.println("Do you CALL or FOLD?");
						String player1call = sc.next();
						//player 1 calls
						if (player1call.equalsIgnoreCase("call"))
						{
							calledAmount = bet2 - bet1;
							player1Money = player1Money - calledAmount;
							pot = pot + calledAmount;
						}
						//player 1 folds
						else if (player1call.equalsIgnoreCase("fold"))
						{
							player2Win = true;
							continueGame = false;
						}
					}
				}
				else
				{
					//Player 1 wins
					player1Win = true;
					continueGame = false;
				}
			}
			
			//player 1 folds
			else
			{
				player2Win = true;
				continueGame = false;
			}
			System.out.println("========================================================================");

			//Discard and draw cards
			if (continueGame == true)
			{
				//Player 1's turn to draw cards
				System.out.println( player1Name + " Cards: " + player1);
				System.out.println("Enter how many cards you want to replace:");
				int amountOfCards = sc.nextInt();
				String cardsToReplace[] = new String[amountOfCards];
				System.out.println("Enter the cards you wish to replace:");
				for (int i = 0; i < amountOfCards; i++)
				{
					cardsToReplace[i] = sc.next();
				}
				int indicesFound[] = new int[amountOfCards];
				for (int j = 0; j < amountOfCards; j++)
				{
					if (player1.findIndex(cardsToReplace[j]) < 5)
					{
						indicesFound[j] = player1.findIndex(cardsToReplace[j]);
					}
				}
				for (int k = 0; k < amountOfCards; k++)
				{
					deck.returnToDeck(player1.replace(deck.dealCard(), indicesFound[k]));
				}
				System.out.println( player1Name + " Cards: " + player1.toString());
				System.out.println("====================================================================");

				//Repeat the process for player 2
				System.out.println( player2Name + " Cards: " + player2);
				System.out.println("Enter how many cards you want to replace:");
				amountOfCards = sc.nextInt();
				String cardsToReplace2[] = new String[amountOfCards];
				System.out.println("Enter the cards you wish to replace:");
				for (int i = 0; i < amountOfCards; i++)
				{
					cardsToReplace2[i] = sc.next();
				}
				int indicesFound2[] = new int[amountOfCards];
				for (int j = 0; j < amountOfCards; j++)
				{
					if (player2.findIndex(cardsToReplace2[j]) < 5)
					{
						indicesFound2[j] = player2.findIndex(cardsToReplace2[j]);
					}
				}
				for (int k = 0; k < amountOfCards; k++)
				{
					deck.returnToDeck(player2.replace(deck.dealCard(), indicesFound2[k]));
				}
				System.out.println( player2Name + " Cards: " + player2.toString());
			}
			
			System.out.println("========================================================================");

			//Player 1 checks, raises, or folds

			if (continueGame == true)
			{
				System.out.println( player1Name + " Cards " + player1.toString());
				System.out.println("Current pot: $" + pot);
				System.out.println("Current bet: $" + bet2);
				System.out.println("Current amount of money: $" + player1Money);
				System.out.println("Enter amount to bet or fold to fold:");
				player1Choice = sc.next();
				//if player 1 does not fold
				if (!player1Choice.equalsIgnoreCase("fold"))
				{
					bet12 = Integer.parseInt(player1Choice);
					player1Money = player1Money - bet12;
					pot = pot + bet12;
				}
				else if (player1Choice.equalsIgnoreCase("fold"))
				{
					player2Win = true;
					continueGame = false;
				}
			}
			System.out.println("========================================================================");
			//Player 2 calls, raises or folds
			if (continueGame == true)
			{
				System.out.println( player2Name + " Cards: " + player2.toString());
				System.out.println("Current pot: $" + pot);
				System.out.println("Current bet: $" + bet12);
				System.out.println("Current amount of money: $" + player2Money);
				System.out.println("Enter same amount to call, a greater amount to raise, or fold to fold:");
				player2Choice = sc.next();
				if (!player2Choice.equalsIgnoreCase("fold"))
				{
					bet22 = Integer.parseInt(player2Choice);
					player2Money = player2Money - bet22; 
					pot = pot + bet22;
					//If player 2 raises player 1 must call or fold to continue
					if (bet22 > bet12)
					{
						//Player 1 must call or fold
						System.out.println( player1Name + " Card: " + player1.toString());
						System.out.println("You have: $" + player1Money);
						System.out.println( player2Name + " raised bet to: $" + bet22);
						System.out.println("Do you CALL or FOLD?");
						String player1call = sc.next();
						if (player1call.equalsIgnoreCase("call"))
						{
							int amountCalled2 = bet22 - bet12;
							player1Money = player1Money - amountCalled2;
							pot = pot + amountCalled2;
						}
						else if (player1call.equalsIgnoreCase("fold"))
						{
							player2Win = true;
							continueGame = false;
						}
					}
				}
				else if (player2Choice.equalsIgnoreCase("fold"))
				{
					player1Win = true;
					continueGame = false;
				}
			}
			System.out.println("========================================================================");
			
			if(continueGame == true)
			{
			//Determine winner
			int player1Hand = player1.evaluate();
			int player2Hand = player2.evaluate();
			
			//determine what type of hand player 1 has
			System.out.print( player1Name + " : " + player1 + "----> ");
			if (player1Hand == 0)
			{
				System.out.println("High Card");
			}
			else if (player1Hand == 1)
			{
				System.out.println("Pair");
			}
			else if (player1Hand == 2)
			{
				System.out.println("Two Pairs");
			}
			else if (player1Hand == 3)
			{
				System.out.println("Three of a Kind");
			}
			else if (player1Hand == 4)
			{
				System.out.println("Straight");
			}
			else if (player1Hand == 5)
			{
				System.out.println("Flush");
			}
			else if (player1Hand == 6)
			{
				System.out.println("Full House");
			}
			else if (player1Hand == 7)
			{
				System.out.println("Four of a Kind");
			}
			else if (player1Hand == 8)
			{
				System.out.println("Straight Flush");
			}
			
			//determine the type of hand player 2 has
			System.out.print( player2Name +" : " + player2 + "----> ");
			if (player2Hand == 0)
			{
				System.out.println("High Card");
			}
			else if (player2Hand == 1)
			{
				System.out.println("Pair");
			}
			else if (player2Hand == 2)
			{
				System.out.println("Two Pairs");
			}
			else if (player2Hand == 3)
			{
				System.out.println("Three of a Kind");
			}
			else if (player2Hand == 4)
			{
				System.out.println("Straight");
			}
			else if (player2Hand == 5)
			{
				System.out.println("Flush");
			}
			else if (player2Hand == 6)
			{
				System.out.println("Full House");
			}
			else if (player2Hand == 7)
			{
				System.out.println("Four of a Kind");
			}
			else if (player2Hand == 8)
			{
				System.out.println("Straight Flush");
			}
			
			//if player 1 hand is better than player 2 hand then player 1 wins
			if (player1Hand > player2Hand)
			{
				player1Win = true;
			}
			
			//if player 2 hand is better than player 1 hand then player 2 wins
			else if (player1Hand < player2Hand)
			{
				player2Win = true;
			}
			
			//determine which has the better hand if both player has the same hand strength	
			else if (player1Hand == player2Hand)
			{
				int comparePlayer1 = player1.sumHandType();
				int comparePlayer2 = player2.sumHandType();
				if(comparePlayer1 > comparePlayer2)
				{
					player1Win = true;
					
				}
				
				else if (comparePlayer1 < comparePlayer2)
				{
					player2Win = true;
					
				}
				
				else if (comparePlayer1 == comparePlayer2)
				{
					player1Win = true;
					player2Win = true;
					pot = pot / 2;
					System.out.println("Tie, Split Pot!!!!");
				}
				
			}
			}


			//Assign money
			//if player 1 wins the pot gets added to their total
			if (player1Win == true)
			{
				player1Money = player1Money + pot;
				System.out.println( player1Name + " Wins!!!!!!!!!!");
			}
			//if player 2 wins the pot gets added to their total
			if (player2Win == true)
			{
				player2Money = player2Money + pot;
				System.out.println( player2Name + " Wins!!!!!!!!!!");
			}
			System.out.println(  player1Name + "'s money: $" + player1Money);
			System.out.println( player2Name + "'s money: $" + player2Money);
			System.out.println("Play again? Type YES or NO:");
			player1Choice = sc.next();
			if (player1Choice.equalsIgnoreCase("yes"))
			{
				continueGame = true;
			}
			else if (player1Choice.equalsIgnoreCase("no"))
			{
				System.out.println("Have a nice day :)");
				continueGame = false;
			}
		}
	}
}
