package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] buckets = new int[M];

        for (Oomage oomage: oomages) {
            int idx = (oomage.hashCode() & 0x7FFFFFFF) % M;
            buckets[idx] += 1;
        }

        for (int i = 0; i < M; i++) {
            if (buckets[i] < oomages.size() / 50 || buckets[i] > oomages.size() / 2.5) {
                return false;
            }
        }
        return true;
    }
}
