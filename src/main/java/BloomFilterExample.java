import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;

import java.util.stream.IntStream;

/**
 * Created by ranand on 7/15/2017 AD.
 */

/**
 * BloomFilter gives us either :
 * <p>
 * 1. A Definite NO
 * 2. A Maybe YES , with some false positives
 */

public class BloomFilterExample {
    public static void main(String[] args) {


        System.out.println("testing bloom filter ... \n");

        // expected insertions to bloom filter in its lifetime
        int expectedInsertions = 1000000;

        // false positive probability (here 0.01 = 1% of expectedInsertions)
        double fpp = 0.01; // this is the accepted false positive
        Funnel<CharSequence> funnel = Funnels.stringFunnel(Charsets.UTF_8);

        BloomFilter<String> words = BloomFilter.create(funnel, expectedInsertions, fpp);

        System.out.println("Expected insertions : "+ expectedInsertions );
        System.out.println("Accepted False positives probability = "+fpp + " approx.");

        int insertions = 1000000;
        System.out.println("Setting up bloom filter ... ");
        IntStream.range(0, insertions)
                .forEach(n -> words.put(("word" + n).toString()));

        System.out.println("Insertions done: "+ insertions + "\n");

        // (positives count should be 1000000)
        System.out.println("Testing for all inserted elements ...");
        long positiveTestCount = IntStream.range(0, insertions)
                .mapToObj(n -> ("word" + n).toString())
                .filter(element -> words.mightContain(element))
                .count();

        System.out.println("Expected positives : " + insertions);
        System.out.println("Real positives : " + positiveTestCount + "\n");


        // (false positives should be around 0.01 % of 100000 = 10000 approx.)
        long falsePositiveCount = IntStream.range(2000000, 3000000)
                .mapToObj(n -> ("word" + n).toString())
                .filter(element -> words.mightContain(element))
                .count();

        System.out.println("Testing for all non-inserted elements ...");
        System.out.println("Expected false positives , i.e. (fpp * expectedInsertions) approx. : " + (fpp * expectedInsertions));
        System.out.println("Real false positives : " + falsePositiveCount);


    }
}
