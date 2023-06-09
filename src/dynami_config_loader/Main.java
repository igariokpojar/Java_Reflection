package dynami_config_loader;

import dynami_config_loader.data.GameConfig;
import dynami_config_loader.data.UserInterfaceConfig;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    private static final Path GAME_CONFIG_PATH = Path.of("resources/game-properties.cfg");
    private static final Path UI_CONFIG_PATH = Path.of("resources/user-interface.cfg");

    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
       // GameConfig config = createConfigObject(GameConfig.class,GAME_CONFIG_PATH);
        UserInterfaceConfig config  = createConfigObject(UserInterfaceConfig.class,UI_CONFIG_PATH);
        System.out.println(config);


    }

    public static <T> T createConfigObject(Class<T> clazz, Path filePath) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Scanner scanner = new Scanner(filePath);
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);

        T configInstance = (T) constructor.newInstance();

        while (scanner.hasNextLine()) {
            String configLine = scanner.nextLine();

            String[] nameValuePair = configLine.split("=");
            String propertyName = nameValuePair[0];
            String propertyValue = nameValuePair[1];

            Field field;
            try {
                field = clazz.getDeclaredField(propertyName);
            } catch (NoSuchFieldException e) {
                System.out.println(String.format("Property name : %s is unsupported", propertyName));
                continue;
            }
            field.setAccessible(true);

            Object parseValue = parseValue(field.getType(),propertyValue);
            field.set(configInstance,parseValue);

        }
        return configInstance;
    }

    private static Object parseValue(Class<?> type,String value){
        if (type.equals(int.class)){
            return Integer.parseInt(value);
        } else if (type.equals(short.class)){
            return Short.parseShort(value);
        } else if (type.equals(long.class)) {
            return Long.parseLong(value);
        } else if (type.equals(double.class)) {
            return Double.parseDouble(value);
        } else if (type.equals(float.class)) {
            return Float.parseFloat(value);
        } else if (type.equals(String.class)) {
            return value;
        }
        throw new RuntimeException(String.format("Type : %s unsupported", type.getName()));
    }

}


