package com.example.se2einzelphase;

public class BinaryConverter {

    public String convertSumToBinary(int[] numbers) {
        // Calculate the sum of the numbers
        int sum = calculateSum(numbers);

        // Convert the sum to binary
        return Integer.toBinaryString(sum);
    }

    private int calculateSum(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

}
