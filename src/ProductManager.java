import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductManager {
    public ArrayList<Product> products;

    public ProductManager(){
        this.products=new ArrayList<>();

    }
    public void addProduct(Scanner scanner){
        System.out.println("Enter your product");
        System.out.println("Enter your Id of new product");
        int id;
        do{
            id=TryCatch.tryCatchInt(scanner);
            if(findById(id)!=null){
                System.out.println("Already have product with this id.Pls enter new id");
            }
        }while (findById(id)!=null);
        System.out.println("Enter your name of new product");
        String name=TryCatch.tryCatchString(scanner);
        System.out.println("Enter your price of new product");
        int price=TryCatch.tryCatchInt(scanner);
        System.out.println("Enter your quantity of new product");
        int quantity=TryCatch.tryCatchInt(scanner);
        System.out.println("Enter your note of new product");
        String note=TryCatch.tryCatchString(scanner);
        Product product=new Product(name,id,price,quantity,note);
       products.add(product);
        System.out.println("Success to add product");
    }
    public void editProducts(Scanner scanner){
        System.out.println("Pls enter your product id you want to edit");
        int id;
        do{
            String s =scanner.nextLine();
            if(s.equals("")) {return;}
                try {
                     id=Integer.parseInt(s);
                    if (findById(id)==null) {
                        System.out.println("Cant find your product");
                    } else {
                        break;
                    }
                } catch (NumberFormatException a){
                    System.out.println(a.getMessage());
                    System.out.println("Pls enter number format");
                }
        }while (true);
        Product product =findById(id);
        System.out.println("Enter your new name of product");
        String name=TryCatch.tryCatchString(scanner);
        product.setName(name);
        System.out.println("Enter your new price of product");
        int price=TryCatch.tryCatchInt(scanner);
        product.setPrice(price);
        System.out.println("Enter your new quantity of product");
        int quantity=TryCatch.tryCatchInt(scanner);
        product.setQuantity(quantity);
        System.out.println("Enter your new note of product");
        String note=TryCatch.tryCatchString(scanner);
        product.setNote(note);
        System.out.println("Success to edit product");
    }
    public void deleteProducts(Scanner scanner){
        System.out.println("Pls enter your product id you want to delete");
        int id;
        do{
            String s =scanner.nextLine();
            if(s.equals("")) {return;}
            try {
                id=Integer.parseInt(s);
                if (findById(id)==null) {
                    System.out.println("Cant find your product");
                } else {
                    break;
                }
            } catch (NumberFormatException a){
                System.out.println(a.getMessage());
                System.out.println("Pls enter number format");
            }
        }while (true);
        Product product=findById(id);
        product.displayAttribute();
        System.out.println("Do you want to delete this product?\"Y\"to accept to delete or not \"Y\" to abort");
        String s =scanner.nextLine();
        if(s.equalsIgnoreCase("Y")){
            products.remove(product);
        } else {
            System.out.println("Abort to delete");
        }
    }
    public void sortProductsByPrice(int choice) {
        for (int i = 0; i < products.size(); i++) {
            Product max = products.get(i);
            for (int j = i; j < products.size(); j++) {
                if (choice*(products.get(j).getPrice() - products.get(i).getPrice())>0) {
                    max = products.get(j);
                }
                products.remove(max);
                products.add(i,max);
            }
        }
    }
    public void findMaxPriceProduct(){
        Product max=products.get(0);
        for (Product element:products) {
            if (element.getPrice()>max.getPrice()){
                max=element;
            }
        }
        max.displayAttribute();
    }
    public Product findById(int id){
        for(Product element:products){
            if (element.getId()==id){
                return element;
            }
        }
        return null;
    }
    public void displayProduct(ArrayList<Product> products){
        System.out.printf("%-15s%-15s%-15s%-15s%s","Id","Name","Price","Quantity","Note"+"\n");
        for(Product element:products){
            element.displayAttribute();
        }
    }
    public void saveProduct() {
        Path path = Paths.get("source\\ product.csv");
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException a) {
            System.out.println("Oop!!Something 's wrong");
        }
        try (FileWriter output = new FileWriter(path.toString())) {
            for (Product element : products) {
                output.write(element.StringAttribute());
            }
        } catch (IOException a) {
            System.out.println("Oop!!Something 's wrong");
        }
    }
    public  void loadProduct() {
        Path path = Paths.get("source\\ product.csv");
        if (!Files.exists(path)) {
            System.out.println("Cannot find the \"product.csv\" to load");
        } else {
            try (BufferedReader in = new BufferedReader(new FileReader(path.toString()))) {
                String s;
                while ((s = in.readLine()) != null) {
                    products.add(Product.create(s));
                }
            } catch (IOException a) {
                System.out.println("Oop!!Something 's wrong");
            }
        }
    }
}
