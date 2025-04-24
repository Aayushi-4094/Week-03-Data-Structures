public class BinarySearchRotationPoint {
    public static int findRotationPoint(int[] arr) {
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            int mid = (left + right) / 2;
            
            if (arr[mid] > arr[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }

    public static void main(String[] args) {
        int[] arr = {6, 7, 9, 15, 19, 2, 3};
        int rotationPoint = findRotationPoint(arr);
        System.out.println("Rotation Point: " + arr[rotationPoint]);
    }
}
