import java.io.*;
import java.util.*;

public class HardSudoku {

	// Create an Object for a pair of coordinates representing a space (cell) on the board //
	public class Cell {
		int row;
		int column;

    	Cell(int row, int column) { 
        	this.row = row; 
       	 	this.column = column; 
    	} 
  	}	
  	
  	// Evaluate characters from original file for subsequent board setup//
  	// If character is not an appropriate integer, takes on value '0' -- must be solved//
	public static int test_numbers(char x) {
    	int output = 0;
    	int test = Character.getNumericValue(x);
    	
    	if (1 <= test && test <= 9) {
    		output = test;
		}
    	return output;
    }
	
	// Create Sudoku game, reading values from file//
	/* Game is formatted as a list of lists, with each inner list representing a row 
	 and outer list containing all rows*/
	 // Catches are put in place to account for unreadable/inappropriate files//
	 //Game board is returned (list of lists)//
	public static List<List<Integer>> create_game(String filename) {
	
		List<List<Integer>> outputboard = new ArrayList<List<Integer>>();
		List<Integer> currentrow = new ArrayList<Integer>();
				
		int columnindex = 0;

		String line = null;
		
		try {
			FileInputStream reader = new FileInputStream(filename);
			char currentcharacter = 0;
			
			while (columnindex < 10 && outputboard.size() < 9) {

				if (columnindex == 9){
					outputboard.add(currentrow);

					currentrow = new ArrayList<Integer>();
					columnindex = 0;
				}
				
				else {
					currentcharacter = (char) reader.read();
					currentrow.add(test_numbers(currentcharacter));
					columnindex++;
				}			
			}         
        }
        
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filename + "'");                
        }
        
        catch(IOException ex) {
            System.out.println("Error reading file '" + filename + "'");                  
        }
        
		return outputboard;
    }
        
    // Update a space on the board with a given value + return updated board//
    public static List<List<Integer>> update_grid(List<List<Integer>> grid, int row, int column, int value) {
		grid.get(row).set(column, value);
		return grid;
    }
       
    //Create + return a list of values (1-9) already present in given column//
    public static List<Integer> column_values(List<List<Integer>> grid, int column) {
	
        List<Integer> column_values = new ArrayList<Integer>();
        
        for (int i = 0; i < 9; i++) {
        	int current_number = grid.get(i).get(column);
        	if (current_number != 0) {
        		column_values.add(current_number);
        	}
        }
        return column_values;
    }
    
    //Create + return a list of values (1-9) already present in given row//  
    public static List<Integer> row_values (List<List<Integer>> grid, int row) {

		List<Integer> row_values = new ArrayList<Integer>();
		    	
    	for (int i = 0; i < 9; i++) {
    		int current_number = grid.get(row).get(i);
    		if (current_number != 0) {
    			row_values.add(current_number);
    		}
    	}
    	return row_values;
    }
    
    //Create + return a list of values (1-9) already present in 3x3 box of given cell//
    public static List<Integer> box_values (List<List<Integer>> grid, int row, int column) {

		List<Integer> box_values = new ArrayList<Integer>();
    	
    	if (0 <= column && column <= 2) {
        	if (0 <= row && row <= 2) {
            	for (int i = 0; i < 3; i ++) {
                	for (int j = 0; j < 3; j ++) {
                    	if (grid.get(i).get(j)!= 0) {
							box_values.add(grid.get(i).get(j));
                    	}
                	}
            	}
        	}                    
   
        	if (3 <= row && row <= 5) {
            	for (int i = 3; i < 6; i ++) {
                	for (int j = 0; j < 3; j ++) {                        
                    	if (grid.get(i).get(j)!= 0) {
							box_values.add(grid.get(i).get(j));
                     	}
                	}
       			}
        	}
        	if (6 <= row && row <= 8) {
            	for (int i = 6; i < 9; i ++) {
                	for (int j = 0; j < 3; j ++) {
                    	if (grid.get(i).get(j)!= 0) {
							box_values.add(grid.get(i).get(j));
                    	}
                	}
            	}
        	}
        }
    	if (3 <= column && column <= 5) {
    	 	if (0 <= row && row <= 2) {
            	for (int i = 0; i < 3; i ++) {
                	for (int j = 3; j < 6; j ++) {                        
                    	if (grid.get(i).get(j)!= 0) {
							box_values.add(grid.get(i).get(j));
                   		}
                	}
            	}
        	}
        	if (3 <= row && row <= 5) {
             	for (int i = 3; i < 6; i ++) {
                	for (int j = 3; j < 6; j ++) {
                    	if (grid.get(i).get(j)!= 0) {
							box_values.add(grid.get(i).get(j));
                      	}
                	}
            	}
        	}
        	if (6 <= row && row <= 8) {
            	for (int i = 6; i < 9; i ++) {
                	for (int j = 3; j < 6; j ++) {
                    	if (grid.get(i).get(j)!= 0) {
							box_values.add(grid.get(i).get(j));
                    	}
                	}
            	}
        	}
        }
    	if (6 <= column && column <= 8) {
        	if (0 <= row && row <= 2) {
            	for (int i = 0; i < 3; i ++) {
                	for (int j = 6; j < 9; j ++) {
                    	if (grid.get(i).get(j)!= 0) {
							box_values.add(grid.get(i).get(j));
                    	}
                	}
            	}
        	}
       		if (3 <= row && row <= 5) {
             	for (int i = 3; i < 6; i ++) {
                	for (int j = 6; j < 9; j ++) {
                    	if (grid.get(i).get(j)!= 0) {
							box_values.add(grid.get(i).get(j));
                    	}
                	}
            	}
        	}
        	if (6 <= row && row <= 8) {
            	for (int i = 6; i < 9; i ++) {
                	for (int j = 6; j < 9; j ++) {
                    	if (grid.get(i).get(j)!= 0) {
							box_values.add(grid.get(i).get(j));
                    	}
                  	}
            	}
        	}
        }  
    	return box_values;
    }

	/* Using values in cell's column+row+box, create + return a list of possible 
	 values a cell could still hold */
	public static List<Integer> possible_list(List<List<Integer>> grid, int row, int column) {

		List<Integer> column_values = (column_values(grid, column));
		List<Integer> row_values = (row_values(grid, row));
		List<Integer> box_values = (box_values(grid, row, column));

		
		List<Integer> possible_list = new ArrayList<Integer>();

		for (int i = 1; i < 10; i++) {
			if (!column_values.contains(i)) {
				if (!row_values.contains(i)) {
					if (!box_values.contains(i)) {
						possible_list.add(i);
					}
				}
			}
		}
		return possible_list;
	}
	
	/* Create + return a hash-map containing cell objects as keys, possible value lists
	 as values */
	public static Map<HardSudoku.Cell,List<Integer>> all_possible_lists(List<List<Integer>> grid) {
	
		Map<HardSudoku.Cell, List<Integer>> all_possible_lists = new HashMap<HardSudoku.Cell, List<Integer>>();
		
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++ ) {
				if (grid.get(row).get(column) == 0) {
					Cell coordinates = new HardSudoku().new Cell(row,column); 
					all_possible_lists.put(coordinates, possible_list(grid, row, column));
				}
			}
		}
		return all_possible_lists;	
	}
	
	// Sort hash-map of all cells/possible values //
	// Create set of keys (cells), convert to list//
	/* Comparator (used by Class) takes cell objects from list + compares corresponding 
	   possible lists by length (using hash-mapping) */
	// Return cell object (coordinates) with shortest possible values list 
	public static HardSudoku.Cell sorted_all_possible_lists (Map<HardSudoku.Cell, List<Integer>> unsorted_lists) {
	
		Set<HardSudoku.Cell> key_set = unsorted_lists.keySet();
		List<HardSudoku.Cell> key_list = new ArrayList<HardSudoku.Cell>(key_set);
		
		class SortbyPossible implements Comparator<Cell> { 

    		public int compare(Cell a, Cell b) { 
        		return unsorted_lists.get(a).size() - unsorted_lists.get(b).size(); 
    		} 
		} 
		Collections.sort(key_list, new SortbyPossible());
		
		HardSudoku.Cell shortest_list_coordinates = key_list.get(0);
		key_list.remove(0);
		
		return shortest_list_coordinates;
	}

	// Print board as 9x9 square grid //
	public static void print_board(List<List<Integer>> grid) {
		for (int i = 0; i < grid.size(); i++) {
    		for (int j = 0; j < grid.get(i).size(); j ++) {
    			System.out.print(grid.get(i).get(j));
    			if (j == 8) {
    				System.out.println("");
    			}
    		}
    	}
    	System.out.println("");
    }

	// Solve sudoku board recursively + print solved board //
	/* Beginning of each recursive call checks whether board is filled + if so, 
	 prints and returns */
	/* Solves for cell with shortest possible values list by iterating through 
	 each value in said list + updating board appropriately */
	/* Process of placing value + solving recursively continues until board is either 
	   filled OR recursive call arrives at a slot with 0 possible values */
	public static boolean solve_board(List<List<Integer>> grid) {
		
		Map<HardSudoku.Cell,List<Integer>> all_possible_lists = all_possible_lists(grid);
		
		if (all_possible_lists.size() == 0) {
			System.out.println("solved");
			print_board(grid);
			return true;
		}
		
		HardSudoku.Cell current_coordinates = sorted_all_possible_lists(all_possible_lists);
		
		List<Integer> current_possible_list = possible_list(grid, current_coordinates.row, current_coordinates.column);
		
		if (current_possible_list.size() == 0) {
			return false;
		}
			
		for (int index = 0; index < current_possible_list.size(); index ++) {
			int current_row = current_coordinates.row;
			int current_column = current_coordinates.column;
			update_grid(grid,current_row,current_column,current_possible_list.get(index));
			
			if (solve_board(grid)) {
				return true;
			}
			update_grid(grid,current_row,current_column,0);
		}
		return false;
	}
	
	/* Main method to take in name of file containing board info, create board, 
	   print unsolved board, and call method to solve board */		
	public static void main(String[] args) {
		String filename = args[0];
		List<List<Integer>> grid = create_game(filename);

		print_board(grid);
		
		solve_board(grid);
	}
	
}


				
				

		
					