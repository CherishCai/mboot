package cn.cherish.mboot.arithmetic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * 并查集
 * @author Cherish
 * @version 1.0
 * @date 2017/8/2 23:07
 */
public class DisjointSet {
    private static int father[];

    public static void main(String[] args) throws Exception {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        while (st.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) st.nval;
            father = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                father[i] = i;
            }
            st.nextToken();
            int m = (int) st.nval;
            st.nextToken();
            int p = (int) st.nval;
            for (int i = 0; i < m; i++) {
                st.nextToken();
                int a = (int) st.nval;
                st.nextToken();
                int b = (int) st.nval;
                union(a, b);
            }

            for (int i = 0; i < p; i++) {
                st.nextToken();
                int a = (int) st.nval;
                st.nextToken();
                int b = (int) st.nval;
                a = findParent(a);
                b = findParent(b);
                System.out.println(a == b ? "Yes" : "No");
            }
        }
    }

    private static void union(int f, int t) {
        int a = findParent(f);
        int b = findParent(t);
        if (a == b) return;
        if (a > b) {
            father[a] = b;
        } else {
            father[b] = a;
        }
    }

    private static int findParent(int f) {
        while (father[f] != f) {
            f = father[f];
        }
        return f;
    }
}
