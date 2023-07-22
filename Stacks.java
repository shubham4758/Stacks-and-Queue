1.// Solution


public class GoodString {

    public static String makeGood(String s) {
        StringBuilder stack = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (stack.length() > 0 && Math.abs(stack.charAt(stack.length() - 1) - ch) == 'a' - 'A') {
                stack.deleteCharAt(stack.length() - 1);
            } else {
                stack.append(ch);
            }
        }

        return stack.toString();
    }

    public static void main(String[] args) {
        String s1 = "leEeetcode";
        System.out.println(makeGood(s1));  // Output: "leetcode"

        String s2 = "abBAcC";
        System.out.println(makeGood(s2));  // Output: ""

        String s3 = "s";
        System.out.println(makeGood(s3));  // Output: "s"
    }
}




2.// Solution


public class RemoveDuplicates {

    public static String removeDuplicates(String s) {
        StringBuilder stack = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (stack.length() > 0 && stack.charAt(stack.length() - 1) == ch) {
                stack.deleteCharAt(stack.length() - 1);
            } else {
                stack.append(ch);
            }
        }

        return stack.toString();
    }

    public static void main(String[] args) {
        String s1 = "abbaca";
        System.out.println(removeDuplicates(s1));  // Output: "ca"

        String s2 = "azxxzy";
        System.out.println(removeDuplicates(s2));  // Output: "ay"
    }
}



3.// Solution



import java.util.Stack;

public class StockSpanner {

    private Stack<int[]> stack;

    public StockSpanner() {
        stack = new Stack<>();
    }

    public int next(int price) {
        int span = 1;
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1];
        }
        stack.push(new int[]{price, span});
        return span;
    }

    public static void main(String[] args) {
        StockSpanner stockSpanner = new StockSpanner();
        System.out.println(stockSpanner.next(100)); // Output: 1
        System.out.println(stockSpanner.next(80));  // Output: 1
        System.out.println(stockSpanner.next(60));  // Output: 1
        System.out.println(stockSpanner.next(70));  // Output: 2
        System.out.println(stockSpanner.next(60));  // Output: 1
        System.out.println(stockSpanner.next(75));  // Output: 4
        System.out.println(stockSpanner.next(85));  // Output: 6
    }
}



4.// Solution


public class TicketBuyTime {

    public static int timeTakenForKthPerson(int[] tickets, int k) {
        int n = tickets.length;
        int time = 0;

        while (tickets[k] > 0) {
            for (int i = 0; i < n && tickets[k] > 0; i++) {
                int minTickets = Math.min(tickets[i], tickets[k] > 0 ? 1 : 0);
                tickets[i] -= minTickets;
                tickets[k] -= minTickets;
                time += minTickets;
            }
        }

        return time;
    }

    public static void main(String[] args) {
        int[] tickets1 = {2, 3, 2};
        int k1 = 2;
        System.out.println(timeTakenForKthPerson(tickets1, k1)); // Output: 6

        int[] tickets2 = {5, 1, 1, 1};
        int k2 = 0;
        System.out.println(timeTakenForKthPerson(tickets2, k2)); // Output: 8
    }
}



5.// Solution


import java.util.ArrayList;
import java.util.List;

public class ProductOfNumbers {

    private List<Integer> nums;
    private List<Integer> cumulativeProduct;

    public ProductOfNumbers() {
        nums = new ArrayList<>();
        cumulativeProduct = new ArrayList<>();
        cumulativeProduct.add(1); // The cumulative product at position 0 is 1 (identity element for multiplication)
    }

    public void add(int num) {
        nums.add(num);
        if (num == 0) {
            cumulativeProduct.clear();
            cumulativeProduct.add(1);
        } else {
            cumulativeProduct.add(cumulativeProduct.get(cumulativeProduct.size() - 1) * num);
        }
    }

    public int getProduct(int k) {
        int n = nums.size();
        if (k >= n) {
            return 0;
        }

        int totalProduct = cumulativeProduct.get(n - 1);
        int kStepBackProduct = cumulativeProduct.get(n - k - 1);

        return totalProduct / kStepBackProduct;
    }

    public static void main(String[] args) {
        ProductOfNumbers productOfNumbers = new ProductOfNumbers();
        productOfNumbers.add(3);
        productOfNumbers.add(0);
        productOfNumbers.add(2);
        productOfNumbers.add(5);
        productOfNumbers.add(4);
        System.out.println(productOfNumbers.getProduct(2)); // Output: 20
        System.out.println(productOfNumbers.getProduct(3)); // Output: 40
        System.out.println(productOfNumbers.getProduct(4)); // Output: 0
        productOfNumbers.add(8);
        System.out.println(productOfNumbers.getProduct(2)); // Output: 32
    }
}



6.// Solution


import java.util.Stack;

public class LargestRectangleInHistogram {

    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights1 = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(heights1)); // Output: 10

        int[] heights2 = {2, 4};
        System.out.println(largestRectangleArea(heights2)); // Output: 4
    }
}



7.// Solution


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MaxSlidingWindow {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // Remove the elements that are out of the current window from the front of the deque
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // Remove elements that are smaller than the current element from the back of the deque
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);

            // The front of the deque represents the maximum element in the current window
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(nums1, k1))); // Output: [3, 3, 5, 5, 6, 7]

        int[] nums2 = {1};
        int k2 = 1;
        System.out.println(Arrays.toString(maxSlidingWindow(nums2, k2))); // Output: [1]
    }
}



8.// Solution


public class CircularQueue {

    private int[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public CircularQueue(int k) {
        capacity = k;
        queue = new int[capacity];
        front = -1;
        rear = -1;
        size = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }

        rear = (rear + 1) % capacity;
        queue[rear] = value;
        if (front == -1) {
            front = rear;
        }
        size++;

        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }

        front = (front + 1) % capacity;
        size--;

        if (isEmpty()) {
            front = -1;
            rear = -1;
        }

        return true;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }

        return queue[front];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }

        return queue[rear];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public static void main(String[] args) {
        CircularQueue circularQueue = new CircularQueue(5);
        System.out.println(circularQueue.enQueue(1));  // Output: true
        System.out.println(circularQueue.enQueue(2));  // Output: true
        System.out.println(circularQueue.enQueue(3));  // Output: true
        System.out.println(circularQueue.enQueue(4));  // Output: true
        System.out.println(circularQueue.Rear());     // Output: 4
        System.out.println(circularQueue.isFull());   // Output: true
        System.out.println(circularQueue.deQueue());   // Output: true
        System.out.println(circularQueue.enQueue(5));  // Output: true
        System.out.println(circularQueue.Rear());     // Output: 5
    }
}
