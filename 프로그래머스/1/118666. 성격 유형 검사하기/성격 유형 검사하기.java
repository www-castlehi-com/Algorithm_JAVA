class Solution {
    
    private int[] score = new int[4];
    private int[] scoreGraph = new int[] {0, 3, 2, 1, 0, 1, 2, 3};
    
    public String solution(String[] survey, int[] choices) {
        for (int i = 0; i < survey.length; i++) {
            String[] indicators = survey[i].split("");
            
            String indicator = indicators[choices[i] / 4];
            if ("RCJA".contains(indicator)) {
                score["RCJA".indexOf(indicator)] += scoreGraph[choices[i]];
            } else {
                score["TFMN".indexOf(indicator)] -= scoreGraph[choices[i]];
            }
        }
        
        String answer = "";
        String left = "RCJA";
        String right = "TFMN";
        for (int i = 0; i < 4; i++) {
            answer += (score[i] >= 0 ? left.charAt(i) : right.charAt(i));
        }
        
        return answer;
    }
}