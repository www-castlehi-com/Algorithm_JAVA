import java.util.*;

class Solution {
    
    class WordChanger {
        String word;
        int changeCount;
        
        public WordChanger(String word, int changeCount) {
            this.word = word;
            this.changeCount = changeCount;
        }
    }
    
    private static Set<String>[] alphabets;
    
    public int solution(String begin, String target, String[] words) {
        initializeAlphabets(words, begin);
        
        return getChangeWordCount(words, begin, target);
    }
    
    private void initializeAlphabets(String[] words, String begin) {
        alphabets = new HashSet[begin.length()];
        for (int i = 0; i < begin.length(); i++) {
            alphabets[i] = new HashSet<>();
        }
        
        for (int i = 0 ; i < words.length; i++) {
            String[] wordAlphabet = words[i].split("");
            for (int j = 0; j < wordAlphabet.length; j++) {
                alphabets[j].add(wordAlphabet[j]);
            }
        }
    }
    
    private int getChangeWordCount(String[] words, String begin, String target) {
        Map<String, Boolean> visited = new HashMap<>();
        Queue<WordChanger> queue = new LinkedList<>();
        visited.put(begin, true);
        queue.add(new WordChanger(begin, 0));
        while(!queue.isEmpty()) {
            WordChanger wordChanger = queue.poll();
            
            if (target.equals(wordChanger.word)) {
                return wordChanger.changeCount;
            }
            
            for (int i = 0; i < alphabets.length; i++) {
                for (String alphabet : alphabets[i]) {
                    String newWord = wordChanger.word.substring(0, i) + alphabet + wordChanger.word.substring(i + 1);
                    
                    if (visited.getOrDefault(newWord, false) == false && containsWord(words, newWord)) {
                        visited.put(newWord, true);
                        queue.add(new WordChanger(newWord, wordChanger.changeCount + 1));
                    }
                }
            }
        }
        
        return 0;
    }
    
    private boolean containsWord(String[] words, String target) {
        for (String word : words) {
            if (word.equals(target)) {
                return true;
            }
        }
        return false;
    }
}