// 결과 제출시 : 삭제
package _2_H_Sorting;

// 결과 제출시 : 학번 이름 기재하기


/**
* Definition for singly-linked list.
* public class ListNode {
* int val;
* ListNode next;
* ListNode() {}
* ListNode(int val) { this.val = val; }
* ListNode(int val, ListNode next) { this.val = val; this.next = next; }
* }
*/
// => 임시 구현체
class ListNode {
 int val;
 ListNode next;
 ListNode() {}
 ListNode(int val) { this.val = val; }
 ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

//결과 제출시 : 클래스 명은 Solution
public class SortingHomework2 {
    public ListNode insertionSortList(ListNode head) {
        ListNode sortedListHeadNode = new ListNode();
        sortedListHeadNode.next = null;
        ListNode currentPos = sortedListHeadNode;
        ListNode newItem = null;

        while (head != null) {
            newItem = head;
            head = head.next;

            if (newItem.val < currentPos.val)
                currentPos = sortedListHeadNode;
            while (currentPos.next != null
                && newItem.val >= currentPos.next.val)
                currentPos = currentPos.next;
            
            newItem.next = currentPos.next;
            currentPos.next = newItem;
        }

        return sortedListHeadNode.next;
    }
}
