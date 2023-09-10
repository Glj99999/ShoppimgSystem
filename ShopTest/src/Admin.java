import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }

    public void changePassword(String newAdminPassword) {
        // 修改管理员密码的逻辑
        setPassword(newAdminPassword);
        System.out.println("管理员密码已修改");
    }

    public void resetPassword(User user, String newPassword) {
        // 重置用户密码的逻辑
        user.setPassword(newPassword);
        System.out.println("用户密码已重置");
    }

    public void listCustomers(List<Customer> customers) {
        // 列出所有客户信息的逻辑
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }
    }

    public void deleteCustomer(List<Customer> customers, int customerId) {
        // 删除客户信息的逻辑
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                customers.remove(customer);
                System.out.println("客户已删除");
                break;
            }
        }
    }

    public void searchCustomer(List<Customer> customers, int customerId) {
        // 根据客户ID查询客户信息的逻辑
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                System.out.println(customer.toString());
                break;
            }
        }
    }

    public void searchCustomer(List<Customer> customers, String username) {
        // 根据客户用户名查询客户信息的逻辑
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                System.out.println(customer.toString());
                break;
            }
        }
    }

    public void manageProducts(List<Product> productList) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("--- 商品管理菜单 ---");
            System.out.println("1. 列出所有商品信息");
            System.out.println("2. 添加商品信息");
            System.out.println("3. 修改商品信息");
            System.out.println("4. 删除商品信息");
            System.out.println("5. 返回主菜单");

            System.out.print("请输入选项：");
            choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            switch (choice) {
                case 1:
                    listAllProducts(productList);
                    break;
                case 2:
                    addProduct(productList, scanner);
                    break;
                case 3:
                    modifyProduct(productList, scanner);
                    break;
                case 4:
                    deleteProduct(productList, scanner);
                    break;
                case 5:
                    System.out.println("返回主菜单...");
                    break;
                default:
                    System.out.println("无效选项，请重试！");
            }
        } while (choice != 5);
    }

    private void listAllProducts(List<Product> productList) {
        System.out.println("--- 所有商品信息 ---");
        if (productList.isEmpty()) {
            System.out.println("暂无商品信息");
        } else {
            for (Product product : productList) {
                System.out.println("商品编号：" + product.getProductNumber());
                System.out.println("商品名称：" + product.getProductName());
                System.out.println("生产厂家：" + product.getManufacturer());
                System.out.println("生产日期：" + product.getProductionDate());
                System.out.println("型号：" + product.getModel());
                System.out.println("进货价：" + product.getPurchasePrice());
                System.out.println("零售价格：" + product.getRetailPrice());
                System.out.println("数量：" + product.getQuantity());
                System.out.println();
            }
        }
    }

    private void addProduct(List<Product> productList, Scanner scanner) {
        System.out.println("--- 添加商品信息 ---");
        System.out.print("请输入商品编号：");
        String productNumber = scanner.nextLine();

        // 检查商品编号是否已存在
        if (isProductNumberExists(productList, productNumber)) {
            System.out.println("商品编号已存在！");
            return;
        }

        System.out.print("请输入商品名称：");
        String productName = scanner.nextLine();
        System.out.print("请输入生产厂家：");
        String manufacturer = scanner.nextLine();
        System.out.print("请输入生产日期：");
        String productionDate = scanner.nextLine();
        System.out.print("请输入型号：");
        String model = scanner.nextLine();
        System.out.print("请输入进货价：");
        double purchasePrice = scanner.nextDouble();
        System.out.print("请输入零售价格：");
        double retailPrice = scanner.nextDouble();
        System.out.print("请输入数量：");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // 消耗换行符

        Product newProduct = new Product(productNumber, productName, manufacturer, productionDate, model, purchasePrice,
                retailPrice, quantity);

        productList.add(newProduct);
        System.out.println("商品添加成功！");
    }

    private void modifyProduct(List<Product> productList, Scanner scanner) {
        System.out.println("--- 修改商品信息 ---");
        System.out.print("请输入要修改的商品编号：");
        String productNumber = scanner.nextLine();

        // 检查商品编号是否存在
        int index = getProductIndexByNumber(productList, productNumber);
        if (index == -1) {
            System.out.println("商品编号不存在！");
            return;
        }

        Product product = productList.get(index);

        // 打印商品信息
        System.out.println("当前商品信息：");
        System.out.println("商品编号：" + product.getProductNumber());
        System.out.println("商品名称：" + product.getProductName());
        System.out.println("生产厂家：" + product.getManufacturer());
        System.out.println("生产日期：" + product.getProductionDate());
        System.out.println("型号：" + product.getModel());
        System.out.println("进货价：" + product.getPurchasePrice());
        System.out.println("零售价格：" + product.getRetailPrice());
        System.out.println("数量：" + product.getQuantity());
        System.out.println();

        System.out.println("请输入修改后的信息：");
        System.out.print("商品名称：");
        String productName = scanner.nextLine();
        System.out.print("生产厂家：");
        String manufacturer = scanner.nextLine();
        System.out.print("生产日期：");
        String productionDate = scanner.nextLine();
        System.out.print("型号：");
        String model = scanner.nextLine();
        System.out.print("进货价：");
        double purchasePrice = scanner.nextDouble();
        System.out.print("零售价格：");
        double retailPrice = scanner.nextDouble();
        System.out.print("数量：");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // 消耗换行符

        product.setProductName(productName);
        product.setManufacturer(manufacturer);
        product.setProductionDate(productionDate);
        product.setModel(model);
        product.setPurchasePrice(purchasePrice);
        product.setRetailPrice(retailPrice);
        product.setQuantity(quantity);
        System.out.println("商品信息修改成功！");
    }

    private void deleteProduct(List<Product> productList, Scanner scanner) {
        System.out.println("--- 删除商品信息 ---");
        System.out.print("请输入要删除的商品编号：");
        String productNumber = scanner.nextLine();

        // 检查商品编号是否存在
        int index = getProductIndexByNumber(productList, productNumber);
        if (index == -1) {
            System.out.println("商品编号不存在！");
            return;
        }

        Product product = productList.get(index);

        // 打印商品信息
        System.out.println("要删除的商品信息：");
        System.out.println("商品编号：" + product.getProductNumber());
        System.out.println("商品名称：" + product.getProductName());
        System.out.println("生产厂家：" + product.getManufacturer());
        System.out.println("生产日期：" + product.getProductionDate());
        System.out.println("型号：" + product.getModel());
        System.out.println("进货价：" + product.getPurchasePrice());
        System.out.println("零售价格：" + product.getRetailPrice());
        System.out.println("数量：" + product.getQuantity());
        System.out.println();

        System.out.print("确认删除该商品吗？(y/n)：");
        String confirm = scanner.nextLine();

        if (confirm.equals("y")) {
            productList.remove(index);
            System.out.println("商品删除成功！");
        } else {
            System.out.println("取消删除操作！");
        }
    }

    private boolean isProductNumberExists(List<Product> productList, String productNumber) {
        for (Product product : productList) {
            if (product.getProductNumber().equals(productNumber)) {
                return true;
            }
        }
        return false;
    }

    private int getProductIndexByNumber(List<Product> productList, String productNumber) {
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            if (product.getProductNumber().equals(productNumber)) {
                return i;
            }
        }
        return -1;
    }

}
