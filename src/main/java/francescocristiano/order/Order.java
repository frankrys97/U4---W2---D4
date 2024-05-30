package order;

import customer.Customer;
import enums.Status;
import product.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;


public class Order {
    protected long id;
    protected String status;
    protected LocalDate orderDate;
    protected LocalDate deliveryDate;
    protected List<Product> products;
    protected Customer customer;


    public Order(Customer customer, List<Product> products) {

        Random random = new Random();

        this.id = Math.abs(random.nextLong());
        this.status = generateRandomicStatus(Status.values()[random.nextInt(Status.values().length)]);
        this.customer = customer;
        this.orderDate = generateRandomDate();
        this.deliveryDate = orderDate.plusDays(7);
        this.products = products;
    }

    public static LocalDate generateRandomDate() {
        Random random = new Random();
        int year = random.nextInt(2021, 2023);
        int month = random.nextInt(1, 12);
        int day = random.nextInt(1, 28);
        return LocalDate.of(year, month, day);
    }

    public static String generateRandomicStatus(Status status) {

        return switch (status) {
            case ORDERED -> "Ordered";
            case PENDING -> "Pending";
            case DELIVERED -> "Delivered";
            case CANCELLED -> "Cancelled";
            default -> "Unknown";
        };

    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Customer getCustomer() {
        return customer;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", products=" + products +
                ", customer=" + customer +
                '}';
    }


}
