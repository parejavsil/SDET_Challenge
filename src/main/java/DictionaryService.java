import java.util.List;

public interface DictionaryService {
    List<String> getEnglishDictionary();

    boolean isEnglishWord(String word);
}
