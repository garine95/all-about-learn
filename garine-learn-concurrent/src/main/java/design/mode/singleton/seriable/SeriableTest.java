package design.mode.singleton.seriable;

import java.io.*;

public class SeriableTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SeriableHungerSingleton singleton = SeriableHungerSingleton.getInstance();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("tmp.obj")));
        objectOutputStream.writeObject(singleton);
        objectOutputStream.flush();
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("tmp.obj")));
        SeriableHungerSingleton singleton1 = (SeriableHungerSingleton) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println(singleton);
        System.out.println(singleton1);
    }
}
