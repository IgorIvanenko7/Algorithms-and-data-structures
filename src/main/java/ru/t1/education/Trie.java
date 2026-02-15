package ru.t1.education;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Trie {
    /**
     *  Три строки для префиксного дерева
     */
    static String STR_PREF_TRIE_1 = "APP";
    static String STR_PREF_TRIE_2 = "APPLE";
    static String STR_PREF_TRIE_3 = "APPLY";
    static String[] STR_WORDS = {STR_PREF_TRIE_1, STR_PREF_TRIE_2, STR_PREF_TRIE_3};

    public static void main(String[] args) {
            // Создаем инстанс дерева
            var prefTrie = new PrefixTrie();
            // Добавляем слова в дерево
            Arrays.stream(STR_WORDS).forEach(prefTrie::insert);
            // Поиск полного слова
            System.out.println(prefTrie.search(STR_PREF_TRIE_3)
                    ? "Слово найдено" : "Слово не найдено");
            // Поиск слов с заданного префикса -> вывод
            prefTrie.searchPrefix("APPL").forEach(System.out::println);
    }
}


class PrefixTrie {
    private final TrieNode root = new TrieNode();
    /**
     * Вставка слова в дерево
     */
    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toLowerCase().toCharArray()) {
            int index = ch - 'a';
            // Если в массиве по этому индексу пусто — создаем новый узел
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    /**
     * Поиск полного слова
     */
    public boolean search(String word) {
        TrieNode node = findNode(word);
        return node != null && node.isEndOfWord;
    }

    /**
     * Поиск всех слов, начинающихся с префикса
     */
    public List<String> searchPrefix(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode prefixNode = findNode(prefix);

        if (prefixNode != null) {
            // Если префикс существует, начинаем собирать слова от этого узла
            backtrack(prefixNode, new StringBuilder(prefix.toLowerCase()), results);
        }
        return results;
    }

    /**
     * Вспомогательный метод для навигации к конкретному узлу.
     */
    private TrieNode findNode(String str) {
        TrieNode current = root;
        for (char ch : str.toLowerCase().toCharArray()) {
            int index = ch - 'a';
            if (current.children[index] == null) {
                return null;
            }
            current = current.children[index];
        }
        return current;
    }

    /**
     * Рекурсивный обход -> сбор слов
     */
    private void backtrack(TrieNode node,
                           StringBuilder currentWord, List<String> results) {
        // Признак конеца слова -> добавление в список
        if (node.isEndOfWord) {
            results.add(currentWord.toString());
        }

        // Проверяем все 26 возможных веток
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                char ch = (char) ('a' + i); // Превращаем индекс обратно в символ
                currentWord.append(ch);
                backtrack(node.children[i], currentWord, results);
                currentWord.setLength(currentWord.length() - 1); // Удаляем последний символ (откат)
            }
        }
    }
}

class TrieNode {
    // Массив узлов префиксного дерева.
    // Размер 26 для латинских букв 'a'-'z'
    TrieNode[] children = new TrieNode[26];
    boolean isEndOfWord = false;
}