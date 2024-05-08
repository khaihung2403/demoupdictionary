package org.example.dictionaryapp;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private TrieNode root = new TrieNode();
    ArrayList<String> words = new ArrayList();
    TrieNode prefix_root;
    String cur_prefix;

    public Trie() {
    }

    public void insertWordToTrie(Word word) {
        TrieNode current = this.root;

        for(int i = 0; i < word.getWord_target().length(); ++i) {
            char ch = word.getWord_target().charAt(i);
            TrieNode node = (TrieNode)current.child.get(ch);
            if (node == null) {
                node = new TrieNode();
                current.child.put(ch, node);
            }

            current = node;
        }

        current.is_complete_word = true;
        current.value = word.getWord_explain();
    }

    public boolean searchInTrie(String search_target) {
        TrieNode current = this.root;

        for(int i = 0; i < search_target.length(); ++i) {
            char ch = search_target.charAt(i);
            TrieNode node = (TrieNode)current.child.get(ch);
            if (node == null) {
                return false;
            }

            current = node;
        }

        return !current.child.isEmpty() || current.is_complete_word;
    }

    public String lookup(String search_target) {
        String result = "";
        TrieNode current = this.root;

        for(int i = 0; i < search_target.length(); ++i) {
            char ch = search_target.charAt(i);
            current = (TrieNode)current.child.get(ch);
            if (current == null) {
                return "";
            }
        }

        if (current.is_complete_word) {
            result = current.value;
        }

        return result;
    }

    public List<String> suggestKeywords(String prefix) {
        List<String> suggestions = new ArrayList<>();
        TrieNode current = this.root;

        // Traverse to the node representing the prefix
        for (int i = 0; i < prefix.length(); ++i) {
            char ch = prefix.charAt(i);
            TrieNode node = current.child.get(ch);
            if (node == null) {
                return suggestions; // Prefix not found
            }
            current = node;
        }

        // Collect all words under the prefix
        collectWords(current, prefix, suggestions);
        return suggestions;
    }

    private void collectWords(TrieNode node, String prefix, List<String> suggestions) {
        if (node == null) return;

        if (node.is_complete_word) {
            suggestions.add(prefix);
        }

        for (char ch : node.child.keySet()) {
            collectWords(node.child.get(ch), prefix + ch, suggestions);
        }
    }
    public void deleteWordFromTrie(String wordToDelete) {
        deleteHelper(root, wordToDelete, 0);
    }

    private boolean deleteHelper(TrieNode current, String wordToDelete, int index) {
        if (index == wordToDelete.length()) {
            // If we have reached the end of the wordToDelete
            // Mark the current node as not a complete word
            if (!current.is_complete_word) {
                return false; // Word not found
            }
            current.is_complete_word = false;
            return current.child.isEmpty(); // Return true if no child nodes
        }

        char ch = wordToDelete.charAt(index);
        TrieNode node = current.child.get(ch);
        if (node == null) {
            return false; // Word not found
        }

        boolean shouldDeleteNode = deleteHelper(node, wordToDelete, index + 1);

        // If shouldDeleteNode is true, then delete the mapping of current character and TrieNode reference from map.
        if (shouldDeleteNode) {
            current.child.remove(ch);
            // Return true if no child nodes and current node is not the end of another word.
            return current.child.isEmpty() && !current.is_complete_word;
        }

        return false;
    }
}

