package be.howest.ti.secure.development.g6.g08;

public class ExampleG6G08 {
    private String state;
    public static void main(String[] args)
    {
        new ExampleG6G08().run();
    }

    public void run() {
        state = new String("Start");
        System.out.println(state);
        setState("Started");
        System.out.println(state);
        setState("Error");
        System.out.printf(state);
    }

    public String getState(){
        return state;
    }

    public void setState(final String newSate){
        this.state = requireValidation(newSate);
    }

    private static String requireValidation(final String state){
        if(state.equalsIgnoreCase("error")){
            throw new IllegalArgumentException("state equals error,");
        }
        return state;
    }

}
