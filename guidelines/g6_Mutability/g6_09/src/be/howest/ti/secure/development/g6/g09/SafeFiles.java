package be.howest.ti.secure.development.g6.g09;

public class SafeFiles {
    public static final String separator = "/";
    private String path;

    public SafeFiles() {
        this.path = separator + "foo" + separator + "bar";
    }

    public String getPath() {
        return this.path;
    }
}
