import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class WordTest {

    DictionaryService dictionaryService;
    DictionaryImpl dictionary = new DictionaryImpl();
    List<String> dictionaryList;

    /**
     * Creates a list of words based on the EnglishDictionaryWords file in order to mock the dictionary service
     *
     * @return the list of the dictionary that we want to mock
     */
    static List<String> createDictionaryArray() {
        List<String> dictionaryList = new ArrayList<String>();
        BufferedReader reader;

        try {
            ClassLoader loader = WordTest.class.getClassLoader();
            File file = new File(Objects.requireNonNull(loader.getResource("EnglishDictionaryWords")).getFile());
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                dictionaryList.add(line);
                line = reader.readLine(); // read next line
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionaryList;
    }

    /**
     * Validate if given word exists in the dictionary (EnglishWords in this case) to mock the isEnglishWord function
     *
     * @param word This is the word that we want to verify if exist in the dictionary
     * @return boolean based if the word was found in the dictionary
     */
    public boolean isThisEnglishWord(String word) {
        boolean isAnEnglishWord = false;
        for (String w : dictionaryList) {
            if (w.equals(word.toLowerCase())) {
                isAnEnglishWord = true;
                System.out.println(word + " is a valid english word");
            }
        }
        return isAnEnglishWord;
    }

    @Before
    public void setUp() {
        dictionaryService = mock(DictionaryImpl.class);
        when(dictionaryService.getEnglishDictionary()).thenReturn(createDictionaryArray());
        dictionaryList = dictionaryService.getEnglishDictionary();
    }

    @Test
    public void printCollectionOfWords() {
        String wordInput = "hell";
        when(dictionaryService.isEnglishWord(wordInput)).thenReturn(isThisEnglishWord(wordInput));
        System.out.println(dictionary.findEnglishWords(dictionaryList, wordInput));
    }

    @Test
    public void validateIsThisEnglishWordMethod() {
        String word = "tip";
        assertTrue("The word " + word.toLowerCase() + " is not in english", isThisEnglishWord(word));
    }

    @Test
    public void validateIsEnglishWord() {
        String word = "Train";
        when(dictionaryService.isEnglishWord(word)).thenReturn(isThisEnglishWord(word));
        assertTrue("The word " + word.toLowerCase() + " is not in english", dictionaryService.isEnglishWord(word));
    }

    @Test
    public void validateRawCollectionOfWordsGenerated() {
        String word = "raw";
        System.out.println(dictionary.findEnglishWords(dictionaryList, word));
        assertEquals("The collection is not the expected.",
                Arrays.asList("r", "a", "w", "ar", "wr", "ra", "aa", "wa", "aw", "arr", "war", "ara", "aaa", "awa", "raw", "waw"),
                (dictionary.findEnglishWords(dictionaryList, word)));
    }
}
