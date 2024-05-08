package org.example.dictionaryapp;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.example.dictionaryapp.Dictionary;

public class DictionaryManagement {
    private Dictionary dictionary;
    private Trie trie;

    public DictionaryManagement(Dictionary dictionary) {
        this.dictionary = dictionary;
        this.trie = new Trie();
    }

    public DictionaryManagement(Dictionary dictionary, Trie trie) {
        this.dictionary = dictionary;
        this.trie = trie;
    }

    // Hàm nhập từ điển từ dòng lệnh
    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Nhập số lượng từ vựng: ");
        int numWords = sc.nextInt();
        sc.nextLine(); // Đọc ký tự xuống dòng thừa

        for (int i = 0; i < numWords; i++) {
            System.out.println("Nhập từ tiếng Anh thứ " + (i + 1) + ":");
            String englishWord = sc.nextLine();

            System.out.println("Nhập giải thích bằng tiếng Việt:");
            String vietnameseDefinition = sc.nextLine();

            Word word = new Word(englishWord, vietnameseDefinition);
            dictionary.addWord(word);
        }

        System.out.println("Đã nhập thành công " + numWords + " từ vựng.");
    }

    //Ham doc du lieu tu tep txt
    public void insertFromFile(String filename) {
        try (BufferedReader in = new BufferedReader(new FileReader("dictionary/data/dictionaries.txt"))) {
            String str;
            while ((str = in.readLine()) != null) {
                String[] parts = str.split("\t", 2); // Tách từ và nghĩa bằng dấu tab

                if (parts.length == 2) {
                    String word_target = parts[0].trim(); // Từ tiếng Anh
                    String word_explain = parts[1].trim(); // Nghĩa tiếng Việt

                    Word word = new Word(word_target, word_explain);
                    dictionary.addWord(word);
                    trie.insertWordToTrie(word);
                } else {
                    // Xử lý trường hợp dòng không hợp lệ
                    System.out.println("Dòng không hợp lệ: " + str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String dictionarySearcher(String wordSearch) {
        for (Word word : dictionary.getAllWords()) {
            if (word.getWord_target().trim().equalsIgnoreCase(wordSearch.trim())) {
                return word.getWord_target() + ": " + word.getWord_explain();
            }
        }
        return "Không tìm thấy từ trong từ điển.";
    }

    public List<String> suggestKeywords(String prefix) {
        return trie.suggestKeywords(prefix);
    }

    // Hiển thị gợi ý từ khóa cho người dùng
    public void disctionarylookup(List<String> suggestions) {
        if (suggestions.isEmpty()) {
            System.out.println("Không có gợi ý từ khóa.");
        } else {
            System.out.println("Các gợi ý từ khóa:");
            for (String suggestion : suggestions) {
                System.out.println("- " + suggestion);
            }
        }
    }

    public void addWordFromCommandline(String wordTarget, String wordExplain) {
        Word word = new Word(wordTarget, wordExplain);
        dictionary.addWord(word);
        trie.insertWordToTrie(word);
        System.out.println("Đã thêm từ thành công.");
    }


    public void dictionaryExportToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dictionary/data/dictionaries.txt",true))) {
            List<Word> allWords = dictionary.getAllWords();
            for (Word word : allWords) {
                writer.write(word.getWord_target() + "\t" + word.getWord_explain() + "\n");

            }
            System.out.println("Xuất dữ liệu từ điển ra tệp thành công.");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi xuất dữ liệu từ điển ra tệp.");
            e.printStackTrace();
        }
    }


    // Hàm sửa từ trong từ điển từ dòng lệnh
    public void editWordFromCommandline(String wordTarget, String newDefinition) {
        Word wordToEdit = null;
        for (Word word : dictionary.getAllWords()) {
            if (word.getWord_target().equalsIgnoreCase(wordTarget)) {
                wordToEdit = word;
                break;
            }
        }

        if (wordToEdit != null) {
            wordToEdit.setWord_explain(newDefinition);
            System.out.println("Đã sửa từ thành công.");
        } else {
            System.out.println("Không tìm thấy từ cần sửa trong từ điển.");
        }
    }


    // Hàm xóa từ khỏi từ điển từ dòng lệnh
    /*public void deleteWordFromCommandline(String wordTarget, String wordExplain) {
        Word word = new Word(wordTarget, wordExplain);
        dictionary.removeWord(word);
        trie.removeWordFromTrie(wordTarget);
        System.out.println("Đã xóa từ thành công.");
    }*/
    // Hàm xóa từ khỏi từ điển từ dòng lệnh
    public void deleteWordFromCommandline(String wordTarget) {
        Word wordToDelete = null;
        for (Word word : dictionary.getAllWords()) {
            if (word.getWord_target().equalsIgnoreCase(wordTarget)) {
                wordToDelete = word;
                break;
            }
        }

        if (wordToDelete != null) {
            dictionary.removeWord(wordToDelete);
            trie.deleteWordFromTrie(wordTarget);
            System.out.println("Đã xóa từ thành công.");
        } else {
            System.out.println("Không tìm thấy từ cần xóa trong từ điển.");
        }
    }

}