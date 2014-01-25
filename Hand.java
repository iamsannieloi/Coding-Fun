
public class Hand {

	protected int size;
	protected Card cards[];

	public Hand(int size)
	{
		cards = new Card[size];
	}
	public Hand()
	{
		size = 5;
		cards = new Card[size];
	}

	//Prints out the names of all the cards.
	public String toString()
	{
		String temp = "";
		for (int i = 0; i < size; i++)
		{
			if (cards[i] != null)
			{
				temp = temp.concat(cards[i].toString() + " ");
			}
		}
		return temp;
	}
	//Specify the name of the card (IE: KC for King of Clubs) and find its index
	public int findIndex(String cardName)
	{
		//Determines the value and suit of the specified card
		int cardValue = 0;
		int cardSuit = 0;
		int temp = 5;
		if (cardName.charAt(0) == 'A')
		{
			cardValue = 12;
		}
		else if (cardName.charAt(0) == 'K')
		{
			cardValue = 11;
		}
		else if (cardName.charAt(0) == 'Q')
		{
			cardValue = 10;
		}
		else if (cardName.charAt(0) == 'J')
		{
			cardValue = 9;
		}
		else if (cardName.charAt(0) == 'T')
		{
			cardValue = 8;
		}
		else if (cardName.charAt(0) == '9')
		{
			cardValue = 7;
		}
		else if (cardName.charAt(0) == '8')
		{
			cardValue = 6;
		}
		else if (cardName.charAt(0) == '7')
		{
			cardValue = 5;
		}
		else if (cardName.charAt(0) == '6')
		{
			cardValue = 4;
		}
		else if (cardName.charAt(0) == '5')
		{
			cardValue = 3;
		}
		else if (cardName.charAt(0) == '4')
		{
			cardValue = 2;
		}
		else if (cardName.charAt(0) == '3')
		{
			cardValue = 1;
		}
		else if (cardName.charAt(0) == '2')
		{
			cardValue = 0;
		}
		if (cardName.charAt(1) == 'S')
		{
			cardSuit = 3;
		}
		else if (cardName.charAt(1) == 'H')
		{
			cardSuit = 2;
		}
		else if (cardName.charAt(1) == 'D')
		{
			cardSuit = 1;
		}
		else if (cardName.charAt(1) == 'C')
		{
			cardSuit = 0;
		}
		for (int i = 0; i < size; i++)
		{
			//Finds card with equal suit and value and returns the index, if temp is still equal to 5, wrong input given
			if (cards[i].getSuit() == cardSuit && cards[i].getValue() == cardValue)
			{
				temp = i;
			}
		}
		return temp;
	}
	int var = 0;
		public int evaluate()
		{
			this.sort();
			int handType = 0;
			int cardValue[] = new int[5]; 
			int findPair = 0;

			for (int i =0; i < 5; i ++)
			{
				cardValue[i] = cards[i].getValue();
			}
			
			for(int i = 0; i < 5; i ++)
			{
				for(int j = i+1 ; j < 5; j ++)
				{
					if (cardValue[i] == cardValue[j])
					{
						findPair ++;
						var = j;
					}
				}
			}

			//for pairs
			if(findPair == 1)
			{
				handType = 1;
			}
			//for four of a kind 
			else if(findPair == 6 )
			{
				handType = 7;
			}
			//for three of a kind
			else if(findPair == 3)
			{
				handType = 3;
			}
			//for two pair
			else if(findPair == 2)
			{
				handType = 2;
				
			}
			//for a full house 
			else if(findPair == 4)
			{
				handType = 6;
			}



		//if there is a straight
		int findPattern[] = new int[5];
		int counter = 0;
		for(int i =0; i < 5; i ++)
		{
			findPattern[i] = cards[i].getValue();
		}

		for(int i = 0; i < 4; i ++)
		{
			if(findPattern[i+1] - findPattern[i] == 1)
			{
				counter ++;
			}		
		}

		if (counter == 4)
		{
			handType = 4;
		}

		//if there is a flush
		int findSuit[] = new int[5];
		int counter2 = 0;
		for(int i =0; i < 5; i ++)
		{
			findSuit[i] = cards[i].getSuit();
		}

		for (int i =0; i < 4; i ++)
		{
			if(findSuit[i] == findSuit[i+1])
			{
				counter2 ++;
			}
		}

		if (counter2 == 4)
		{
			handType = 5;
		}

		//if there is a straight flush
		if(counter == 4 && counter2 == 4)
		{
			handType = 8;
		}
		
		return handType;
	}
	
	public void sort()
	{
		//Sort cards from least to greatest
		for (int i = 1; i <= 4; i++)
		{
			//Compare cards[i] and cards[i-1], take the larger one and move it up in the array, then compare the lesser one to the rest of the cards to maintain order
			if (cards[i].getValue() < cards[i-1].getValue())
			{
				Card lesser = cards[i];
				cards[i] = cards[i-1];
				cards[i-1] = lesser;
				for (int j = i-1; j >0; j--)
				{
					if (cards[j].getValue() < cards[j-1].getValue())
					{
						Card lesser2 = cards[j];
						cards[j] = cards[j-1];
						cards[j-1] = lesser2;
					}
				}
			}
		}
	}
	//Specify card to add to the hand, and the index in where to put it
	//Returns the card that used to occupy that spot
	public Card replace(Card add, int index)
	{
		Card temp = cards[index];
		cards[index] = add;
		return temp;
	}
	//find highest hand type if hand types are the same 
	public int sumHandType()
	{
		int handValue = 0;
		int handType = this.evaluate();
		//for high card
		if (handType == 0 || handType == 5)
		{
			handValue = cards[4].getValue();
		}
		//for a straight or straight flush 
		else if(handType == 4 || handType == 8)
		{
			for(int i = 0; i < 5; i++)
			{
				handValue = handValue + cards[i].getValue();
			}
		}
		//for a pair 
		else if(handType == 1)
		{
			handValue = cards[var].getValue();
			
		}
		//for two pairs
		else if(handType == 2)
		{
			handValue = cards[var].getValue();
		}
			
		//for three of a kind
		else if(handType == 3)
		{
			handValue = cards[var].getValue();
		}
			
			//for four of a kind 
		else if(handType == 7)
		{
			handValue = cards[var].getValue();
		}
			//for a full house
		else if(handType == 6)
		{
			handValue = cards[var].getValue();
		}
		return handValue; 
	}
}
