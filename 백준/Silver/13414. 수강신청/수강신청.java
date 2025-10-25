import java.io.*;
import java.util.*;

public class Main {
    
    private static Map<String, Integer> apply = new HashMap<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int index = 0;
        for (int i = 0; i < l; i++) {
            String id = br.readLine();
            apply.put(id, index++);
        }
        
        List<String> ids = new ArrayList<>(apply.keySet());
        Collections.sort(ids, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return apply.get(a) - apply.get(b);
            }
        });
        for (int i = 0; i < k && i < ids.size(); i++) {
            sb.append(ids.get(i) + "\n");
        }
        
        System.out.print(sb);
    }
}