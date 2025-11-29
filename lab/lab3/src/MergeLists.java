//1. Дано K связанных списков. Слейте их в один полностью отсортированный список. Сложность O (N log K). Докажите сложность.

import java.util.*;

public class MergeLists {
    private static final Scanner scanner = new Scanner(System.in);

    private static LinkedList<Integer> mergeLists(LinkedList<Integer> first, LinkedList<Integer> second){
        LinkedList<Integer> mergedList = new LinkedList<Integer>();

        while ((first.peekFirst()!=null)&&(second.peekFirst()!=null)){ // Перебираем элементы поочередно, забирая по по одному => ложность O(N)
            if (first.peekFirst()>second.peekFirst()){
                mergedList.add(second.poll());
            } else {
                mergedList.add(first.poll());
            }
        }
        if (first.peekFirst()!=null){
            mergedList.addAll(first);//O(N)
        }
        if (second.peekFirst()!=null){
            mergedList.addAll(second);//O(N)
        }
        return mergedList;
    }//3*O(N)~O(N)


    private static LinkedList<Integer> mergeAnyLists(ArrayList<LinkedList<Integer>> lists){
        if (lists.isEmpty()){
            return new LinkedList<>();
        }

        ArrayList<LinkedList<Integer>> remainingLists = new ArrayList<>(lists);

        while (remainingLists.size() > 1) { //Каждая итерация цикла сокращает число списков вдвое -> O(logK)
            ArrayList<LinkedList<Integer>> mergedLists = new ArrayList<>();

            for (int i = 0; i < remainingLists.size(); i += 2) {
                LinkedList<Integer> first = remainingLists.get(i);
                LinkedList<Integer> second = (i+ 1 < remainingLists.size())
                        ? remainingLists.get(i + 1)
                        : new LinkedList<>();

                mergedLists.add(mergeLists(first, second));//О(N)
            }

            remainingLists = mergedLists;
        }

        return remainingLists.get(0);
    }
    //Основной цикл - О(N), вложенный - O(logK) -> O(NlogK)

    private static LinkedList<Integer> input(int n){
        LinkedList<Integer> inputList = new LinkedList<>();
        for(int i = 0; i<n;i++){
            inputList.add(scanner.nextInt());
        }
        return inputList;
    }

    public static void main(String[] args){

        ArrayList<LinkedList<Integer>> lists = new ArrayList<>();

        System.out.print("Введите число списков, которые необходимо соединить: ");
        int k = scanner.nextInt();
        int n;
        for(int i = 0; i<k;i++){
            System.out.println("Введите кол-во элементов списка в отсортированном виде ( в порядке возрастания): ");
            n = scanner.nextInt();
            System.out.println("Введите элементы списка  в отсортированном виде ( в порядке возрастания): ");
            lists.add(input(n));
        }

        System.out.println("Итог: ");
        LinkedList<Integer> result = mergeAnyLists(lists);
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i < result.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}