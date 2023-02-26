//Question 5
//b)
//        Assume an electric vehicle must go from source city s to destination city d. You can locate many service centers
//        along the journey that allow for the replacement of batteries; however, each service center provides batteries with a
//        specific capacity. You are given a 2D array in which servicecenter[i]=[xi,yj] indicates that the ith service center is
//        xi miles from the source city and offers yj miles after the automobile can travel after replacing batteries at specific
//        service centers. Return the number of times the car's batteries need to be replaced before arriving at the destination.
//        Input: serviceCenters = [{10,60},{20,30},{30,30},{60,40}], targetMiles= 100, startChargeCapacity = 10
//        Output: 2
//        Explanation: The car can go 10 miles on its initial capacity; after 10 miles, the car replaces batteries with a
//        capacity of 60 miles; and after travelling 50 miles, at position 60 we change batteries with a capacity of 40 miles;
//        and ultimately, we may arrive at our destination.

import java.util.Arrays;
public class Question5B {
    public static int batteryReplacement(int[][] serviceCenters, int targetMiles, int startChargeCapacity) {
        Arrays.sort(serviceCenters, (a, b) -> a[0] - b[0]);
        int count = 0;
        int curMiles = startChargeCapacity;
        int i = 0;
        while (curMiles < targetMiles) {
            int maxMiles = 0;
            while (i < serviceCenters.length && serviceCenters[i][0] <= curMiles) {
                maxMiles = Math.max
                        (maxMiles, serviceCenters[i][1]);
                i++;
            }
            if (maxMiles == 0) {
                return -1;
            }
            curMiles = maxMiles + curMiles;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] serviceCenters = {{10,60},{20,30},{30,30},{60,40}};
        int targetMiles = 100;
        int startChargeCapacity = 10;
        System.out.println(batteryReplacement(serviceCenters, targetMiles, startChargeCapacity)); // output: 3
    }


}