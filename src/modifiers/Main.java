package modifiers;

import modifiers.auction.Auction;
import modifiers.auction.Bid;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {

    public static void main(String[] args) {

      //  printClassModifier(Auction.class);
      //  printClassModifier(Bid.Builder.class);
      //  printClassModifier(Serializable.class);

      //  printMethodModifiers(Auction.class.getDeclaredMethods());

        printFieldsModifiers(Auction.class.getDeclaredFields());
    }

    public static void printFieldsModifiers(Field[] fields){
        for (Field field:fields){
            int modifier = field.getModifiers();

            System.out.println(String.format("Field \"%s\" access  modifier is %s",field.getName(),getAccessModifierName(modifier)));

            if (Modifier.isVolatile(modifier)){
                System.out.println("The field is volatile");
            }
            if (Modifier.isFinal(modifier)){
                System.out.println("The filed is final");
            }
            if (Modifier.isTransient(modifier)){
                System.out.println("The filed is transient and will not be serialized");
            }
            System.out.println();
        }
    }

    public static void printMethodModifiers(Method[]methods){
        for (Method method:methods) {
            int modifier = method.getModifiers();

            System.out.println(String.format("%s() access modifier is %s",
                    method.getName(),
                    getAccessModifierName(modifier)));

            if (Modifier.isSynchronized(modifier)){
                System.out.println("The method is synchronized");
            }else {
                System.out.println("The method is not synchronized");
            }
            System.out.println();
        }
    }

    public static void printClassModifier(Class<?>clazz){
        int modifier = clazz.getModifiers();

        System.out.println(String.format("Class %s access modifier is %s",
                clazz.getSimpleName(),
                getAccessModifierName(modifier)));

        if (Modifier.isAbstract(modifier)){
            System.out.println("the class is abstract");
        }
        if (Modifier.isInterface(modifier)){
            System.out.println("The class is interface");
        }
        if (Modifier.isStatic(modifier)){
            System.out.println("The class is static");
        }
    }

    private static String getAccessModifierName(int modifier){
        if (Modifier.isPublic(modifier)){
            return "public";
        } else if (Modifier.isProtected(modifier)) {
            return "protected";
        } else if (Modifier.isPrivate(modifier)) {
            return "private";
        }else {
            return "package-private";
        }
    }
    public static void runAuction(){
        Auction auction = new Auction();
        auction.startAuction();

        Bid bid1 = Bid.builder()
                .setBidderName("Company1")
                .setPrice(10)
                .build();
        auction.addBid(bid1);

        Bid bid2 = Bid.builder()
                .setBidderName("Company2")
                .setPrice(11)
                .build();
        auction.addBid(bid2);

        auction.stopAuction();

        System.out.println(auction.getAllBids());
        System.out.println("Highest bid:" + auction.getHighestBid().get());

    }
}
