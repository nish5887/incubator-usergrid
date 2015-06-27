import org.apache.usergrid.java.client.Client;
import org.apache.usergrid.java.client.SingletonClient;
import org.apache.usergrid.java.client.builder.QueryBuilder;
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
      f = new FileInputStream("/Users/ApigeeCorporation/code/usergrid/myfork/incubator-usergrid/sdks/java/src/main/resources/secure/api-connectors.properties");
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

//// GET /org/app/pets?ql=select * where
//// name="max"
//// and name contians "max"
//// and age gte 10
//// and age gte 10
//// and age gte 10
//
//    QueryBuilder query = SingletonClient.QueryBuilder
//        .type("pets")
//        .filter("name", "max")
//        .containsWord("name", "max") // true: "max is my cat" / false: "maxisnotmycat"
//        .containsText("name", "max")// true: "max is my cat" / true: "maxisnotmycat"
//        .startsWith("name", "max")
//        .endsWith("name", "max")
//        .endsWith("name", "max")
//        .limit(10)
//        .offset(10)
//        .attributeExists("mustHave")
//        .descending("age")
//        .ascending("age")
//        .gte("age", 10)
//        .gt("age", 10)
//        .lt("age", 10)
//        .lte("age", 10)
//        .build();
//
//    ApiResponse response2 = query.get();

//    client.createEntityAsync(entity, new ApiResponseCallback(){
//      @Override
//      public void onException(Exception ex) {
//        Log.i("NewBook", ex.getMessage());
//      }
//
//      @Override
//      public void onResponse(ApiResponse response) {
//        CounterIncrement counterIncrement = new CounterIncrement();
//        counterIncrement.setCounterName("book_add");
//        client.createEventAsync(null, counterIncrement, new ApiResponseCallback(){
//          @Override
//          public void onException(Exception ex) {
//            Log.i("book_add", ex.getMessage());
//          }
//
//          @Override
//          public void onResponse(ApiResponse counterResponse) {
//            Log.i("book_add", "counter incremented");
//          }
//        });
//        finish();
//      }
//    });

  }
}
