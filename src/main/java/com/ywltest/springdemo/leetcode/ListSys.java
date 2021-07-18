package com.ywltest.springdemo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yangWenlong
 * @date 2021/2/9- ${time}
 */
public class ListSys {

    public static void main(String[] args) {

//        int[] integerList = new int[5];
//        integerList[0]=1;
//        integerList[1]=3;
//        integerList[2]=4;
//        integerList[3]=5;
//        int[] integerListx={1,2,4,6,5};
//
//
//        int i = searchInsert(integerList, -1);
//
//        System.out.println("i:"+i);
//
//        int t = searchInsert1(integerList, 5);
//        System.out.println("t:"+t);

        int[][] ints = generateMatrix(5);
        System.out.println(Arrays.deepToString(ints));
    }


    static int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n - 1; // 定义target在左闭右闭的区间里，[left, right]
        while (left <= right) { // 当left==right，区间[left, right]依然有效
            int middle = left + ((right - left) / 2);// 防止溢出 等同于(left + right)/2
            if (nums[middle] > target) {
                right = middle - 1; // target 在左区间，所以[left, middle - 1]
            } else if (nums[middle] < target) {
                left = middle + 1; // target 在右区间，所以[middle + 1, right]
            } else { // nums[middle] == target
                return middle;
            }
        }
        // 分别处理如下四种情况
        // 目标值在数组所有元素之前  [0, -1]
        // 目标值等于数组中某一个元素  return middle;
        // 目标值插入数组中的位置 [left, right]，return  right + 1
        // 目标值在数组所有元素之后的情况 [left, right]， return right + 1
        return right + 1;
    }

    static int searchInsert1(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n; // 定义target在左闭右开的区间里，[left, right)  target
        while (left < right) { // 因为left == right的时候，在[left, right)是无效的空间
            int middle = left + ((right - left) >> 1);
            if (nums[middle] > target) {
                right = middle; // target 在左区间，在[left, middle)中
            } else if (nums[middle] < target) {
                left = middle + 1; // target 在右区间，在 [middle+1, right)中
            } else { // nums[middle] == target
                return middle; // 数组中找到目标值的情况，直接返回下标
            }
        }
        // 分别处理如下四种情况
        // 目标值在数组所有元素之前 [0,0)
        // 目标值等于数组中某一个元素 return middle
        // 目标值插入数组中的位置 [left, right) ，return right 即可
        // 目标值在数组所有元素之后的情况 [left, right)，return right 即可
        return right;
    }


    public static int[][] generateMatrix(int n) {
        // 边界
        int left = -1;
        int top = -1;
        int right  = n;
        int bottom = n;
        // 坐标
        int x = 0;
        int y = 0;
        // 循环次数
        int loop = n / 2;
        // n为奇数时，最后一圈是个点，特殊处理（不用这个变量，在循环里判断num==n^2时，直接返回结果也行，但是这样要在【从左往右】和【从下往上】，两个循环里加判断）
        boolean hasCenterPoint = n % 2 == 1;
        // 填充的值
        int num = 1;
        int[][] result = new int[n][n];
        for (int i = 0; i < loop; i++) {
            // 从左往右
            while (y < right) {
                result[x][y++] = num++;
            }
            y--;// y多加了1，要减掉
            top++;// 上面一行遍历完，则边界top要加1
            x++;// x加1，移到下一行
            // 从上往下
            while (x < bottom) {
                result[x++][y] = num++;
            }
            x--;
            right--;
            y--;
            // 从右往左
            while (y > left) {
                result[x][y--] = num++;
            }
            y++;
            bottom--;
            x--;
            // 从下往上
            while (x > top) {
                result[x--][y] = num++;
            }
            x++;
            left++;
            y++;
        }
        if (hasCenterPoint) {
            result[x][y] = num;
        }

        return result;
    }

    public ListNode removeElements(ListNode head, int val) {
        //删除值相同的头结点后，可能新的头结点也值相等，用循环解决
        while(head!=null&&head.val==val){
            head=head.next;
        }
        if(head==null) {
            return head;
        }
        ListNode prev=head;
        //确保当前结点后还有结点
        while(prev.next!=null){
            if(prev.next.val==val){
                prev.next=prev.next.next;
            }else{
                prev=prev.next;
            }
        }
        return head;
    }



}
