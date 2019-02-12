package google;

import java.util.Collections;
import java.util.*;
public class CountSmallerNumberAfterSelf {
    private static class TreeNode {
        TreeNode left, right;
        int num, smallerCount;
        public TreeNode(int num, int smallerCount) {
            this.num = num;
            this.smallerCount = smallerCount;
        }
    }

    public static List<Integer> countSmaller(int[] nums) {
        List<Integer> counts = new ArrayList<>();
        if (nums == null || nums.length == 0) return counts;

        counts.add(0);
        TreeNode root = new TreeNode(nums[nums.length - 1], 0);
        for (int i = nums.length - 2; i >= 0; i--) {
            int count = countSmaller(root, nums[i]);
            counts.add(count);
        }
        Collections.reverse(counts);

        return counts;
    }

    private static int countSmaller(TreeNode root, int num) {
        int smallerCount = 0;
        while (root != null) {
            if (num < root.num) {
                root.smallerCount++;
                if (root.left == null) {
                    root.left = new TreeNode(num, 0);
                    break;
                } else {
                    root = root.left;
                }
            } else {
                smallerCount += root.smallerCount + (num == root.num ? 0 : 1);
                if (root.right == null) {
                    root.right = new TreeNode(num, 0);
                    break;
                } else {
                    root = root.right;
                }
            }
        }
        return smallerCount;
    }
}
