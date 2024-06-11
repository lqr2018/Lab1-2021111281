package ui;
import basis.DirectedGraph;
import basis.GraphProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


class calcShortestPathTest {

    private GraphProcessor graphProcessor;
    private DirectedGraph graph;
    private BaseWindowController bwc;
    private final int infinity = Integer.MAX_VALUE;

    @BeforeEach
    void setUp() {
        graphProcessor = new GraphProcessor();
        graph = new DirectedGraph();
        bwc = new BaseWindowController();
        File tempFile = null;
        try {
            tempFile = File.createTempFile("testGraph", ".txt");
            FileWriter writer = new FileWriter(tempFile);
            writer.write("a little baby");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to create temporary file for testing.");
        }
        graph = GraphProcessor.generateGraph(tempFile.getAbsolutePath());
        bwc.graph = graph;
    }

    @Test
    void testCalcShortestPath_NoVerticesFound1() {
        //缺少
        assertEquals("No an or babies in the graph!", bwc.calcShortestPath("an", "babies"));
        System.out.println("测试1通过");
    }

    @Test
    void testCalcShortestPath_NoVerticesFound2() {
        //缺少
        assertEquals("No a or babies in the graph!", bwc.calcShortestPath("a", "babies"));
        System.out.println("测试2通过");
    }

    @Test
    void testCalcShortestPath_NoPathFound() {
        //初始化图，但不可达
        assertEquals("No path from baby to a!", bwc.calcShortestPath("baby", "a"));
        System.out.println("测试3通过");
    }

    @Test
    void testCalcShortestPath_PathFound() {
        //初始化图，并可达
        assertEquals("The length of the shortest path is 2", bwc.calcShortestPath("a", "baby"));
        System.out.println("测试4通过");
    }

}
