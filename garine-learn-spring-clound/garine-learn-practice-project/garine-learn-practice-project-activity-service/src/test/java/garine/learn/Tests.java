package garine.learn;

public class Tests {
    public static void main(String[] args) {
        getConsumerOffset();
    }

    public static void getConsumerOffset() {
        System.out.println(Math.abs("activity-service".hashCode())%50);;
    }
}
