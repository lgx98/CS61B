public class Palindrome {
    /**
     * Converts a String to a Deque(actually, ArrayDeque)
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> ret = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            ret.addLast(word.charAt(i));
        }
        return ret;
    }

    /**
     * Checks if the input word is a palindrome.
     */
    public boolean isPalindrome(String word) {
        return isDequePalindrome(wordToDeque(word));
    }

    /**
     * "elegant recursion"
     */
    private boolean isDequePalindrome(Deque<Character> input) {
        if (input.size() > 1) {
            if (input.removeFirst() != input.removeLast()) {
                return false;
            } else {
                return isDequePalindrome(input);
            }
        } else {
            return true;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> input = wordToDeque(word);
        while (input.size() > 1) {
            if (!cc.equalChars(input.removeFirst(), input.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
