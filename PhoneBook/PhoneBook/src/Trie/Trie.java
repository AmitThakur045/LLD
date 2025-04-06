package Trie;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode currNode = root;
        for (char ch: word.toLowerCase().toCharArray()) {
            currNode = currNode.children.computeIfAbsent(ch, k -> new TrieNode());
        }
        currNode.isEndOfWord = true;
    }

    public List<String> search(String word) {
        List<String> results = new ArrayList<>();
        TrieNode currNode = root;
        for (char ch : word.toLowerCase().toCharArray()) {
            currNode = currNode.children.get(ch);
            if (currNode == null) {
                return  results;
            }
        }
        collectWords(currNode, new StringBuilder(word), results);
        return results;
    }

    public void collectWords(TrieNode node, StringBuilder prefix, List<String> results) {
        if (node.isEndOfWord) {
            results.add(prefix.toString());
        }

        node.children.forEach((key, value) -> {
            prefix.append(key);
            collectWords(value, prefix, results);
            prefix.deleteCharAt(prefix.length()-1);
        });
    }
}
