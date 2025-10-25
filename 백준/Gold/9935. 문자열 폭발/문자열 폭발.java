import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String str = br.readLine();
        String bomb = br.readLine();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length() ; i++) {
            stack.push(str.charAt(i));
            
            if (str.charAt(i) == bomb.charAt(bomb.length() - 1) && stack.size() >= bomb.length()) {
                bomb(stack, bomb);
            }
        }
        
        int resultLength = stack.size();
        for(char c : stack) {
            sb.append(c);
        }
        
        System.out.print(resultLength == 0 ? "FRULA" : sb);
    }
    
    private static void bomb(Stack<Character> stack, String bomb) {
        for (int j = 1; j <= bomb.length(); j++) {
            if (stack.get(stack.size() - j) != bomb.charAt(bomb.length() - j)) {
                return;
            }
        }
        
        for (int j = 0; j < bomb.length(); j++) {
            stack.pop();
        }
    }
}