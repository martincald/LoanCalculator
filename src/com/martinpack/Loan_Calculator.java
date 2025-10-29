package com.martinpack;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Loan Amount (R$5K - R$1M) : ");
        int ammount;
        while (true){
            ammount = scanner.nextInt();
            if (ammount >= 5_000 && ammount <= 1_000_000)
                break;
            System.out.println("Invalid amount. Chose a value between R$5K - R$1M");
        }

        System.out.println("Loan Anual intrest rate: ");
        float anualIntrestRate;
        float monthlyIntrestRate;
        while (true) {
            anualIntrestRate = scanner.nextFloat();
            if (anualIntrestRate > 0 && anualIntrestRate <= 30)
                break;
            System.out.println("Invalid amount. Chose a value bigger than 0 and smaller than 30.");
        }
        monthlyIntrestRate = anualIntrestRate / PERCENT / MONTHS_IN_YEAR;

        System.out.println("Loan Period (Years): ");
        byte years;
        int numberOfPayments;
        while (true) {
            years = scanner.nextByte();
            if (years >= 1 && years <= 80)
                break;
            System.out.println("Invalid amount. Chose a value bigger than 1 and smaller than 80.");
        }
        numberOfPayments = years * MONTHS_IN_YEAR;

        double loan = ammount * (monthlyIntrestRate * Math.pow(1 + monthlyIntrestRate, numberOfPayments)) / (Math.pow(1 + monthlyIntrestRate, numberOfPayments) - 1);
        double totalPayments = loan * numberOfPayments;

        String loanFormated = NumberFormat.getCurrencyInstance().format(loan);
        String totalPaymentsFormated = NumberFormat.getCurrencyInstance().format(totalPayments);
        System.out.println("Total Payment: " + totalPaymentsFormated);
        System.out.println("Monthly Payments: " + loanFormated);
    }
}
