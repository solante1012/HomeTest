/**
 * Description: 类装载时完成实例化
 * date: 2020/7/18 21:14
 *
 * @author cc<br />
 * @since JDK 1.8
 */
public class SingleClass {
    private static final SingleClass SINGLE_CLASS = new SingleClass();

    private SingleClass (){
    }
    public static SingleClass getInstance(){
        return SINGLE_CLASS;
    }

    public  void m(){
        System.out.println("hello 我是懒汉式");
    }

    public static void main(String[] args) {
        SingleClass instance1 = SingleClass.getInstance();
        SingleClass instance2 = SingleClass.getInstance();
        System.out.println(instance1 == instance2);


    }
}
