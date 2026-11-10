import java.util.Scanner;

public class CustomerTransactionManager {

    // Display all transaction records
    public static void displayTransactions(
            String[] ids, double[] amounts, double[][] details) {

        System.out.println("\n========== CUSTOMER TRANSACTIONS ==========");

        System.out.printf("%-12s %-15s %-12s %-12s %-12s%n",
                "Customer ID", "Total Amount", "Category 1",
                "Category 2", "Category 3");

        for (int i = 0; i < amounts.length; i++) {

            System.out.printf("%-12s %-15.2f %-12.2f %-12.2f %-12.2f%n",
                    ids[i],
                    amounts[i],
                    details[i][0],
                    details[i][1],
                    details[i][2]);
        }

        System.out.println("============================================");
    }

    // Swap two customer records
    public static void swap(String[] ids, double[] amounts,
                            double[][] details, int i, int j) {

        String tempID = ids[i];
        ids[i] = ids[j];
        ids[j] = tempID;

        double tempAmount = amounts[i];
        amounts[i] = amounts[j];
        amounts[j] = tempAmount;

        double[] tempDetails = details[i];
        details[i] = details[j];
        details[j] = tempDetails;
    }

    // Bubble Sort
    public static void bubbleSort(String[] ids, double[] amounts,
                                  double[][] details, boolean ascending) {

        int n = amounts.length;

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - 1 - i; j++) {

                boolean condition = ascending
                        ? amounts[j] > amounts[j + 1]
                        : amounts[j] < amounts[j + 1];

                if (condition) {
                    swap(ids, amounts, details, j, j + 1);
                }
            }
        }
    }

    // Insertion Sort
    public static void insertionSort(String[] ids, double[] amounts,
                                     double[][] details, boolean ascending) {

        for (int i = 1; i < amounts.length; i++) {

            String keyID = ids[i];
            double keyAmount = amounts[i];
            double[] keyDetails = details[i];

            int j = i - 1;

            while (j >= 0 &&
                    (ascending
                    ? amounts[j] > keyAmount
                    : amounts[j] < keyAmount)) {

                ids[j + 1] = ids[j];
                amounts[j + 1] = amounts[j];
                details[j + 1] = details[j];

                j--;
            }

            ids[j + 1] = keyID;
            amounts[j + 1] = keyAmount;
            details[j + 1] = keyDetails;
        }
    }

    // Selection Sort
    public static void selectionSort(String[] ids, double[] amounts,
                                     double[][] details, boolean ascending) {

        int n = amounts.length;

        for (int i = 0; i < n - 1; i++) {

            int selected = i;

            for (int j = i + 1; j < n; j++) {

                boolean condition = ascending
                        ? amounts[j] < amounts[selected]
                        : amounts[j] > amounts[selected];

                if (condition) {
                    selected = j;
                }
            }

            if (selected != i) {
                swap(ids, amounts, details, i, selected);
            }
        }
    }

    // Merge Sort
    public static void mergeSort(String[] ids, double[] amounts,
                                 double[][] details, boolean ascending) {

        if (amounts.length <= 1) {
            return;
        }

        int middle = amounts.length / 2;

        String[] leftIDs = new String[middle];
        String[] rightIDs = new String[amounts.length - middle];

        double[] leftAmounts = new double[middle];
        double[] rightAmounts = new double[amounts.length - middle];

        double[][] leftDetails = new double[middle][];
        double[][] rightDetails = new double[amounts.length - middle][];

        for (int i = 0; i < middle; i++) {
            leftIDs[i] = ids[i];
            leftAmounts[i] = amounts[i];
            leftDetails[i] = details[i];
        }

        for (int i = middle; i < amounts.length; i++) {
            int k = i - middle;

            rightIDs[k] = ids[i];
            rightAmounts[k] = amounts[i];
            rightDetails[k] = details[i];
        }

        mergeSort(leftIDs, leftAmounts, leftDetails, ascending);
        mergeSort(rightIDs, rightAmounts, rightDetails, ascending);

        merge(ids, amounts, details,
                leftIDs, leftAmounts, leftDetails,
                rightIDs, rightAmounts, rightDetails, ascending);
    }

    // Merge the two sorted halves
    public static void merge(
            String[] ids, double[] amounts, double[][] details,
            String[] leftIDs, double[] leftAmounts, double[][] leftDetails,
            String[] rightIDs, double[] rightAmounts, double[][] rightDetails,
            boolean ascending) {

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < leftAmounts.length && j < rightAmounts.length) {

            boolean condition = ascending
                    ? leftAmounts[i] <= rightAmounts[j]
                    : leftAmounts[i] >= rightAmounts[j];

            if (condition) {

                ids[k] = leftIDs[i];
                amounts[k] = leftAmounts[i];
                details[k] = leftDetails[i];

                i++;

            } else {

                ids[k] = rightIDs[j];
                amounts[k] = rightAmounts[j];
                details[k] = rightDetails[j];

                j++;
            }

            k++;
        }

        while (i < leftAmounts.length) {

            ids[k] = leftIDs[i];
            amounts[k] = leftAmounts[i];
            details[k] = leftDetails[i];

            i++;
            k++;
        }

        while (j < rightAmounts.length) {

            ids[k] = rightIDs[j];
            amounts[k] = rightAmounts[j];
            details[k] = rightDetails[j];

            j++;
            k++;
        }
    }

    // Main method
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println(" CUSTOMER TRANSACTION MANAGEMENT SYSTEM");
        System.out.println("==========================================");

        // Ask for number of customers
        System.out.print("Enter number of customer transactions: ");
        int count = scanner.nextInt();

        while (count <= 0) {
            System.out.print("Please enter a number greater than 0: ");
            count = scanner.nextInt();
        }

        // 1D Arrays
        String[] transactionIDs = new String[count];
        double[] transactionAmounts = new double[count];

        // 2D Array: Customers x 3 item categories
        double[][] itemDetails = new double[count][3];

        // Input customer information
        for (int i = 0; i < count; i++) {

            System.out.println("\n--- Customer " + (i + 1) + " ---");

            System.out.print("Enter Transaction ID: ");
            transactionIDs[i] = scanner.next();

            System.out.print("Enter Total Purchase Amount: ");
            transactionAmounts[i] = scanner.nextDouble();

            for (int j = 0; j < 3; j++) {

                System.out.print("Enter Category " + (j + 1)
                        + " Amount: ");

                itemDetails[i][j] = scanner.nextDouble();
            }
        }

        // Display original records
        displayTransactions(
                transactionIDs, transactionAmounts, itemDetails);

        // Choose sorting algorithm
        System.out.println("\nSELECT SORTING ALGORITHM");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Insertion Sort");
        System.out.println("3. Selection Sort");
        System.out.println("4. Merge Sort");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        while (choice < 1 || choice > 4) {
            System.out.print("Invalid choice. Enter 1-4: ");
            choice = scanner.nextInt();
        }

        // Choose sorting order
        System.out.println("\nSELECT SORTING ORDER");
        System.out.println("1. Ascending (Lowest to Highest)");
        System.out.println("2. Descending (Highest to Lowest)");

        System.out.print("Enter your choice: ");
        int order = scanner.nextInt();

        while (order != 1 && order != 2) {
            System.out.print("Invalid choice. Enter 1 or 2: ");
            order = scanner.nextInt();
        }

        boolean ascending = (order == 1);

        // Execute selected algorithm
        switch (choice) {

            case 1:
                bubbleSort(transactionIDs, transactionAmounts,
                        itemDetails, ascending);
                System.out.println("\nUsing Bubble Sort...");
                break;

            case 2:
                insertionSort(transactionIDs, transactionAmounts,
                        itemDetails, ascending);
                System.out.println("\nUsing Insertion Sort...");
                break;

            case 3:
                selectionSort(transactionIDs, transactionAmounts,
                        itemDetails, ascending);
                System.out.println("\nUsing Selection Sort...");
                break;

            case 4:
                mergeSort(transactionIDs, transactionAmounts,
                        itemDetails, ascending);
                System.out.println("\nUsing Merge Sort...");
                break;
        }

        // Display sorted records
        System.out.println(ascending
                ? "\nSORTED TRANSACTIONS: ASCENDING ORDER"
                : "\nSORTED TRANSACTIONS: DESCENDING ORDER");

        displayTransactions(
                transactionIDs, transactionAmounts, itemDetails);

        System.out.println("\nProgram completed successfully!");

        scanner.close();
    }
}
