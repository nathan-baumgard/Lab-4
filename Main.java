import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
      System.out.println("Unsorted Array ---------------------------------------------------");
      ArrayList<Integer> integerList = Lab4.getList();
      Lab4.outputList(integerList);

      System.out.println("\n\nBubble sort results ----------------------------------------------");
      
      // Run bubble sort and time it
      Long startTime = System.nanoTime();
      ArrayList<Integer> bubbleSortedList = Lab4.bubbleSort(integerList);
      Long endTime = System.nanoTime();
      
      Lab4.outputList(bubbleSortedList);
      System.out.println("\n\nBubble sort time: " + (endTime - startTime) + " nanoseconds");

      // Run insertion sort and time it
      System.out.println("\n\nInsertion sort results -------------------------------------------");
      integerList = Lab4.getList(); // Reset the list to unsorted state
      startTime = System.nanoTime();
      ArrayList<Integer> insertionSortedList = Lab4.insertionSort(integerList);
      endTime = System.nanoTime();

      Lab4.outputList(insertionSortedList);
      System.out.println("\n\nInsertion sort time: " + (endTime - startTime) + " nanoseconds");
    }
}

class Lab4 {
  public static ArrayList<Integer> insertionSort(ArrayList<Integer> integerList) {
    
    // Walk forwrard through list starting at index 1
    for (int i = 1; i < integerList.size(); i++) { 
      
      // Store the current element to be compared
      int currentElement = integerList.get(i);
      int k;

      // Walk backward through sorted sectionand shift elements right
      for (k = i - 1; k >= 0 && integerList.get(k) > currentElement; k--) {

        integerList.set(k + 1, integerList.get(k));

      }

      // Insert the current element into correct position
      integerList.set(k + 1, currentElement);

    }

    return integerList;

  }

  public static ArrayList<Integer> bubbleSort(ArrayList<Integer> integerList) {

    // Indicate if another pass is needed
    boolean needNextPass = true;

    // Walk through the list until no more passes are needed
    for (int k = 1; k < integerList.size() && needNextPass; k++) {

      // Next pass may not be needed
      needNextPass = false;

      // Walk through list 
      for (int i = 0; i < integerList.size() - k; i++) {

        // Swap if next element is smaller than current
        if (integerList.get(i) > integerList.get(i + 1)) {

          int temp = integerList.get(i);
          integerList.set(i, integerList.get(i + 1));
          integerList.set(i+1, temp);
          needNextPass = true;

        }
      }
    }

    return integerList;
    
  }

  public static ArrayList<Integer> getList() {
    ArrayList<Integer> integerList = new ArrayList<>();
    String line;
    try (BufferedReader br = new BufferedReader(new FileReader("integers.txt"))) {
        while ((line = br.readLine()) != null) {
            integerList.add(Integer.parseInt(line));
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return integerList;
  }

  public static void outputList(ArrayList<Integer> integerList) {
    for (int i = 0; i < integerList.size(); i++) {
        System.out.print(integerList.get(i) + " ");
    }
  }
}