package JSONwriter;

import data.*;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {

        Actor actor1 = new Actor("Anatol Durbala",new String[]{"Varvara","Ora de Ras"});
        Actor actor2 = new Actor("Kate Beckinsale",new String[]{"Underworld Evolution"});
        Actor actor3 = new Actor("Michael Sheen", new String[]{"Underworld Blood of Wars"});
        Actor actor4 = new Actor("Bill Nighty", new String[]{ " Underworld Endless War"});

        Movie movie = new Movie("Underworld Future",9.6f,new String[]{"Action","Thriller","Fantastic"},
                new Actor[]{actor1,actor2,actor3,actor4});

        String json = objectToJson(actor1,0);
        System.out.println(json);

        String json1 = objectToJson(movie,0);
        System.out.println(json1);

//        Address address = new Address("Chicago Way", (short) 484);
//        Company company = new Company("IBM","Chicago",new Address("auction.Main str",(short) 300));
//        Person person = new Person("Alex", true, 40, 100.225f, address, company);
//
//        String json = objectToJson(person, 0);
//        System.out.println(json);

    }
    public static String objectToJson(Object instance, int indentSize) throws IllegalAccessException{
        Field [] fields = instance.getClass().getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(indent(indentSize));
        stringBuilder.append("{");
        stringBuilder.append("\n");
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            if (field.isSynthetic()){
                continue;

            }
            stringBuilder.append(indent(indentSize +1));
            stringBuilder.append(formatStringValue(field.getName()));
            stringBuilder.append(":");

            if (field.getType().isPrimitive()){
                stringBuilder.append(formatPrimitiveValue(field.get(instance),field.getType()));
            } else if (field.getType().equals(String.class)) {
                stringBuilder.append(formatStringValue(field.get(instance).toString()));
            } else if (field.getType().isArray()) {
                stringBuilder.append(arrayToJson(field.get(instance),indentSize+1));

            }else{
                stringBuilder.append(objectToJson(field.get(instance),indentSize+1));
            }
            if (i != fields.length-1){
                stringBuilder.append(",");
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append(indent(indentSize));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
    private static String indent(int indentSize){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <indentSize ; i++) {
            stringBuilder.append("\t");
        }
        return stringBuilder.toString();
    }
    private static String arrayToJson(Object arrayInstance,int intendSize) throws IllegalAccessException {
       StringBuilder stringBuilder = new StringBuilder();
       int arrayLength = Array.getLength(arrayInstance);

       Class<?> componentType = arrayInstance.getClass().getComponentType();

       stringBuilder.append("[");
       stringBuilder.append("\n");
        for (int i = 0; i < arrayLength; i++) {
            Object element = Array.get(arrayInstance,i);

            if (componentType.isPrimitive()){
                stringBuilder.append(indent(intendSize+1));
                stringBuilder.append(formatPrimitiveValue(element,componentType));
            } else if (componentType.equals(String.class)) {
                stringBuilder.append(indent(intendSize+1));
                stringBuilder.append(formatStringValue(element.toString()));
            }else {
               stringBuilder.append(objectToJson(element,intendSize+1));
            }
             if (i != arrayLength){
                 stringBuilder.append(",");
             }
            stringBuilder.append("\n");
        }
        stringBuilder.append(indent(intendSize));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private static String formatPrimitiveValue(Object instance, Class<?>type){
        if(type.equals(boolean.class)
        || type.equals(int.class)
        || type.equals(long.class)
        || type.equals(short.class)){
            return instance.toString();
        } else if (type.equals(double.class) || type.equals(float.class)) {
            return String.format("%02f",instance);
        }
        throw new RuntimeException(String.format("Type : %s is unsupported", type.getTypeName()));
    }
    private static String formatStringValue(String value){
        return String.format("\"%s\"",value);
    }
}
