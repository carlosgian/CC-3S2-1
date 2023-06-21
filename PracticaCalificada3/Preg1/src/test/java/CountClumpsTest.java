import org.example.CountClumps;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountClumpsTest {
    CountClumps count = new CountClumps();

    @Test //T1
    void inputArrayIsNull(){
        int actual = count.countClumps(null);
        assertEquals(0, actual );
    }

    @Test //T2
    void inputArrayIsLengthZero(){
        int[] example = new int[]{};
        int actual = count.countClumps(example);
        assertEquals(0, actual );
    }

    @Test //T3
    void inputArrayIsLengthOne(){
        int[] example = new int[]{2};
        int actual = count.countClumps(example);
        assertEquals(0, actual );
    }

    @Test //T4
    void inputArrayIsAllTheSame(){
        int[] example = new int[]{2,2,2,2,2};
        int actual = count.countClumps(example);
        assertEquals(1, actual );
    }

    @Test //T5
    void inputArrayHasClumpAtStart(){
        int[] example = new int[]{2,2,2,1,3};
        int actual = count.countClumps(example);
        assertEquals(1, actual );
    }

    @Test //T6
    void inputArrayHasClumpAtEnd(){
        int[] example = new int[]{1,3,2,2,2};
        int actual = count.countClumps(example);
        assertEquals(1, actual );
    }

    @Test //T7
    void inputArrayHasClumpAtStartAndEnd(){
        int[] example = new int[]{2,2,1,2,2,2};
        int actual = count.countClumps(example);
        assertEquals(2, actual );
    }

    @Test //T8
    void inputArrayHasClumpInTheMiddle(){
        int[] example = new int[]{1,2,3,3,3,2,1};
        int actual = count.countClumps(example);
        assertEquals(1, actual );
    }
}
