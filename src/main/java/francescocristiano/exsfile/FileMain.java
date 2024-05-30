package francescocristiano.exsfile;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.function.Supplier;

public class FileMain {
    public static void main(String[] args) {
        File file = new File("src/main/java/francescocristiano/exsfile/Prodotti.txt");

        Random random = new Random();


        Supplier<product.Product> productSupplier = () -> new product.Product(enums.Category.values()[random.nextInt(enums.Category.values().length)], product.Product.generateRandomPrice());

        salvaProdottiSuDisco(productSupplier.get(), file);
        salvaProdottiSuDisco(productSupplier.get(), file);
        salvaProdottiSuDisco(productSupplier.get(), file);
        salvaProdottiSuDisco(productSupplier.get(), file);
        salvaProdottiSuDisco(productSupplier.get(), file);


        leggiProdottiDaDisco(file);


    }


    public static void salvaProdottiSuDisco(product.Product product, File file) {
        try {
            FileUtils.writeStringToFile(file, product.toString() + System.lineSeparator(), "UTF-8", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void leggiProdottiDaDisco(File file) {
        try {
            System.out.println(FileUtils.readFileToString(file, "UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
