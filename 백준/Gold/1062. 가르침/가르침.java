import java.util.*;
import java.io.*;

public class Main {

    private static int ASCII_STANDARD = 'a';
    private static String ALREADY_STUDY = "antic";

    private static int studied = 0;
    private static int characters = 0;
    private static int leftStudyCount = 0;
    private static List<String> words = new ArrayList<>();
    private static int maxReadableCount = 0;
  
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      
      st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken()) - ALREADY_STUDY.length();
      
      if (k >= 0) {
        initialize();
        
        for (int i = 0; i < n; i++) {
          String word = br.readLine();
          words.add(word);
          divideCharacter(word);
        }
        study(k, 0, 0);
      }
      
      System.out.println(maxReadableCount);
  }
  
  private static void initialize() {
    for (int i = 0; i < 5; i++) {
      studied |= 1 << ALREADY_STUDY.charAt(i) - ASCII_STANDARD;
    }
  }
  
  private static boolean isStudied(int target) {
    return (studied & (1 << target)) == 0 ? false : true;
  }
  
  private static boolean isDuplicateCharacter(int target) {
    return (characters & (1 << target)) == 0 ? false : true;
  }
  
  private static void divideCharacter(String word) {
    for (int i = 4; i <= word.length() - 4; i++) {
      int characterIndex = word.charAt(i) - ASCII_STANDARD;
      if (!isStudied(characterIndex) && !isDuplicateCharacter(characterIndex)) {
        characters |= (1 << characterIndex);
        leftStudyCount++;
      }
    }
  }
  
  private static void study(int limitCount, int studyCount, int currentCharacter) {
    if (studyCount == limitCount || studyCount == leftStudyCount) {
      checkReadableWords();
      
      if (maxReadableCount == words.size()) {
        return ;
      }
    } else {
      for (int i = currentCharacter; i < 27; i++) {
        if (isDuplicateCharacter(i)) {
          studied |= (1 << i);
          study(limitCount, studyCount + 1, i + 1);
          studied &= ~(1 << i);
        }
      }
    }
  }
  
  private static void checkReadableWords() {
    int readableCount = 0;
    for (String word : words) {
      boolean canRead = true;
      for (int i = 4; i <= word.length() - 4; i++) {
        if (!isStudied(word.charAt(i) - ASCII_STANDARD)) {
          canRead = false;
          break;
        }
      }
      if (canRead) {
        readableCount++;
      }
    }
    maxReadableCount = Math.max(maxReadableCount, readableCount);
  }
}