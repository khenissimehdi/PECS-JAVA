# PECS (Producer Extends Consumer Super) in JAVA
In this gitHub repository I will try to explain the PECS rule as I understand and as I learned from my teacher
and what I found on the internet.

## 0.  Personnel opinion about the matter
To be honest understanding the PECS rule was one of the hardest things I ever encountered while learning Java
and im sure that there is some other hidden aspects of this rule that are not out on the web and that you will get used
too as you code. 

## 1. Introduction
The PECS rule means Producer -> Extends, Consumer -> Super you will use this rule when you are dealing with Generics,
specially with Java when you are going to be picking between using an `<? extends Class>` or `<? super Class>`.
The rule is supposed to make your life easier while dealing with this **TORTUROUS** problem. 
but no one really explain how to use this problem, and sometimes you will think of a Producer as Consumer and 
Consumer as a Producer, so bear with me will I try to explain it to myself and to the people reading this paper.

## 2. super and extends ?
### 2.1 What is `<? super Class>` ?
`<? super Class>` means that whatever type that you are going to accept have to be or contain ether the  type`Class` itself
or bigger
### 2.2 What is `<? extends Class>` ?
`<? extends Class>` means that whatever type tha you are going to accept have to be or contain ether the type `Class` itself
or smaller
### 2.3 Drawing that explains what i mean
```
               +-------------------------------------------------------------------------+                                                                                                                     
               |               +--------------------------------------+                  |                                         
               |               |THE FATHER OF THE FATHER OF YOUR CLASS|                  |                                         
               |               +--------------------------------------+                  |                                         
               |                                                                         |--------------- ? super YOURCLASS        
               |                                                                         |                   AREA IS HERE !        
               |                      +------------------------+                         |                                         
               |                      |THE FATHER OF YOUR CLASS|                         |                                         
               |                      +------------------------+                         |                                                                                                              
               +-------------------------------------------------------------------------+                                         
               |                            +--------------+                             |                                         
               |                            |  YOUR CLASS  |                             |---------------- YOURCLASS WILL BE ALWAYS
               |                            +--------------+                             |                      IN BOTH AREAS      
               |-------------------------------------------------------------------------|                                                                                                                                                       
               |                            +--------------+                             |                                         
               |                            |   YOUR KID   |                             |                                         
               |                            +--------------+                             |                                         
               |                                                                         |-------------- ? extends YOURCLASS       
               |                                                                         |                   AREA IS HERE !                                               
               |                        +-----------------------+                        |                                         
               |                        |  THE KID OF YOUR KID  |                        |                                         
               |                        +-----------------------+                        |                                                                                                                                      
               +-------------------------------------------------------------------------+  
```


## 3. Producer and Consumer ?
### 3.1 What is Producer ?
A Producer is for example a list that we are going to ONLY READ FROM.

### 3.2 What is a Consumer ?
A Consumer is for example a list that we are going to ONLY WRITE INTO

## 4. BOXES are the solution

### 4.1 You CAN'T put a BIGGER BOX into A SMALLER one, and You CAN only put a SMALLER BOX into a bigger one !
Pretty simple right and logical right but basically this the simple concept that helped me understand this PECS rule
all credits goes this answer on Stackoverflow : [answer link ](https://stackoverflow.com/questions/2723397/what-is-pecs-producer-extends-consumer-super/64888058#64888058)
So lets say we have a box from now on I will be giving example using that box.

### 4.2 Producer and boxes
The Producer will be something that we are going to read from (in our case we can see it as a box of some type) and put it elements inside our box that means that those 
elements can't be  bigger but at least equal to our box and in Java the only way we have, to know if the box is smaller is by checking if the type of the elements extends form the wanted type (the type of the elements we are going to put in our box ),
and that where the `<? extends Class>` comes from 

### 4.3 Consumer and boxes
The Consumer will be something that we are going to write into (in our case we can see it as a box of some type) and put our elements (elements of our box of some type) inside it, that means the box need to be big enough to accept elements
means that it have to be bigger or at least equal to our box and in Java the only way we have, to know if the box(we are writing into) is bigger is by checking that the type of the elements is a super type of us, and that where
the `<? super Class>` comes from 

### 4.4 Example 
```java
public class Collections { 
  public static <T> void copy(List<? super T> dest, List<? extends T> src) {
      for (int i = 0; i < src.size(); i++) 
        dest.set(i, src.get(i)); 
  } 
}
```
Here the Consumer is the dest list that is going to take elements from the Producer that is in this case src List and consume them ( means putting them inside her )
