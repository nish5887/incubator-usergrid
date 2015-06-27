import org.apache.usergrid.java.client.Client;
import org.apache.usergrid.java.client.SingletonClient;
import org.apache.usergrid.java.client.entities.Entity;
import org.apache.usergrid.java.client.response.ApiResponse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ApigeeCorporation on 6/26/15.
 */
public class ConnectorsClientV2 {

  public static void main(String[] args) {
    Properties props = new Properties();
    FileInputStream f = null;

    try {
      f = new FileInputStream("/Users/ApigeeCorporation/code/usergrid/myfork/incubator-usergrid/sdks/java/src/main/resources/example.properties");
      props.load(f);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    String orgName = props.getProperty("usergrid.organization");
    String appName = props.getProperty("usergrid.application");

    String client_id = props.getProperty("usergrid.client_id");
    String client_secret = props.getProperty("usergrid.client_secret");
    String apiUrl = props.getProperty("usergrid.apiUrl");

    SingletonClient.initialize(apiUrl, orgName, appName);
    Client client = SingletonClient.getInstance();

    ApiResponse response = client.authorizeAppClient(client_id, client_secret);

    System.out.println(response);

    String token = client.getAccessToken();

    System.out.println(token);

    Entity pet = new Entity();

    pet.setType("pet");
    pet.setProperty("name", "max");
    pet.setProperty("age", 15);
    pet.setProperty("owner", (String) null);
    pet.save();

    Entity owner = new Entity();
    owner.setType("owner");
    owner.setProperty("name", "jeff");
    owner.setProperty("age", 15);
    owner.save();

    client.connectEntities(pet, owner, "ownedBy");
    client.connectEntities(owner, pet, "owns");
  }
}
