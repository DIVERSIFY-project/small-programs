package md5;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import java.util.Random;

/**
 * User: Simon
 * Date: 26/04/16
 * Time: 13:58
 */
public class MD5Test {
    protected int stringSizeMin = 50;
    protected int stringSizeMax = 1000;
    protected int nbTrial = 100;
    protected Random random = new Random();

    @Test(timeout = 5000)
    public void md5Test() {
        for(int trial = 0; trial < nbTrial; trial++) {
            String string = randomString();
            assertArrayEquals(MD5.computeMD5(string.getBytes()), MD5Oracle.computeMD5(string.getBytes()));
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
