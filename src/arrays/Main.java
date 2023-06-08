package arrays;

import data.Company;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int [] oneDimensionalArray = {1,2,3};
        inspectArrayObjects(oneDimensionalArray);

        double[][] twoDimensionalArray = {{2.2,3.8},{4.8,7.6}};
       // inspectArrayObjects(twoDimensionalArray);

       // inspectArrayValues(oneDimensionalArray);
        inspectArrayValues(twoDimensionalArray);
    }

    public static void inspectArrayValues(Object arrayObject){
        int arrayLength = Array.getLength(arrayObject);
        System.out.print("[");
        for (int i = 0; i < arrayLength; i++) {
            Object element = Array.get(arrayObject,i);

            if (element.getClass().isArray()){
                inspectArrayValues(element);
            } else {
                System.out.print(element);
            }
                if (i !=arrayLength -1){
                System.out.print(",");
            }
        }
        System.out.print("]");
    }


    public static void inspectArrayObjects(Object arrayObject){
        Class<?> clazz = arrayObject.getClass();

        System.out.println(String.format("Is array : %s",clazz.isArray()));

        Class<?> arrayComponentType = clazz.getComponentType();

        System.out.println(String.format("This is an array of type : %s",arrayComponentType.getTypeName()));
    }
}
