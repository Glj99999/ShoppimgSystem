import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer extends User {
    private int id;
    private String level;
    private String registrationTime;
    private double totalExpense;
    private String phoneNumber;
    private String emailAddress;

    public Customer(int id, String username, String password, String level, String registrationTime, double totalExpense, String phoneNumber, String emailAddress) {
        super(username, password);
        this.id = id;
        this.level = level;
        this.registrationTime = registrationTime;
        this.totalExpense = totalExpense;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public void changePassword(String newPassword) {
        setPassword(newPassword);
        System.out.println("密码已更改！");
    }
    @Override
    public String toString() {
        return "Customer [id=" + id + ", username=" + getUsername() + ", level=" + level + ", registrationTime=" + registrationTime
                + ", totalExpense=" + totalExpense + ", phoneNumber=" + phoneNumber + ", emailAddress=" + emailAddress + "]";
    }

    public void manageShoppingCart(List<Product> productList , List<Product> shoppingCart) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("--- 购物车管理菜单 ---");
            System.out.println("1. 添加商品到购物车");
            System.out.println("2. 从购物车中移除一件商品");
            System.out.println("3. 修改一件商品的数量");
            System.out.println("4. 购物车商品结算");
            System.out.println("5. 查看消费历史");
            System.out.println("6. 返回主菜单");

            System.out.print("请输入您的选项:");
            choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            switch (choice) {
                case 1:
                    addProductToCart(scanner, productList, shoppingCart);
                    break;
                case 2:
                    removeProductFromCart(scanner, shoppingCart);
                    break;
                case 3:
                    modifyProductQuantity(scanner, shoppingCart);
                    break;
                case 4:
                    payForProducts(productList, shoppingCart);
                    break;
                case 5:
                    viewShoppingHistory(shoppingCart);
                    break;
                case 6:
                    System.out.println("返回主菜单...");
                    break;
                default:
                    System.out.println("无效选项，请重试！");
            }
        } while (choice != 6);
    }

    public static void addProductToCart(Scanner scanner, List<Product> productList , List<Product> shoppingCart) {
        System.out.print("输入需要添加到购物车的商品的编号:");
        String productNumber = scanner.nextLine();

        // 根据商品编号从商品列表中选择
        for (Product product : productList) {
            if (productNumber.equals(product.getProductNumber())) {
                System.out.print("请输入需要添加的商品数量:");
                int quantity = scanner.nextInt();

                // 检查商品数量是否足够
                if (quantity > product.getQuantity()) {
                    System.out.println("商品数量不足!");
                    return;
                }

                // 添加商品到购物车中
                Product cartProduct = new Product(product.getProductNumber(), product.getProductName(),
                        product.getManufacturer(), product.getProductionDate(), product.getModel(),
                        product.getPurchasePrice(), product.getRetailPrice(), quantity);

                shoppingCart.add(cartProduct);
                System.out.println("商品添加成功!");
                return;
            }
        }

        System.out.println("不存在您所选择的商品!");
    }

    public static void removeProductFromCart(Scanner scanner, List<Product> shoppingCart) {
        System.out.print("请输入需要移除的商品编号: ");
        String productNumber = scanner.nextLine();
        scanner.nextLine(); // 消耗换行符

        // 根据商品编号从购物车中寻找需要移除的商品
        for (Product product : shoppingCart) {
            if (productNumber.equals(product.getProductNumber())) {
                System.out.print("你确定将该商品从购物车中移除吗? (y/n): ");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("y")) {
                    shoppingCart.remove(product);
                    System.out.println("移除商品成功!");
                } else {
                    System.out.println("已取消移除该商品");
                }
                return;
            }
        }

        System.out.println("未在购物车中找到该商品!");
    }

    public static void modifyProductQuantity(Scanner scanner, List<Product> shoppingCart) {
        System.out.print("请输入需要修改数量的商品的编号 ");
        String productNumber = scanner.nextLine();
        scanner.nextLine(); // 消耗换行符

        // 根据商品编号从购物车中寻找需要移除的商品
        for (Product product : shoppingCart) {
            if (productNumber.equals(product.getProductNumber())) {
                System.out.print("输入新的购买数目:");
                int newQuantity = scanner.nextInt();
                scanner.nextLine(); // 消耗换行符

                if (newQuantity <= 0) {
                    shoppingCart.remove(product);
                    System.out.println("商品从购物车中移除成功!");
                } else {
                    product.setQuantity(newQuantity);
                    System.out.println("商品购买数目修改成功!");
                }
                return;
            }
        }

        System.out.println("未在购物车中找到该商品!");
    }

    public static void payForProducts(List<Product> productList , List<Product> shoppingCart) {
        double totalAmount = 0;

        // 计算需要支付的商品的总价值
        for (Product product : shoppingCart) {
            totalAmount += product.getRetailPrice() * product.getQuantity();
        }

        // 模拟支付(显示需要支付的金额)
        System.out.println("需要支付的商品的总金额为: " + totalAmount);
        System.out.println("已成功支付!");

        // 支付成功后将商品列表中的商品数目进行更新
        for (Product product : shoppingCart) {
            for (Product adminProduct : productList) {
                if (product.getProductNumber().equals(adminProduct.getProductNumber())) {
                    int remainingQuantity = adminProduct.getQuantity() - product.getQuantity();
                    adminProduct.setQuantity(remainingQuantity);
                    break;
                }
            }
        }
    }

    public static void viewShoppingHistory(List<Product> shoppingCart) {
        // 显示消费历史
        for (Product product : shoppingCart) {
            System.out.println("商品名: " + product.getProductName() + ", 购买数量: " + product.getQuantity());
        }
    }

}
