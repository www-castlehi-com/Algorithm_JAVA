import java.util.*;

class Solution {
    
    private static class InOutRecords {
        List<Integer> inRecords = new ArrayList<>();
        List<Integer> outRecords = new ArrayList<>();
        
        public List<Integer> getRecords(String type) {
            if (type.equals("IN")) {
                return inRecords;
            }
            return outRecords;
        }
        
        public List<Integer> getInRecords() {
            return inRecords;
        }
        
        public List<Integer> getOutRecords() {
            return outRecords;
        }
    }
    
    public int[] solution(int[] fees, String[] records) {
        Map<Integer, InOutRecords> recordsByCarNumber = new HashMap<>();
        for (String record: records) {
            String[] seenRecord = record.split(" ");
            String[] time = seenRecord[0].split(":");
            int minute = Integer.valueOf(time[0]) * 60 + Integer.valueOf(time[1]);
            int carNumber = Integer.valueOf(seenRecord[1]);
            
            InOutRecords carRecord = recordsByCarNumber.getOrDefault(carNumber, new InOutRecords());
            carRecord.getRecords(seenRecord[2]).add(minute);
            recordsByCarNumber.put(carNumber, carRecord);
        }
        
        List<int[]> feesByCar = new ArrayList<>();
        for (Map.Entry<Integer, InOutRecords> entry: recordsByCarNumber.entrySet()) {
            int carNumber = entry.getKey();
            InOutRecords carRecords = entry.getValue();
            int totalFee = fees[1];
            int duration = 0;
            for (int i = 0; i < carRecords.getInRecords().size(); i++) {
                int in = carRecords.getInRecords().get(i);
                int out = carRecords.getOutRecords().size() <= i ? 23 * 60 + 59 : carRecords.getOutRecords().get(i);
                
                duration += (out - in);
            }
            
            duration -= fees[0];
            if (duration > 0) {
                int full = duration / fees[2];
                int over = duration % fees[2];

                totalFee += (over != 0 ? full + 1 : full) * fees[3];
            }
            
            feesByCar.add(new int[]{carNumber, totalFee});
        }
        
        Collections.sort(feesByCar, (f1, f2) -> f1[0] - f2[0]);
        
        int[] result = new int[feesByCar.size()];
        for (int i = 0; i < feesByCar.size(); i++) {
            result[i] = feesByCar.get(i)[1];
        }
        return result;
    }
}