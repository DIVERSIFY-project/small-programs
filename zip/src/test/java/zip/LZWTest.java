package zip;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * User: Simon
 * Date: 26/04/16
 * Time: 14:09
 */
public class LZWTest {
    protected int stringSizeMin = 50;
    protected int stringSizeMax = 1000;
    protected int nbTrial = 100;
    protected Random random = new Random();


    @Test
    public void compressDecompressTest() {
        for(int trial = 0; trial < nbTrial; trial++) {
            String string = randomString();
            assertEquals(LZW.decompress(LZW.compress(string)), string);
        }
    }

    @Test
    public void sizeTest() {
        for(int trial = 0; trial < nbTrial; trial++) {
            String string = randomString();
            double size = LZW.compress(string).size();
            double oracleSize = LZWOracle.compress(string).size();
            assertTrue(size < (oracleSize * 1.1));
        }
    }

    protected String randomString() {
        String string = "";
        int stringSize = random.nextInt(stringSizeMax - stringSizeMin) + stringSizeMin;
        for (int i = 0; i < stringSize; i++) {
            string += ((char)random.nextInt(256));
        }
        return string;
    }
}
