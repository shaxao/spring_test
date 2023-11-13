package leetcode;


import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.math.MathUtil;
import org.commonmark.node.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Solution {


    /**
     * 跳跃游戏
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        if(nums.length == 1){
            return true;
        }
        int i =0;
        int k = 0;
        while(i < nums.length && k < nums.length){
            i = k;
            if(nums[i] != 0){
                i += nums[i];
                if(i >= nums.length-1){
                    return true;
                }
                k++;
            }else{
                if(k == nums.length-2 && nums.length > 1){
                    return false;
                }
                if(i == nums.length-1){
                    return true;
                }
                k++;
            }
        }
        return false;
    }


    /**
     *  删除有序数组中的重复项
     * @param nums
     * @return
     */
    public static int[] removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int k = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[k]) {
                k++;
                nums[k] = nums[i];
            }
        }

        return nums;
    }


    /**
     * 搜索插入位置
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        if (nums.length == 0 || 0 == target) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                count = i;
                break;
            }
            if (nums[i] < target) {
                count = i + 1;
            }
        }
        return count;
    }

    /**
     * x 的平方根
     *
     * @param x
     * @return
     */
    public static int mySqrt(int x) {  //46,339
        int i = 1;
        int j = x;
        int ans = 0;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            // upper bound的形式，因为我们要找的ans要是最接近于x的最大的数，利用upper bound
            if (mid <= x / mid) {
                i = mid + 1;
                ans = mid; // 只要mid <= x/mid，left左边界就会一直向右移动，ans就会一直更新（变大），直到不在满足mid <= x/mid的条件为止，ans就会停止更新，永远停在满足mid<=x/mid条件下面的最后一次更新，即满足ans * ans <= mid的最大值。
            } else
                j = mid - 1;
        }

        return ans;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }


        /**
         * 想同的树
         *
         * @param p
         * @param q
         * @return
         */
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            }
            if (p == null || q == null) {
                return false;
            }
            if (p.val != q.val) {
                return false;
            }
            boolean left = isSameTree(p.left, q.left);
            boolean right = isSameTree(p.right, q.right);
            return left && right;
        }

    }
    public static int strStr(String haystack, String needle) {
        int a = 0;
        int b = 0;
        int len = 0;
        while(a < haystack.length()){
            if(haystack.charAt(a) != needle.charAt(b)){
                a++;
            }else {
                b++;
                len = a;
                break;
            }

        }
        if(haystack.substring(len,len+needle.length()).equals(needle)){
            return len;
        }
        return -1;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int a = 0,b = 0,i = 0,j = 0,sum = 0;
        while(true){
            a += l1.val * power(10,i);
            b += l2.val * power(10,j);
            if(l1.next == null && l2.next == null){
                break;
            }
            if(l1.next != null){
                i++;
            }
            if(l2.next != null){
                j++;
            }
        }
        i = 0;
        sum = a+b;
        ListNode l3 = null;
        ListNode lastNode = null;
        while(true){
            if(sum < 10){
                l3.val = sum;
                l3.next = null;
                break;
            }
            if(l3 == null){
                l3.val = (sum %= 10);
                if(sum < 10){
                    l3.next = new ListNode(sum);
                    break;
                }else {
                    l3.next = new ListNode(sum %= 10);
                }
            }else {

            }

        }
        return l3;
    }

    public static ListNode add(int val){
        return new ListNode(val);
    }


    public static int power(int base, int exponent) {
        if (exponent == 0) {
            return 1;
        }

        if (exponent < 0) {
            base = 1 / base;
            exponent = -exponent;
        }

        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }

        return result;
    }
   static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

    /**
     *     给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     *
     *     你可以假设数组是非空的，并且给定的数组总是存在多数元素
     *     可以使用摩尔投票算法来解决这个问题。
     *
     * 摩尔投票算法的基本思想是通过不断地消除不同的元素对，最终剩下的元素就是多数元素。
     *
     * 具体步骤如下：
     *
     * 初始化候选元素 candidate 为数组的第一个元素，计数器 count 为 1。
     * 遍历数组，如果当前元素与候选元素相同，则计数器 count 加 1；如果当前元素与候选元素不同，则计数器 count 减 1。
     * 如果计数器 count 变为 0，重新选择候选元素为当前元素，并将计数器 count 重置为 1。
     * 遍历结束后，候选元素 candidate 即为多数元素。
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            } else if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }

    /**
     *给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
     *
     * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        int count = 0;
        for(int i = s.length()-1;i >= 0;i--){
            if(s.charAt(i) != ' '){
                count++;
            }
            if(s.charAt(i) == ' ' && count != 0){
                break;
            }

        }
        return count;
    }

    static int indexOf(char[] source, int sourceOffset, int sourceCount,
                       char[] target, int targetOffset, int targetCount,
                       int fromIndex) {
        // 如果起始位置超出源字符数组的长度，则返回-1（如果目标字符数组为空，则返回源字符数组的长度）
        if (fromIndex >= sourceCount) {
            return (targetCount == 0 ? sourceCount : -1);
        }
        // 如果起始位置小于0，则将起始位置设置为0
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        // 如果目标字符数组为空，则直接返回起始位置
        if (targetCount == 0) {
            return fromIndex;
        }

        // 获取目标字符数组的第一个字符
        char first = target[targetOffset];
        // 计算源字符数组中的最大索引位置
        int max = sourceOffset + (sourceCount - targetCount);

        // 从起始位置开始遍历源字符数组
        for (int i = sourceOffset + fromIndex; i <= max; i++) {
            /* Look for first character. */
            // 查找源字符数组中与目标字符数组第一个字符相等的字符
            if (source[i] != first) {
                // 如果不相等，则继续向后查找，直到找到相等的字符或者遍历到最大索引位置
                while (++i <= max && source[i] != first);
            }

            /* Found first character, now look at the rest of v2 */
            // 如果找到了与目标字符数组的第一个字符相等的字符
            if (i <= max) {
                // 从源字符数组和目标字符数组的第二个字符开始，依次比较每个字符
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && source[j]
                        == target[k]; j++, k++);

                // 如果内层循环能够完整地遍历目标字符数组，并且每个字符都与源字符数组中的相应字符匹配
                if (j == end) {
                    /* Found whole string. */
                    // 返回目标字符数组在源字符数组中的索引位置
                    return i - sourceOffset;
                }
            }
        }
        // 如果未找到目标字符数组，则返回-1
        return -1;
    }

    /**
     * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
     *
     * 如果可以，返回 true ；否则返回 false 。
     *
     * magazine 中的每个字符只能在 ransomNote 中使用一次。
     * @param ransomNote
     * @param magazine
     * @return
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26];
        for (char c : magazine.toCharArray()) {
            count[c - 'a']++;
        }
        for(int c:count){
            System.out.println(c);
        }

        for (char c : ransomNote.toCharArray()) {
            if (count[c - 'a'] == 0) {
                return false;
            }
            count[c - 'a']--;
        }
        return true;
    }


        // 最高位的二进制位编号为 30
        static final int HIGH_BIT = 30;

        public int findMaximumXOR(int[] nums) {
            int x = 0;
            for (int k = HIGH_BIT; k >= 0; --k) {
                Set<Integer> seen = new HashSet<Integer>();
                // 将所有的 pre^k(a_j) 放入哈希表中
                for (int num : nums) {
                    // 如果只想保留从最高位开始到第 k 个二进制位为止的部分
                    // 只需将其右移 k 位
                    seen.add(num >> k);
                }

                // 目前 x 包含从最高位开始到第 k+1 个二进制位为止的部分
                // 我们将 x 的第 k 个二进制位置为 1，即为 x = x*2+1
                int xNext = x * 2 + 1;
                boolean found = false;

                // 枚举 i
                for (int num : nums) {
                    if (seen.contains(xNext ^ (num >> k))) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    x = xNext;
                } else {
                    // 如果没有找到满足等式的 a_i 和 a_j，那么 x 的第 k 个二进制位只能为 0
                    // 即为 x = x*2
                    x = xNext - 1;
                }
            }
            return x;
        }

    /**
     * 给你一个字符串数组 words ，找出并返回 length(words[i]) * length(words[j]) 的最大值，
     * 并且这两个单词不含有公共字母。如果不存在这样的两个单词，返回 0 。
     * 示例 1：
     *
     * 输入：words = ["abcw","baz","foo","bar","xtfn","abcdef"]
     * 输出：16
     * 解释：这两个单词为 "abcw", "xtfn"。
     * @param words
     * @return
     */
    public static int maxProduct(String[] words) {
        int n = words.length, idx = 0;
        int[] masks = new int[n];
        for (String w : words) {
            int t = 0;
            for (int i = 0; i < w.length(); i++) {
                int u = w.charAt(i) - 'a';
                t |= (1 << u);
            }
            System.out.println(t);
            masks[idx++] = t;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if ((masks[i] & masks[j]) == 0) ans = Math.max(ans, words[i].length() * words[j].length());
            }
        }
        return ans;
    }



    /**
     * 社团共有 num 为成员参与破冰游戏，编号为 0 ~ num-1。成员们按照编号顺序围绕圆桌而坐。社长抽取一个数字 target，从 0 号成员起开始计数，排在第 target 位的成员离开圆桌，且成员离开后从下一个成员开始计数。请返回游戏结束时最后一位成员的编号。
     * @param num
     * @param target
     * @return
     */
    public static int iceBreakingGame(int num, int target) {
        //目前理解了题意:是第target的人离开，然后继续循环，直到剩下最后一个人
        // ArrayList<Integer> list = new ArrayList<>(num);
        // for(int i = 0;i < num;i++){
        //     list.add(i);
        // }
        // int idx = 0;
        // while(num > 1){
        //     idx = (idx + target - 1) % num;
        //     list.remove(idx);
        //     num--;
        // }
        // return list.get(0);

        //f(n,m) = (f(n-1,m))+m % n  使用反推法得到  已知结果最后只有一个人，因此开始反推每次游戏结束时存货的人的编号，最后得此结论
        int ans = 0;
        for(int i = 2;i <= num;i++){
            ans = (ans + target) % i;
        }
        return ans;
    }



    public static void main(String[] args) {
       int[] nums = {3,2,3};
       String[] words = {"abcw","baz","foo","bar","xtfn","abcdef"};
//        int target = 7;
//        int i = searchInsert(nums, target);
//        int i = mySqrt(2563347);
//        TreeNode p = new TreeNode(1, new TreeNode(2), new TreeNode(1));
//        TreeNode q = new TreeNode(1, new TreeNode(1), new TreeNode(2));
//        TreeNode node = new TreeNode();
//        boolean sameTree = node.isSameTree(p, q);

//       String haystack = "hello", needle = "ll";
//        int i = strStr(haystack, needle);
//        haystack.indexOf(needle);
        //int[] nums = {3,2,1,0,4};
//        removeDuplicates(nums);
//        for(int i =0;i < nums.length;i++){
//            System.out.println(nums[i]);
//        }

//        int i = majorityElement(nums);
            //iceBreakingGame(7,4);
        int i = maxProduct(words);
        System.out.println(i);

    }
}
