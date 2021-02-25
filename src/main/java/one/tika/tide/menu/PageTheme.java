package one.tika.tide.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum PageTheme {

    TOP,
    BOTTOM,
    LEFT,
    RIGHT,
    CINEMATIC,
    SURROUND;

    public List<Integer> getFillSlots(int rows) {
        List<Integer> slots = new ArrayList<>(9);

        switch(this) {
            case TOP:
                for (int i = 0; i < 9; i++) slots.add(i);
                break;
            case BOTTOM:
                for (int i = (rows - 1) * 9; i < rows * 9; i++) slots.add(i);
                break;
            case LEFT:
                for (int i = 0; i < rows * 9; i += 9) slots.add(i);
                break;
            case RIGHT:
                for (int i = 8; i < rows * 9; i += 9) slots.add(i);
                break;
            case CINEMATIC:
                for (int i = 0; i < 9; i++) slots.add(i);
                for (int i = (rows - 1) * 9; i < rows * 9; i++) slots.add(i);
                break;
            case SURROUND:
                for (int i = 0; i < 9; i++) slots.add(i);
                for (int i = (rows - 1) * 9; i < rows * 9; i++) slots.add(i);

                for (int i = 0; i < rows * 9; i += 9) slots.add(i);
                for (int i = 8; i < rows * 9; i += 9) slots.add(i);
                break;
        }

        return slots;
    }

    public List<Integer> getControlSlots(int rows) {
        switch(this) {
            case TOP: return Arrays.asList(3, 4, 5);
            case BOTTOM: return Arrays.asList((rows * 9) - 3, (rows * 9) - 4, (rows * 9) - 5);
            case LEFT:
                if (rows < 3) return null;
                switch (rows) {
                    case 3: return Arrays.asList(0, 9, 18);
                    case 4:
                    case 5:
                        return Arrays.asList(9, 18, 27);
                    case 6: return Arrays.asList(9, 18, 36);
                }
                break;
            case RIGHT:
                if (rows < 3) return null;
                switch (rows) {
                    case 3:
                        return Arrays.asList(8, 17, 26);
                    case 4:
                    case 5:
                        return Arrays.asList(17, 26, 35);
                    case 6:
                        return Arrays.asList(17, 26, 44);
                }
                break;
            case CINEMATIC:
                return Arrays.asList(3, 4, 5, (rows * 9) - 3, (rows * 9) - 4, (rows * 9) - 5);
            case SURROUND:
                if (rows < 3) return null;
                List<Integer> slots = Arrays.asList(3, 4, 5, (rows * 9) - 3, (rows * 9) - 4, (rows * 9) - 5);
                switch (rows) {
                    case 3: slots.addAll(Arrays.asList(0, 9, 18, 8, 17, 26));
                    case 4:
                    case 5:
                        slots.addAll(Arrays.asList(9, 18, 27, 17, 26, 35));
                    case 6: slots.addAll(Arrays.asList(9, 18, 36, 17, 26, 44));
                }

                return slots;
        }

        return null;
    }

}
