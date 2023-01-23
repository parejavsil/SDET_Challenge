import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class WordTest {

    private DictionaryService dictionaryService;
    List<String> dictionaryList;


    /*
     * Create a list of words based on the EnglishDictionaryWords file in order to mock the dictionary service
     *
     * @return list of Strings with the content of the dictionary
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


    /*
     * Validate if given word exists in the dictionary (EnglishWords in this case) to mock the isEnglishWord function
     * @param word
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
        if (!isAnEnglishWord) System.out.println(word + " is not a valid english word");
        return isAnEnglishWord;
    }

    @Before
    public void setUp() {
        dictionaryService = mock(DictionaryImpl.class);

        when(dictionaryService.getEnglishDictionary()).thenReturn(createDictionaryArray());
        dictionaryList = dictionaryService.getEnglishDictionary();
    }

    @Test
    public void validateWorkingWord () {
        DictionaryImpl dictionary= new DictionaryImpl();

        String wordInput="ajiac";
        when(dictionaryService.isEnglishWord(wordInput)).thenReturn(isThisEnglishWord(wordInput));
        System.out.println(dictionary.findEnglishWords(dictionaryList,wordInput));
    }
}
