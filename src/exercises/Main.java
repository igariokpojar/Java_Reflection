package exercises;

import java.util.PrimitiveIterator;

public class Main {
    private static void printClassInfo(Class<?> ... classes){
       for (Class<?> clazz : classes){
           System.out.println(String.format("class name : %s, class package name : %s",
                   clazz.getSimpleName(),
                   clazz.getPackageName()));

           Class<?>[] implementInterface = clazz.getInterfaces();
           for (Class<?> implementedInterface : implementInterface){
               System.out.println(String.format("class %s implements : %s",
                       clazz.getSimpleName(),
                       implementedInterface.getSimpleName()));
           }
           System.out.println();
           System.out.println();
       }
    }

    private static class Square implements Drawable {

        @Override
        public int getNumberOfCorners() {
            return 4;
        }
    }

    private static interface Drawable{
        int getNumberOfCorners();
    }

   private enum Color{
        BLUE,
        RED,
        GREEN
   }
}
