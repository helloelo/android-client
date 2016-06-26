package tk.helloelo.helloelo;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class BichActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String[] games;
    String[] gameIds;

    BichActivity self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bich);

        HelloEloClient.get("games", null, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                ArrayList<String> g = new ArrayList<String>();
                ArrayList<String> gi = new ArrayList<String>();
                for(int i = 0 ; i < response.length(); i++){
                    try {
                        JSONObject row = response.getJSONObject(i);
                        g.add(row.getString("name").toString());
                        gi.add(row.getString("id_game").toString());
                    }
                    catch (JSONException e) {
                        Log.d("yeah", e.getMessage());
                    }
                }
                games = (String[])g.toArray();
                gameIds = (String[])gi.toArray();

                // Set up ListView and Adapter
                ListView listView = (ListView) findViewById(R.id.list);

                // Использование собственного шаблона
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(self,
                        R.layout.image_btn_list_item, R.id.label, games);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(self);
            }

            @Override
            public void onRetry(int retryNo) {
            }

        });

        self = this;

        TextView playerName = (TextView) findViewById(R.id.playerName);
        playerName.setText(HelloEloClient.player.name);

        ImageView playerPicture = (ImageView) findViewById(R.id.playerPicture);
        new ImageDownloader(playerPicture).execute(HelloEloClient.player.playerPicture);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
