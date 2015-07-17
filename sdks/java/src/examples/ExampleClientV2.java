import org.apache.usergrid.java.client.Client;
import org.apache.usergrid.java.client.SingletonClient;
import org.apache.usergrid.java.client.entities.Entity;
import org.apache.usergrid.java.client.query.Query;
import org.apache.usergrid.java.client.response.ApiResponse;
//import org.apache.usergrid.java.client.query

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ApigeeCorporation on 6/26/15.
 */
public class ExampleClientV2 {

  public static void main(String[] args) {
    Properties props = new Properties();
    FileInputStream f = null;

    try {
      f = new FileInputStream("/Users/ayeshadastagiri/incubator-usergrid/sdks/java/src/main/resources/example.properties");
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

    // ignore above...

    // below is the sample code

    SingletonClient.initialize(apiUrl, orgName, appName);
//    Apigee.initializeBaaSClient(apiUrl, orgName, appName);
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
    pet.save(); // PUT if by name/uuid, otherwise POST

    pet.patch(); // PATCH to update individual fields?
    pet.post(); // POST to create, fails if exists?
    pet.put(); // PUT to update, fails if doesn't exist?
    pet.delete(); // DELETE

    Entity owner = new Entity();
    owner.setType("owner");
    owner.setProperty("name", "jeff");
    owner.setProperty("age", 15);
    owner.save();

    // consider for v2 api
    //    /_entities/{collection}:{name}
    //    /_entities/{uuid}

    owner.connect(pet, "owns");
    client.connectEntities(pet, owner, "ownedBy");
    client.connectEntities(owner, pet, "owns");

    Query q = new Query.QueryBuilder()
        .collection("pets")
        .limit(100)
        .gt("age", 100)
        .gte("age", 100)
        .containsWord("field", "value")
        .desc("cats")
        .asc("dogs")
        .build();

  }
}
