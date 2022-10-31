package util;

import java.util.Scanner;

public class Leitor {
    private static Scanner scanner = new Scanner(System.in);
    private Leitor(){}
    public static Scanner getLeitor(){
        return scanner;
    }
}