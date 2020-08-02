package com.tool.demo.algorithm;

/**
 * ListNode
 *
 * @author WangChao
 * @date 2020/08/02
 **/
public class ListNode {

//    int val;
//    ListNode next;
//    ListNode(int x) {
//        val = x;
//    }

//    int val;
//    ListNode next;
//    ListNode(int x) {
//        val = x;
//        next = null;
//    }

    int val;
    ListNode next;
    ListNode() {
    }
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

}
