public class HelloWorldClass {
    public void sayHello() {
        class EnglishGreeting implements HelloWorld {
            String name = "world";
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hello " + name);
            }
        }
        HelloWorld englishGreeting = new EnglishGreeting();
        englishGreeting.greetSomeone("Professor Rice");
    }
    
    public static void main(String[] args) {
        (new HelloWorldClass()).sayHello();
    }
}
