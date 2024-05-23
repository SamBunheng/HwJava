package view;

import java.util.Scanner;

public class View {
    public static int menu(){
        System.out.println("=".repeat(40));
        System.out.println("1.Add new Course");
        System.out.println("2.List Courses");
        System.out.println("3.Find courses by ID");
        System.out.println("4.Find courses by Title");
        System.out.println("5.Remove courses by ID");
        System.out.println("0.Exit");
        System.out.println("=".repeat(40));
        System.out.print("[+]Insert option: ");
        return new Scanner(System.in).nextInt();
    }
}
