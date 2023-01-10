import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        ProductManager productManager = new ProductManager();
        int choice;
        do {
            System.out.println("1.Display");
            System.out.println("2.Add Product");
            System.out.println("3.Update Product");
            System.out.println("4.Sort Product");
            System.out.println("5.Delete bills");
            System.out.println("6.Display max price product");
            System.out.println("7.load file");
            System.out.println("8.save file");
            System.out.println("0.Exit");
            System.out.println("Enter your choice");
            choice = TryCatch.tryCatchInt(scanner);
            switch (choice) {
                case 1:
                    productManager.displayProduct(productManager.products);
                    break;
                case 2:
                  productManager.addProduct(scanner);
                    break;
                case 3:
                   productManager.editProducts(scanner);
                    break;
                case 4:
                    System.out.println("1.ascending order");
                    System.out.println("2.decreasing order");
                    System.out.println("3.Enter your choice");
                    int choice1=Integer.parseInt(scanner.nextLine());
                    if(choice1 ==1){
                   productManager.sortProductsByPrice(-1);}
                    if(choice1==2){
                        productManager.sortProductsByPrice(1);
                    }
                    break;
                case 5:
                    productManager.deleteProducts(scanner);
                    break;
                case 6:
                    productManager.findMaxPriceProduct();
                    break;
                case 7:
                    productManager.loadProduct();
                    break;
                case 8:
                   productManager.saveProduct();
            }
        } while (choice != 0);
    }
}