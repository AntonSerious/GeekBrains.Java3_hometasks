import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class hometask1 {
    public static void main(String[] args) {
        //1.Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);

        ArrayList<String> strArr = new ArrayList<>();
        strArr.add("str1");
        strArr.add("str2");
        strArr.add("str3");

        ArrayList<Integer> intArr = new ArrayList<>();
        intArr.add(1);
        intArr.add(2);
        intArr.add(3);

        System.out.println("arrays before swap: ");
        System.out.println(strArr.toString());
        System.out.println("---------------");
        System.out.println(intArr.toString());

        swapElements(strArr, 0,2);
        swapElements(intArr, 0,1);

        System.out.println("arrays after swap: ");
        System.out.println(strArr.toString());
        System.out.println("---------------");
        System.out.println(intArr.toString());
//-----------------------------------
        //2. Написать метод, который преобразует массив в ArrayList;
        String[] arr1 = new String[3];
        System.out.println(arr1.getClass().getTypeName());
        System.out.println((arrToArrList(arr1).getClass().getTypeName()));
        //чисто технически здесь создается новый список, в который перекладывается содержимое массива.
        // Именно преобразовать массив в список похоже, что невозможно.
//-------------------------------
    //3. Большая задача: см классы Fruit, Apple, Orange, Box
        Apple a1 = new Apple();
        Apple a2 = new Apple();
        Apple a3 = new Apple();

        Orange o1 = new Orange();
        Orange o2 = new Orange();
        Orange o3 = new Orange();

        Box<Orange> boxO = new Box<Orange>();
        Box<Apple> boxA = new Box<Apple>();

        boxA.addFruit(a1);
        boxA.addFruit(a2);
        //boxA.addFruit(o1);   // на такое ругается. Так и должно быть

        boxO.addFruit(o1);
        boxO.addFruit(o2);
        boxO.addFruit(o3);
        //boxO.addFruit(a1);   // на такое ругается. Так и должно быть


        boxA.getInfo();
        boxO.getInfo();

        System.out.println(boxA.compare(boxO));  //true

        Box<Apple> additionalBoxA = new Box<Apple>();
        additionalBoxA.addFruit(a3);
        System.out.println("Before shift");
        boxA.getInfo();
        additionalBoxA.getInfo();

        boxA.shiftFruits(additionalBoxA);
        System.out.println("Shift is done");
        boxA.getInfo();
        additionalBoxA.getInfo();
        //-----------Попытаемся переложить яблоки в коробку с апельсинами
        //additionalBoxA.shiftFruits(boxO);  //Ругается. Так и должно быть.

        System.out.println("The wight of boxA is: "+ boxA.getWeight());
        System.out.println("The wight of additionalBoxA is: "+ additionalBoxA.getWeight());
        System.out.println("The wight of orangeA is: "+ boxO.getWeight());

    }
    public static <T> void swapElements(ArrayList<T> arr, int el1, int el2){
        T tmpVar = arr.get(el1);
        arr.set(el1, arr.get(el2));
        arr.set(el2, tmpVar);
    }
    public static <T> List<T> arrToArrList(T[] arr){
        return Arrays.asList(arr);
    }
}


