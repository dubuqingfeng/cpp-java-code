public class Solution {
    public int reverse(int x) {
        int result = 0;
        while(true) {
			if (result > (Integer.MAX_VALUE / 10)){
				return 0;
			}
			
			if (result < (-Integer.MIN_VALUE / 10)){
				return 0;
			}
            result = result * 10 + x % 10;
            x = x / 10;
            if (x ==0)
            {
                break;
            }
        }
        return result;
    }
}
