import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Examples {
    public static void main(String[] args) {
        int[] ho= new int[25];
        ho[0]= 1;
        ho[2]= 1;
        ho[4]= 5;
        System.out.println(Arrays.toString(ho));
        System.out.println(Arrays.toString(removeDuplicates(ho)));

       int[] findNegTest = {110, -1, 11, 101, -101};
        for (Integer i : findNegatives(findNegTest)) {
            System.out.print(i + " ");
        }
        System.out.println();

        String foo = "()()[()]([[]])";
        String bar = "([)])";
        System.out.println(checkBraces(foo));
        System.out.println(checkBraces(bar));
    }

    //problem 1: Remove duplicates
    //notes:collection:set
    /** Return a new array with all unique values in array */
    public static Integer[] removeDuplicates(int[] array) {
    	Set<Integer> hs = new HashSet<>();
    	Integer[] newarray=new Integer[array.length];
    	int n=0;
    	for(int i:array){
    		newarray[n]=Integer.valueOf(i);
    		hs.add(newarray[n]);
    		n++;
    	}
    	
    	
    	
    	return hs.toArray(new Integer[hs.size()]) ;
    	//throw new UnsupportedOperationException();
    }

    // problem 2: find all negative numbers in an array
    //notes:new list, going through the array,add numbers to it, 
    /** return a new array with all negative values in the input array */
    public static Integer[] findNegatives(int[] array) {
        List<Integer> al=new ArrayList<>();
    	for(int i:array){
        	if(i<0) al.add(i);
        }
    	return al.toArray(new Integer[al.size()]);
    	//throw new UnsupportedOperationException();
    }

    // problem 3: validate braces in a string
    //notes:use stack. proper nesting:[([])] unproper nesting:[(])
    /** return true iff all parens and square braces, i.e., ( and [
     * are properly matched and legally nested.  */
    public static boolean checkBraces(String s) {    
    	Stack<Character> stack = new Stack<Character>();
    	HashMap<Character, Character> map = new HashMap<Character, Character>();
    	//Associates specified values with specified keys
    	map.put('(', ')');
    	map.put('[', ']');
    	
    	for (int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
    		//Use a stack to keep track of every open square bracket or open parenthesis seen. 
    		if (map.keySet().contains(c)) {
    			stack.push(c);
    		} 
    		if (map.values().contains(c)) {//if find a closing brace
    			
    			//if the stack isn't empty and there's a matching open brace in the stack--True
    			//otherwise--False
    			if (!stack.empty() && map.get(stack.peek()) == c) {
    				stack.pop();//remove the stored open brace
    			} 
    			else {
    				return false;
    			}
    		}
    	}
     
    	return stack.empty();//If some braces are left in stack--false
    	//throw new UnsupportedOperationException();
    }
}
