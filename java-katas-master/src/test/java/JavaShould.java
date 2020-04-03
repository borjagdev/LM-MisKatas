import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

class SomeType {
    public String someField;
    public int[] numbers;
    public SomeType child;

    public SomeType(){
        numbers = new int[10];
    }

    public void firstMethod(String arg1){
        arg1 = "foo";
    }

    public void secondMethod(SomeType other){
        other = new SomeType();
        other.someField = "blah";
    }

    public void thirdMethod(SomeType other){
        other.numbers[0] = 100;
        other.someField = "changed";
        other.child = new SomeType();
    }

    public void fourthMethod(int[] someNumbers){
        someNumbers[0] = this.numbers[0];
        someNumbers[1] = this.numbers[9];
    }
}


public class JavaShould {
    @Test
    public void work_with_values() throws Exception {
        SomeType instance = new SomeType();

        String arg1 = "bar";
        instance.firstMethod(arg1);
        //assertThat(arg1).isEqualTo("???");

        SomeType other = new SomeType();
        other.numbers[0] = 100;
        instance.secondMethod(other);
        //assertThat(other.numbers[0]).isEqualTo(-1000);
        //assertThat(other.someField).isEqualTo("???");

        SomeType another = new SomeType();
        another.numbers[0] = 200;
        instance.thirdMethod(another);
        //assertThat(another.numbers[0]).isEqualTo(-1000);
        //assertThat(another.someField).isEqualTo("???");
        //assertThat(another.child).isNull();

        SomeType yetAnother = new SomeType();
        yetAnother.numbers[0] = 200;
        instance.fourthMethod(yetAnother.numbers);
        //assertThat(yetAnother.numbers[0]).isEqualTo(-1000);

        SomeType t1 = new SomeType();
        SomeType t2 = new SomeType();
        //assertThat(t1).isEqualTo(t2);
    }
}

