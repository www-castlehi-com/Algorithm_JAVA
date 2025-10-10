import java.util.*;

class Solution {
    
    private Map<String, Integer> idMap = new HashMap<>();
    private Set<Integer>[] reportSet;
    
    public int[] solution(String[] id_list, String[] report, int k) {
        initializeId(id_list);
        
        reportSet = new HashSet[id_list.length];
        makeReportSet(report);

        return getMailCount(k);
    }
    
    private void initializeId(String[] id_list) {
        for (int i = 0; i < id_list.length; i++) {
            idMap.put(id_list[i], i);
        }
    }
    
    private void makeReportSet(String[] report) {
        for (int i = 0; i < reportSet.length; i++) {
            reportSet[i] = new HashSet<>();
        }
        
        for (int i = 0; i < report.length; i++) {
            String[] s = report[i].split(" ");
            String reporter = s[0];
            String reported = s[1];
            
            reportSet[idMap.get(reported)].add(idMap.get(reporter));
        }
    }
    
    private int[] getMailCount(int k) {
        int[] mailCount = new int[idMap.size()];
        for (int i = 0; i < reportSet.length; i++) {
            if (reportSet[i].size() >= k) {
                for (int reporter : reportSet[i]) {
                    mailCount[reporter]++;
                }
            }
        }
        
        return mailCount;
    }
}