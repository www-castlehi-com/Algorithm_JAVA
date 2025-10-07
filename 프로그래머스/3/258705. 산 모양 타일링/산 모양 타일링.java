class Solution {
    
    private static final int DIV = 10007;
    
    private int[] a;
    private int[] b;
    
    public int solution(int n, int[] tops) {
        a = new int[n];
        b = new int[n];
        
        initialize(tops[0]);
        
        dp(tops);
        
        return (a[n - 1] + b[n - 1]) % DIV;
    }
    
    private void initialize(int top) {
        a[0] = 1;
        if (top == 0) {
            b[0] = 2;
        } else {
            b[0] = 3;
        }
    }
    
    private void dp(int[] tops) {
        for (int i = 1; i < tops.length; i++) {
            a[i] = (a[i - 1] + b[i - 1]) % DIV;
            if (tops[i] == 0) {
                b[i] = (a[i - 1] + 2 * b[i - 1]) % DIV;
            } else {
                b[i] = (2 * a[i - 1] + 3 * b[i - 1]) % DIV;
            }
        }
    }
}