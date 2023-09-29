public class HelloWorldAnonymousClass {
    public void sayHello() {
        HelloWorld englishGreeting = new HelloWorld() {
            String name = "world";
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hello " + name);
            }
        };
        englishGreeting.greetSomeone("Professor Rice");
    }
    
    public static void main(String[] args) {
        (new HelloWorldAnonymousClass()).sayHello();
    }
}
