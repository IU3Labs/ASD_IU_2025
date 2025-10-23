//В рамках данной лабораторной работы предлагается решить основную
//задачу и дополнительные. Для сдачи лабораторной работы необходимо набрать 4
//баллов.

//1.Дано K связанных списков. Слейте их в один полностью
// отсортированный список. Сложность O (N log K). Докажите сложность.

//В задании не указано, даны нам отсортированные или не отсортированные списки. Однако, если списки не отсортированы,
//на их сортировку и слияние в процессе выполнения алгоритма будет потрачено О(N*logN) - лучшая сложность по времени
//для алгоритмов сортировки(для MergeSort, например, тк в нем идет разбиение на половинки до того, пока не останется
//по 1 элементу в каждой половинке (logN), а слияние после их сравнения, которое происходит для каждой пары половинок,
//имеет линейную сложность O(N)) - а не O(N logK).
//Таким образом для достижения поставленой задачи при выполнении задания будем считать, что входные списки подразумеваются
//отсортированными.


import java.util.*;

public class MergedLists_B1 {
    private static final Scanner scanner = new Scanner(System.in);

    private static LinkedList<Integer> mergeLists(LinkedList<Integer> list1, LinkedList<Integer> list2){
        LinkedList<Integer> mergedList = new LinkedList<Integer>();

        while ((list1.peekFirst()!=null)&&(list2.peekFirst()!=null)){ //Сложность O(N), тк мы сравниваем элементы по очереди, каждый раз
            if (list1.peekFirst()>list2.peekFirst()){                   //забирая по одному, таким образом проходя все N элементов из обоих
                mergedList.add(list2.poll());                           //списков.
            } else {
                mergedList.add(list1.poll());
            }
        }
        if (list1.peekFirst()!=null){
            mergedList.addAll(list1);//O(N)
        }
        if (list2.peekFirst()!=null){
            mergedList.addAll(list2);//O(N)
        }
        return mergedList;
    }//3*O(N)~O(N)

    private static LinkedList<Integer> mergeKLists(ArrayList<LinkedList<Integer>> lists){
        if (lists.isEmpty()){
            return null;
        }

        ArrayList<LinkedList<Integer>> currentLists = new ArrayList<>(lists);

        while (currentLists.size() > 1) {//Каждая итерация цикла сокращает число списков вдвое -> O(logK)
            ArrayList<LinkedList<Integer>> mergedLists = new ArrayList<>();

            for (int i = 0; i < currentLists.size(); i += 2) {
                LinkedList<Integer> list1 = currentLists.get(i);
                LinkedList<Integer> list2 = (i + 1 < currentLists.size())
                        ? currentLists.get(i + 1)
                        : new LinkedList<>();

                mergedLists.add(mergeLists(list1, list2));//О(N)
            }

            currentLists = mergedLists;
        }

        return currentLists.get(0);
    }//Основной цикл - О(N), вложенный - O(logK) -> O(NlogK)

    private static LinkedList<Integer> inputList(int n){
        LinkedList<Integer> newList = new LinkedList<>();
        for(int i = 0; i<n;i++){
            newList.add(scanner.nextInt());
        }
        return newList;
    }

    public void main(){

        ArrayList<LinkedList<Integer>> lists = new ArrayList<>();

        System.out.print("Введите количество списков: ");
        int k = scanner.nextInt();
        int n;
        for(int i = 0; i<k;i++){
            System.out.println("Введите количество элементов списка№"+ i +": ");
            n = scanner.nextInt();
            System.out.println("Введите элементы списка№"+ i +" в порядке возрастания: ");
            lists.add(inputList(n));
        }

        System.out.println("Ваш итоговый список: ");

        System.out.println();
        for(Object o : mergeKLists(lists)){
            System.out.print(o + ", ");
        }
    }
}

