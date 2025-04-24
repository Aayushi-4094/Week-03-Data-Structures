public class BinarySearchPeakElement {
    public static int findPeakElement(int[] arr) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if ((mid == 0 || arr[mid] > arr[mid - 1]) && (mid == arr.length - 1 || arr[mid] > arr[mid + 1])) {
                return arr[mid];
            } else if (mid > 0 && arr[mid] < arr[mid - 1]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1; // This line is never reached because the peak is guaranteed to be found.
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 20, 4, 1};
        int peakElement = findPeakElement(arr);
        System.out.println("Peak Element: " + peakElement);
    }
}
