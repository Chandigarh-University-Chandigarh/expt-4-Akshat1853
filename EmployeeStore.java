import java.util.*;

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary);
    }
}

public class EmployeeStore 
{
    public static void main(String[] args) 
    {
        ArrayList<Employee> employees = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) 
        {
            System.out.println("\n1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Search Employee");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) 
            {
                case 1:
                    System.out.print("Enter Id of the Employee: ");
                    int id = sc.nextInt();
                    
                    sc.nextLine(); // Consume newline
                    
                    System.out.print("Enter name of the Employee: ");
                    String name = sc.nextLine();
                    
                    System.out.print("Enter salary of the Employee: ");
                    double salary = sc.nextDouble();

                    employees.add(new Employee(id, name, salary));
                    System.out.println("Employee added successfully!");
                    break;

                case 2:
                    System.out.print("Enter ID to update: ");
                    int updateId = sc.nextInt();
                    boolean updated = false;

                    for (Employee e : employees) 
                    {
                        if (e.id == updateId) 
                        {
                            sc.nextLine(); // Consume newline
                            System.out.print("Enter new Name: ");
                            e.name = sc.nextLine();
                            
                            System.out.print("Enter new Salary: ");
                            e.salary = sc.nextDouble();
                            
                            System.out.println("Employee updated successfully!");
                            updated = true;
                            
                            break;
                        }
                    }

                    if (!updated) 
                    {
                        System.out.println("Employee with ID " + updateId + " not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter ID to Remove: ");
                    int removeId = sc.nextInt();
                    
                    boolean removed = employees.removeIf(e -> e.id == removeId);

                    if (removed) 
                    {
                        System.out.println("Employee removed successfully!");
                    } 
                    else 
                    {
                        System.out.println("Employee with ID " + removeId + " not found.");
                    }
                    break;
                    

                case 4:
                    System.out.print("Enter ID to search: ");
                    int searchId = sc.nextInt();
                    boolean found = false;

                    for (Employee e : employees) 
                    {
                        if (e.id == searchId) 
                        {
                            e.display();
                            found = true;
                            break;
                        }
                    }

                    if (!found) 
                    {
                        System.out.println("Employee with ID " + searchId + " not found.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting program...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 5.");
            }
        }
    }
}
