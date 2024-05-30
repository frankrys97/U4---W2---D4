package francescocristiano.exsfile;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class FileMain {
    public static void main(String[] args) {
        File file = new File("src/main/java/francescocristiano/exsfile/Prodotti.txt");

        Random random = new Random();


        Supplier<product.Product> productSupplier = () -> new product.Product(enums.Category.values()[random.nextInt(enums.Category.values().length)], product.Product.generateRandomPrice());


        List<product.Product> products1 = new ArrayList<>();
        List<product.Product> products2 = new ArrayList<>();
        List<product.Product> products3 = new ArrayList<>();
        List<product.Product> products4 = new ArrayList<>();
        List<product.Product> products5 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            products1.add(productSupplier.get());
            products2.add(productSupplier.get());
            products3.add(productSupplier.get());
            products4.add(productSupplier.get());
            products5.add(productSupplier.get());
        }

        salvaProdottiSuDisco(products1, file);
        salvaProdottiSuDisco(products2, file);
        salvaProdottiSuDisco(products3, file);
        salvaProdottiSuDisco(products4, file);
        salvaProdottiSuDisco(products5, file);


        System.out.println(leggiProdottiDaDisco(file));

    }


    public static void salvaProdottiSuDisco(List<product.Product> products, File file) {
        try {
            FileUtils.writeStringToFile(file, products.toString() + System.lineSeparator(), "UTF-8", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List leggiProdottiDaDisco(File file) {
        try {
            String content = FileUtils.readFileToString(file, "UTF-8");
            List<String> lines = new ArrayList<>(List.of(content.split(System.lineSeparator())));

            return lines;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
