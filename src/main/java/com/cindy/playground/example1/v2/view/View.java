package com.cindy.playground.example1.v2.view;

import java.util.Scanner;

public class View {
    private final Scanner scanner = new Scanner(System.in);

    public void print(String message) {
        System.out.println(message);
    }

    public String getString(String message) {
        print(message);
        return scanner.next();
    }
}
