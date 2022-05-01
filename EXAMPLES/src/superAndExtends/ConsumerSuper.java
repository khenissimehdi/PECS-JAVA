package superAndExtends;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;


public class ConsumerSuper {
    public static void main(String[] args) {

        class BoxKeeper<T> {
            // private final List<? extends T> innerBox works too because its smaller or equal to T
            private final List<T> innerBox;

            public BoxKeeper(List<T> list) {
                innerBox = list;
            }

            /**
             * write elements from small box(innerBox) into bigger box(outBox_CONSUMER)
             * @param outBox_CONSUMER Collection<? super T>
             * */
            public void writeInto(Collection<? super T> outBox_CONSUMER) {
                outBox_CONSUMER.addAll(innerBox);
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


        var packOfYou = List.of(you);
        var packOfYourKid = List.of(yourKid);
        var packOfTheKidOfYourKid = List.of(theKidOfYourKid);


        var boxKeeperOfYou  = new BoxKeeper<You>(packOfYou);
        var boxKeeperOfYourKids = new BoxKeeper<YourKid>(packOfYourKid);
        var boxKeeperOfTheKidOfYourKid = new BoxKeeper<TheKidOfYourKid>(packOfTheKidOfYourKid);


        var consumerOfYouElements = new ArrayList<You>();
        var consumerOfYourKidElements = new ArrayList<YourKid>();
        var consumerOfTheKidOfYourKidElements = new ArrayList<TheKidOfYourKid>();


        boxKeeperOfYou.writeInto(consumerOfYouElements);
        System.out.println(consumerOfYouElements); // -> [I'm you]

        // boxKeeperOfYou.write(consumerOfYourKidElements); doesn't compile
        // boxKeeperOfYou.write(consumerOfTheKidOfYourKidElements); doesn't compile

        consumerOfYouElements.clear(); // clear the array for future use

        IntStream.range(0, 25).forEach(e -> System.out.print("-"));
        System.out.println("");

        boxKeeperOfYourKids.writeInto(consumerOfYourKidElements);
        System.out.println(consumerOfYourKidElements); // -> [I'm your kid]

        consumerOfYourKidElements.clear();

        boxKeeperOfYourKids.writeInto(consumerOfYouElements);
        System.out.println(consumerOfYouElements); // -> [I'm your kid]

        consumerOfYouElements.clear();

       // boxKeeperOfYourKids.write(consumerOfTheKidOfYourKidElements); doesn't compile

        IntStream.range(0, 25).forEach(e -> System.out.print("-"));
        System.out.println("");

        boxKeeperOfTheKidOfYourKid.writeInto(consumerOfYouElements);
        System.out.println(consumerOfYouElements);// -> [I'm the kid of your kid]
        boxKeeperOfTheKidOfYourKid.writeInto(consumerOfYourKidElements);
        System.out.println(consumerOfYourKidElements);// -> [I'm the kid of your kid]
        boxKeeperOfTheKidOfYourKid.writeInto(consumerOfTheKidOfYourKidElements);
        System.out.println(consumerOfTheKidOfYourKidElements);// -> [I'm the kid of your kid]

        IntStream.range(0, 25).forEach(e -> System.out.print("-"));
        System.out.println("");
    }
}
