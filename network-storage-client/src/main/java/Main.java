public class Main {
    public static void main(String[] args) {
        new Thread(ClientApp::new).start();
    }
}
