class Main {
    public static void main(String[]args){
        Client client = new Client();
        client.startConnection("127.0.0.1", 6666);
        Cat bob = new Cat("Bob", 3, "good");
        Cat response = client.sendMessage(bob);
        String result = response.sayMeow();
    }
}
