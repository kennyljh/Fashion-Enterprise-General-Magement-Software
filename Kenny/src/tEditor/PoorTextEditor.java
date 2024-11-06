/**
 * @author Kenny
 */
package tEditor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A very limited text parser, editor, and printer
 */
public class PoorTextEditor {

	private Map<String, Object> repository = new LinkedHashMap<>();
	
	/**
	 * Takes a text file in the format of:
	 * 
	 * e.g. FruitsInventory.txt
	 * 
	 * item1:
	 * name/apple
	 * price/4.12
	 * quantity/500
	 * 
	 * item2:
	 * name/banana
	 * price/1.32
	 * quantity/2000
	 * 
	 * A ":" indicates the start of an array item
	 * A "/" indicates the separation of a key value pair
	 * 
	 * When an array item (item1) has been successfully added to a HashMap
	 * and its contents into a nested HashMap, it stops further updating
	 * to the array item when encountering a new array item (item2) as
	 * indicated by ":"
	 * 
	 * Repository is updated with information gathered from text file
	 * 
	 * @param filePath path of file, relative or absolute
	 */
	public void processTextFile(String filePath) {
		
		repository = new LinkedHashMap<>();
		parseTextToRepo(filePath);
	}
	
	/**
     * Writes the data from the repository (HashMap) to a text file.
     * The output format will look like:
     * 
     * item1:
     * name/apple
     * price/4.12
     * quantity/500
     * 
     * item2:
     * name/banana
     * price/1.32
     * quantity/2000
     * 
     * @param filePath desired path of the file to write to
     */
	public void writeToTextFile(String filePath) {
		
		writeDataToFile(filePath);
	}
	
	/**
	 * @return the HashMap repository
	 */
	public Map<String, Object> getRepository() {
		
		return repository;
	}
	
	/**
	 * To retrieve a specific value for a specific key under a specific array
	 * item
	 * @param arrayName specific name of array item
	 * @param keyName specific name of key name
	 * @return value associated with key and array item
	 */
	public String retrieveValue(String arrayName, String keyName) {
		
		return retrieveValueFromKey(arrayName, keyName);
	}
	
	/**
	 * To set a specific value for a specific key under a specific array
	 * item
	 * @param arrayName specific name of array item
	 * @param keyName specific name of key name
	 * @param value replacement value
	 */
	public void setValue(String arrayName, String keyName, String value) {
		
		setValueFromKey(arrayName, keyName, value);
	}
	
	/**
	 * Retrieves a HashMap of Key Value data under a specified array
	 * item name
	 * @param arrayItemName specific array item name
	 * @return HashMap of Key Value data
	 */
	public Map<String, Object> getArrayItems(String arrayName){
		
		return getArrayItemList(arrayName);
	}
	
	/**
	 * Retrieves a HashMap of Key Value data under a specified array
	 * item name
	 * @param arrayItemName specific array item name
	 * @return HashMap of Key Value data
	 */
	private Map<String, Object> getArrayItemList(String arrayItemName) {
		
		if (repository.isEmpty()) {
			
			System.out.println("Repository has not been initialized with data");
			return null;
		}
		
		Map<String, Object> arrayItem;
		
		if (repository.containsKey(arrayItemName)) {
			
			arrayItem = (Map<String, Object>) repository.get(arrayItemName);
		}
		else {
			
			System.out.println("ArrayItem: " + arrayItemName + " not found");
			return null;
		}
		return arrayItem;	
	}
	
	/**
	 * To retrieve a specific value for a specific key under a specific array
	 * item
	 * @param arrayName specific name of array item
	 * @param keyName specific name of key name
	 * @return value associated with key and array item
	 */
	private String retrieveValueFromKey(String arrayName, String keyName) {
		
		if (repository.isEmpty()) {
			
			System.out.println("Repository has not been initialized with data");
			return null;
		}
		
		String value = null;
		
		if (repository.containsKey(arrayName)) {
			
			Map<String, Object> arrayItem = (Map<String, Object>) repository.get(arrayName);
			
			if (arrayItem.containsKey(keyName)) {
				
				value = (String) arrayItem.get(keyName);
				
				if (value == null) {
					
					System.out.println("Value not found for key: " + keyName + " under arrayItem: " + arrayName);
				}
			}
			else {
				System.out.println("Key: " + keyName + " not found under arrayItem: " + arrayName);
			}
		}
		else {
			System.out.println("ArrayItem: " + arrayName + " not found");
		}
		return value;
	}
	
	/**
	 * To set a specific value for a specific key under a specific array
	 * item
	 * @param arrayName specific name of array item
	 * @param keyName specific name of key name
	 * @param value replacement value
	 */
	private void setValueFromKey(String arrayName, String keyName, String value) {
		
		if (repository.isEmpty()) {
			
			System.out.println("Repository has not been initiated with data");
		}
		else {
			
			if (repository.containsKey(arrayName)) {
				
				Map<String, Object> arrayItem = (Map<String, Object>) repository.get(arrayName);
				
				if (arrayItem.containsKey(keyName)) {
					
					arrayItem.replace(keyName, value);
				}
				else {
					System.out.println("Key: " + keyName + " not found under arrayItem: " + arrayName);
				}
			}
			else {
				System.out.println("ArrayItem: " + arrayName + " not found");
			}
		}
	}
	
	/**
	 * Takes a text file in the format of:
	 * 
	 * e.g. FruitsInventory.txt
	 * 
	 * item1:
	 * name/apple
	 * price/4.12
	 * quantity/500
	 * 
	 * item2:
	 * name/banana
	 * price/1.32
	 * quantity/2000
	 * 
	 * A ":" indicates the start of an array item
	 * A "/" indicates the separation of a key value pair
	 * 
	 * When an array item (item1) has been successfully added to a HashMap
	 * and its contents into a nested HashMap, it stops further updating
	 * to the array item when encountering a new array item (item2) as
	 * indicated by ":"
	 * 
	 * Repository is updated with information gathered from text file
	 * 
	 * @param filePath path of file, relative or absolute
	 */
	private void parseTextToRepo(String filePath){
		
		// to store array items in HashMap
		Map<String, Object> arrayItemList = new LinkedHashMap<>();
		Map<String, Object> currentItem = null;
		// more efficient compared to Scanner
		String currentArrayItem = null;
		BufferedReader reader = null;
		
		try {
			
			reader = new BufferedReader(new FileReader(filePath));
			String currentLine;
			
			while ((currentLine = reader.readLine()) != null) {
			
				// remove trailing whitespace
				currentLine = currentLine.trim();
			
				// check for new array item or reached the end of an array item
				if (currentLine.endsWith(":")){
					
					// if array item already exists
					if ( currentArrayItem != null && currentItem != null) {
						
						arrayItemList.put(currentArrayItem, currentItem);
					}
					// getting array item name without ":" or whitespace
					currentArrayItem = currentLine.substring(0, currentLine.length() - 1).trim();
					// create new array item to store items
					currentItem = new LinkedHashMap<>();
				}
				// otherwise process key and value pairs
				else if (currentItem != null && currentLine.contains("/")) {
					
					// split current line into key and value pair at ","
					String[] keyValue = currentLine.split("/", 2);
					
					if (keyValue.length == 2) {
						
						String key = keyValue[0].trim();
						String value = keyValue[1].trim();
						currentItem.put(key, value);
					}
				}
			}
			// last processed array item is added to list
			if (currentArrayItem != null && currentItem != null) {
				arrayItemList.put(currentArrayItem, currentItem);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		repository = arrayItemList;
	}
	
	/**
     * Writes the data from the repository (HashMap) to a text file.
     * The output format will look like:
     * 
     * item1:
     * name/apple
     * price/4.12
     * quantity/500
     * 
     * item2:
     * name/banana
     * price/1.32
     * quantity/2000
     * 
     * @param filePath path of the file to write to
     */
    private void writeDataToFile(String filePath) {
        
        BufferedWriter writer = null;
        
        try {
        	
            writer = new BufferedWriter(new FileWriter(filePath));
            
            // Iterate over the repository map
            for (Map.Entry<String, Object> entry : repository.entrySet()) {
                
                String arrayName = entry.getKey();
                Map<String, String> arrayItem = (Map<String, String>) entry.getValue();
                
                // writing the array name followed by ":"
                writer.write(arrayName + ":\n");
                
                // write all key-value pairs under array item
                for (Map.Entry<String, String> keyValueEntry : arrayItem.entrySet()) {
                    writer.write(keyValueEntry.getKey() + "/" + keyValueEntry.getValue() + "\n");
                }
                
                // adding blank line to separate array items
                writer.write("\n");
            }
            
            // debug
            System.out.println("Data written to " + filePath + " successfully.");
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
