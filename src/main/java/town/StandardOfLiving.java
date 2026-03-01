package town;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum StandardOfLiving {
    LOW,
    ULTRA_LOW,
    NIGHTMARE;

    public static String all_values() {
        return Arrays.stream(values())
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }
}
