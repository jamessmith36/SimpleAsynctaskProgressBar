package dev.tae.com.SimpleAsynctaskProgressBar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private ProgressBar progressBar;
    private TextView status;
    private int result;
    private Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.pb_progress);
        progressBar.setVisibility(View.INVISIBLE);
        goButton = (Button) findViewById(R.id.btn_go);
        status = (TextView) findViewById(R.id.tv_status);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DownloadStatus().execute();
            }
        });
    }

    public class DownloadStatus extends AsyncTask<String, Integer, String> {

        private int max = 100;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            status.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

           // String str = params[0];
            for (int i = 0; i <= max; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Log.wtf("doInBackground", e.getMessage());
                }
                result = i;
                publishProgress(i);
            }
            return "Download complete!!!";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            status.setText(result + "%");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(), "complete", Toast.LENGTH_LONG);
            progressBar.setVisibility(View.INVISIBLE);

        }
    }
}
