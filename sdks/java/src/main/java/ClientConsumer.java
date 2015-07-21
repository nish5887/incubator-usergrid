import org.apache.usergrid.java.client.Client;
import org.apache.usergrid.java.client.UGConnection;
import org.apache.usergrid.java.client.UsergridClient;
import org.apache.usergrid.java.client.NewClientBuilder;
import org.apache.usergrid.java.client.entities.Entity;
import org.apache.usergrid.java.client.model.UGEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ApigeeCorporation on 6/23/15.
 */
public class ClientConsumer {
  public static void main(String[] args) {

    // need a singleton client
//    Client
//        .withClientId("")
//        .withApiUrl("")
//        .withDefaultCollection("")
//    .build();

    //easily delete entity by UUID
//    Entity.UUID("<uuid>").delete();
//    Entity.UUID("<uuid>").get();
//    Entity.Type("pets"').UUID("<uuid>").get();
    // Entity("Pets", "<uuid>").delete()
//    Entity.Type("pets").Name("<name>").get();

    // this incurs object creation and GC cost
    new Entity("<uuid>").delete();

    // DAO pattern: entity
//    Entity e = new Entity("<uuid>");
//    e.collection("pets");
//    e.setType("pets");
//    e.setProperty("breed", "terrier");
//    e.setProperty("color", "black");
//    e.setProperty("weight", "black");
//    e.save();

  // DAO pattern: connection
//    UGConnection ugConnection = new UGConnection();
//    ugConnection.source("<uuid>");
//    ugConnection.target("<uuid>");
//    ugConnection.target("<collection>", "<name>");
//    ugConnection.save();
  }
}
