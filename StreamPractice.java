import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.*;


public class StreamPractice {

    public static void main(String[] args) throws IOException {
        
        List<Integer> numberList = fillNumberList(10000, 1000);  
        
        /* 
        // showing the difference between iteration and streams 

        // counting the number of times a target appears- iteration versus streams
        // "old school" iterative approach
        int target = 43;
        long count = 0;
        
        for(int number : numberList) {
            if(number==target) {
                count++;
            }
        }
        System.out.println(target + " appears is the list " + count + " times");
        
        // stream approach
        count = numberList.stream().filter( (number) -> number==target ).count();
        
        System.out.println(target + " appears is the list " + count + " times");
        */
        
                
        /* STREAM OF RANDOM INTEGERS */
        int printNum = 10;
        int randomNumberLimit = 1000;
        Random generator = new Random(); // effectively final!
        
        // infinite stream of random integers between 0-999
        Stream<Integer> randomIntegerStream = Stream.generate(
                () -> generator.nextInt(randomNumberLimit)             
                );
        //randomIntegerStream.limit(printNum).forEach(System.out::println);
      
        // filter only the evens
        //randomIntegerStream.filter( num -> num%2==0 ).limit(printNum).forEach(System.out::println);  
        
        // get only unique random numbers
        //randomIntegerStream.distinct().limit(5).forEach(System.out::println);
        
        // convert to an IntStream and find the min and max of the first 100
        /*
        int max = randomIntegerStream.mapToInt( myIntegerObject -> myIntegerObject.intValue() ) // can swap lambda with Integer::intValue
                        .limit(100)
                        .max().getAsInt();
        System.out.println("max is " + max);
        */

        // find the min of the first 100
        //Optional<Integer> minOptional = randomIntegerStream.limit(100).min(Integer::compare);
        // Integer::compare or (n1, n2) -> Integer.compare(n1, n2)
        //int min = minOptional.get();
        
        // summing up the first 100
        // in reality, would not use reduce for this, but it's a good example
        //int sum = randomIntegerStream.limit(100).reduce( (n1, n2) -> n1 + n2).get();
        // could swap out (n1, n2) -> n1 + n2 with Integer::sum
        
        /* STREAM OF ODD POSITIVES */
        // infinite stream of odd positive numbers
        //Stream.iterate(1, num -> num + 2).limit(printNum).forEach(System.out::println);
        Stream<Integer> oddPosStream = Stream.iterate(1, num -> num + 2);
        
        // concatenate the first 10 random numbers with the odds in position 20-25
        /*
        Stream<Integer> firstTenRandoms = randomIntegerStream.limit(10);
        Stream<Integer> oddsInPosition = oddPosStream.skip(19).limit(6);
        Stream<Integer> concatenatedStream = Stream.concat(firstTenRandoms, oddsInPosition);
        concatenatedStream.forEach(System.out::println);
        */   
    
        /* STREAM OF WORDS */
        // create stream of dictionary words
        List<String> scrabbleWords = Files.readAllLines(Paths.get("words.txt"), Charset.forName("ISO-8859-1"));
        //Collections.shuffle(scrabbleWords);
        Stream<String> scrabbleStream = scrabbleWords.stream();
        
        // another way to generate the stream from the file
        //Stream<String> scrabbleStream = Files.lines(Paths.get("words.txt"), Charset.forName("ISO-8859-1"));
        
        //scrabbleStream.limit(printNum).forEach(System.out::println);
        
        // filter all two-letter words
        //scrabbleStream.filter( word -> word.length()==2 ).forEach(System.out::println);
        
        // map onto upper case- mapping to same type (Stream<String> to Stream<String>)
        //scrabbleStream.map( word -> word.toUpperCase() ).forEach(System.out::println);
        //scrabbleStream.map( String::toUpperCase ).forEach(System.out::println);
        
        // map onto a stream of the last character- mapping to a different type (Stream<String> to Stream<Character>)
        //Stream<Character> lastCharStream = scrabbleStream.map( word -> word.charAt(word.length()-1));
        //lastCharStream.limit(printNum).forEach(System.out::println);
        
        // pipelining together: filter x and z words and map to upper case
        //scrabbleStream.filter(word -> word.indexOf('x') >= 0 || word.indexOf('x') >= 0 )
        //                .map(word -> word.toUpperCase() )
        //                .forEach(System.out::println);
             
        // find the average number of vowels in all words
        //char[] vowels = {'a','e','i','o','u'};
        //double vowelAverage = scrabbleStream.mapToInt( word -> countChars(word, vowels) ).average().getAsDouble();
        //System.out.println("The average number of vowels in the words is " + vowelAverage);
              
        // find the total number of zs in all words
        char[] zChar = {'z'};
        //int totalZs = scrabbleStream.mapToInt( word -> countChars(word, zChar)).sum();
        //System.out.println("The total nunmber of zs in all the words is " + totalZs);
   
        // count up the two-letter words
        long numberOfTwoLetterWords = scrabbleWords.stream()
                .filter(word -> word.length()==2)
                .count();
        
        // find the word with the most z's
        String mostZs = scrabbleWords.stream().max( // max takes one parameter- a Comparator
                (word1, word2) -> { // this is the Comparator lambda
                    int word1ZCount = countChars(word1, zChar);
                    int word2ZCount = countChars(word2, zChar);
                    return Integer.compare(word1ZCount, word2ZCount);
                }
                ).get(); // invoked on the Optional<String>- only do this if you are certain there is a value
        System.out.println(mostZs);
        
        // are there any words with "qi"?
        boolean qiWords = scrabbleWords.stream().anyMatch( (word) -> word.toLowerCase().indexOf("qi")>=0);
        System.out.println("QI words? " + qiWords);
        
        // find any word with a q but no u
        String qNoUWord = scrabbleWords.stream()
                .filter( word -> word.toLowerCase().indexOf("q")>=0 &&
                                 word.toLowerCase().indexOf("u")<0   )
                .findAny()
                .get(); // invokes get() on the Optional<String>
        System.out.println("Q no u word: " + qNoUWord);
     
        String qNoUWithXWord = scrabbleWords.stream()
                .filter( word -> word.toLowerCase().indexOf("q")>=0 &&
                                 word.toLowerCase().indexOf("u")<0   &&
                                 word.toLowerCase().indexOf("x")>=0    )
                .findAny()
                //.get(); // invokes get() on the Optional<String>, but the optional has no value, so this will crash!
                //.ifPresent(System.out::println);  // option 1- print it if it's there- does not send back a value
                .orElse("No Such Word"); // option 2- offer up an alternative- this does send back a value
                //.orElseThrow(NoSuchElementException::new); // option3- throw an exception- this does send back a value (if one exists)
        System.out.println("Q no u word with X: " +  qNoUWithXWord);
        
        // find the largest word using a reduction
        String largestWord = scrabbleWords.stream()
            .reduce( (word1, word2) -> { // reduce takes a BinaryOperator: lambda of (String,String) that returns a String
                if(word1.compareTo(word2) >= 0) {
                    return word1;
                } else {
                    return word2;
                }
            }).get(); // only do this if I KNOW that the stream is not empty
            // if wanted to print instead of store: instead of .get(), use .ifPresent(System.out::println);
        System.out.println("the largest word is " + largestWord);
        
        // concatenate a list of words with a q but no u
        String qWithoutUWordList = scrabbleWords.stream()
            .filter(word -> word.toLowerCase().indexOf("q")>=0 &&
                                 word.toLowerCase().indexOf("u")<0   )
            .collect(Collectors.joining(","));
        System.out.println(qWithoutUWordList);
      
        // testing laziness!
        /*
        String[] words = {"apple", "ball", "banana"};
        Stream.of(words)
            .map(s -> {
                System.out.println("in the map with "+ s);
                return s.toUpperCase();
                 })
        .forEach(s -> System.out.println("in the forEach with "+ s));
        */
    }

    // helper method to count how many characters in a word
    private static int countChars(String word, char[] charsToCheck) {
        int total = 0;
        char[] letters = word.toLowerCase().toCharArray();
        for(char letter: letters) {
            for(char charToCheck : charsToCheck) {
                if(letter==charToCheck) {
                    total++;
                }
            }
        }
        return total;
    }
    

    
    private static List<Integer> fillNumberList(int size, int max) {
        Random generator = new Random();
        List<Integer> numbers = new ArrayList<>();
        for(int i=0; i<size; i++) {
            numbers.add(generator.nextInt(max));
        }
        return numbers;
    }
}
