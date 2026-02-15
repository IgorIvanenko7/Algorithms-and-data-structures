package ru.t1.education;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrieTest {

    static String STR_PREF_TRIE_1 = "APP";
    static String STR_PREF_TRIE_2 = "APPLICATION";
    static String STR_PREF_TRIE_3 = "APPROVE";
    static String STR_PREF_TRIE_4 = "CAR";
    static String STR_PREF_TRIE_5 = "COMPUTER";
    static String[] STR_WORDS = {STR_PREF_TRIE_1, STR_PREF_TRIE_2, STR_PREF_TRIE_3, STR_PREF_TRIE_4, STR_PREF_TRIE_5};

    /**
     *  Тестовый метод добавления параллельной ветки -> Два дерева
     */
    @Test()
    public void doubleTrie() {
        var prefTrie = new PrefixTrie();
        Arrays.stream(STR_WORDS).forEach(prefTrie::insert);
        assertEquals(2, prefTrie.searchPrefix("C").size(),
                "Все слова второго дерева не найдены");
    }

    /**
     *  Поиск не существующего слова
     */
    @Test
    public void detectNotSubsequence() {
        var prefTrie = new PrefixTrie();
        Arrays.stream(STR_WORDS).forEach(prefTrie::insert);
        assertEquals(0, prefTrie.searchPrefix("X").size(),
                "Алгоритм поиска не верный");
    }
}
