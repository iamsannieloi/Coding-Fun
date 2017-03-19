import java.util.Random;

public class Deck {

	protected int size = 52;
	protected Card [] inDeck = new Card[size];

	//Creates a deck of cards, with 13 values and 4 suits
	public Deck()
	{
		int cardIndex = 0;
		for (int i = 0; i < 13; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				inDeck[cardIndex] = new Card(i, j);
				cardIndex++;
			}
		}
	}

	//Randomly shuffles the deck, then sorts it to put all the null values at the end, ensuring that you cannot draw a null value
	public void shuffle()
	{
		Random r = new Random();
		for (int i = 0; i < size; i++)
		{
			int randomPos = r.nextInt(size);
			Card store = inDeck[i];
			inDeck[i] = inDeck[randomPos];
			inDeck[randomPos] = store;
		}
		int numNulls = 0;
		for (int i = 0; i < size; i++)
		{
			if (inDeck[i] == null)
			{
				numNulls++;
			}
		}
		for (int j = 0; j < numNulls; j++)
		{
			for (int i = 0; i < size; i++)
			{
				if (inDeck[i] == null)
				{
					Card temp = inDeck[size - numNulls + j];
					inDeck[size - numNulls + j] = inDeck[i];
					inDeck[i] = temp;
				}
			}
		}
	}

	//Return the top card from the deck, set it to void to simulate removing from deck, and cycle all cards up one in the array to draw the next one
	public Card dealCard()
	{
		Card deal = inDeck[0];
		for (int i = 1; i < size; i++)
		{
			inDeck[i-1] = inDeck[i];
		}
		inDeck[51] = null;
		return deal;
	}

	//Finds a null value in the array of cards, and sets it equal to given card, simulating returning a card to the deck
	public void returnToDeck(Card returnCard)
	{
		int i = 0;
		while (inDeck[i] != null)
		{
			i++;
		}
		inDeck[i] = returnCard;
	}

	public String toString()
	{
		String cardsInDeck = "";
		for (int i = 0; i < size; i++)
		{
			if (inDeck[i] != null)
			{
				cardsInDeck = cardsInDeck.concat(inDeck[i].toString()) + " ";
			}
			else
			{
				cardsInDeck = cardsInDeck.concat("null ");
			}

		}
		return cardsInDeck;
	}
}