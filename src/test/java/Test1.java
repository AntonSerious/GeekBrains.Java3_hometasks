import hometask6.Calculator;
import hometask6.Hometask6;
import org.junit.*;

public class Test1 {

    @Before
    public void init(){
        System.out.println("init");
    }

    //тесты первого задания
    @Test
    public void testArrHandler1(){
        int[] arrToCheck = {1,2,3,5,4,6,8};
        int[] arrExpected = {6,8};

        try {
            Assert.assertArrayEquals(arrExpected, Hometask6.arrHandler(arrToCheck));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test()
    public void testArrHandler3() throws Exception {
        int[] arrToCheck = {1,4, 4, 2,3,5,6,8};
        int[] arrExpected = {2,3,5,6,8};
        Assert.assertArrayEquals(arrExpected, Hometask6.arrHandler(arrToCheck));
    }
    @Test(expected = RuntimeException.class)
    public void testArrHandler4() throws Exception {
        int[] arrToCheck = {1,2,3,5,6,8};
        int[] arrExpected = {6,8};
        Assert.assertArrayEquals(arrExpected, Hometask6.arrHandler(arrToCheck));
    }

    //тесты второго задания


    @Test
    public void testOnesAndFoursChecker1(){
        int[] arrToCheck = new int[]{1,2,3,4,1,3};
        Assert.assertEquals(true, Hometask6.onesAndFoursChecker(arrToCheck));
    }
    @Test
    public void testOnesAndFoursChecker2(){
        int[] arrToCheck = new int[]{2,3,3};
        Assert.assertEquals(false, Hometask6.onesAndFoursChecker(arrToCheck));
    }
    @Test
    public void testOnesAndFoursChecker3(){
        int[] arrToCheck = new int[]{};
        Assert.assertEquals(false, Hometask6.onesAndFoursChecker(arrToCheck));
    }




    @After
    public void shutdown(){
        System.out.println("end");
    }
}
