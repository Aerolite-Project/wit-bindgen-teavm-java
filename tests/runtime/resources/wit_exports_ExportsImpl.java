package wit.exports;

import java.util.ArrayList;

public class ExportsImpl {
    private static final class State<T> {
        private final ArrayList<T> resources = new ArrayList<>();
        private final ArrayList<Integer> values = new ArrayList<>();

        void put(T resource, int value) {
            for (int i = 0; i < resources.size(); i++) {
                if (resources.get(i) == resource) {
                    values.set(i, value);
                    return;
                }
            }
            resources.add(resource);
            values.add(value);
        }

        int get(T resource) {
            for (int i = 0; i < resources.size(); i++) {
                if (resources.get(i) == resource) {
                    return values.get(i);
                }
            }
            throw new IllegalStateException("resource state is unavailable");
        }

        void remove(T resource) {
            for (int i = 0; i < resources.size(); i++) {
                if (resources.get(i) == resource) {
                    resources.remove(i);
                    values.remove(i);
                    return;
                }
            }
        }
    }

    private static final State<wit.exports.Exports.X> X_VALUES = new State<>();
    private static final State<wit.exports.Exports.Z> Z_VALUES = new State<>();
    private static final State<wit.exports.Exports.KebabCase> KEBAB_VALUES = new State<>();

    public static wit.exports.Exports.X constructorX(int a) {
        wit.exports.Exports.X result = new wit.exports.Exports.X(0, true);
        X_VALUES.put(result, a);
        return result;
    }

    public static int methodXGetA(wit.exports.Exports.X self) {
        return X_VALUES.get(self);
    }

    public static void methodXSetA(wit.exports.Exports.X self, int a) {
        X_VALUES.put(self, a);
    }

    public static wit.exports.Exports.X staticXAdd(wit.exports.Exports.X x, int a) {
        wit.exports.Exports.X result = new wit.exports.Exports.X(0, true);
        X_VALUES.put(result, X_VALUES.get(x) + a);
        return result;
    }

    public static wit.exports.Exports.Z constructorZ(int a) {
        wit.exports.Exports.Z result = new wit.exports.Exports.Z(0, true);
        Z_VALUES.put(result, a);
        return result;
    }

    public static int methodZGetA(wit.exports.Exports.Z self) {
        return Z_VALUES.get(self);
    }

    public static int staticZNumDropped() {
        return 0;
    }

    public static wit.exports.Exports.Z add(wit.exports.Exports.Z a, wit.exports.Exports.Z b) {
        wit.exports.Exports.Z result = new wit.exports.Exports.Z(0, true);
        Z_VALUES.put(result, Z_VALUES.get(a) + Z_VALUES.get(b));
        return result;
    }

    public static void consume(wit.exports.Exports.X x) {
        X_VALUES.remove(x);
    }

    public static wit.exports.Exports.KebabCase constructorKebabCase(int a) {
        wit.exports.Exports.KebabCase result = new wit.exports.Exports.KebabCase(0, true);
        KEBAB_VALUES.put(result, a);
        return result;
    }

    public static int methodKebabCaseGetA(wit.exports.Exports.KebabCase self) {
        return KEBAB_VALUES.get(self);
    }

    public static int staticKebabCaseTakeOwned(wit.exports.Exports.KebabCase k) {
        return KEBAB_VALUES.get(k);
    }

    public static wit.worlds.Resources.Result<wit.worlds.Resources.Tuple0, String> testImports() {
        return wit.worlds.Resources.Result.ok(wit.worlds.Resources.Tuple0.INSTANCE);
    }
}
