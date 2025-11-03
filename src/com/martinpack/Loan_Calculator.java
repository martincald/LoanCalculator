package com.martinpack;

import java.text.NumberFormat;
import java.util.Scanner;

public class Loan_Calculator {
    static final byte MONTHS_IN_YEAR = 12;
    static final byte PERCENT = 100;

    public static void main(String[] args) {

        int loanAmount = (int) readNumber("Loan Amount: ", 5000, 1_000_000);
        float anualIntrestRate = (float) readNumber("Loan Anual intrest rate: " , 1, 30);
        byte years = (byte) readNumber("Loan period (years): ", 1, 80);

        double loan = calculateLoan(loanAmount, anualIntrestRate, years);
        paymentSchedule(loanAmount, years, anualIntrestRate);
        double totalPayment = loan * (years * 12); // Years * Months in year

        String loanFormated = NumberFormat.getCurrencyInstance().format(loan);
        String totalPaymentsFormated = NumberFormat.getCurrencyInstance().format(totalPayment);
        System.out.println("-----------------\nTotal Payment: " + totalPaymentsFormated);
        System.out.println("Monthly Payments: " + loanFormated + "\n-----------------");
    }

    public static double readNumber(String prompt, double min, double max){
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if (value >= min && value <= max)
                break;
            System.out.println("Invalid amount. Enter a value between " + min + " and " + max);
        }
        return value;
    }

    public static double calculateLoan(int loanAmount, float anualInterestRate, byte years) {

        float monthlyIntrestRate = anualInterestRate / PERCENT / MONTHS_IN_YEAR;
        short numberOfPayments = (short)(years * MONTHS_IN_YEAR);

        return loanAmount * (monthlyIntrestRate * Math.pow(1 + monthlyIntrestRate,
                numberOfPayments)) / (Math.pow(1 + monthlyIntrestRate, numberOfPayments) - 1);
    }

    public static void paymentSchedule(int loanAmount, byte years, float anualIntrestRate) {

        int numberOfPayments = (years * MONTHS_IN_YEAR);
        float monthlyInterestRate = anualIntrestRate / PERCENT / MONTHS_IN_YEAR;

        double remainingBalance;
        int paymentsMade = 1;
        System.out.println("\n-----------------\nPayment schedule\n-----------------");
        while (true) {
            remainingBalance = loanAmount * (Math.pow(1 + monthlyInterestRate, numberOfPayments)
                    - Math.pow(1 + monthlyInterestRate, paymentsMade))
                    / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);
            paymentsMade++;
            if (remainingBalance == 0)
                break;
            System.out.println("Remaining balance in month number " + paymentsMade + " is "
                    + NumberFormat.getCurrencyInstance().format(remainingBalance));
        }
    }
}