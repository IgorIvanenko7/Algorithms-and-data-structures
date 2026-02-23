package ru.t1.education;

import java.util.HashSet;
import java.util.Set;

public class StreamBenchmark {
    public static void main(String[] args) {
        int size = 5_000_000;
        Set<String> data = new HashSet<>(size);

        for (int i = 0; i < size; i++) {
            data.add("Item_" + i);
        }

        System.out.println("Начинаем замеры для " + size + " элементов...");

        // 1. Последовательная обработка
        long start = System.currentTimeMillis();
        long countSeq = data.stream()
                .filter(StreamBenchmark::heavyTask)
                .count();
        long end = System.currentTimeMillis();
        System.out.println("Последовательный стрим: " + (end - start) + " мс. Найдено: " + countSeq);

        // 2. Параллельная обработка
        start = System.currentTimeMillis();
        long countPar = data.parallelStream() // или data.stream().parallel()
                .filter(StreamBenchmark::heavyTask)
                .count();
        end = System.currentTimeMillis();
        System.out.println("Параллельный стрим:    " + (end - start) + " мс. Найдено: " + countPar);
    }

    // Имитация тяжелой работы
    private static boolean heavyTask(String s) {
        double result = 0;
        for (int i = 0; i < 100; i++) {
            result += Math.sin(i) * Math.cos(i);
        }
        return s.contains("123") && result > -100;
    }


}
