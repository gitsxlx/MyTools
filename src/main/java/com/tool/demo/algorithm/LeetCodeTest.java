package com.tool.demo.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * LeetCode
 *
 * @author WangChao
 * @date 2020/08/01
 **/
public class LeetCodeTest {

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        long startTime = System.currentTimeMillis();
        moveZeroes(nums);
        long endTime = System.currentTimeMillis();
        System.out.println("移动零||时间:" + (endTime - startTime) + "||结果:" + Arrays.toString(nums));

        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        long startTime1 = System.currentTimeMillis();
        int area = maxArea(height);
        int area1 = maxArea1(height);
        long endTime1 = System.currentTimeMillis();
        System.out.println("盛最多水的容器||时间:" + (endTime1 - startTime1) + "||结果:" + area + ":" + area1);

        int n = 3;
        long startTime2 = System.currentTimeMillis();
        int count = climbStairs(n);
        int count1 = climbStairs1(n);
        long endTime2 = System.currentTimeMillis();
        System.out.println("爬楼梯||时间:" + (endTime2 - startTime2) + "||结果:" + count + ":" + count1);

        int[] nums1 = {2, 7, 11, 15};
        int target = 9;
        long startTime3 = System.currentTimeMillis();
        int[] twoSum = twoSum(nums1, target);
        int[] twoSum1 = twoSum1(nums1, target);
        int[] twoSum2 = twoSum2(nums1, target);
        long endTime3 = System.currentTimeMillis();
        System.out.println(
                "两数之和||时间:" + (endTime3 - startTime3) + "||结果:" + Arrays.toString(twoSum) + ":" + Arrays
                        .toString(twoSum1) + ":" + Arrays.toString(twoSum2));

        int[] nums2 = {-1, 0, 1, 2, -1, -4};
        long startTime4 = System.currentTimeMillis();
        List<List<Integer>> threeSum = threeSum(nums2);
        List<List<Integer>> threeSum1 = threeSum1(nums2);
        long endTime4 = System.currentTimeMillis();
        System.out.println(
                "三数之和||时间:" + (endTime4 - startTime4) + "||结果:" + threeSum + ":" + threeSum1);

        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(4);
        ListNode listNode4 = new ListNode(5);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        String linked = linked(listNode);
        long startTime5 = System.currentTimeMillis();
        ListNode reverseListNode = reverseList(listNode);
        String reverseLinked = linked(reverseListNode);
        ListNode reverseListNode1 = reverseList1(reverseListNode);
        long endTime5 = System.currentTimeMillis();
        System.out.println(
                "反转链表||时间:" + (endTime5 - startTime5) + "||原:" + linked + "||结果:" + reverseLinked
                        + ":" + linked(reverseListNode1));

        ListNode node = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        String linked1 = linked(node);
        long startTime6 = System.currentTimeMillis();
        ListNode reverseNode = swapPairs(node);
        String reverseNodeLinked = linked(reverseNode);
        ListNode swapPairsNode = swapPairs1(reverseNode);
        long endTime6 = System.currentTimeMillis();
        System.out.println(
                "两两交换链表中的节点||时间:" + (endTime6 - startTime6) + "||原:" + linked1 + "||结果:" + reverseNodeLinked
                        + ":" + linked(swapPairsNode));

        int[] nums3 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        String str = Arrays.toString(nums3);
        long startTime7 = System.currentTimeMillis();
        int length = removeDuplicates(nums3);
        long endTime7 = System.currentTimeMillis();
        System.out.println(
                "删除排序数组中的重复项||时间:" + (endTime7 - startTime7) + "||原:" + str + "||结果:" + length + ":"
                        + Arrays.toString(nums3));

        //int[] nums4 = {4, 3, 2, 1};
        int[] nums5 = {9, 9, 9, 9};
        String str1 = Arrays.toString(nums5);
        long startTime8 = System.currentTimeMillis();
        int[] digits = plusOne(nums5);
        long endTime8 = System.currentTimeMillis();
        System.out.println(
                "加一||时间:" + (endTime8 - startTime8) + "||原:" + str1 + "||结果:"
                        + Arrays.toString(digits));

        int[] nums6 = {1, 2, 3};
        int[] nums7 = {2, 4, 6};
        int[] nums8 = {1, 2, 3, 0, 0, 0};
        String str2 = Arrays.toString(nums6) + ":" + Arrays.toString(nums7);
        long startTime9 = System.currentTimeMillis();
        merge(nums8, nums6.length, nums7, nums7.length);
        long endTime9 = System.currentTimeMillis();
        System.out.println(
                "合并两个有序数组||时间:" + (endTime9 - startTime9) + "||原:" + str2 + "||结果:"
                        + Arrays.toString(nums8));

        int[] nums20 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        String str20 = Arrays.toString(nums20);
        long startTime20 = System.currentTimeMillis();
        int trap = trap(nums20);
        int trap1 = trap1(nums20);
        int trap2 = trap2(nums20);
        int trap3 = trap3(nums20);
        long endTime20 = System.currentTimeMillis();
        System.out.println(
                "接雨水||时间:" + (endTime20 - startTime20) + "||原:" + str20 + "||结果:" + trap + ":"
                        + trap1 + ":" + trap2 + ":" + trap3);
    }

    /**
     * 输出linked
     *
     * @param listNode
     * @return
     */
    public static String linked(ListNode listNode){
        String linked = listNode.val + "";
        ListNode node = listNode;
        while(null != node.next){
            linked += " -> " + node.next.val;
            node = node.next;
        }
        return linked;
    }

    /**
     * 移动零
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        for (int i = 0, j = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                if (i != j) {
                    nums[i] = 0;
                }
                j++;
            }
        }
    }

    /**
     * 盛最多水的容器
     * 暴力法
     * 时间复杂度为 O(n^2)
     * 空间复杂度：O(1)
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length - 1; ++i) {
            for (int j = i + 1; j < height.length; ++j) {
                int area = (j - i) * Math.min(height[i], height[j]);
                max = Math.max(max, area);
            }
        }
        return max;
    }

    /**
     * 盛最多水的容器
     * 双指针
     * 时间复杂度为 O(n)
     * 空间复杂度：O(1)
     *
     * @param height
     * @return
     */
    public static int maxArea1(int[] height) {
        int max = 0;
        for (int i = 0, j = height.length - 1; i < j;) {
            int minHeight = height[i] < height[j] ? height[i++] : height[j--];
            int area = (j - 1 + i) * minHeight;
            max = Math.max(max, area);
        }
        return max;
    }

    /**
     * 爬楼梯（斐波那契数列）
     * 递归法
     * 时间复杂度：O(2^n)
     * 空间复杂度：O(n)
     *
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    /**
     * 爬楼梯
     * 动态规划
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return
     */
    public static int climbStairs1(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    /**
     * 两数之和
     * 暴力法
     * 时间复杂度为 O(n^2)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 两数之和
     * 两遍哈希表
     * 时间复杂度为 O(n)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 两数之和
     * 一遍哈希表
     * 时间复杂度为 O(n)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 三数之和
     * 暴力法
     * 时间复杂度：O(n^3)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    private static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        Set<List<Integer>> result = new LinkedHashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> value = Arrays.asList(nums[i], nums[j], nums[k]);
                        result.add(value);
                    }
                }
            }
        }

        return new ArrayList<>(result);
    }

    /**
     * 三数之和
     * 排序+双指针
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int k = 0; k < nums.length - 2; k++){
            if(nums[k] > 0) break;
            if(k > 0 && nums[k] == nums[k - 1]) continue;
            int i = k + 1, j = nums.length - 1;
            while(i < j){
                int sum = nums[k] + nums[i] + nums[j];
                if(sum < 0){
                    while(i < j && nums[i] == nums[++i]);
                } else if (sum > 0) {
                    while(i < j && nums[j] == nums[--j]);
                } else {
                    res.add(new ArrayList<Integer>(Arrays.asList(nums[k], nums[i], nums[j])));
                    while(i < j && nums[i] == nums[++i]);
                    while(i < j && nums[j] == nums[--j]);
                }
            }
        }
        return res;
    }

    /**
     * 反转链表
     * 迭代
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * 反转链表
     * 递归
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param head
     * @return
     */
    public static ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    /**
     * 两两交换链表中的节点
     * 递归
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        // If the list has no node or has only one node left.
        if ((head == null) || (head.next == null)) {
            return head;
        }

        // Nodes to be swapped
        ListNode firstNode = head;
        ListNode secondNode = head.next;

        // Swapping
        firstNode.next  = swapPairs(secondNode.next);
        secondNode.next = firstNode;

        // Now the head is the second node
        return secondNode;
    }

    /**
     * 两两交换链表中的节点
     * 迭代
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs1(ListNode head) {
        // Dummy node acts as the prevNode for the head node
        // of the list and hence stores pointer to the head node.
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prevNode = dummy;

        while ((head != null) && (head.next != null)) {
            // Nodes to be swapped
            ListNode firstNode = head;
            ListNode secondNode = head.next;

            // Swapping
            prevNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            // Reinitializing the head and prevNode for next swap
            prevNode = firstNode;
            head = firstNode.next; // jump
        }

        // Return the new head node.
        return dummy.next;
    }

    /**
     * 删除排序数组中的重复项
     * 双指针法
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    /**
     * 加一
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param digits
     * @return
     */
    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    /**
     * 合并两个有序数组
     * 时间复杂度 : O((n + m)log(n + m))
     * 空间复杂度 : O(1)
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    /**
     * 合并两个有序数组
     * 双指针/从前往后
     * 时间复杂度 : O(n + m)
     * 空间复杂度 : O(m)
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        // Make a copy of nums1.
        int [] nums1_copy = new int[m];
        System.arraycopy(nums1, 0, nums1_copy, 0, m);

        // Two get pointers for nums1_copy and nums2.
        int p1 = 0;
        int p2 = 0;

        // Set pointer for nums1
        int p = 0;

        // Compare elements from nums1_copy and nums2
        // and add the smallest one into nums1.
        while ((p1 < m) && (p2 < n))
            nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];

        // if there are still elements to add
        if (p1 < m)
            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        if (p2 < n)
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
    }

    /**
     * 合并两个有序数组
     * 双指针/从后往前
     * 时间复杂度 : O(n + m)
     * 空间复杂度 : O(1)
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        // two get pointers for nums1 and nums2
        int p1 = m - 1;
        int p2 = n - 1;
        // set pointer for nums1
        int p = m + n - 1;

        // while there are still elements to compare
        while ((p1 >= 0) && (p2 >= 0))
            // compare two elements from nums1 and nums2
            // and add the largest one in nums1
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];

        // add missing elements from nums2
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    /**
     * 旋转数组
     * 暴力法
     * 时间复杂度：O(n * k)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int temp, previous;
        for (int i = 0; i < k; i++) {
            previous = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                temp = nums[j];
                nums[j] = previous;
                previous = temp;
            }
        }
    }

    /**
     * 旋转数组
     * 额外的数组
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @param k
     */
    public void rotate1(int[] nums, int k) {
        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            a[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = a[i];
        }
    }

    /**
     * 旋转数组
     * 环状替换
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }

    /**
     * 旋转数组
     * 反转
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @param k
     */
    public void rotate3(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * 合并两个有序链表
     * 递归
     * 时间复杂度：O(n + m)
     * 空间复杂度：O(n + m)
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }

    /**
     * 合并两个有序链表
     * 迭代
     * 时间复杂度：O(n + m)
     * 空间复杂度：O(1)
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }

    /**
     * K个一组翻转链表
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) end = end.next;
            if (end == null) break;
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;

            end = pre;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /**
     * 环形链表
     * 哈希表
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode1 head) {
        Set<ListNode1> nodesSeen = new HashSet<>();
        while (head != null) {
            if (nodesSeen.contains(head)) {
                return true;
            } else {
                nodesSeen.add(head);
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 环形链表
     * 双指针
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    public boolean hasCycle1(ListNode1 head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode1 slow = head;
        ListNode1 fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    class ListNode1 {
        int val;
        ListNode1 next;
        ListNode1(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 环形链表 II
     * 哈希表
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param head
     * @return
     */
    public ListNode1 detectCycle(ListNode1 head) {
        Set<ListNode1> visited = new HashSet<ListNode1>();

        ListNode1 node = head;
        while (node != null) {
            if (visited.contains(node)) {
                return node;
            }
            visited.add(node);
            node = node.next;
        }

        return null;
    }

    /**
     * 环形链表 II
     * Floyd 算法
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    public ListNode1 detectCycle1(ListNode1 head) {
        if (head == null) {
            return null;
        }

        // If there is a cycle, the fast/slow pointers will intersect at some
        // node. Otherwise, there is no cycle, so we cannot find an e***ance to
        // a cycle.
        ListNode1 intersect = getIntersect(head);
        if (intersect == null) {
            return null;
        }

        // To find the e***ance to the cycle, we have two pointers traverse at
        // the same speed -- one from the front of the list, and the other from
        // the point of intersection.
        ListNode1 ptr1 = head;
        ListNode1 ptr2 = intersect;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        return ptr1;
    }

    private ListNode1 getIntersect(ListNode1 head) {
        ListNode1 tortoise = head;
        ListNode1 hare = head;

        // A fast pointer will either loop around a cycle and meet the slow
        // pointer or reach the `null` at the end of a non-cyclic list.
        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
            if (tortoise == hare) {
                return tortoise;
            }
        }

        return null;
    }


    // Hash table that takes care of the mappings.
    private HashMap<Character, Character> mappings;

    // Initialize hash map with mappings. This simply makes the code easier to read.
    {
        this.mappings = new HashMap<Character, Character>();
        this.mappings.put(')', '(');
        this.mappings.put('}', '{');
        this.mappings.put(']', '[');
    }

    /**
     * 有效的括号
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        // Initialize a stack to be used in the algorithm.
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If the current character is a closing bracket.
            if (this.mappings.containsKey(c)) {

                // Get the top element of the stack. If the stack is empty, set a dummy value of '#'
                char topElement = stack.empty() ? '#' : stack.pop();

                // If the mapping for this bracket doesn't match the stack's top element, return false.
                if (topElement != this.mappings.get(c)) {
                    return false;
                }
            } else {
                // If it was an opening bracket, push to the stack.
                stack.push(c);
            }
        }

        // If the stack still contains elements, then it is an invalid expression.
        return stack.isEmpty();
    }

    /**
     * 柱状图中最大的矩形
     * 单调栈 + 常数优化
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);

        Stack<Integer> mono_stack = new Stack<Integer>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                right[mono_stack.peek()] = i;
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }


    /**
     * 接雨水
     * 暴力法
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int ans = 0;
        int size = height.length;
        for (int i = 1; i < size - 1; i++) {
            int max_left = 0, max_right = 0;
            for (int j = i; j >= 0; j--) { //Search the left part for max bar size
                max_left = Math.max(max_left, height[j]);
            }
            for (int j = i; j < size; j++) { //Search the right part for max bar size
                max_right = Math.max(max_right, height[j]);
            }
            ans += Math.min(max_left, max_right) - height[i];
        }
        return ans;
    }

    /**
     * 接雨水
     * 动态编程
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param height
     * @return
     */
    public static int trap1(int[] height) {
        if (height == null) {
            return 0;
        }
        int ans = 0;
        int size = height.length;
        int[] left_max = new int[size];
        int[] right_max = new int[size];
        left_max[0] = height[0];
        for (int i = 1; i < size; i++) {
            left_max[i] = Math.max(height[i], left_max[i - 1]);
        }
        right_max[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            right_max[i] = Math.max(height[i], right_max[i + 1]);
        }
        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(left_max[i], right_max[i]) - height[i];
        }
        return ans;
    }

    /**
     * 接雨水
     * 栈
     * 时间复杂度： O(n)
     * 空间复杂度 O(n)
     *
     * @param height
     * @return
     */
    public static int trap2(int[] height) {
        int ans = 0, current = 0;
        Stack<Integer> st = new Stack<>();
        while (current < height.length) {
            while (!st.empty() && height[current] > height[st.peek()]) {
                int top = st.peek();
                st.pop();
                if (st.empty()) {
                    break;
                }
                int distance = current - st.peek() - 1;
                int bounded_height = Math.min(height[current], height[st.peek()]) - height[top];
                ans += distance * bounded_height;
            }
            st.push(current++);
        }
        return ans;
    }

    /**
     * 接雨水
     * 双指针
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param height
     * @return
     */
    public static int trap3(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;
        int left_max = 0, right_max = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= left_max) {
                    left_max = height[left];
                } else {
                    ans += (left_max - height[left]);
                }
                ++left;
            } else {
                if (height[right] >= right_max) {
                    right_max = height[right];
                } else {
                    ans += (right_max - height[right]);
                }
                --right;
            }
        }
        return ans;
    }

    /**
     * 滑动窗口最大值
     * 暴力法
     * 时间复杂度：O(n * k)
     * 空间复杂度：O(n - k + 1)
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];

        int [] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for(int j = i; j < i + k; j++)
                max = Math.max(max, nums[j]);
            output[i] = max;
        }
        return output;
    }

    ArrayDeque<Integer> deq = new ArrayDeque<Integer>();
    int [] nums;

    public void clean_deque(int i, int k) {
        // remove indexes of elements not from sliding window
        if (!deq.isEmpty() && deq.getFirst() == i - k)
            deq.removeFirst();

        // remove from deq indexes of all elements
        // which are smaller than current element nums[i]
        while (!deq.isEmpty() && nums[i] > nums[deq.getLast()])                           deq.removeLast();
    }

    /**
     * 滑动窗口最大值
     * 双向队列
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        // init deque and output
        this.nums = nums;
        int max_idx = 0;
        for (int i = 0; i < k; i++) {
            clean_deque(i, k);
            deq.addLast(i);
            // compute max in nums[:k]
            if (nums[i] > nums[max_idx]) max_idx = i;
        }
        int [] output = new int[n - k + 1];
        output[0] = nums[max_idx];

        // build output
        for (int i  = k; i < n; i++) {
            clean_deque(i, k);
            deq.addLast(i);
            output[i - k + 1] = nums[deq.getFirst()];
        }
        return output;
    }

    /**
     * 滑动窗口最大值
     * 动态规划
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        int [] left = new int[n];
        left[0] = nums[0];
        int [] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            // from left to right
            if (i % k == 0) left[i] = nums[i];  // block_start
            else left[i] = Math.max(left[i - 1], nums[i]);

            // from right to left
            int j = n - i - 1;
            if ((j + 1) % k == 0) right[j] = nums[j];  // block_end
            else right[j] = Math.max(right[j + 1], nums[j]);
        }

        int [] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++)
            output[i] = Math.max(left[i + k - 1], right[i]);

        return output;
    }


}
