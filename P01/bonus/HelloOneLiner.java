public class HelloOneLiner {
    public static void main(String[] args) {
        System.console().printf("Hello, %s!\n", 
            System.console().readLine("What is your name? "));
    }
}   
