package org.example.dictionaryapp;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private List<Word> words;

    public Dictionary() {
        this.words = new ArrayList<>();
    }
    // Thêm một từ vào từ điển
    public void addWord(Word word) {
        words.add(word);
    }

    // Xóa một từ khỏi từ điển
    public void removeWord(Word word) {
        words.remove(word);
    }

    // Tìm một từ trong từ điển theo chỉ số
    public Word getWord(int index) {
        if (index >= 0 && index < words.size()) {
            return words.get(index);
        }
        return null;
    }

    // Trả về số lượng từ trong từ điển
    public int getSize() {
        return words.size();
    }

    // Trả về mảng chứa tất cả các từ trong từ điển
    public ArrayList<Word> getAllWords() {
        return new ArrayList<>(words); // Trả về một bản sao của mảng để tránh sửa đổi trực tiếp
    }

    // Xóa tất cả các từ khỏi từ điển
    public void clear() {
        words.clear();
    }

    public List<String> getData() {
        List<String> data = new ArrayList<>();
        for (Word word : words) {
            data.add(word.getWord_target()); // Đây là một ví dụ, bạn có thể điều chỉnh nó tùy theo cấu trúc của lớp Word
        }
        return data;
    }
}
