package nz.ac.auckland.se281;

public class LifePolicy extends Policies {

  public LifePolicy(Profile policyProfile, String[] options) {
    super(policyProfile, options);
  }

  @Override
  public int basePremium() {
    // Convert our sum to be insured to integer
    int sumToInsure = Integer.parseInt(options[0]);
    // Create a double variable to make sure we don't lose information on decimals
    double agePercentage = (1 + (policyProfile.getAge() * 0.01));
    int basePremiumSum = (int) (sumToInsure * agePercentage / 100);
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
  // Method that will calculate what the base premium would be without considering discounts
  @Override
  public int basePremiumNoDiscount() {
    int sumToInsure = Integer.parseInt(options[0]);
    double agePercentage = 1 + (policyProfile.getAge() * 0.01);
    int basePremiumSum = (int) (sumToInsure * agePercentage / 100);
    return basePremiumSum;
  }

  // Method that will return what type of policy it is
  @Override
  public String getType() {
    return "Life Policy";
  }
}
