package com.example.se2einzelphase;

public class BinaryConverter {

    public String convertSumToBinary(int numbers) {

        int [] convNumbers = convertDigitsToArray(numbers);

        // Calculate the sum of the numbers
        int sum = calculateSum(convNumbers);

        // Convert the sum to binary
        String binaryArray = toBinary(sum);

        return binaryArray;
    }

    private int calculateSum(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

    public static String toBinary(int number) {
        if (number == 0) {
            return "0";
        }

        StringBuilder binary = new StringBuilder();
        while (number > 0) {
            binary.insert(0, number % 2);
            number /= 2;
        }
        return binary.toString();
    }

    public int[] convertDigitsToArray(int n) {

        int [] temp = new int[String.valueOf(n).length()]; // Calculate the length of digits
        int i = String.valueOf(n).length()-1 ;  // Initialize the value to the last index

        do {
            temp[i] = n % 10;
            n = n / 10;
            i--;
        } while(n>0);

        return temp;
    }
}

