package nz.ac.auckland.se281;

public class Database {

  public Database() {}

  // Correct the name so only first letter is capitalised
  public String correctName(String userName) {

    String lowerUserName = userName.toLowerCase();
    String upperUserName = userName.toUpperCase();
    char firstLetterUpper = upperUserName.charAt(0);
    String correctedName = firstLetterUpper + lowerUserName.substring(1);

    return correctedName;
  }

  // Methods to check the length of the inputted name.
  public boolean nameLength(String username) {
    if (username.length() < 3) {
      return true;
    }
    return false;
  }

  public boolean agePositive(int age) {
    if (age < 0) {
      return false;
    }
    return true;
  }

  public Integer ageCheck(String age) {
    try {
      return Integer.parseInt(age);
    } catch (NumberFormatException e) {
      return -1;
    }
  }
}
