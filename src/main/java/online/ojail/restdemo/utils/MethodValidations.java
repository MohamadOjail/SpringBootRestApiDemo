package online.ojail.restdemo.utils;

/**
 * @author Mo. Ojail
 * created: 2022-09-20
 */

public class MethodValidations {

    private MethodValidations() {}

    public static void validateNotNullID(Long id, Class aClass){
        if (id == null) throw new RuntimeException(String.format("Supplied %s id is null.", aClass.getName()));
    }

}
