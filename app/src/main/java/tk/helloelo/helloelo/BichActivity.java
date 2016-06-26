package tk.helloelo.helloelo;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class BichActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bich);

        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };
        // Использование собственного шаблона
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.image_btn_list_item, R.id.label, values);

        TextView playerName = (TextView) findViewById(R.id.playerName);
        playerName.setText(HelloEloClient.player.name);

        ImageView playerPicture = (ImageView) findViewById(R.id.playerPicture);
        new ImageDownloader(playerPicture).execute(HelloEloClient.player.playerPicture);

        // Set up ListView and Adapter
        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
