import java.util.Scanner;

public class cc1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s  = scan.nextLine();
        char[] ch = new char[s.length()];
        for (int i=0; i < s.length() ; i++) {
            ch[i] = s.charAt(i);
        }
        mergeSort(ch,0,s.length());
        System.out.println(ch);
    }

    public static void mergeSort(char[] a, int i, int j) {
        if (i < j) {
            int mid = (i + j) /2;
            mergeSort(a,i,mid);
            mergeSort(a,mid+1,j);
            merge(a,i,mid,j);
        }
    }

    public static void merge(char[] a, int i, int mid, int j) {
        char[] temp = new char[j-i+1];
        int left = i, right = mid + 1, it=0;
        while (left <=mid && right<=j) {
            if (a[left] <= a[right]) {
                temp[it++] = a[left++];
            } else {
                temp[it++] = a[right++];
            }
        }

        while (left<=mid) {
            temp[it++] = a[left++];
        }
        while (right<=j) {
            temp[it++] = a[right++];
        }

        for (int k = 0; k < temp.length; k++) {
            a[i+k] = temp[k];
        }
    }

}
