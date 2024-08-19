package works.azzyys.util;

import java.util.Optional;
import java.util.function.Predicate;

public class Opt {

    public static <T> boolean falseIfNull(T object, Predicate<T> predicate) {
        return Optional.of(object).map(predicate::test).orElse(false);
    }
}
