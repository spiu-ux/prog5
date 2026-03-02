package town;

import java.util.Arrays;
import java.util.stream.Collectors;
/**
 * Перечисление уровней жизни населения города.
 */
public enum StandardOfLiving {
    LOW,
    ULTRA_LOW,
    NIGHTMARE;
    /**
     * Возвращает строку со всеми значениями перечисления через запятую.
     * @return строка в формате "LOW, ULTRA_LOW, NIGHTMARE"
     */
    public static String all_values() {
        return Arrays.stream(values())
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }
}
