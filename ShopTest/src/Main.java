import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        // 初始化管理员账户和客户数据
        Admin admin = new Admin("admin", "ynuadmin");

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "customer1", "password1", "Gold", "2021-01-01", 1000.0, "1234567890", "customer1@example.com"));
        customers.add(new Customer(2, "customer2", "password2", "Silver", "2021-02-01", 500.0, "9876543210", "customer2@example.com"));
        customers.add(new Customer(3, "customer3", "password3", "Bronze", "2021-03-01", 200.0, "7894561230", "customer3@example.com"));

        List<Product> products = new ArrayList<>();
        products.add(new Product("1","computer","huawei","2021-01-01","01",1200,1399,3));
        List<Product> shoppingCart = new ArrayList<>();


        Scanner input = new Scanner(System.in);


        // 用户登录逻辑
        boolean loggedIn = false;
        User currentUser = null;

        while (!loggedIn) {
            System.out.print("请输入用户名：");
            String username = input.nextLine();

            System.out.print("请输入密码：");
            String password = input.nextLine();

            // 管理员登录逻辑
            if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
                loggedIn = true;
                currentUser = admin;
                System.out.println("管理员登录成功！");
                break;
            }

            // 客户登录逻辑
            for (Customer customer : customers) {
                if (username.equals(customer.getUsername()) && password.equals(customer.getPassword())) {
                    loggedIn = true;
                    currentUser = customer;
                    System.out.println("客户登录成功！");
                    break;
                }
            }

            if (!loggedIn) {
                System.out.println("登录失败，请确认用户名和密码是否正确。");
                System.out.print("是否需要注册新账户？(y/n): ");
                String choice = input.nextLine();

                if (choice.equalsIgnoreCase("y")) {
                    System.out.println("开始注册新账户。");
                    // 获取新客户的信息
                    System.out.print("请设置用户名：");
                    String newUsername = input.nextLine();
                    while(!isValidUsername(newUsername)){
                        System.out.print("请重新设置用户名：");
                        newUsername = input.nextLine();
                    }

                    System.out.print("请输入密码：");
                    String newPassword = input.nextLine();
                    while(!isValidPassword(newPassword)){
                        System.out.print("请重新设置密码：");
                        newPassword = input.nextLine();
                    }
                    System.out.print("请输入客户ID：");
                    int newCustomerId = Integer.parseInt(input.nextLine());

                    // 创建新客户对象
                    Customer newCustomer = new Customer(newCustomerId, newUsername, newPassword, "", "", 0.0, "", "");

                    // 添加到客户列表
                    customers.add(newCustomer);

                    System.out.println("新账户注册成功！请使用新账户登录。");
                }

            }
        }

        // 用户功能逻辑
        if (currentUser instanceof Admin) {
            Admin currentAdmin = (Admin) currentUser;

            // 管理员功能
            System.out.println("\n管理员功能：");
            System.out.println("(1) 修改密码");
            System.out.println("(2) 重置用户密码");
            System.out.println("(3) 列出所有客户信息");
            System.out.println("(4) 删除客户信息");
            System.out.println("(5) 查询客户信息");
            System.out.println("(6) 管理商品信息");
            boolean adminLoggedIn = true;

            while (adminLoggedIn) {
                System.out.print("\n请输入功能编号：");
                int adminChoice = input.nextInt();
                input.nextLine(); // 读取换行符

                switch (adminChoice) {
                    case 1:
                        System.out.print("请输入新密码：");
                        String newAdminPassword = input.nextLine();
                        currentAdmin.changePassword(newAdminPassword);
                        break;
                    case 2:
                        System.out.print("请输入要重置密码的用户ID：");
                        int userId = input.nextInt();
                        input.nextLine(); // 读取换行符

                        System.out.print("请输入新密码：");
                        String newUserPassword = input.nextLine();

                        for (Customer customer : customers) {
                            if (customer.getId() == userId) {
                                currentAdmin.resetPassword(customer, newUserPassword);
                                break;
                            }
                        }

                        break;
                    case 3:
                        currentAdmin.listCustomers(customers);
                        break;
                    case 4:
                        System.out.print("请输入要删除的客户ID：");
                        int customerId = input.nextInt();
                        input.nextLine(); // 读取换行符
                        currentAdmin.deleteCustomer(customers, customerId);
                        break;
                    case 5:
                        System.out.println("请选择查询方式：");
                        System.out.println("(1) 根据ID查询");
                        System.out.println("(2) 根据用户名查询");
                        System.out.println("(3) 查询所有客户");

                        int searchChoice = input.nextInt();
                        input.nextLine(); // 读取换行符

                        switch (searchChoice) {
                            case 1:
                                System.out.print("请输入要查询的客户ID：");
                                int searchId = input.nextInt();
                                input.nextLine(); // 读取换行符
                                currentAdmin.searchCustomer(customers, searchId);
                                break;
                            case 2:
                                System.out.print("请输入要查询的客户用户名：");
                                String searchUsername = input.nextLine();
                                currentAdmin.searchCustomer(customers, searchUsername);
                                break;
                            case 3:
                                currentAdmin.listCustomers(customers);
                                break;
                            default:
                                System.out.println("无效的选择！");
                                break;
                        }
                        break;
                    case 6:
                        currentAdmin.manageProducts(products);
                        break;
                    default:
                        System.out.println("无效的选择！");
                        break;
                }

                System.out.print("是否继续管理员操作？(Y/N)：");
                String continueChoice = input.nextLine();

                if (!continueChoice.equalsIgnoreCase("Y")) {
                    adminLoggedIn = false;
                }
            }
        }
        if (currentUser instanceof Customer) {
            Customer currentCustomer = (Customer) currentUser;

            // 客户功能
            System.out.println("\n客户功能：");
            System.out.println("(1) 修改密码");
            System.out.println("(2) 忘记密码");
            System.out.println("(3) 进入购物界面");

            boolean customerLoggedIn = true;

            while (customerLoggedIn) {
                System.out.print("\n请输入功能编号：");
                int customerChoice = input.nextInt();
                input.nextLine(); // 读取换行符

                switch (customerChoice) {
                    case 1:
                        System.out.print("请输入新密码：");
                        String newCustomerPassword = input.nextLine();
                        while(!isValidPassword(newCustomerPassword)){
                            System.out.print("请重新设置密码：");
                            newCustomerPassword = input.nextLine();
                        }
                        currentCustomer.changePassword(newCustomerPassword);
                        break;
                    case 2:
                        System.out.print("请输入用户名：");
                        String forgotPasswordUsername = input.nextLine();

                        System.out.print("请输入注册邮箱地址：");
                        String forgotPasswordEmail = input.nextLine();

                        // 调用发送随机密码到邮箱的方法
                        String randomPassword = generateRandomPassword();
                        sendPasswordByEmail(forgotPasswordEmail, randomPassword);
                        break;
                    case 3:
                        currentCustomer.manageShoppingCart(products, shoppingCart);
                        break;
                    default:
                        System.out.println("无效的选择！");
                        break;
                }

                System.out.print("是否继续客户操作？(Y/N)：");
                String continueChoice = input.nextLine();

                if (!continueChoice.equalsIgnoreCase("Y")) {
                    customerLoggedIn = false;
                }
            }
        }

        input.close();
    }

    // 生成随机密码的方法
    public static String generateRandomPassword() {
        // 实现随机密码逻辑
        return "randompassword";
    }

    // 发送密码到邮箱的方法
    public static void sendPasswordByEmail(String email, String password) {
        // 实现发送邮件逻辑
        System.out.println("已发送随机密码到邮箱：" + email);
    }

    // 检查用户名是否符合要求
    private static boolean isValidUsername(String username) {
        return username.length() >= 5;
    }

    // 检查密码是否符合要求
    private static boolean isValidPassword(String password) {
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=[\\]{};:\"\\\\|,.<>/?]]).{8,}$";
        return Pattern.matches(pattern, password);
    }
}