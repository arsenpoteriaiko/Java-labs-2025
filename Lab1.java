public class Main {
    public static void main(String[] args) {
        int c5 = 12 % 5;
        int c7 = 12 % 7;
        int c11 = 12 % 11;
        System.out.printf("c5 = %d c7 = %d c11 = %d \n", c5, c7, c11);

        //Дія з матрицею: C = A+B
        //Тип елементів: char
        //Дія з матрицею С: Обчислити суму найменших елементів кожного рядка матриці

        MatrixCharOperations.calculateMatrix();
    }
}

class MatrixCharOperations {
    public static void calculateMatrix() {
        try {
            char[][] A = {
                    {'A', 'B', 'C'},
                    {'D', 'E', 'F'},
                    {'G', 'H', 'I'}
            };

            char[][] B = {
                    {'a', 'b', 'c'},
                    {'d', 'e', 'f'},
                    {'g', 'h', 'i'}
            };

            if (A.length != B.length || A[0].length != B[0].length) {
                throw new IllegalArgumentException("Матриці мають різні розміри!");
            }

            char[][] C = new char[A.length][A[0].length];
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[i].length; j++) {
                    C[i][j] = (char) (A[i][j] + B[i][j]);
                }
            }

            System.out.println("Матриця C = A + B:");
            printMatrix(C);

            int sumOfMins = 0;
            for (char[] row : C) {
                char min = row[0];
                for (char value : row) {
                    if (value < min) {
                        min = value;
                    }
                }
                sumOfMins += min;
            }

            System.out.println("Сума найменших елементів кожного рядка матриці C = " + sumOfMins);

        } catch (IllegalArgumentException e) {
            System.err.println("Помилка: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Невідома помилка: " + e.getMessage());
        }
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            for (char value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}