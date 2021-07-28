import freeuni.edu.ge.Helpers.HashUsingSHA1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTests {

    @Test
    public void simpleTest1(){
        HashUsingSHA1 hash = new HashUsingSHA1();
        assertEquals(hash.generateHash("example"),hash.generateHash("example"));
        assertEquals(hash.generateHash("trySec1ond"),hash.generateHash("trySec1ond"));
        assertEquals(hash.generateHash("dbh5!wdDD23"),hash.generateHash("dbh5!wdDD23"));
    }
}
