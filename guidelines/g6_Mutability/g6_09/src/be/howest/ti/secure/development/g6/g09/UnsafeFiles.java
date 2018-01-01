package be.howest.ti.secure.development.g6.g09;

public class UnsafeFiles {
    public static String separator = "/";
    private String path;

    public UnsafeFiles() {
        this.path = separator + "foo" + separator + "bar";
    }

    public String getPath() {
        return this.path;
    }
}
