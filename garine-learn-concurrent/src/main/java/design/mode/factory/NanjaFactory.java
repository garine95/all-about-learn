package design.mode.factory;

public class NanjaFactory extends AbstractNanjaFactory {
    @Override
    public Nanja getWater() {
        publicLogic();
        return new WaterNanJa();
    }

    @Override
    public Nanja getFire() {
        publicLogic();
        return new FireNanja();
    }

    public static void main(String[] args) {
        AbstractNanjaFactory nanjaFactory = new NanjaFactory();
        System.out.println(nanjaFactory.getFire().getType());
    }
}
