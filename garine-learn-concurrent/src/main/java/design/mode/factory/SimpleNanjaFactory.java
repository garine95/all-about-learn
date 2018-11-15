package design.mode.factory;

/**
 * 简单工厂，根据传入的参数的不同决定生产何种产品
 */
public class SimpleNanjaFactory {

    /**
     * 所有产品在同一个生产线生产，通过用户传入参数。这种方式容易出错，例如传入非Fire和Water的参数
     * @param nanJaType
     * @return
     */
    public Nanja getNanJa(String nanJaType){
        if ("Fire".equals(nanJaType)){
            return new FireNanja();
        }else if ("Water".equals(nanJaType)){
            return new WaterNanJa();
        }else {
            return null;
        }
    }

    public static void main(String[] args) {
        SimpleNanjaFactory simpleNanjaFactory = new SimpleNanjaFactory();
        //用户手动传入参数获取实例容易出错
        System.out.println(simpleNanjaFactory.getNanJa("Water").getType());
    }
}
