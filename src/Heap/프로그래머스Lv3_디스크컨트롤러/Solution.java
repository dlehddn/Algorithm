package Heap.프로그래머스Lv3_디스크컨트롤러;

import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int compareResult = o1[0] - o2[0];
                if(compareResult == 0) {
                    return o1[1] - o2[1];
                }
                return compareResult;
            }
        });
        // for(int[] a : jobs) System.out.println(Arrays.toString(a));

        int totalUseTime = 0;
        int currentTimePoint = 0;
        int index = 0;
        int count = 0;
        while(count < jobs.length) {
            if(pq.isEmpty()) {
                currentTimePoint = jobs[index][0] + jobs[index][1];
                totalUseTime += jobs[index][1];
                index++;
                count++;
            }
            if(!pq.isEmpty()) {
                int[] poll = pq.poll();
                currentTimePoint += poll[1];
                totalUseTime += (currentTimePoint - poll[0]);
                count++;
            }
            while(index < jobs.length && jobs[index][0] <= currentTimePoint) {
                pq.add(jobs[index]);
                index++;
            }
        }
        return totalUseTime/jobs.length;
    }
}