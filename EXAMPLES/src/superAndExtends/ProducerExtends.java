package superAndExtends;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;


public class ProducerExtends {
    public static void main(String[] args) {

        class BoxKeeper<T> {

            //private List<? super T> innerBox; works too because its bigger or equal to T
            private List<T> innerBox;

            /**
             * take small box (outBox_PRODUCER) read from it and put it elements in bigger box (innerBox)
             * @param outBox_PRODUCER Collection<? extends T>
             * */
            public void readFrom(Collection<? extends T> outBox_PRODUCER) {
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

        var boxKeeperOfYou  = new BoxKeeper<You>();
        var boxKeeperOfYourKids = new BoxKeeper<YourKid>();
        var boxKeeperOfTheKidOfYourKid = new BoxKeeper<TheKidOfYourKid>();

        var producerYouElements = List.of(you);
        var producerYourKidElements = List.of(yourKid);
        var producerTheKidOfYourKidElements = List.of(theKidOfYourKid);


        boxKeeperOfYou.readFrom(producerYouElements);
        System.out.println(boxKeeperOfYou); // -> [I'm you]

        boxKeeperOfYourKids.readFrom(producerYourKidElements);
        System.out.println(boxKeeperOfYourKids); // -> [I'm your kid]

        boxKeeperOfTheKidOfYourKid.readFrom(producerTheKidOfYourKidElements);
        System.out.println(boxKeeperOfTheKidOfYourKid); // -> [I'm the kid of your kid]

        IntStream.range(0,  boxKeeperOfYou.toString().length()).forEach(e -> System.out.print("-"));
        System.out.println("");

        boxKeeperOfYourKids.readFrom(producerYourKidElements);
        System.out.println(boxKeeperOfYourKids);  // -> [I'm your kid]

        boxKeeperOfYourKids.readFrom(producerTheKidOfYourKidElements);
        System.out.println(boxKeeperOfYourKids);  // -> [I'm the kid of your kid]

        // boxKeeperOfYourKids.put(producerYouElements); doesn't compile
        IntStream.range(0,  boxKeeperOfYou.toString().length()).forEach(e -> System.out.print("-"));
        System.out.println("");


        boxKeeperOfTheKidOfYourKid.readFrom(producerTheKidOfYourKidElements);
        System.out.println(boxKeeperOfTheKidOfYourKid);  // -> [I'm the kid of your kid]

        // boxKeeperOfTheKidOfYourKid.put(producerYourKidElements); // doesn't compile

        // boxKeeperOfTheKidOfYourKid.put(producerYouElements); doesn't compile

        IntStream.range(0,  boxKeeperOfYou.toString().length()).forEach(e -> System.out.print("-"));
        System.out.println("");
    }
}
