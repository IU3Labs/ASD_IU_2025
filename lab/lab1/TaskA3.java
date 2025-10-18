public class TaskA3 {
    

    public static boolean isIsogramAlternative(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }
        
        String lowerCaseStr = str.toLowerCase();
        
        for (int i = 0; i < lowerCaseStr.length(); i++) {
            char currentChar = lowerCaseStr.charAt(i);
            for (int j = i + 1; j < lowerCaseStr.length(); j++) {
                if (currentChar == lowerCaseStr.charAt(j)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        
        System.out.println(isIsogramAlternative("baumanka"));
        System.out.println(isIsogramAlternative("aba")); 
        System.out.println(isIsogramAlternative("IU3"));
    }
}