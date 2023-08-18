package nz.ac.auckland.se281;

public class CarPolicy extends Policies {
  public CarPolicy(Profile policyProfile, String[] options) {
    super(policyProfile, options);
  }

  @Override
  public int basePremium() {
    // Convert our sum to be insured from the options string array
    int sumToInsure = Integer.parseInt(options[0]);
    double basePremium = 0;
    boolean mechanicalBreakdown = false;
    String mechanicalString = options[3];
    mechanicalString = mechanicalString.toLowerCase();
    if (mechanicalString.charAt(0) == 'y') {
      mechanicalBreakdown = true;
    }
    // Check the current profile's age and apply the correct rate toward the premium
    if (policyProfile.getAge() < 25) {
      basePremium = sumToInsure * 0.15;
    }
    if (policyProfile.getAge() >= 25) {
      basePremium = sumToInsure * 0.1;
    }
    // Check if user wanted the mechanical breakdown coverage, add 80 if it contains y or yes
    if (mechanicalBreakdown == true) {
      basePremium += 80;
    }
    // Discount calculations
    if (policyProfile.getPolicyNumber() == 2) {
      double basePremiumDiscount = (basePremium - (basePremium * 0.1));
      return (int) basePremiumDiscount;
    }
    if (policyProfile.getPolicyNumber() >= 3) {
      double basePremiumDiscount = (basePremium - (basePremium * 0.2));
      return (int) basePremiumDiscount;
    }
    return (int) basePremium;
  }

  // This method is for use when displaying our original base premium without
  // considering discounts
  @Override
  public int basePremiumNoDiscount() {
    int sumToInsure = Integer.parseInt(options[0]);
    double basePremium = 0;
    boolean mechanicalBreakdown = false;
    String mechanicalString = options[3];
    mechanicalString = mechanicalString.toLowerCase();
    if (mechanicalString.charAt(0) == 'y') {
      mechanicalBreakdown = true;
    }
    mechanicalString = mechanicalString.toLowerCase();
    if (policyProfile.getAge() < 25) {
      basePremium = sumToInsure * 0.15;
    }
    if (policyProfile.getAge() >= 25) {
      basePremium = sumToInsure * 0.1;
    }
    if (mechanicalBreakdown == true) {
      basePremium += 80;
    }
    return (int) basePremium;
  }
  // Method that will return what type of policy it is
  @Override
  public String getType() {
    return "Car Policy";
  }
}
