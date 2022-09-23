import java.util.Random;
import java.util.Scanner;

public class BullsAndCows {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int suggestion = 0;
        int[] number = generateNumber();
        boolean win = false;


        while (!win) {
            try {
                suggestion = Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e){
                System.out.println("Invalid input!");
                suggestion = 0;
            }
            if (suggestion>9999){
                System.out.println("Invalid input!");
                suggestion = 0;
            }
            win = checkSuggestion(suggestion, number);
        }
        System.out.println("You win!");
    }

    private static int[] generateNumber() {
        Random random = new Random();
        int[] number = new int[4];
        boolean[] used = new boolean[10];
        for (int i = 0; i < 4; i++) {
            number[i] = random.nextInt(10);
            while (used[number[i]-1]){
                number[i] = random.nextInt(10);
            }
            used[number[i]-1] = true;
        }


        return number;
    }

    private static boolean checkSuggestion(int suggestion, int[] number) {
        boolean win  = false;
        int[] suggestDigits = new int[4];
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < 4; i++) {
            suggestDigits[i] = suggestion % 10;
            suggestion /= 10;
            if (number[i] == suggestDigits[i]) {
                bulls++;
            }
            if (bulls==4){
                win = true;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (number[i]==suggestDigits[j]){
                    cows++;
                }
            }
        }
        cows -=bulls;
        System.out.printf("You have %d bulls and %d cows.%n", bulls,cows);
        return win;
    }
}