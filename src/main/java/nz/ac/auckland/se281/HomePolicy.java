package nz.ac.auckland.se281;

public class HomePolicy extends Policies {

  public HomePolicy(Profile policyProfile, String[] options) {
    super(policyProfile, options);
  }

  @Override
  public int basePremium() {
    // Convert our sum to be insured to an integer
    int sum = Integer.parseInt(options[0]);
    String rentalString = options[2];
    rentalString = rentalString.toLowerCase();
    boolean rentalBoolean = false;
    if (rentalString.charAt(0) == 'y') {
      rentalBoolean = true;
    }
    // If the property is a rental we will calculate the base premium as 2%
    if (rentalBoolean == true) {
      int basePremiumSum = sum * 2 / 100;
      // Discount calculations
      if (policyProfile.getPolicyNumber() == 2) {
        double basePremiumDiscount = (basePremiumSum - (basePremiumSum * 0.1));
        return (int) basePremiumDiscount;
      }
      if (policyProfile.getPolicyNumber() >= 3) {
        double basePremiumDiscount = (basePremiumSum - (basePremiumSum * 0.2));
        return (int) basePremiumDiscount;
      }
      return basePremiumSum;
    }
    // If it is not a rental, will use 1% instead
    int basePremiumSum = sum * 1 / 100;
    // Discount Calculations
    if (policyProfile.getPolicyNumber() == 2) {
      double basePremiumDiscount = (basePremiumSum - (basePremiumSum * 0.1));
      return (int) basePremiumDiscount;
    }
    if (policyProfile.getPolicyNumber() >= 3) {
      double basePremiumDiscount = (basePremiumSum - (basePremiumSum * 0.2));
      return (int) basePremiumDiscount;
    }
    return basePremiumSum;
  }
  // Method that will calculate the base premium without considering discounts
  @Override
  public int basePremiumNoDiscount() {
    int sum = Integer.parseInt(options[0]);
    String rentalString = options[2];
    rentalString = rentalString.toLowerCase();
    boolean rentalBoolean = false;
    if (rentalString.charAt(0) == 'y') {
      rentalBoolean = true;
    }
    if (rentalBoolean == true) {
      int basePremiumSum = sum * 2 / 100;
      return basePremiumSum;
    }
    int basePremiumSum = sum * 1 / 100;
    return basePremiumSum;
  }
  // Method that will return what type of policy it is
  @Override
  public String getType() {
    return "Home Policy";
  }
}
