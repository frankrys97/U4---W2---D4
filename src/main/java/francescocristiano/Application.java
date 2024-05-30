package francescocristiano;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {

        Random random = new Random();

        Supplier<product.Product> productSupplier = () -> new product.Product(enums.Category.values()[random.nextInt(enums.Category.values().length)], product.Product.generateRandomPrice());

        Supplier<customer.Customer> customerSupplier = () -> new customer.Customer();

        List<product.Product> products = new ArrayList<>();
        List<customer.Customer> customers = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            products.add(productSupplier.get());
            customers.add(customerSupplier.get());
        }
        System.out.println("La lista prodotti del nostro negozio: ");
        System.out.println(products);
        System.out.println();

        System.out.println("La lista clienti del nostro negozio: ");
        System.out.println(customers);
        System.out.println();

        Supplier<order.Order> orderSupplier = () -> {
            customer.Customer customer = customers.get(random.nextInt(customers.size()));
            List<product.Product> orderProducts = new ArrayList<>();

            int numProducts = random.nextInt(1, products.size() + 1);
            for (int i = 0; i < numProducts; i++) {
                orderProducts.add(products.get(random.nextInt(products.size())));
            }

            return new order.Order(customer, orderProducts);
        };

        List<order.Order> orders = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            orders.add(orderSupplier.get());
        }


        System.out.println("La lista degli ordini del nostro negozio: ");
        System.out.println(orders);
        System.out.println();

/*        // Esercizio 1

        List<product.Product> bookProducts = products
                .stream()
                .filter(product -> product.getCategory().equals(enums.Category.BOOKS) && product.getPrice() > 10)
                // ho messo 10 e non 100 poichè avendo generato randomicamente tutti i valori ho preferito mettere un numero più basso
                // altrimenti la probabilità che uscisse un libro nella lista degli ordini con prezzo maggiore di 100 era molto bassa!!!
                .toList();


        System.out.println("La lista di prodotti per libri con prezzo maggiore di 10:");
        if (bookProducts.isEmpty()) {
            System.out.println("Nessun libro trovato");
            System.out.println();
        } else {
            System.out.println(bookProducts);
            System.out.println();
        }

        // Esercizio 2

        List<order.Order> babyOrders = orders.stream().filter(order -> order.getProducts().stream().anyMatch(product -> product.getCategory().equals(enums.Category.BABY))).toList();


        System.out.println("La lista di ordini con prodotti per bambini:");

        if (babyOrders.isEmpty()) {
            System.out.println("Nessun ordine trovato");
            System.out.println();
        } else {
            System.out.println(babyOrders);
            System.out.println();
        }

        // Esercizio 3

        List<product.Product> boysWithDiscount = products.stream().filter(product -> product.getCategory().equals(enums.Category.BOYS)).map(product -> {
            product.setPrice(product.getPrice() * 0.9);
            return product;
        }).toList();


        System.out.println("La lista di prodotti per ragazzi con sconto del 10%:");
        if (boysWithDiscount.isEmpty()) {
            System.out.println("Nessun prodotto per ragazzi trovato");
            System.out.println();
        } else {
            System.out.println(boysWithDiscount);
            System.out.println();
        }

        // Esercizio 4

        LocalDate startDate = LocalDate.of(2021, 2, 1);
        LocalDate endDate = LocalDate.of(2022, 4, 1);

        List<product.Product> productClient2BetwDate = orders.stream().filter(order -> order.getCustomer().getTier() == 2 && order.getOrderDate().isAfter(startDate) && order.getOrderDate().isBefore(endDate)).flatMap(order -> order.getProducts().stream()).toList();

        System.out.println("La lista di prodotti per clienti di livello 2 con ordini tra il 1/2/2021 e il 1/4/2022:");
        if (productClient2BetwDate.isEmpty()) {
            System.out.println("Nessun prodotto trovato");
            System.out.println();
        } else {
            System.out.println(productClient2BetwDate);
            System.out.println();
        }*/

        // Esercizio 1 - giorno 4

        Map<customer.Customer, List<order.Order>> ordersByCustomer = orders
                .stream()
                .collect(Collectors.groupingBy(order -> order.getCustomer()));


        ordersByCustomer.forEach((customer, ordini) -> System.out.println("Il Cliente " + customer.getName() + " ha effettuato i seguenti ordini: " + ordini));
        System.out.println();

        // Esercizio 2 - giorno 4

        Map<customer.Customer, Double> sumTotalByCustomer = orders
                .stream()
                .collect(Collectors.groupingBy(order -> order.getCustomer(), Collectors.summingDouble(order -> order.getProducts().stream().mapToDouble(product -> product.getPrice()).sum())));

        sumTotalByCustomer.forEach((customer, totale) -> System.out.println("Il cliente " + customer.getName() + " ha un totale di " + totale + " €."));

/*        Map<customer.Customer, Double> averageTotalByCustomer = orders
                .stream()
                .collect(Collectors.groupingBy(order -> order.getCustomer(), Collectors.averagingDouble(order -> order.getProducts().stream().mapToDouble(product -> product.getPrice()).average().getAsDouble())));

        averageTotalByCustomer.forEach((customer, media) -> System.out.println("Il cliente " + customer.getName() + " ha una media totale di " + media + " €."));*/

        System.out.println();
        // Esercizio 3 - giorno 4

        List<product.Product> findMoreExpensiveProduct =
                products.stream().sorted(Comparator.comparingDouble(product.Product::getPrice).reversed()).limit(5).toList();


        findMoreExpensiveProduct.forEach(product -> System.out.println(product.getName() + " - " + product.getPrice() + " €"));

        System.out.println();

        // Esercizio 4 - giorno 4

        OptionalDouble averageOfSummingOrders = orders
                .stream()
                .mapToDouble(order -> order.getProducts().stream().mapToDouble(product -> product.getPrice()).sum())
                .average();


        if (averageOfSummingOrders.isPresent()) {
            System.out.println("La media totale degli ordini e': " + averageOfSummingOrders.getAsDouble() + " €");
        } else {
            System.out.println("Nessun ordine presente");
        }

        System.out.println();

        // Esercizio 5 - giorno 4

        Map<enums.Category, Double> totalByCategory = products
                .stream()
                .collect(Collectors.groupingBy(product -> product.getCategory(), Collectors.summingDouble(product -> product.getPrice())));


        totalByCategory.forEach((category, total) -> System.out.println("La categoria " + category + " ha una somma totale di " + total + " €."));


        // Esercizio 6 - giorno 4

        
    }

}
