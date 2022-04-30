package superAndExtends;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;


public class ProducerExtends {
    public static void main(String[] args) {

        class BoxKeeper<T> {
            private List<T> innerBox;
            public void put(Collection<? extends T> outBox_PRODUCER) {
                innerBox = List.copyOf(outBox_PRODUCER);
            }

            @Override
            public String toString() {
                return Arrays.toString(innerBox.toArray());
            }
        }


        class You {
            @Override
            public String toString() {
                return "I'm you";
            }
        }

        class YourKid extends You {
            @Override
            public String toString() {
                return "I'm your kid";
            }
        }
        class TheKidOfYourKid extends YourKid {
            @Override
            public String toString() {
                return "I'm the kid of your kid";
            }
        }

        var you = new You();
        var yourKid = new YourKid();
        var theKidOfYourKid = new TheKidOfYourKid();

        var boxKeeperOfYouElementsType  = new BoxKeeper<You>();
        var boxKeeperOfYourKidsElementsType = new BoxKeeper<YourKid>();
        var boxKeeperOfTheKidOfYourKidElementsType = new BoxKeeper<TheKidOfYourKid>();

        var packOfYouElements = List.of(you,you,you);
        var packOfYourKidElements = List.of(yourKid,yourKid,yourKid);
        var packOfTheKidOfYourKidElements = List.of(theKidOfYourKid,theKidOfYourKid,theKidOfYourKid);


        boxKeeperOfYouElementsType.put(packOfYouElements);
        System.out.println(boxKeeperOfYouElementsType); // -> [I'm you, Im you, I'm you]
        boxKeeperOfYouElementsType.put(packOfYourKidElements);
        System.out.println(boxKeeperOfYouElementsType); // -> [I'm your kid, I'm your kid, I'm your kid]
        boxKeeperOfYouElementsType.put(packOfTheKidOfYourKidElements);
        System.out.println(boxKeeperOfYouElementsType); // -> [I'm the kid of your kid, I'm the kid of your kid, I'm the kid of your kid]

        IntStream.range(0,  boxKeeperOfYouElementsType.toString().length()).forEach(e -> System.out.print("-"));
        System.out.println("");

        boxKeeperOfYourKidsElementsType.put(packOfYourKidElements);
        System.out.println(boxKeeperOfYourKidsElementsType);  // -> [I'm your kid, Im your kid, I'm your kid]
        boxKeeperOfYourKidsElementsType.put(packOfTheKidOfYourKidElements);
        System.out.println(boxKeeperOfYourKidsElementsType);  // -> [I'm the kid of your kid, I'm the kid of your kid, I'm the kid of your kid]

        // boxKeeperOfYourKidsElementsType.put(packOfYouElements); doesn't compile
        IntStream.range(0,  boxKeeperOfYouElementsType.toString().length()).forEach(e -> System.out.print("-"));
        System.out.println("");


        boxKeeperOfTheKidOfYourKidElementsType.put(packOfTheKidOfYourKidElements);
        // boxKeeperOfTheKidOfYourKidElementsType.put(packOfYourKidElements);  doesn't compile
        // boxKeeperOfYourKidsElementsType.put(packOfYouElements); doesn't compile
        System.out.println(packOfTheKidOfYourKidElements);  // -> [Im the kid of your kid, Im the kid of your kid, Im the kid of your kid]
        IntStream.range(0,  boxKeeperOfYouElementsType.toString().length()).forEach(e -> System.out.print("-"));
        System.out.println("");
    }
}
