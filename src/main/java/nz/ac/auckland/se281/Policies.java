package nz.ac.auckland.se281;

public abstract class Policies {
  protected Profile policyProfile;
  protected String[] options;

  public Policies(Profile policyProfile, String[] options) {
    this.policyProfile = policyProfile;
    this.options = options;
  }

  public abstract int basePremium();

  public abstract int basePremiumNoDiscount();

  public abstract String getType();

  // The index 0 for options is the string for the sum to be insured
  public String getSumInsured() {
    return options[0];
  }

  // For Car Policy, option1 is make and model, for Home, it is address.
  public String getOption1() {
    return options[1];
  }
  // For Car Policy, this would be a licence plate
  public String getOption2() {
    return options[2];
  }
  // Gets the associated profile for a policy
  public Profile getOwnerProfile() {
    return policyProfile;
  }
}
