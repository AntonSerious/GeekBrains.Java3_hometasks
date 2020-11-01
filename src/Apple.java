public class Apple extends Fruit {
    Apple(){
        super(1.0f);
    }
    //получается этот метод даже излишен
    @Override
    public float getWeight(){
       return this.weight;
    }

}
