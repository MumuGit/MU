package mu.yibang.com.mu;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {

            @Override
            public void run() {
                Looper.prepare();

                handler=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what){
                            case 00:

                                System.out.println("ssss"+Thread.currentThread());
                                break;
                        }
                    }
                };
                handler.sendEmptyMessage(00);
                Toast.makeText(getApplicationContext(), "子线程显示", Toast.LENGTH_SHORT).show();

                Looper.loop();

            }
        }).start();

    }

}
