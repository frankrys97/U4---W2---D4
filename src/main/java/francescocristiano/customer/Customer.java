package customer;

import java.util.Random;

public class Customer {

    protected long id;
    protected String name;
    protected int tier;

    public Customer() {

        Random random = new Random();

        this.id = Math.abs(random.nextLong());
        this.name = generateRandomicName();
        this.tier = random.nextInt(1, 5);
    }

    public static String generateRandomicName() {
        String[] names = {"Chiara Fais", "Francesco Cossu", "Claudia Burali", "Marina Lucentini", "Alessio Vulpinari", "Vasco Panigi", "Cristian Martucci"};

        return names[new Random().nextInt(names.length)];
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTier() {
        return tier;
    }

    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name= '" + name + '\'' +
                ", tier= " + tier +
                '}';
    }
}
