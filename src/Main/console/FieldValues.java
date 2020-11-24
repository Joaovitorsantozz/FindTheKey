package Main.console;

import java.lang.reflect.Field;

public class FieldValues implements FieldManipulate {
    public int valueIncrease = 5, valdecrease = 0;
    public boolean increase, decrease;

    @Override
    public void Increment(Field field, Object obj) {
        if (increase) {
            try {
                float actual = field.getFloat(obj);
                actual++;
                field.setFloat(obj, actual);
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
            increase = false;
        }
    }

    @Override
    public void Decrease(Field field, Object obj) {
        if (decrease) {
            try {
                float actual = field.getFloat(obj);
                actual--;
                field.setFloat(obj, actual);
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
            decrease=false;
        }
    }

    @Override
    public void setTrue(Field field, Object obj) throws NoSuchMethodException, IllegalAccessException {
        if (!field.getType().getName().equals("boolean")) throw new NoSuchMethodException("The type need be a boolean");
        field.set(obj, true);
    }

    @Override
    public void setFalse(Field field, Object obj) throws NoSuchMethodException, IllegalAccessException {
        if (!field.getType().getName().equals("boolean")) throw new NoSuchMethodException("The type need be a boolean");
        field.set(obj, false);
    }
}
