package tetris2;

import java.util.*;

public class Puzzle {

    Pair[] point = new Pair[4];
    Pair[] temp = new Pair[4];
    int i;

    public Puzzle() {
        for (int j = 0; j < 4; j++) {
            point[j] = new Pair();
            temp[j] = new Pair();
        }
        point[0].set(3, 1);
        i = rand(1, 13);
        set(i);
    }

    public void set(int i) {
        if (this.i == 1) {
            point[1].set(point[0].first + 1, point[0].second);
            point[2].set(point[0].first - 1, point[0].second);
            point[3].set(point[0].first + 2, point[0].second);
        }
        if (this.i == 3) {
            point[1].set(point[0].first, point[0].second + 1);
            point[2].set(point[0].first, point[0].second - 1);
            point[3].set(point[0].first + 1, point[0].second);
        }
        if (this.i == 5) {
            point[1].set(point[0].first + 1, point[0].second);
            point[2].set(point[0].first, point[0].second + 1);
            point[3].set(point[0].first + 1, point[0].second + 1);
        }
        if (i == 2) {
            point[1].set(point[0].first, point[0].second - 1);
            point[2].set(point[0].first, point[0].second + 1);
            point[3].set(point[0].first, point[0].second + 2);
        }
        if (i == 4) {
            point[1].set(point[0].first, point[0].second + 1);
            point[2].set(point[0].first - 1, point[0].second);
            point[3].set(point[0].first + 1, point[0].second);
        }
        if (i == 6) {
            point[1].set(point[0].first, point[0].second - 1);
            point[2].set(point[0].first, point[0].second + 1);
            point[3].set(point[0].first + 1, point[0].second + 1);
        }
        if (i == 7) {
            point[1].set(point[0].first - 1, point[0].second + 1);
            point[2].set(point[0].first + 1, point[0].second);
            point[3].set(point[0].first - 1, point[0].second);
        }
        if (i == 8) {
            point[1].set(point[0].first - 1, point[0].second);
            point[2].set(point[0].first, point[0].second + 1);
            point[3].set(point[0].first + 1, point[0].second + 1);
        }
        if (i == 9) {
            point[1].set(point[0].first + 1, point[0].second - 1);
            point[2].set(point[0].first + 1, point[0].second);
            point[3].set(point[0].first, point[0].second + 1);
        }
        if (i == 10) {
            point[1].set(point[0].first, point[0].second - 1);
            point[2].set(point[0].first, point[0].second + 1);
            point[3].set(point[0].first - 1, point[0].second + 1);
        }
        if (i == 11) {
            point[1].set(point[0].first - 1, point[0].second - 1);
            point[2].set(point[0].first + 1, point[0].second);
            point[3].set(point[0].first - 1, point[0].second);
        }
        if (i == 14) {
            point[1].set(point[0].first, point[0].second - 1);
            point[2].set(point[0].first, point[0].second + 1);
            point[3].set(point[0].first - 1, point[0].second);
        }
        if (i == 15) {
            point[1].set(point[0].first, point[0].second - 1);
            point[2].set(point[0].first + 1, point[0].second);
            point[3].set(point[0].first - 1, point[0].second);
        }
        if (i == 16) {
            point[1].set(point[0].first, point[0].second - 1);
            point[2].set(point[0].first, point[0].second + 1);
            point[3].set(point[0].first - 1, point[0].second - 1);
        }
        if (i == 17) {
            point[1].set(point[0].first + 1, point[0].second - 1);
            point[2].set(point[0].first + 1, point[0].second);
            point[3].set(point[0].first - 1, point[0].second);
        }
        if (i == 12) {
            point[1].set(point[0].first, point[0].second + 1);
            point[2].set(point[0].first + 1, point[0].second);
            point[3].set(point[0].first - 1, point[0].second + 1);
        }
        if (i == 13) {
            point[1].set(point[0].first, point[0].second - 1);
            point[2].set(point[0].first + 1, point[0].second);
            point[3].set(point[0].first + 1, point[0].second + 1);
        }
        if (i == 18) {
            point[1].set(point[0].first, point[0].second - 1);
            point[2].set(point[0].first + 1, point[0].second - 1);
            point[3].set(point[0].first, point[0].second + 1);
        }
        if (i == 19) {
            point[1].set(point[0].first - 1, point[0].second);
            point[2].set(point[0].first + 1, point[0].second);
            point[3].set(point[0].first + 1, point[0].second + 1);
        }
    }

    public void save() {
        for (int i = 0; i < 4; i++) {
            temp[i].first = point[i].first;
            temp[i].second = point[i].second;
        }
    }

    public void load() {
        for (int i = 0; i < 4; i++) {
            point[i].first = temp[i].first;
            point[i].second = temp[i].second;
        }
    }

    public static int rand(int min, int max) {
        try {
            Random rn = new Random();
            int range = max - min + 1;
            int randomNum = min + rn.nextInt(range);
            return randomNum;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
