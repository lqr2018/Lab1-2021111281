package basis;

//import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

class GraphProcessorTest {

    @org.junit.jupiter.api.Test
    public void testGenerateGraph() {
        // Create a temporary file
        File tempFile = null;
        int flag = 4;
        try {
            tempFile = File.createTempFile("testGraph", ".txt");
            FileWriter writer = new FileWriter(tempFile);
            switch(flag){
                case 1:
                    writer.write("a little baby");
                    break;
                case 2:
                    writer.write("@ @ @ @");
                    break;
                case 3:
                    writer.write("a @ little @ baby");
                    break;
                case 4:
                    writer.write("a @ little @ baby の");
                    break;
                default:
                    System.out.println("wrong number");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to create temporary file for testing.");
        }

        // Generate graph
        DirectedGraph graph = GraphProcessor.generateGraph(tempFile.getAbsolutePath());

        // Validate graph structure
        switch(flag){
            case 1:
                assertNotNull(graph);
                assertEquals(3, graph.getVertices().size());
                assertTrue(graph.hasEdge("a", "little"));
                assertTrue(graph.hasEdge("little", "baby"));
                System.out.println("测试1通过");
                break;
            case 2:
                assertNotNull(graph);
                assertEquals(0, graph.getVertices().size());
                System.out.println("测试2通过");
                break;
            case 3:
                assertNotNull(graph);
                assertEquals(3, graph.getVertices().size());
                assertTrue(graph.hasEdge("a", "little"));
                assertTrue(graph.hasEdge("little", "baby"));
                System.out.println("测试3通过");
                break;
            case 4:
                assertNotNull(graph);
                assertEquals(3, graph.getVertices().size());
                assertTrue(graph.hasEdge("a", "little"));
                assertTrue(graph.hasEdge("little", "baby"));
                System.out.println("测试4通过");
                break;
            default:
                System.out.println("wrong number");
        }

        // Delete the temporary file
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }
}