import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int c3 = 12 % 3;
        int c17 = 12 % 17;
        System.out.printf("c3 = %d c17 = %d \n", c3, c17);

        //Тип змінних: StringBuilder
        //Дія з текстом: Відсортувати слова заданого тексту за кількістю входжень визначеного символу в них.

        StringBuilderSorter.sortWords();
    }
}

class StringBuilderSorter {
    public static void sortWords() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введіть текст: ");
            String inputText = scanner.nextLine();
            if (inputText.isEmpty())
                throw new IllegalArgumentException("Текст не може бути пустим");

            System.out.print("Введіть символ, за яким сортувати слова: ");
            String inputChar = scanner.nextLine();
            if (inputChar.isEmpty())
                throw new IllegalArgumentException("Текст не може бути пустим");
            char targetChar = inputChar.charAt(0);

            StringBuilder text = new StringBuilder(inputText);

            System.out.println("\nПочатковий текст:");
            System.out.println(text);
            System.out.println();

            List<StringBuilder> words = splitIntoWords(text);

            words.sort((w1, w2) -> {
                int count1 = countChar(w1, targetChar);
                int count2 = countChar(w2, targetChar);
                if (count1 == count2) {
                    return w1.toString().compareToIgnoreCase(w2.toString());
                }
                return Integer.compare(count2, count1);
            });

            StringBuilder sortedText = new StringBuilder();
            for (StringBuilder word : words) {
                sortedText.append(word).append(" ");
            }

            System.out.println("Відсортовані слова (за кількістю '" + targetChar + "'):");
            System.out.println(sortedText.toString().trim());

        } catch (Exception e) {
            System.err.println("Помилка: " + e.getMessage());
        }
    }

    private static List<StringBuilder> splitIntoWords(StringBuilder text) {
        List<StringBuilder> words = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isWhitespace(ch)) {
                if (current.length() > 0) {
                    words.add(new StringBuilder(current));
                    current.setLength(0);
                }
            } else {
                current.append(ch);
            }
        }
        if (current.length() > 0) {
            words.add(current);
        }
        return words;
    }

    private static int countChar(StringBuilder word, char ch) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (Character.toLowerCase(word.charAt(i)) == Character.toLowerCase(ch)) {
                count++;
            }
        }
        return count;
    }
}