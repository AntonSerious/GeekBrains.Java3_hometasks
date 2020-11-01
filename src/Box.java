import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> box = new ArrayList<T>();

    public Box(T fruit){
        box.add(fruit);
    }
    public Box(){
    }

    public void addFruit(T fruit ){
        this.box.add(fruit);
    }
    public float getWeight(){
        float totalWeight = 0;
        for (T t : box) {
            totalWeight += t.getWeight();
        }
        return totalWeight;
    }

    public boolean compare(Box<?> box){
        if(this.getWeight() == box.getWeight()){
            return true;
        }
        else{
            return false;
        }
    }

    public void shiftFruits(Box<T> box){
        for (int i = 0; i < this.box.size(); i++) {
            box.addFruit(this.box.get(i));
        }
        this.box.clear();
    }
    public void getInfo(){
        if(this.box.size() == 0){
            System.out.println(this + " is empty");
            return;
        }
        System.out.println("Box is "+ this + " There are " + this.box.size() + " fruit(s) inside. Fruit's type is " + this.box.get(0).getClass().getName()  );
    }
}
