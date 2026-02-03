package ru.t1.education;

public class Lcs {
    /**
     *  Две строки для поиска наибольшей общей последовательности (строки)
     */
    static String STR_SEQ_1 = "ABDEFADRFG";
    static String STR_SEQ_2 = "DAFERG";

    public static void main(String[] args) {
        System.out.println("--- Наибольшая общая последовательность ---");
        System.out.println(lcs(STR_SEQ_1, STR_SEQ_2));
    }

    public static String lcs(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        // Создаем матрицу состояний
        int[][] dp = new int[n + 1][m + 1];
        //Переменная собирающая результирующую строку
        StringBuilder resultLcs = new StringBuilder();

        // Заполнение матрицы состояний
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Сборка результирующей строки через StringBuilder (Обратный ход)
        int i = n, j = m;
        while (i > 0 && j > 0) {
            // Если символы совпали — это часть нашей LCS
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                resultLcs.append(s1.charAt(i - 1));
                // Переход по диагонали
                i--;
                j--;
            }
            // Если не совпали, идем в сторону большего соседа
            else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        // Так как мы шли с конца, нужно развернуть строку
        return resultLcs.reverse().toString();
    }
}
