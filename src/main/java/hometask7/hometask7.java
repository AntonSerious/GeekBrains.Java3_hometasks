package hometask7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class hometask7 {
    public static void main(String[] args) throws Exception {
        start(ClassToTest.class);
   }
   public static void start(Class c) throws InvocationTargetException, IllegalAccessException {
       Method[] methods = c.getDeclaredMethods();
       List<Method> l = new ArrayList<>();
       int beforeSuiteCount = 0;
       int afterSuiteCount = 0;
       for(Method m : methods){
           if(m.isAnnotationPresent(Test.class)){
               l.add(m);
           }
       }
       l.sort((o1, o2) -> o2.getAnnotation(Test.class).priority()-o1.getAnnotation(Test.class).priority());
       for(Method m : methods) {

           if (m.getAnnotation(BeforeSuite.class) !=null) {
               if (beforeSuiteCount < 1) {
                   beforeSuiteCount++;
                   l.add(0, m);
               } else {
                   throw new RuntimeException("multiple beforeSuiteAnnotation Exception");
               }
           }
           if (m.isAnnotationPresent(AfterSuite.class)) {
               if (afterSuiteCount < 1) {
                   afterSuiteCount++;
                   l.add(l.size(), m);
               } else {
                   throw new RuntimeException("multiple afterSuiteAnnotation Exception");
               }
           }

       }
       if(beforeSuiteCount != 1 || afterSuiteCount !=1){
            throw new RuntimeException("no before- or afterSuiteAnnotation");
       }
       for (Method o: l) {
           o.invoke(null);
       }
   }

}
