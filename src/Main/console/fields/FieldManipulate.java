package Main.console.fields;

import java.lang.reflect.Field;

public interface FieldManipulate {
   void Increment(Field field,Object obj) throws IllegalAccessException;
   void Decrease(Field field,Object obj) throws IllegalAccessException;
   void setTrue(Field field,Object obj) throws NoSuchMethodException, IllegalAccessException;
   void setFalse(Field field,Object obj) throws NoSuchMethodException, IllegalAccessException;
}
