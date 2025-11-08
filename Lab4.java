import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int c17 = 12 % 17;
        System.out.printf("c17 = %d%n", c17);

        //Дія з текстом: Відсортувати слова заданого тексту за кількістю входжень визначеного символу в них.

        TextProcessor.sortWords();
    }
}

//Клас для представлення літери.
class Letter {
    private final char value;

    public Letter(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

//Клас для представлення слова, що складається з літер.
class Word {
    private final List<Letter> letters;

    public Word(String word) {
        letters = new ArrayList<>();
        for (char ch : word.toCharArray()) {
            letters.add(new Letter(ch));
        }
    }

    //Підрахунок входжень символу в слові (ігноруючи регістр).
    public int countChar(char target) {
        int count = 0;
        for (Letter letter : letters) {
            if (Character.toLowerCase(letter.getValue()) == Character.toLowerCase(target)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Letter letter : letters) {
            sb.append(letter.getValue());
        }
        return sb.toString();
    }
}

//Клас для представлення розділового знаку.
class Punctuation {
    private final char value;

    public Punctuation(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

//Клас для представлення речення, що складається зі слів і розділових знаків.
class Sentence {
    private final List<Object> elements; // Word або Punctuation

    public Sentence(String sentence) {
        elements = new ArrayList<>();
        StringBuilder wordBuffer = new StringBuilder();
        for (char ch : sentence.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                wordBuffer.append(ch);
            } else if (!Character.isWhitespace(ch)) {
                if (wordBuffer.length() > 0) {
                    elements.add(new Word(wordBuffer.toString()));
                    wordBuffer.setLength(0);
                }
                elements.add(new Punctuation(ch));
            } else {
                if (wordBuffer.length() > 0) {
                    elements.add(new Word(wordBuffer.toString()));
                    wordBuffer.setLength(0);
                }
            }
        }
        if (wordBuffer.length() > 0) {
            elements.add(new Word(wordBuffer.toString()));
        }
    }

    public List<Word> getWords() {
        List<Word> words = new ArrayList<>();
        for (Object element : elements) {
            if (element instanceof Word) {
                words.add((Word) element);
            }
        }
        return words;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object element : elements) {
            sb.append(element.toString());
            if (element instanceof Word) {
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }
}

//Клас для представлення тексту, що складається з речень.
class Text {
    private final List<Sentence> sentences;

    public Text(String rawText) {
        // Замінюємо послідовності пробілів та табуляцій на один пробіл
        String normalizedText = rawText.replaceAll("\\s+", " ").trim();
        sentences = new ArrayList<>();
        String[] splitSentences = normalizedText.split("(?<=[.!?])");
        for (String s : splitSentences) {
            sentences.add(new Sentence(s.trim()));
        }
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Sentence sentence : sentences) {
            sb.append(sentence.toString()).append(" ");
        }
        return sb.toString().trim();
    }
}

//Клас, що виконує сортування слів тексту за кількістю входжень заданого символу.
class TextProcessor {
    public static void sortWords() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введіть текст: ");
            String inputText = scanner.nextLine();
            if (inputText.isEmpty()) {
                throw new IllegalArgumentException("Текст не може бути пустим");
            }

            System.out.print("Введіть символ, за яким сортувати слова: ");
            String inputChar = scanner.nextLine();
            if (inputChar.isEmpty()) {
                throw new IllegalArgumentException("Символ не може бути пустим");
            }
            char targetChar = inputChar.charAt(0);

            Text text = new Text(inputText);

            System.out.println("\nПочатковий текст:");
            System.out.println(text);

            // Збираємо всі слова з тексту
            List<Word> words = new ArrayList<>();
            for (Sentence sentence : text.getSentences()) {
                words.addAll(sentence.getWords());
            }

            // Сортування слів за кількістю входжень символу
            words.sort(Comparator.comparingInt((Word w) -> w.countChar(targetChar))
                    .reversed()
                    .thenComparing(Word::toString, String.CASE_INSENSITIVE_ORDER));

            System.out.println("\nВідсортовані слова (за кількістю '" + targetChar + "'):");
            for (Word word : words) {
                System.out.print(word + " ");
            }
            System.out.println();

        } catch (Exception e) {
            System.err.println("Помилка: " + e.getMessage());
        }
    }
}
