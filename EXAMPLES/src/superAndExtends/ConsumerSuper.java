package superAndExtends;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;


public class ConsumerSuper {
    public static void main(String[] args) {

        class BoxKeeper<T> {
            public void write(Collection<? extends T> innerBox, Collection<? super T> outBox_CONSUMER) {
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

        var boxKeeperOfYouElementsType  = new BoxKeeper<You>();
        var boxKeeperOfYourKidsElementsType = new BoxKeeper<YourKid>();
        var boxKeeperOfTheKidOfYourKidElementsType = new BoxKeeper<TheKidOfYourKid>();

        var packOfYouElements = List.of(you,you,you);
        var emptyOfYouElements = new ArrayList<You>();
        var packOfYourKidElements = List.of(yourKid,yourKid,yourKid);
        var emptyOfYourKidElements = new ArrayList<YourKid>();
        var packOfTheKidOfYourKidElements = List.of(theKidOfYourKid,theKidOfYourKid,theKidOfYourKid);
        var emptyOfTheKidOfYourKidElements = new ArrayList<TheKidOfYourKid>();


        boxKeeperOfYouElementsType.write(packOfYouElements, emptyOfYouElements);
        System.out.println(emptyOfYouElements);
        boxKeeperOfYouElementsType.write(packOfYourKidElements, emptyOfYouElements);
        System.out.println(emptyOfYouElements);
        boxKeeperOfYouElementsType.write(packOfTheKidOfYourKidElements, emptyOfYouElements);
        System.out.println(emptyOfYouElements);


        IntStream.range(0,  boxKeeperOfYouElementsType.toString().length()).forEach(e -> System.out.print("-"));
        System.out.println("");

        boxKeeperOfYourKidsElementsType.write(packOfYourKidElements, emptyOfYourKidElements);
        System.out.println(emptyOfYourKidElements);
        boxKeeperOfYourKidsElementsType.write(packOfTheKidOfYourKidElements, emptyOfYourKidElements);
        System.out.println(emptyOfYourKidElements);

        IntStream.range(0,  boxKeeperOfYouElementsType.toString().length()).forEach(e -> System.out.print("-"));
        System.out.println("");

        boxKeeperOfTheKidOfYourKidElementsType.write(packOfTheKidOfYourKidElements, emptyOfYouElements);
        System.out.println(emptyOfYouElements);
        boxKeeperOfYourKidsElementsType.write(packOfTheKidOfYourKidElements, emptyOfYourKidElements);
        System.out.println(emptyOfYourKidElements);
        boxKeeperOfTheKidOfYourKidElementsType.write(packOfTheKidOfYourKidElements, emptyOfTheKidOfYourKidElements);
        System.out.println(emptyOfTheKidOfYourKidElements);

        IntStream.range(0,  boxKeeperOfYouElementsType.toString().length()).forEach(e -> System.out.print("-"));
        System.out.println("");
    }
}
