package org.example.dictionaryapp;

import java.util.*;

public class DictionaryCommandline {

    private Dictionary dictionary;
    private DictionaryManagement dictionaryManagement;
    private Trie trie;

    // Constructor mặc định
    public DictionaryCommandline() {
        this.dictionary = new Dictionary();
        this.dictionaryManagement = new DictionaryManagement(dictionary, trie);
    }




    public DictionaryCommandline(Dictionary dictionary, Trie trie) {
        this.dictionary = dictionary;
        this.dictionaryManagement = new DictionaryManagement(dictionary, trie);
    }

    // Hiển thị tất cả các từ vựng theo thứ tự alphabet
    public void showAllWords() {
        System.out.println("No | English | Vietnamese");
        System.out.println("--------------------------");
        int count = 0;
        for (Word word : dictionary.getAllWords()) {
            count++;
            System.out.println(count + " | " + word.getWord_target() + " | " + word.getWord_explain());
        }
    }

    // Thực hiện các chức năng cơ bản của từ điển
    public void dictionaryBasic() {
        dictionaryManagement.insertFromCommandline();
        showAllWords();
    }


        // Phương thức dictionarySearch
        public List<String> dictionarySearch(String input) {
            List<String> result = new ArrayList<>();
            for (Word word : dictionary.getAllWords()) {
                // Kiểm tra nếu getWord_target() không trả về một chuỗi
                String wordTarget = word.getWord_target(); // Không cần chuyển đổi sang String
                if (wordTarget.toLowerCase().startsWith(input.toLowerCase())) {
                    result.add(wordTarget + ": " + word.getWord_explain());
                }
            }

            // Sắp xếp kết quả theo thứ tự alphabet
            Collections.sort(result);

            return result;
        }


    private void displayMenu() {
        System.out.println("Welcome to My Application!");
        System.out.println("[0] Exit");
        System.out.println("[1] Add");
        System.out.println("[2] Remove");
        System.out.println("[3] Update");
        System.out.println("[4] Display");
        System.out.println("[5] Lookup");
        System.out.println("[6] Search");
        System.out.println("[7] Game");
        System.out.println("[8] Import from file");
        System.out.println("[9] Export to file");
    }

    // Phương thức chạy ứng dụng với giao diện menu dòng lệnh
    public void dictionaryAdvanced() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        do {
            displayMenu();
            System.out.print("Your action: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    case 1:
                        System.out.print("Nhập từ tiếng Anh: ");
                        String wordTarget = scanner.nextLine();
                        System.out.print("Nhập nghĩa tiếng Việt: ");
                        String wordExplain = scanner.nextLine();
                        dictionaryManagement.addWordFromCommandline(wordTarget, wordExplain);
                        break;
                    case 2:
                        // Xóa từ
                        break;
                    case 3:
                        // Sửa từ
                        break;
                    case 4:
                        // Hiển thị danh sách từ
                        break;
                    case 5:
                        // Tra cứu từ
                        break;
                    case 6:
                        // Tìm kiếm từ
                        break;
                    case 7:
                        // Truy cập phần Game
                        break;
                    case 8:
                        // Nhập danh sách từ vựng từ tệp
                        break;
                    case 9:
                        // Xuất dữ liệu danh sách từ vựng ra tệp
                        break;
                    default:
                        System.out.println("Action not supported.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Action not supported.");
            }
        } while (choice != 0);
    }
}
