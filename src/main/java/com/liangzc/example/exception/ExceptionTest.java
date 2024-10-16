package com.liangzc.example.exception;

public class ExceptionTest {

    public static void main(String[] args) {

        try {
            int[] nums1 = {5, 2, 6, 10, 8, 30, 12};
            int[] nums2 = {1, 5, 3, 0, 9, 8};

            for (int i = 0; i < nums1.length; i++) {
                try {
                    if (nums2[i] == 0) {
                        throw new OwenException("自定义异常提示信息");
                    }
                    System.out.println(nums1[i] / nums2[i]);
                } catch (ArithmeticException e) {
                    System.out.println("除数不能为0");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("数组越界");
                }
            }
        } catch (OwenException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }

}
