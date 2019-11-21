/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Daniel Allen
 */
public class Sorting {

    public static void main(String[] args) {
        //Daniel
        try {
            //Fill the array list with values
            BufferedReader br = new BufferedReader(new FileReader(new File("TheListFile.txt")));

            ArrayList<String> list = new ArrayList<String>();
            String line = "";
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            br.close();
            System.out.println("Unsorted:                    " + list);
            list = selectionSort(list);
            System.out.println("Selection Sort:              " + list);
            list = bubbleSort(list);
            System.out.println("Bubble Sort:                 " + list);
            list = insertionSort(list, true);
            System.out.println("Insertion Sort (ascending):  " + list);
            list = insertionSort(list, false);
            System.out.println("Insertion Sort (descending): " + list);
            System.out.println("\n\n");
            //boolean asc = true;
            line = "";
            Scanner scan = new Scanner(System.in);
            while (!line.equalsIgnoreCase("9")) {
                System.out.println("Choose:");
                System.out.println("\u001B[2m\u001B[32m1\u001B[0m - Read Names from File and display the list\u001B[0m");
                System.out.println("\u001B[2m\u001B[32m2\u001B[0m - Selection Sort Ascending and display the list\u001B[0m");
                System.out.println("\u001B[2m\u001B[32m3\u001B[0m - Selection Sort Descending and display the list\u001B[0m");
                System.out.println("\u001B[2m\u001B[32m4\u001B[0m - Bubble Sort Ascending and display the list\u001B[0m");
                System.out.println("\u001B[2m\u001B[32m5\u001B[0m - Bubble Sort Descending and display the list\u001B[0m");
                System.out.println("\u001B[2m\u001B[32m6\u001B[0m - Insertion Sort Ascending and display the list\u001B[0m");
                System.out.println("\u001B[2m\u001B[32m7\u001B[0m - Insertion Sort Descending and display the list\u001B[0m");
                System.out.println("\u001B[2m\u001B[32m8\u001B[0m - Binary Search - Ask user for a string to search for and return the position of the item in the array list.\u001B[0m");
                System.out.println("\u001B[2m\u001B[32m9\u001B[0m - Exit\u001B[0m");
                //ghost code was for a custom menu
                /*System.out.println("T: Set mode to \u001B[2m\u001B[32m" + (!asc ? "ASCENDING" : "DESCENDING") + "\u001B[0m");
                System.out.println("R: Randomize order of list  ");
                System.out.println("S: Sort using \u001B[2m\u001B[32mSelection Sort\u001B[0m");
                System.out.println("B: Sort using \u001B[2m\u001B[32mBubble Sort   \u001B[0m");
                System.out.println("I: Sort using \u001B[2m\u001B[32mInsertion Sort\u001B[0m");
                System.out.println("P: Print list               ");*/

                System.out.print("Enter your command: ");
                line = scan.nextLine();
                if (!line.equalsIgnoreCase("9")) {
                    switch (line.toUpperCase()) {
                        //Following ghost code was meant to be a custom menu system but isn't used. Kept around to possibly switch it later.
                        /*case "T":
                        asc = !asc;
                        System.out.println("Set mode to \u001B[2m\u001B[32m" + (asc ? "ASCENDING" : "DESCENDING") + "\u001B[0m");
                        break;
                        case "R":
                        List<String> possible = new ArrayList<>();
                        for (String s : list) {
                        possible.add(s);
                        }
                        list.clear();
                        while (possible.size() > 0) {
                        int r = (int) Math.floor(Math.random() * possible.size());
                        list.add(possible.get(r));
                        possible.remove(r);
                        }
                        System.out.println("Randomized");
                        break;
                        case "S":

                        break;
                        case "B":

                        break;
                        case "I":
                        list = insertionSort(list, asc);
                        break;
                        case "P":
                        System.out.println(list);
                        break;*/
                        case "1":
                            //read names from file
                            break;
                        case "2":
                            //sel sort asc
                            break;
                        case "3":
                            //sel sort desc
                            break;
                        case "4":
                            //bub sort asc
                            break;
                        case "5":
                            //bub sort desc
                            break;
                        case "6":
                            //ins sort asc
                            insertionSort(list, true);
                            System.out.println(joinList(list));
                            break;
                        case "7":
                            //ins sort asc
                            insertionSort(list, false);
                            System.out.println(joinList(list));
                            break;
                        case "8":
                            //bin search
                            boolean asc = false;
                            System.out.print("Enter search queury: ");
                            String target = scan.nextLine();
                            boolean valid = false;
                            if(target == null){
                                target = "";
                            }
                            int foundAt = binarySearch(list, target, asc);
                            System.out.println();
                            break;
                        default:
                            System.out.println("\u001B[31mUnknown command\u001B[0m");
                    }
                }
                System.out.println("\n\n");
            }
            scan.close();
        } catch (IOException ex) {
            Logger.getLogger(Sorting.class.getName()).log(Level.WARNING, "File not found", ex);
        }
    }

    /**
     * Joins a list with a comma and space (", ") delimiter
     *
     * @author Daniel
     * @param list The list to join
     * @return the list joined by ", "
     */
    public static String joinList(List<String> list) {
        //could use String.join or Stream but I found this approach to consistantly be 150-500ns faster
        StringBuilder sb = new StringBuilder(list.size());
        for (int z = 0; z < list.size() - 1; z++) {
            sb.append(list.get(z));
            sb.append(',').append(' ');
        }
        sb.append(list.get(list.size() - 1));
        return sb.toString();
    }

    /**
     * Sorts a list alphabetically using the insertion sort algorithm in
     * Θ(n<sup>2</sup>) time
     * <br>
     * <b>&nbsp;&nbsp;&nbsp;&nbsp;- Daniel</b>
     * <br>
     * Note: I originally used a List&lt;String&gt; as the list parameter, but
     * to add compatibility with the other sorting algorithms I made it a type
     * instead
     *
     * @param <T> extends List&lt;String&gt;
     * @param list List to sort
     * @param ascending <code>true</code> to sort A-Z, <code>false</code> to
     * sort Z-A
     * @return Sorted List
     * @author Daniel
     */
    public static <T extends List<String>> T insertionSort(T list, boolean ascending) {
        //Daniel
        //Set pos to 1
        //Repeat while pos is less than the number of items in the array
        for (int pos = 1; pos < list.size(); pos++) {
            //Set pos2 to pos
            //Repeat while pos2 greater than 0 and list(pos2 - 1) greater than than list(pos2) and ascending, or list(pos2 -1) less than list (pos2) and not ascending
            for (int pos2 = pos; pos2 > 0; pos2--) {
                if (((list.get(pos2 - 1)).compareTo(list.get(pos2)) > 0 && ascending) || ((list.get(pos2 - 1)).compareTo(list.get(pos2)) < 0 && !ascending)) {
                    //temp = list(pos2)
                    String temp = list.get(pos2);
                    //list(pos2) = list(pos2 - 1)
                    list.set(pos2, list.get(pos2 - 1));
                    //list(pos2 - 1) = temp
                    list.set(pos2 - 1, temp);
                }
                //Subtract 1 from pos2
                //End repeat
            }
            //Add 1 to pos
            //End repeat
        }
        return list;
    }

    public static ArrayList selectionSort(ArrayList<String> listIn) {
        for (int i = 0; i < listIn.size(); i++) {
            for (int j = i + 1; j < listIn.size(); j++) {
                if (listIn.get(i).compareTo(listIn.get(j)) > 0) {
                    String temp = listIn.get(j);
                    listIn.set(j, listIn.get(i));
                    listIn.set(i, temp);
                }
            }

        }
        return listIn;
    }

    public static ArrayList bubbleSort(ArrayList<String> listIn) {
        String temp;
        for (int i = 0; i < listIn.size(); i++) {
            for (int j = i + 1; j < listIn.size(); j++) {
                if (listIn.get(i).compareTo(listIn.get(j)) > 0) {
                    temp = listIn.get(i);
                    listIn.set(i, listIn.get(j));
                    listIn.set(j, temp);
                }
            }
        }
        return listIn;
    }

    /**
     * Finds the index of a target String in a list
     * <br><b>&nbsp;&nbsp;&nbsp;&nbsp;- Daniel</b>
     *
     * @param list The list to search
     * @param target The String to search for
     * @param ascending <code>true</code> if the list is sorted A-Z,
     * <code>false</code> if the list is sorted Z-A
     * @return the index of the target, or -1 if it is not found.
     * @author Daniel
     */
    public static int binarySearch(List<String> list, String target, boolean ascending) {
        //Daniel
        //set the left and right to 0 and the last index of the list
        int left = 0;
        int right = list.size() - 1;
        //repeat while the left position is less than or equal to the right position
        while (left <= right) {
            //set the mid to the mean of left and right
            int mid = (left + right) / 2;
            //get the comparison value of the list's mid vs the target
            int comp = list.get(mid).compareTo(target);
            //if the list's value at mid is the target, return the mid
            if (comp == 0) {
                return mid;
            } else if (comp > 0) {
                //if the list's value is less than the target, move left or right whether its ascending or descending
                if (ascending) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (comp < 0) {
                //if the list's value is greater than the target, move left or right whether its ascending or descending
                if (ascending) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
