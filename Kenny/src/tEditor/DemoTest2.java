package tEditor;

public class DemoTest2 {
	
	
	public static void main(String[] args) {
		
		PoorTextEditor editor = new PoorTextEditor();
		editor.processTextFile("src/resources/FruitsInventory.txt");
		
		editor.setValue("pineapple", "price", "5.00");
		
		editor.writeToTextFile("src/resources/DELETEME.txt");
	}
}
