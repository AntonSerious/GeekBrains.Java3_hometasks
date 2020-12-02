package hometask7;

public class ClassToTest {
    public static  void noAnnotMethod() {
        System.out.println("NoAnnotMethod");
    }

    @BeforeSuite
    public static void beforeSuite() {
        System.out.println("BeforeSuite");
    }

    @Test(priority = 6)
    public static  void method2() {
        System.out.println("priority 6");
    }

    @Test(priority = 9)
    public static  void method3() {
        System.out.println("priority 9");
    }

    @Test(priority = 1)
    public static  void method4() {
        System.out.println("priority 1");
    }
    @AfterSuite
    public static void afterSuite() {
        System.out.println("AfterSuite");
    }
}
