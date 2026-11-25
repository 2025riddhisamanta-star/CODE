import java.util.ArrayList;
import java.util.List;

public class Optimal {
    public static void main(String[] args) {
        int[] refString = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1};
        int capacity = 3;

        List<Integer> memory = new ArrayList<>();
        int pageFaults = 0;
        int pageHits = 0;

        for (int i = 0; i < refString.length; i++) {
            int page = refString[i];

            if (memory.contains(page)) {
                pageHits++;
            } else {
                pageFaults++;
                if (memory.size() < capacity) {
                    memory.add(page);
                } else {
                    int farthest = -1;
                    int victimIdx = -1;

                    for (int j = 0; j < memory.size(); j++) {
                        int memPage = memory.get(j);
                        int nextUse = Integer.MAX_VALUE;

                        // Future mein dekho yeh page kab use hoga
                        for (int k = i + 1; k < refString.length; k++) {
                            if (refString[k] == memPage) {
                                nextUse = k;
                                break;
                            }
                        }

                        if (nextUse > farthest) {
                            farthest = nextUse;
                            victimIdx = j;
                        }
                    }
                    memory.set(victimIdx, page);
                }
            }
        }

        System.out.println("Optimal -> Page Faults: " + pageFaults + ", Page Hits: " + pageHits);
    }
}