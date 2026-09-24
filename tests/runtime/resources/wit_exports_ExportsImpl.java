package wit.exports;

import java.util.HashMap;
import java.util.Map;

public class ExportsImpl {
    private static final Map<wit.exports.Exports.X, Double> VALUES = new HashMap<>();

    public static wit.exports.Exports.X constructorX(double a) {
        wit.exports.Exports.X result = new wit.exports.Exports.X(0, true);
        VALUES.put(result, a);
        return result;
    }

    public static double methodXGetA(wit.exports.Exports.X self) {
        return VALUES.get(self);
    }

    public static void methodXSetA(wit.exports.Exports.X self, double a) {
        VALUES.put(self, a);
    }

    public static wit.exports.Exports.X staticXAdd(wit.exports.Exports.X x, double a) {
        wit.exports.Exports.X result = new wit.exports.Exports.X(0, true);
        VALUES.put(result, VALUES.get(x) + a);
        return result;
    }
}
