package com.liangzc.example.sort;

public class SortTest {

    public static void main(String[] args) {

        int[] nums = {10,-5,22,30,5,12,88,56,54};

        ascTest(nums);
        descTest(nums);
        ascTestReverse(nums);

    }

    /**
     * 例如：8个数，首先最多需要比较7次，之后依次下排：6、 5、 4、 3、 2、 1 由内层循环控制
     * 外层循环控制总体的循环次数
     * @param nums
     * 从数组下标索引顺序处理(从索引0开始，正向)
     */
    public static void ascTest(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            /**
             * nums.length - 1 - i的意思为：第一次需要比较n-1-0次，比如有4个数比较，则第一次需要比较3次找出最大值放在数组最后一位，比较完成后则已经确定了最大的值就在数组的最后一位
             * 第二次则无需与已经找到的最大值作比较，所以只需要比较n-1-1次，找出其余的最大值放在数组的倒数第二位，
             * 第三次则不需要比较前两个最大值了，所以只需要比较n-1-2次，找出剩余的最大的值，再依次放在数组倒数第三位
             * ...............
             * 以此类推即可，最终得到了正序排序的数
             */
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if(nums[j] > nums[j+1]){//正序
                    int temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }

        for (int num : nums) {
            System.out.println(num);
        }
        System.out.println("-------------------------------------------------------------------------");
    }

    /**
     * 倒叙和正序的思路都是一样的，比如有4个数，第一次，则首先比较4-1-0(i)次，将最小的值放置在数组最后一位
     * 第二次则不需要与已经找到的最小值作比较，只需比较4-1-1(i)次，找出其余的数中最小值放置在数组倒数第二位
     * ................
     * 以此类推，便可得到倒叙排序的数
     * @param nums
     */
    public static void descTest(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
               if(nums[j] < nums[j+1]){//倒叙
                   int temp = nums[j + 1];
                   nums[j + 1] = nums[j];
                   nums[j]=temp;
               }
            }
        }

        for (int num : nums) {
            System.out.println(num);
        }
        System.out.println("---------------------------------------------");
    }


    /**
     *
     * @param nums
     * 从数组下标索引逆序处理（从最后一位索引开始，反向）
     */
    public static void ascTestReverse(int[] nums){

//        10,-5,22,30,5,12,88,56,54
        for (int i = 0; i < nums.length; i++) {
            for (int j = nums.length-1; j >i ; j--) {//
                if(nums[j] < nums[j-1]){
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j-1] = temp;
                }
            }
        }
        for (int num : nums) {
            System.out.println(num);
        }

        System.out.println("------------------------------------");
    }


}
