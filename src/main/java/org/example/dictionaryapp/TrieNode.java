package org.example.dictionaryapp;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    protected Map<Character, TrieNode> child = new HashMap();
    protected TrieNode parent;
    protected boolean is_complete_word = false;
    protected String value;

    public TrieNode() {
    }
}
