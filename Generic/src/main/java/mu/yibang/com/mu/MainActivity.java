package mu.yibang.com.mu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static android.os.Build.VERSION_CODES.N;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
//        AA aa=new AA();
        Object[] PA=new Object[]{ TypedValue.COMPLEX_UNIT_SP,50.0f};
        try {
            method(textView,"setTextSize",PA, int.class,float.class );
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    class AA{
        String a1;
        int a2;
        public  void method(String a1,int a2){
            System.out.println(a1+a2);

        }
    }
    public <T> void method(Object object, String method, Object[] objects,Class<?>... params) throws  InvocationTargetException, IllegalAccessException {
        Class classsObject = object.getClass();

        Method method1 = null;
        try {
            method1 = classsObject.getDeclaredMethod(method, params);
        } catch (NoSuchMethodException e) {
            try {
                method1 = classsObject.getMethod(method, params);
            } catch (NoSuchMethodException e1) {
                e1.printStackTrace();
            }
        }
        method1.invoke(object,objects);
    }


}
