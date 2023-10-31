package test;

import java.util.Scanner;

public class MyTest3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入a、b和c的值：");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        scanner.close();

        double delta = b * b - 4 * a * c;
        if (delta > 0) {
            double root1 = (-b + Math.sqrt(delta)) / (2 * a);
            double root2 = (-b - Math.sqrt(delta)) / (2 * a);
            System.out.println("方程有两个不相等的实根：x1 = " + root1 + ", x2 = " + root2);
        } else if (delta == 0) {
            double root = -b / (2 * a);
            System.out.println("方程有两个相等的实根：x1 = x2 = " + root);
        } else {
            System.out.println("方程无实根");
        }
    }
}
