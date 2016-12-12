package com.example.yusuf.game;

import java.util.Random;

/**
 * Created by yusuf on 10.11.2016.
 */
public class Dice {
    int num;
    Random rand = new Random();

    //Very simple Constructor, simply sets the 'num' attribute that this class has, to 0.
    public Dice(){
        num = 0;
    }

    // Returns a random number betwen 1 and 6, like a dice
    public int getNum(){
        int rolled = 0;
        for (int i = 0; i < rand.nextInt(rand.nextInt(rand.nextInt(5) + 1) +1) + 1; i++)
            rolled = rand.nextInt(6) + 1;
        return rolled;
    }

}
