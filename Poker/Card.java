public class Card 
{  
	private int suit; 
	private int value; 

	public Card(int newValue,int newSuit)
	{
		value = newValue;
		suit = newSuit;
	}
	public int getValue()
	{
		return value;
	}
	public int getSuit()
	{
		return suit;
	}
	public String toString()
	{
		String cardValue = " ";
		String cardSuit = " ";
		if (value == 12)
		{
			cardValue = "A";
		}
		else if (value == 11)

		{
			cardValue = "K";
		}
		else if (value == 10)
		{
			cardValue = "Q";
		}
		else if (value == 9)
		{
			cardValue = "J";
		}
		else if (value == 8)
		{
			cardValue = "T";
		}
		else if (value == 7)
		{
			cardValue = "9";
		}
		else if (value == 6)
		{
			cardValue = "8";
		}
		else if (value == 5)
		{
			cardValue = "7";
		}
		else if (value == 4)
		{
			cardValue = "6";
		}
		else if (value == 3)
		{
			cardValue = "5";
		}
		else if (value == 2)
		{
			cardValue = "4";
		}
		else if (value == 1)
		{
			cardValue = "3";
		}
		else if (value == 0)
		{
			cardValue = "2";
		}
		
		if (suit == 3)
		{
			cardSuit = "S";
		}
		else if (suit == 2)
		{
			cardSuit = "H";
		}
		else if (suit == 1)
		{
			cardSuit = "D";
		}
		else if (suit == 0)
		{
			cardSuit = "C";
		}
		String name = cardValue.concat(cardSuit);
		return name;

	}


}