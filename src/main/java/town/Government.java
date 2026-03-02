package town;

import java.util.Arrays;
import java.util.stream.Collectors;
/**
 * Перечисление форм правления для города
 */
public enum Government {
    KLEPTOCRACY,
    PUPPET_STATE,
    PATRIARCHY,
    JUNTA;
    /**
     * Возвращает строку со всеми значениями перечисления через запятую.
     * @return строка в формате "KLEPTOCRACY, PUPPET_STATE, PATRIARCHY, JUNTA"
     */
    public static String all_values() {
        return Arrays.stream(values())
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }
}
