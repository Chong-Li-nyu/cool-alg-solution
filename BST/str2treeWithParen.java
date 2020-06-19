/*
536. Construct Binary Tree from String
Medium

You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

Example:

Input: "4(2(3)(1))(6(5))"
Output: return the tree root node representing the following tree:

       4
     /   \
    2     6
   / \   / 
  3   1 5   

*/
class Solution {
    public TreeNode str2tree(String s) {
        return dfs(s);
    }
    public TreeNode dfs(String s ){
        if(s== null || s.length() == 0) return null;
        int start = s.indexOf("(");
        if(start == -1) return new TreeNode(Integer.parseInt(s)); //单剩一个数
        
        TreeNode root = new TreeNode(Integer.parseInt(s.substring(0, start))); // leading number可能为负数
        int count = 0; //count '('
        for(int i = start; i < s.length(); i++){
            if(s.charAt(i) == '('){
                count++;
            }
            if(s.charAt(i) == ')'){
                count--;
            }
            if(count == 0) { //valid substring again, recurse
                root.left = dfs(s.substring(start+1, i)); // until i-1, a number or ')'
                if(i+2 <= s.length() - 1)
                    root.right = dfs(s.substring(i+2, s.length() - 1)); //s[i+1]='('
                break;
            }
            
        }
        return root;
    }
}
