import java.util.*;

class Card 
{
    private final String value;

    public Card(String value) 
    {
        this.value = value;
    }

    @Override
    public String toString() 
    {
        return value;
    }
}

public class CardCollection 
{
    private static final Map<String, Set<Card>> cardMap = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    private static void addCard() 
    {
        System.out.print("Enter Card Symbol: ");
        String symbol = scanner.nextLine().trim();
        System.out.print("Enter Card Value: ");
        String value = scanner.nextLine().trim();

        cardMap.computeIfAbsent(symbol, k -> new HashSet<>()).add(new Card(value));
        System.out.println("Card added successfully!");
    }

    private static void findCardsBySymbol() 
    {
        System.out.print("Enter Symbol to search: ");
        String symbol = scanner.nextLine().trim();
        if (cardMap.containsKey(symbol)) 
        {
            System.out.println("Cards under " + symbol + ": " + cardMap.get(symbol));
        } 
        else 
        {
            System.out.println("No cards found for symbol: " + symbol);
        }
    }

    private static void displayAllCards()
    {
        if (cardMap.isEmpty()) 
        {
            System.out.println("No cards available.");
        } 
        else 
        {
            cardMap.forEach((symbol, cards) -> 
                System.out.println(symbol + ": " + cards));
        }
    }

    public static void main(String[] args) 
    {
        while (true) 
        {
            System.out.println("\n1. Add Card\n2. Find Cards by Symbol\n3. Display All Cards\n4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) 
            {
                case 1 -> addCard();
                case 2 -> findCardsBySymbol();
                case 3 -> displayAllCards();
                case 4 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
