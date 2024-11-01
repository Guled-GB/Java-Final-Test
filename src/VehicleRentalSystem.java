import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Vehicle {
    private String licensePlate;
    private double pricePerDay;
    private boolean isRented;
    private List<Rental> rentalHistory = new ArrayList<>();

    public Vehicle(String licensePlate, double pricePerDay) {
        this.licensePlate = licensePlate;
        this.pricePerDay = pricePerDay;
    }

    public void rentVehicle(Rental rental) {
        isRented = true;
        rentalHistory.add(rental);
    }

    public void returnVehicle() {
        isRented = false;
    }

    public double calculateRentalCost(int days) {
        return pricePerDay * days;
    }

    public List<Rental> getLastFiveRentals() {
        int start = Math.max(0, rentalHistory.size() - 5);
        return rentalHistory.subList(start, rentalHistory.size());
    }

    public boolean isRented() { return isRented; }
    public String getLicensePlate() { return licensePlate; }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private int rentalDays;

    public Rental(Vehicle vehicle, Customer customer, int rentalDays) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.rentalDays = rentalDays;
    }

    public double calculateTotalPrice() {
        return vehicle.calculateRentalCost(rentalDays);
    }

    public LocalDate getRentalDate() {
        return LocalDate.now();
    }

    public int getRentalDays() { return rentalDays; }
    public Customer getCustomer() { return customer; }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Vehicle car = new Vehicle("UG363453", 40.0);
        Customer customer = new Customer("Abdullahi Abdukadir");

        // Rent the car
        Rental rental1 = new Rental(car, customer, 3);
        car.rentVehicle(rental1);
        System.out.println("******Welcome My simple Program*****");
        // Print rental cost
        System.out.println("Total cost for 3 days: $" + rental1.calculateTotalPrice());

        // Return the car and add rentals for demonstration
        car.returnVehicle();
        for (int i = 0; i < 6; i++) {
            Rental additionalRental = new Rental(car, customer, 1);
            car.rentVehicle(additionalRental);
            car.returnVehicle();
        }

        // Display last 5 rentals
        System.out.println("Last 5 rentals for vehicle " + car.getLicensePlate() + ":");
        for (Rental rental : car.getLastFiveRentals()) {
            System.out.println("Date: " + rental.getRentalDate() +
                    ", Customer: " + rental.getCustomer().getName() +
                    ", Days: " + rental.getRentalDays());
        }
    }
}
