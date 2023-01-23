import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DictionaryImpl implements DictionaryService {

    public List<String> getEnglishDictionary() {
        return null;
    }

    public boolean isEnglishWord(String word) {
        return false;
    }

    /**
     * This method obtain all the possible combinations and words with the chars of the input word
     *
     * @param length This is the quantity of letters that we want the permutations have
     * @param word   This is the input word which we want to permute
     * @return The method returns all the possible word permutations
     */

    public List<String> getAllPossibleCombinations(int length, String word) {
        final char[] chars = word.toLowerCase().toCharArray();
        final double NUMBER_OF_PERMUTATIONS = Math.pow(chars.length, length);

        List<String> words = new ArrayList<>();

        char[] temporal = new char[length];
        Arrays.fill(temporal, '0');

        int i = 0;

        while (i < NUMBER_OF_PERMUTATIONS) {
            int n = i;
            for (int k = 0; k < length; k++) {
                temporal[k] = chars[n % chars.length];
                n /= chars.length;
            }

            words.add(String.valueOf(temporal));
            i++;
        }
        return words;
    }

    /**
     * Find all the possible english words (based on our mocked dictionary) that can be written with the letters
     * or the input word
     * @param englishDictionary this is the english dictionary that is mocked
     * @param word the word with the letters with which we will find for the possible words
     * @return a list with all possible words than can be written
     */
    public List<String> findEnglishWords(List<String> englishDictionary, String word) {
        List<String> foundWords;
        List<String> finalList = new ArrayList<>();

        for (int i = 1; i <= word.length(); i++) {
            foundWords = getAllPossibleCombinations(i, word);
            foundWords.retainAll(englishDictionary);
            finalList.addAll(foundWords);
        }
        return finalList;
    }
}
