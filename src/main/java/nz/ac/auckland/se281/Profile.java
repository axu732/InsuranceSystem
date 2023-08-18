package nz.ac.auckland.se281;

public class Profile {
  private String userName;
  private int age;
  private int policyNumber = 0;
  private boolean lifePolicy = false;
  private boolean loadedProfile = false;

  public Profile(String name, int age) {
    userName = name;
    this.age = age;
  }

  public boolean getLoadStatus() {
    return loadedProfile;
  }

  public void loadProfile() {
    loadedProfile = true;
  }

  public void unloadProfile() {
    loadedProfile = false;
  }

  public String getUserName() {
    return userName;
  }

  public int getAge() {
    return age;
  }

  public void incrementPolicies() {
    policyNumber++;
  }

  public void setLifePolicy() {
    lifePolicy = true;
  }

  public boolean getLifePolicy() {
    return lifePolicy;
  }

  public int getPolicyNumber() {
    return policyNumber;
  }
}
