package wit.exports.test.javaExportedResource;

public class TestImpl {
    private static int value;
    private static boolean alive;

    public static Test.Item constructorItem(int initialValue) {
        value = initialValue;
        alive = true;
        return new Test.Item(0, true);
    }

    public static int methodItemGet(Test.Item self) {
        if (!alive) {
            throw new IllegalStateException("resource state is unavailable");
        }
        return value;
    }

    public static void methodItemSet(Test.Item self, int newValue) {
        if (!alive) {
            throw new IllegalStateException("resource state is unavailable");
        }
        value = newValue;
    }
}
