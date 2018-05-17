public class HashMap {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap();
        for(int i = 0; i < nums.length; i++) {
            if(map.get(nums[i])!=null) {
                int[] result = {i, map.get(nums[i])};
                return result;
            }
            map.put(target - nums[i], i);
        }
        int[] result = null;
        return result;
    }
}
