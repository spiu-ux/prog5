package town;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Government {
    KLEPTOCRACY,
    PUPPET_STATE,
    PATRIARCHY,
    JUNTA;

    public static String all_values() {
        return Arrays.stream(values())
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }
}
