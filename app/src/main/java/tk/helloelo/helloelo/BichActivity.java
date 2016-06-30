package tk.helloelo.helloelo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class BichActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<String> gameIds = new ArrayList<String>();

    BichActivity self;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bich);

        self = this;

        TextView playerName = (TextView) findViewById(R.id.playerName);
        playerName.setText(HelloEloClient.player.name);

        ImageView playerPicture = (ImageView) findViewById(R.id.playerPicture);
        new ImageDownloader(playerPicture).execute(HelloEloClient.player.playerPicture);

        // Set up ListView and Adapter
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(this);

        HelloEloClient.get("games", null, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                ArrayList<String> games = new ArrayList<String>();
                //String[] gamesArray = {};
                for(int i = 0 ; i < response.length(); i++){
                    try {
                        JSONObject row = response.getJSONObject(i);
                        games.add(row.getString("name"));
                        //adapter.add(row.getString("name"));
                        gameIds.add(row.getString("id_game"));
                    }
                    catch (JSONException e) {
                        Log.d("yeah", e.getMessage());
                    }
                }

                //games.toArray(gamesArray);
                adapter = new ArrayAdapter<String>(self,
                        R.layout.image_btn_list_item, R.id.label, games);

                ListView listView = (ListView) findViewById(R.id.list);
                listView.setAdapter(adapter);

                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onRetry(int retryNo) {
            }

        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
