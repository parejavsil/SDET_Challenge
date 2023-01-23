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

    public List<String> findEnglishWords(List<String> englishDictionary, String word) {

        List<String> foundWords = new ArrayList<>();
        List<String> finalList = new ArrayList<>();
        //     List<String> englishDictionary = getEnglishDictionary();

        for (int i = 1; i <= word.length(); i++) {
            foundWords = getAllPossibleCombinations(i, word);
            foundWords.retainAll(englishDictionary);
            finalList.addAll(foundWords);
        }
        return finalList;
    }


    /*Obtaining all possible combinations with the chars of the input word*/

    public List<String> getAllPossibleCombinations(int length, String word) {
        final char[] chars = word.toLowerCase().toCharArray();
        final double NUMBER_OF_PERMUTATIONS = Math.pow(chars.length, length);

        List<String> words = new ArrayList<>(Double.valueOf(
                NUMBER_OF_PERMUTATIONS).intValue());

        char[] temp = new char[length];
        Arrays.fill(temp, '0');

        int i = 0;
        while (i < NUMBER_OF_PERMUTATIONS) {
            int n = i;
            for (int k = 0; k < length; k++) {
                temp[k] = chars[n % chars.length];
                n /= chars.length;
            }

            words.add(String.valueOf(temp));
            i++;
        }
        return words;
    }

}
