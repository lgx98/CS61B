import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } //Uncomment this class once you've created your Palindrome class. */

    @Test
    public void testisPalindrome() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("A"));
        assertTrue(palindrome.isPalindrome("tenet")); // nice movie!
        assertFalse(palindrome.isPalindrome("Palindrome"));
        assertFalse(palindrome.isPalindrome("Noon"));

        CharacterComparator cut = new OffByOne(); // Class-Under-Test
        assertTrue(palindrome.isPalindrome("", cut));
        assertTrue(palindrome.isPalindrome("B", cut));
        assertTrue(palindrome.isPalindrome("flake", cut));
        assertFalse(palindrome.isPalindrome("Cab", cut));
        assertFalse(palindrome.isPalindrome("TNT", cut));
    }
}
