package com.hrishikeshmishra.practices.mixed;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Problem:
 * Trie Implementation
 * ;
 * Tree Node has three attributes:
 * - Character
 * - boolean to check isLeaf
 * - children map
 * ;
 * ;
 * Insert Algorithm
 * - Take root children
 * - Iterate all characters of given string
 * - - Set current_character = string.charAt(i)
 * - - If current_character not exist in children map then
 * - - - Create a trie node for current_character and add to children map
 * - - Get trieNode from children map for current_character
 * - - If current_character is last character in given string then
 * - - - Set trieNode.isLeaf = true
 * - - Set children = trieNode.children
 * ;
 * ;
 * Search Node Algorithm
 * - Get children of root
 * - Set trieNode = null
 * - Iterate all characters of given string
 * - - Set current_character = string.charAt(i)
 * - - If current_character not exist in children map then
 * - - - return null
 * - - Else
 * - - - Set trieNode = children.get(current_character)
 * - - - Set children = trieNode.children
 * - Return trieNode
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/trie-implementation/
 */
public class Trie {

    private static class TrieNode {
        private char character;
        private Map<Character, TrieNode> children;
        private boolean isLeaf;

        public TrieNode() {
            children = new HashMap<>();
        }

        public TrieNode(char character) {
            this.character = character;
            children = new HashMap<>();
        }

        public TrieNode(char character, Map<Character, TrieNode> children, boolean isLeaf) {
            this.character = character;
            this.children = children;
            this.isLeaf = isLeaf;
        }

        public char getCharacter() {
            return character;
        }

        public void setCharacter(char character) {
            this.character = character;
        }

        public Map<Character, TrieNode> getChildren() {
            return children;
        }

        public void setChildren(Map<Character, TrieNode> children) {
            this.children = children;
        }

        public boolean isLeaf() {
            return isLeaf;
        }

        public void setLeaf(boolean leaf) {
            isLeaf = leaf;
        }

        @Override
        public String toString() {
            return "TrieNode{" +
                    "character=" + character +
                    ", isLeaf=" + isLeaf +
                    '}';
        }
    }

    public TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String str) {
        Map<Character, TrieNode> children = this.root.children;
        for (int index = 0; index < str.length(); index++) {
            char c = str.charAt(index);

            if (!children.containsKey(c)) {
                children.put(c, new TrieNode(c));
            }

            TrieNode temp = children.get(c);

            if (index == str.length() - 1) {
                temp.setLeaf(true);
            }
            children = temp.getChildren();
        }
    }

    public boolean search(String word) {
        TrieNode trieNode = searchNode(word);
        return (!Objects.isNull(trieNode) && trieNode.isLeaf());
    }

    public boolean startWith(String prefix) {
        TrieNode trieNode = searchNode(prefix);
        return !Objects.isNull(trieNode);
    }

    private TrieNode searchNode(String str) {
        Map<Character, TrieNode> children = this.root.children;
        TrieNode node = null;
        for (int i = 0; i < str.length(); i++) {

            if (children.containsKey(str.charAt(i))) {
                node = children.get(str.charAt(i));
                children = node.children;
            } else {
                return null;
            }
        }
        return node;
    }
}


class TrieTest {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("hrishikesh");
        System.out.println("Is prefix \"hrishi\"  exist? " + trie.startWith("hrishi"));
        System.out.println("Is \"hrishik\" exist? " + trie.search("hrishik"));
        System.out.println("Is \"hrishikesh\" exist? " + trie.search("hrishikesh"));
        System.out.println("Is \"kumar\" exist? " + trie.search("kumar"));
    }
}