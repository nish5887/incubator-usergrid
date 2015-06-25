import org.apache.usergrid.java.client.UsergridClient;
import org.apache.usergrid.java.client.NewClientBuilder;
import org.apache.usergrid.java.client.model.UGEntity;

import java.util.HashMap;

/**
 * Created by ApigeeCorporation on 6/23/15.
 */
public class ClientConsumer {
    public static void main(String[] args) {

//        UsergridClient client1 = new NewClientBuilder()
//                .withProperties(propsFile).build();
//
//        UsergridClient clienta = new NewClientBuilder()
//                .withMap(new HashMap()).build();

        UsergridClient client2 = new NewClientBuilder()
                .withOrganization("MyOrg")
                .withApp("MyApp")
                .build();

        client2.collection("people")
                .create();

        UGEntity e = client2.collection("people").newEntity();

        e.put();
    }
}
