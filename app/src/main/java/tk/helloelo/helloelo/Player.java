package tk.helloelo.helloelo;

import org.json.JSONException;
import org.json.JSONObject;

public class Player {
    public String playerId;
    public String playerPicture;
    public String name;
    public String email;
    public String organizationId;
    public String organizationName;

    /**
     *
     *             'player_id' => $player->idPlayer,
     'player_picture' => $player->picture,
     'name' => $player->name,
     'email' => $player->email,
     'organization_id' => $organization->idOrganization,
     'organization' => $organization->name,

     */


    public static Player fromJSONObject(JSONObject data) throws JSONException {
        Player player = new Player();
        player.name = data.get("name").toString();
        player.playerId = data.get("player_id").toString();
        player.playerPicture = data.get("player_picture").toString();
        player.email = data.get("email").toString();
        player.organizationId = data.get("organization_id").toString();
        player.organizationName = data.get("organization").toString();
        return player;
    }
}
