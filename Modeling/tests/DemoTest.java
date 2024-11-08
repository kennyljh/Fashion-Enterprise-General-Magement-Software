package Modeling.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import txedt.PoorTextEditor;

import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

public class DemoTest {

    private PoorTextEditor editor;

    @BeforeEach
    public void setUp() {
        editor = new PoorTextEditor();
    }

    @Test
    public void testProcessTextFile() throws Exception {
        // Arrange
        String inputFile = "FruitsInventory.txt";
        // Creating a sample input text file
        String inputContent = "item1:\nname/apple\nprice/4.12\nquantity/500\n" +
                "item2:\nname/banana\nprice/1.32\nquantity/2000\n";
        Files.write(Paths.get(inputFile), inputContent.getBytes());

        // Act: Process the text file
        editor.processTextFile(inputFile);

        // Assert: Check if the repository is populated
        assertNotNull(editor.getRepository(), "Repository should not be null");
        assertTrue(editor.getRepository().containsKey("item1"), "Repository should contain item1");
        assertTrue(editor.getRepository().containsKey("item2"), "Repository should contain item2");
    }

    @Test
    public void testRetrieveValue() throws Exception {
        // Arrange: Prepare input and process
        String inputFile = "FruitsInventory.txt";
        String inputContent = "item1:\nname/apple\nprice/4.12\nquantity/500\n" +
                "item2:\nname/banana\nprice/1.32\nquantity/2000\n";
        Files.write(Paths.get(inputFile), inputContent.getBytes());
        editor.processTextFile(inputFile);

        // Act: Retrieve a value from the repository
        String retrievedValue = editor.retrieveValue("item1", "price");

        // Assert: Check if the correct value is retrieved
        assertEquals("4.12", retrievedValue, "The price for item1 should be 4.12");
    }

    @Test
    public void testSetValue() throws Exception {
        // Arrange: Prepare input and process
        String inputFile = "FruitsInventory.txt";
        String inputContent = "item1:\nname/apple\nprice/4.12\nquantity/500\n" +
                "item2:\nname/banana\nprice/1.32\nquantity/2000\n";
        Files.write(Paths.get(inputFile), inputContent.getBytes());
        editor.processTextFile(inputFile);

        // Act: Set a new value
        editor.setValue("item1", "price", "5.00");
        String updatedValue = editor.retrieveValue("item1", "price");

        // Assert: Check if the value is updated
        assertEquals("5.00", updatedValue, "The price for item1 should be updated to 5.00");
    }

    @Test
    public void testWriteToTextFile() throws Exception {
        // Arrange: Prepare input and process
        String inputFile = "FruitsInventory.txt";
        String inputContent = "item1:\nname/apple\nprice/4.12\nquantity/500\n" +
                "item2:\nname/banana\nprice/1.32\nquantity/2000\n";
        Files.write(Paths.get(inputFile), inputContent.getBytes());
        editor.processTextFile(inputFile);

        // Act: Write the repository data to a new file
        String outputFile = "OutputFruitsInventory.txt";
        editor.writeToTextFile(outputFile);

        // Assert: Verify the file content
        String outputContent = new String(Files.readAllBytes(Paths.get(outputFile)));
        assertTrue(outputContent.contains("item1:"), "Output file should contain item1");
        assertTrue(outputContent.contains("item2:"), "Output file should contain item2");
        assertTrue(outputContent.contains("name/apple"), "Output file should contain 'name/apple'");
        assertTrue(outputContent.contains("price/4.12"), "Output file should contain 'price/4.12'");
    }

    @Test
    public void testEmptyRepository() {
        // Act: Retrieve value from empty repository (not processed yet)
        String value = editor.retrieveValue("item1", "price");

        // Assert: The value should be null and the repository should not contain anything
        assertNull(value, "Repository should be empty before processing any file.");
    }

    @Test
    public void testArrayItemNotFound() throws Exception {
        // Arrange: Prepare input and process
        String inputFile = "FruitsInventory.txt";
        String inputContent = "item1:\nname/apple\nprice/4.12\nquantity/500\n" +
                "item2:\nname/banana\nprice/1.32\nquantity/2000\n";
        Files.write(Paths.get(inputFile), inputContent.getBytes());
        editor.processTextFile(inputFile);

        // Act: Try to retrieve a value from a non-existent item
        String retrievedValue = editor.retrieveValue("item3", "price");

        // Assert: It should print an error message and return null
        assertNull(retrievedValue, "Retrieving a value from a non-existent item should return null.");
    }

    @Test
    public void testKeyNotFound() throws Exception {
        // Arrange: Prepare input and process
        String inputFile = "FruitsInventory.txt";
        String inputContent = "item1:\nname/apple\nprice/4.12\nquantity/500\n";
        Files.write(Paths.get(inputFile), inputContent.getBytes());
        editor.processTextFile(inputFile);

        // Act: Try to retrieve a non-existent key
        String retrievedValue = editor.retrieveValue("item1", "color");

        // Assert: It should print an error message and return null
        assertNull(retrievedValue, "Retrieving a non-existent key should return null.");
    }
}
