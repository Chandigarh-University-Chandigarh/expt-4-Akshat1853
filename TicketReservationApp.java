import java.util.Scanner;
import java.util.concurrent.locks.*;

class SeatReservationSystem 
{
    private int seatsAvailable;
    private final Lock seatLock = new ReentrantLock();

    public SeatReservationSystem(int totalSeats) 
    {
        this.seatsAvailable = totalSeats;
    }

    public void reserveSeat(String userName, int requestedSeats) 
    {
        seatLock.lock();
        try 
        {
            if (seatsAvailable >= requestedSeats) 
            {
                System.out.println(userName + " successfully reserved " + requestedSeats + " seat(s). Seats left: " + (seatsAvailable - requestedSeats));
                seatsAvailable -= requestedSeats;
            } 
            else 
            {
                System.out.println("Apologies, " + userName + "! Only " + seatsAvailable + " seat(s) remaining.");
            }
        } 
        finally 
        {
            seatLock.unlock();
        }
    }
}

class Passenger extends Thread 
{
    private SeatReservationSystem reservationSystem;
    private String userName;
    private int requestedSeats;

    public Passenger(SeatReservationSystem system, String name, int seats, int priorityLevel) 
    {
        this.reservationSystem = system;
        this.userName = name;
        this.requestedSeats = seats;
        setPriority(priorityLevel);
    }

    @Override
    public void run() 
    {
        reservationSystem.reserveSeat(userName, requestedSeats);
    }
}

public class TicketReservationApp 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter total number of seats available: ");
        int totalSeats = input.nextInt();
        SeatReservationSystem system = new SeatReservationSystem(totalSeats);

        System.out.print("Enter number of passengers: ");
        int passengerCount = input.nextInt();
        input.nextLine();

        Passenger[] passengers = new Passenger[passengerCount];

        for (int i = 0; i < passengerCount; i++) 
        {
            System.out.print("Enter passenger name: ");
            String name = input.nextLine();
            System.out.print("Enter number of seats required: ");
            int seats = input.nextInt();
            System.out.print("Select priority level (1 - High, 2 - Standard): ");
            int priority = input.nextInt();
            input.nextLine();

            int assignedPriority = (priority == 1) ? Thread.MAX_PRIORITY : Thread.NORM_PRIORITY;
            passengers[i] = new Passenger(system, name, seats, assignedPriority);
        }

        for (Passenger passenger : passengers) 
        {
            passenger.start();
        }

        input.close();
    }
}
