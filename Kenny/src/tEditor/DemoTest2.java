package tEditor;

public class DemoTest2 {
	
	
	public static void main(String[] args) {
		
		PoorTextEditor editor = new PoorTextEditor();
		editor.processTextFile("src/resources/FruitsInventory.txt");
		
		editor.setValue("pineapple", "price", "5.00");
		String[] demo = editor.getArrayNames();
		for (String s : demo) {
			editor.setValue(s, "price", "5.00");
			System.out.println(s);
		}
		editor.removeArrayItem("banana");
		demo = editor.getArrayNames();
		for (String s : demo) {
			editor.setValue(s, "price", "5.00");
			System.out.println(s);
		}
		editor.writeToTextFile("src/resources/DELETEME");
	}
}
