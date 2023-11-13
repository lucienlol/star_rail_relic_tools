package com.example.starrail.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RelicCheckServiceImplTest {

    @Test
    void prepare() {
        int statCount1 = 1;
        int statCount2 = 1;
        int statCount3 = 0;
        int statCount4 = 3;

        double[] weightedValue = new double[5];
        weightedValue[0] = 0;
        int countedNum = 0;
        int index = 1;
        while(index <= 4 && countedNum < statCount1) {
            weightedValue[index] = weightedValue[index - 1] + 4;
            index++;
            countedNum++;
        }
        countedNum = 0;
        while(index <= 4 && countedNum < statCount2) {
            weightedValue[index] = weightedValue[index - 1] + 3;
            index++;
            countedNum++;
        }
        countedNum = 0;
        while(index <= 4 && countedNum < statCount3) {
            weightedValue[index] = weightedValue[index - 1] + 2;
            index++;
            countedNum++;
        }
        countedNum = 0;
        while(index <= 4 && countedNum < statCount4) {
            weightedValue[index] = weightedValue[index - 1] + 1;
            index++;
            countedNum++;
        }
        while(index <= 4) {
            weightedValue[index] = weightedValue[index - 1];
            index++;
        }

        System.out.println(Arrays.toString(weightedValue));
    }
}