package linkedlist;

import java.util.Stack;

public class LinkedLIstExercises {

	@Deprecated
	public static void main(String[] args) {
		SingleLinkedList<Integer> sl = new SingleLinkedList<Integer>();
		sl.add(1);
		sl.add(2);
		sl.add(3);
		sl.add(2);
		sl.add(3);
		Node<Integer> n1 = sl.add(1);
		sl.add(10);
		sl.add(3);
		sl.add(1);
		Node<Integer> n2 = sl.add(10);

		n2.next = n1;

		// detectLoopNode(sl.root);

		SingleLinkedList<Integer> sl1 = new SingleLinkedList<Integer>();
		sl1.add(9);
		sl1.add(8);
		sl1.add(5);
		sl1.add(5);
		sl1.add(8);
		sl1.add(9);
		sl1.add(1);
		sl1.add(2);
		sl1.add(3);

		// System.out.println(isPalindrome(sl1.root));

		// System.out.println(isPalindromeRecursive(sl1.root, sl1.size()).flag);

		// Node<Integer> head = sl1.root;
		// nthToLast(head,3);
		// partitionList(head, 2);

		// Node<Integer> head = addLists(sl.root,sl1.root,0);
		// SingleLinkedList.print(head);

	}

	private static Node<Integer> addLists(Node<Integer> head1,
			Node<Integer> head2, int carry) {

		if (head1 == null || head2 == null) {
			return null;
		}

		Node<Integer> result = new Node<Integer>();
		int data = head1.data + head2.data + carry;
		int value = data % 10;
		carry = data / 10;

		result.data = value;
		Node<Integer> t = addLists(head1.next, head2.next, carry);

		result.next = t;

		return result;

	}

	private static int nthToLast(Node<Integer> head, int k) {
		// TODO Auto-generated method stub
		if (head == null)
			return 0;

		int i = nthToLast(head.next, k) + 1;
		if (i == k)
			System.out.println(head.data);
		return i;

	}

	private static void partitionList(Node<Integer> head, Integer d) {
		Node<Integer> bHead = null;
		Node<Integer> aHead = null;

		Node<Integer> t = head;

		while (t != null) {
			if (t.data <= d) {
				Node<Integer> node = new Node<Integer>(t.data);
				node.next = bHead;
				bHead = node;
			} else {
				Node<Integer> node = new Node<Integer>(t.data);
				node.next = aHead;
				aHead = node;
			}
			t = t.next;
		}

		t = bHead;
		while (t.next != null) {
			t = t.next;
		}

		t.next = aHead;

		new SingleLinkedList<Integer>(bHead).print();

	}

	private static void detectLoopNode(Node<Integer> head) {

		Node<Integer> slowPtr = null;
		Node<Integer> fastPtr = null;

		slowPtr = fastPtr = head;
		slowPtr = slowPtr.next;
		fastPtr = fastPtr.next.next;

		while (slowPtr != fastPtr && slowPtr != null && fastPtr != null) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
		}

		slowPtr = head;

		while (slowPtr != fastPtr) {
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next;

		}

		System.out.println(slowPtr.data);

	}

	public static Boolean isPalindrome(Node<Integer> head) {

		Stack<Integer> st = new Stack<Integer>();
		Node<Integer> slow = head;
		Node<Integer> fast = head;

		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			st.push(slow.data);
			slow = slow.next;
		}

		// 1,2,3,4,5,6,7
		if (fast != null) {
			slow = slow.next;

			while (slow != null) {
				if (st.pop() != slow.data)
					return false;
			}
		}

		return true;
	}

	public static class Result<T> {
		Boolean flag;
		Node<T> node;

		public Result(Boolean arg1, Node<T> arg2) {
			flag = arg1;
			node = arg2;
		}
	}

	public static Result<Integer> isPalindromeRecursive(Node<Integer> head,
			int length) {

		if (head == null || length == 0)
			return new Result<Integer>(true, null);

		if (length == 1)
			return new Result<Integer>(true, head.next);

		if (length == 2)
			return new Result<Integer>(head.data == head.next.data,
					head.next.next);

		Result<Integer> c1 = isPalindromeRecursive(head.next, length - 2);

		if (!c1.flag) {
			return c1;
		}

		if (c1.node.data != head.data) {
			c1.flag = false;

		}
		c1.node.data = head.next.data;
		return c1;

	}

}

// *******************
class ReverseKLL {
	static class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
			next = null;
		}
	}

	public static Node head = null; // Create head node

	// Simple iterative Reversal of a linked list
	private static Node iterativeReverse(Node node) {

		if (node == null || node.next == null)
			return node;
		Node curr = node, prev = null, next = node;
		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;

		}
		return prev;
	}

	// Simple Recursive Reverse of a linked list
	private static Node recursiveReverse(Node node) {
		if (node == null || node.next == null)
			return node;

		Node rest = recursiveReverse(node.next);

		node.next.next = node;
		node.next = null;
		return rest;
	}

	// Recursive Reversal of a group of k elements
	// 1. Reverse the first sub-list of k element and keep track of next and
	// previous nodes
	// 2. head.next = reverse(next,k ) - recursively call this function on
	// sub-groups
	// 3. return prev - prev becomes new next
	private static Node reverse(Node node, int k) {
		Node current = node;
		Node next = node, prev = null;
		int count = 0;
		// reverse first k elements
		while (current != null && count < k) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
			count++;
		}
		// next is now pointer to k+1th node. Recursively call reverse
		// for the list starting from that point
		if (next != null) {
			node.next = reverse(next, k);
		}
		// prev is the new head
		return prev;
	}

	// 1,2,3,4,5,6,7,8
	private static Node reversek(Node root, int k) {
		Node prev = null, curr = root, next = root;
		int count = 0;
		while (curr != null && count < k) {
			System.out.println();
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;

			count++;
			System.out.format("prev:%d curr %d next:%d count:%d",
					prev == null ? -1 : prev.data, curr == null ? -1
							: curr.data, next == null ? -1 : next.data, count);
		}
		if (next != null)
			root.next = reversek(curr, k);
		return prev;
	}

	// 1,2,3,4,5,6,7,8
	private static Node reverse1k(Node root, int k) {
		if (root == null)
			return root;
		Node prev = null, curr = root, next = root;
		int count = 1;
		while (curr != null && count < k) {
			System.out.println();
			next = curr.next;
			// curr.next = prev;
			prev = curr;
			curr = next;

			count++;
			System.out.format("prev:%d curr %d next:%d count:%d",
					prev == null ? -1 : prev.data, curr == null ? -1
							: curr.data, next == null ? -1 : next.data, count);
		}
		if (curr == null)
			return root;
		else {
			next = curr.next;
			prev.next.next = root.next;
			prev.next = root;
			root.next = reverse1k(next, k);
			return curr;
		}
	}

	// Push all the odds behind the list
	// 1-2-3-4-5-6-7-8-1-2-3-4-5-6-7-8-null
	// 2-2-4-3-5-7
	/*
	 * System.out.print("curr->"); System.out.println(curr.data);
	 * System.out.print("prev->"); System.out.println(prev.data);
	 * System.out.print("next->"); System.out.println(next == null ? -1 :
	 * next.data); System.out.print("head->"); printList(head);
	 * System.out.print("oddhead->"); printList(oddHead);
	 * 
	 * System.out.println("-----------");
	 */
	private static Node pushOddBack(Node head) {
		if (head == null || head.next == null)
			return head;
		Node oddHead = null, oddTail = null; //head and tail of new linked list which contains odd no.
		while (head.data % 2 != 0) {
			Node newHead = head.next;
			if (oddHead == null) {
				oddHead = head;
				oddTail = head;
			} else {
				oddTail.next = head;
				oddTail = head;
			}
			oddTail.next = null;
			head = newHead;
		}
		Node curr = head, prev = head, next = null;
		while (curr != null) {
			next = curr.next;
			if (curr.data % 2 != 0) {
				oddTail.next = curr;
				oddTail = curr;
				oddTail.next = null;
				prev.next = next;
				curr = next;
			} else {
				prev = curr;
				curr = next;
			}
		}
		prev.next = oddHead; // appending odd list to the end
		return head;

	}

	// utility methods
	// push a node in the ll
	private static void push(int data) {
		Node newnode = new Node(data);
		newnode.next = head;
		head = newnode;

	}

	public static void printList(Node node) {
		while (node != null) {
			System.out.print(node.data + "->");
			node = node.next;
		}
		System.out.println();
	}

	public static void main(String args[]) {
		ReverseKLL kll = new ReverseKLL();
		kll.push(8);
		kll.push(8);
		kll.push(8);
		kll.push(7);
		kll.push(6);
		kll.push(5);
		kll.push(4);
		kll.push(3);
		kll.push(2);
		kll.push(1);
		kll.push(8);
		kll.push(7);
		kll.push(6);
		kll.push(5);
		kll.push(4);
		kll.push(3);
		kll.push(2);
		kll.push(1);
		kll.push(3);
		kll.push(3);
		kll.push(3);

		kll.printList(head);
		// head = kll.reverse(head, 3);
		// head = kll.reverse1k(head, 5);
		// head = kll.recursiveReverse(head);
		// head = kll.iterativeReverse(head);
		head = kll.pushOddBack(head);
		System.out.println("===========================");
		kll.printList(head);

	}
}
