import com.tinkerpop.blueprints.*;
import org.apache.commons.configuration.Configuration;
import org.apache.usergrid.java.client.NewClientBuilder;
import org.apache.usergrid.java.client.UsergridClient;

/**
 * Created by ApigeeCorporation on 6/23/15.
 */
public class UGBlueprintsGraphImpl
        implements Graph {

    private UsergridClient client;

    public UGBlueprintsGraphImpl(Configuration config) {
        client = new NewClientBuilder().build();
    }

    @Override
    public Features getFeatures() {
        return null;
    }

    @Override
    public Vertex addVertex(Object o) {
        return null;
    }

    @Override
    public Vertex getVertex(Object o) {
        return null;
    }

    @Override
    public void removeVertex(Vertex vertex) {

    }

    @Override
    public Iterable<Vertex> getVertices() {
        return null;
    }

    @Override
    public Iterable<Vertex> getVertices(String s, Object o) {
        return null;
    }

    @Override
    public Edge addEdge(Object o, Vertex vertex, Vertex vertex2, String s) {
        return null;
    }

    @Override
    public Edge getEdge(Object o) {
        return null;
    }

    @Override
    public void removeEdge(Edge edge) {

    }

    @Override
    public Iterable<Edge> getEdges() {
        return null;
    }

    @Override
    public Iterable<Edge> getEdges(String s, Object o) {
        return null;
    }

    @Override
    public GraphQuery query() {
        return null;
    }

    @Override
    public void shutdown() {

    }
}
