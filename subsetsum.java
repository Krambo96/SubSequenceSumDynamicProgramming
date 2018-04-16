import java.util.HashSet;

public class subsetsum {

    static HashSet<String> naive = new HashSet<>();
    static HashSet<String> dp = new HashSet<>();
    public static void main(String[] args){
        int[] set = new int[]{3, 34, 4, 12, 5, 2, 7, 9};

        long startTime = System.currentTimeMillis();
        System.out.println(subsetsum(set, 9));
        long endTime = System.currentTimeMillis();

        System.out.println("Naive implementation time: " + (endTime - startTime));

        System.out.println("Beginning DP Solution...");
        long startDP = System.currentTimeMillis();
        System.out.println(subsetsumdp(set, 9));
        long endDP = System.currentTimeMillis();
        System.out.println("DP implementation time: " + (endDP - startDP));
    }

    static int subsetsum(int[] set, int sum){
        if(set == null || set.length < 1) return 0;
        else{
            return helper(set, sum, set.length - 1);
        }
    }

    static int helper(int[] set, int total, int i){
        if(total < 0) return 0;
        else if(total == 0) return 1;
        else if(i < 0) return 0;
        else{
            String temp = "Total is: " + total + " and index is: " + i;
            if(!naive.add(temp)){
                System.out.println(temp);
            }
            return helper(set, total - set[i], i-1) + helper(set, total, i-1);
        }
    }

    static int subsetsumdp(int[] set, int sum){
        if(set == null || set.length < 1) return 0;
        else{
            int[][] memo = new int[sum + 1][set.length];
            for(int i = 0; i < memo.length; i++){
                for(int j = 0; j < memo[i].length; j++){
                    memo[i][j] = -1;
                }
            }

            return helperdp(set, memo, sum, set.length - 1);
        }
    }

    static int helperdp(int[] set, int[][] memo, int total, int i){
        if(total < 0)
            return 0;
        else if(total == 0)
            return 1;
        else if(i < 0)
            return 0;
        else if(memo[total][i] != -1)
            return memo[total][i];
        else {
            String temp = "Total is: " + total + " and index is: " + i;
            if(!dp.add(temp)){
                System.out.println(temp);
            }
            memo[total][i] = helperdp(set, memo, total - set[i], i - 1) + helperdp(set, memo, total, i - 1);
            return memo[total][i];
        }

    }
}
