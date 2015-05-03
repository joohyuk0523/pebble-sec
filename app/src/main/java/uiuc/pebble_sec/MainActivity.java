package uiuc.pebble_sec;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RSA testobj = new RSA();
        testobj.generateKey();
        final Button enButton = (Button) findViewById(R.id.enbutton);
        final Button deButton = (Button) findViewById(R.id.debutton);
        final EditText input = (EditText) findViewById(R.id.Input);
        final EditText Raw = (EditText) findViewById(R.id.raw);
        final EditText output = (EditText) findViewById(R.id.OriginText);
        enButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Raw.setText(RSA.encrypt(input.getText().toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        });
        deButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    output.setText(String.valueOf(RSA.decrypt(Raw.getText()
                            .toString())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
