public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> out = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i += 1) {
            out.addLast(word.charAt(i));
        }
        return out;
    }

    private boolean isPalindromeRecursive(Deque<Character> wordDeque) {
        if (wordDeque.size() <= 1) {
            return true;
        }

        if (wordDeque.removeFirst() == wordDeque.removeLast()) {
            return isPalindromeRecursive(wordDeque);
        }
        return false;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeRecursive(wordDeque);
    }

    private boolean isPalindromeRecursiveOffByOne(Deque<Character> wordDeque, CharacterComparator cc) {
        if (wordDeque.size() <= 1) {
            return true;
        }

        if (cc.equalChars(wordDeque.removeFirst(), wordDeque.removeLast())) {
            return isPalindromeRecursiveOffByOne(wordDeque, cc);
        }
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeRecursiveOffByOne(wordDeque, cc);
    }
}