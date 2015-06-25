package org.apache.usergrid.java.client;

/**
 * Created by ApigeeCorporation on 6/23/15.
 */
public class NewClientBuilder {

    private String myOrg;
    private String myApp;

    public NewClientBuilder withOrganization(String myOrg) {
        this.myOrg = myOrg;
        return this;
    }

    public NewClientBuilder withApp(String myApp) {
        this.myApp = myApp;
        return this;
    }

    public UsergridClient build() {
        UsergridClient client = new UsergridClient();
        return client;
    }
}
