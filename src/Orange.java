public class Orange extends Fruit{
    Orange(){
        super(1.0f);
    }
    @Override
    public float getWeight(){
        return this.weight;
    }
    @Override
    public String toString(){
        return "Orange";
    }
}
