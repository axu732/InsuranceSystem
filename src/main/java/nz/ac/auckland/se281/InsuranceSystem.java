package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {
  private ArrayList<Profile> profilesList = new ArrayList<Profile>();
  private Database insuranceDatabase = new Database();
  private ArrayList<Policies> policiesList = new ArrayList<Policies>();

  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).

  }

  public void printDatabase() {

    // Initialise how many profiles there by evaluating database's size
    int profileCount = profilesList.size();
    String stringProfileCount = Integer.toString(profileCount);

    // If there is nothing in our database yet, print unique message with period
    if (profileCount == 0) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(stringProfileCount, "s", ".");
      return;
    }
    // If there is one profile, create unique message that says "profile" rather than profiles
    if (profileCount == 1 && profilesList.get(0).getLoadStatus() == false) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(stringProfileCount, "", ":");
      // Special message for when user has one policy.
      if (profilesList.get(0).getPolicyNumber() == 1) {
        MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
            "",
            "1",
            profilesList.get(0).getUserName(),
            Integer.toString(profilesList.get(0).getAge()),
            Integer.toString(profilesList.get(0).getPolicyNumber()),
            "y",
            Integer.toString(totalBasePremium(profilesList.get(0))));
        printPolicies(profilesList.get(0));
        return;
      }
      // Normal Message when user has 0 or more than 1 policy
      MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
          "",
          "1",
          profilesList.get(0).getUserName(),
          Integer.toString(profilesList.get(0).getAge()),
          Integer.toString(profilesList.get(0).getPolicyNumber()),
          "ies",
          Integer.toString(totalBasePremium(profilesList.get(0))));
      if (profilesList.get(0).getPolicyNumber() > 0) {
        printPolicies(profilesList.get(0));
      }
      return;
    }

    // If there is a profile and it is loaded, we instead have to put stars next to it.
    if (profileCount == 1 && profilesList.get(0).getLoadStatus() == true) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(stringProfileCount, "", ":");
      // Special message when loaded and one policy exists.
      if (profilesList.get(0).getPolicyNumber() == 1) {
        MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
            "*** ",
            "1",
            profilesList.get(0).getUserName(),
            Integer.toString(profilesList.get(0).getAge()),
            Integer.toString(profilesList.get(0).getPolicyNumber()),
            "y",
            Integer.toString(totalBasePremium(profilesList.get(0))));
        if (profilesList.get(0).getPolicyNumber() > 0) {
          printPolicies(profilesList.get(0));
        }
        return;
      }
      // Normal Message for when there are 0 or more than 1 policies
      MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
          "*** ",
          "1",
          profilesList.get(0).getUserName(),
          Integer.toString(profilesList.get(0).getAge()),
          Integer.toString(profilesList.get(0).getPolicyNumber()),
          "ies",
          Integer.toString(totalBasePremium(profilesList.get(0))));
      if (profilesList.get(0).getPolicyNumber() > 0) {
        printPolicies(profilesList.get(0));
      }
      return;
    }
    // If there are multiple profiles, we need to loop and print out each one.
    else {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(stringProfileCount, "s", ":");
      for (int i = 0; i < profileCount; i++) {
        // This if statement will check if the name we are loading is the current loaded profile
        if (profilesList.get(i).getLoadStatus() == true) {
          // Special Message for when there is only 1 policy for a loaded profile
          if (profilesList.get(i).getPolicyNumber() == 1) {
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "*** ",
                Integer.toString(i + 1),
                profilesList.get(i).getUserName(),
                Integer.toString(profilesList.get(i).getAge()),
                Integer.toString(profilesList.get(i).getPolicyNumber()),
                "y",
                Integer.toString(totalBasePremium(profilesList.get(i))));
            if (profilesList.get(i).getPolicyNumber() > 0) {
              printPolicies(profilesList.get(i));
            }
            continue;
          }
          // Normal Message when there are 0 or more profiles
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "*** ",
              Integer.toString(i + 1),
              profilesList.get(i).getUserName(),
              Integer.toString(profilesList.get(i).getAge()),
              Integer.toString(profilesList.get(i).getPolicyNumber()),
              "ies",
              Integer.toString(totalBasePremium(profilesList.get(i))));
          if (profilesList.get(i).getPolicyNumber() > 0) {
            printPolicies(profilesList.get(i));
          }
          continue;
        }
        // If not loaded and profile has only 1 policy
        if (profilesList.get(i).getPolicyNumber() == 1) {
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "",
              Integer.toString(i + 1),
              profilesList.get(i).getUserName(),
              Integer.toString(profilesList.get(i).getAge()),
              Integer.toString(profilesList.get(i).getPolicyNumber()),
              "y",
              Integer.toString(totalBasePremium(profilesList.get(i))));
          if (profilesList.get(i).getPolicyNumber() > 0) {
            printPolicies(profilesList.get(i));
          }
          continue;
        }
        // Normal message when there are multiple or 0 policies
        MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
            "",
            Integer.toString(i + 1),
            profilesList.get(i).getUserName(),
            Integer.toString(profilesList.get(i).getAge()),
            Integer.toString(profilesList.get(i).getPolicyNumber()),
            "ies",
            Integer.toString(totalBasePremium(profilesList.get(i))));
        if (profilesList.get(i).getPolicyNumber() > 0) {
          printPolicies(profilesList.get(i));
        }
      }
    }
  }

  public void createNewProfile(String userName, String age) {

    // Check if there is a currently loaded user, if so we return until the profile is unloaded.
    for (Profile profileIndex : profilesList) {
      if (profileIndex.getLoadStatus() == true) {
        MessageCli.CANNOT_CREATE_WHILE_LOADED.printMessage(profileIndex.getUserName());
        return;
      }
    }

    // Accounting for random caps
    String correctName = insuranceDatabase.correctName(userName);

    // Check for unique name
    int profileCount = profilesList.size();
    for (int i = 0; i < profileCount; i++) {
      if (correctName.equals(profilesList.get(i).getUserName())) {
        MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(profilesList.get(i).getUserName());
        return;
      }
    }

    // Check for correct name length
    if (insuranceDatabase.nameLength(correctName) == true) {
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(correctName);
      return;
    }

    // Check Age using parseint. If we return a value of -1, then we do not have a valid age

    int correctedAge = insuranceDatabase.ageCheck(age);
    if (correctedAge == -1) {
      MessageCli.INVALID_AGE.printMessage(age, correctName);
      return;
    }
    // Age is then checked if it is a positive integer, if not we print and return
    if (insuranceDatabase.agePositive(correctedAge) == false) {
      MessageCli.INVALID_AGE.printMessage(Integer.toString(correctedAge), correctName);
      return;
    }

    // Add to profile array list if all checks are past
    Profile newProfile = new Profile(correctName, correctedAge);
    profilesList.add(newProfile);
    MessageCli.PROFILE_CREATED.printMessage(correctName, Integer.toString(correctedAge));
  }

  public void loadProfile(String userName) {

    // Lower the given username string to be first letter capitalised and rest lower case
    String searchName = insuranceDatabase.correctName(userName);

    // Loop through our profile index if there is a username that fits our search name
    // If sucessful, unload the current profile then load the current profile
    for (Profile profileIndex : profilesList) {
      if (profileIndex.getUserName().equals(searchName)) {
        for (Profile unloadProfileIndex : profilesList) {
          if (unloadProfileIndex.getLoadStatus() == true) {
            unloadProfileIndex.unloadProfile();
          }
        }
        profileIndex.loadProfile();
        MessageCli.PROFILE_LOADED.printMessage(profileIndex.getUserName());
        return;
      }
    }

    // If no names are found, we print an error message
    MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(searchName);
  }

  public void unloadProfile() {
    // Check if there is a current loaded profile, if true print name then unload it
    for (Profile profileIndex : profilesList) {
      if (profileIndex.getLoadStatus() == true) {
        MessageCli.PROFILE_UNLOADED.printMessage(profileIndex.getUserName());
        profileIndex.unloadProfile();
        return;
      }
    }

    // Tell user there is no loaded profile found if the search fails

    MessageCli.NO_PROFILE_LOADED.printMessage();
  }

  public void deleteProfile(String userName) {
    // Lower the given username string to be first letter capitalised and rest lower case
    String searchName = insuranceDatabase.correctName(userName);

    // We will check if the name is loaded name, if so we will warn the user
    for (Profile profileIndex : profilesList) {
      if (searchName.equals(profileIndex.getUserName()) && profileIndex.getLoadStatus() == true) {
        MessageCli.CANNOT_DELETE_PROFILE_WHILE_LOADED.printMessage(searchName);
        return;
      }
    }

    // Loop through our profile arraylist if there is a username that fits our search name
    // If it is not the current loaded name and is found, we will delete it
    for (Profile profileIndex : profilesList) {
      if (profileIndex.getUserName().equals(searchName)) {
        profilesList.remove(profileIndex);
        MessageCli.PROFILE_DELETED.printMessage(searchName);
        return;
      }
    }
    // If it is not found, we will tell the user it is not in our database
    MessageCli.NO_PROFILE_FOUND_TO_DELETE.printMessage(searchName);
  }

  public void createPolicy(PolicyType type, String[] options) {

    // Look for our loaded profile and assign it for use inside our policies
    Profile currentLoadedProfile = null;
    for (Profile profileIndex : profilesList) {
      if (profileIndex.getLoadStatus() == true) {
        currentLoadedProfile = profileIndex;
        break;
      }
    }
    // If the loaded profile is still null, we will print an error.
    if (currentLoadedProfile == null) {
      MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();
      return;
    }

    if (type == PolicyType.HOME) {
      // Create home policy object and then store in array list
      HomePolicy newHomePolicy = new HomePolicy(currentLoadedProfile, options);
      MessageCli.NEW_POLICY_CREATED.printMessage("home", currentLoadedProfile.getUserName());
      currentLoadedProfile.incrementPolicies();
      policiesList.add(newHomePolicy);
    }

    if (type == PolicyType.CAR) {
      // Create new car policy object and then store in array list
      CarPolicy newCarPolicy = new CarPolicy(currentLoadedProfile, options);
      MessageCli.NEW_POLICY_CREATED.printMessage("car", currentLoadedProfile.getUserName());
      currentLoadedProfile.incrementPolicies();
      policiesList.add(newCarPolicy);
    }

    if (type == PolicyType.LIFE) {
      // If the current profiles age is older than 100, we do not create a life policy
      if (currentLoadedProfile.getAge() > 100) {
        MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(currentLoadedProfile.getUserName());
        return;
      }
      // If they already have a life policy, we do not create another.
      if (currentLoadedProfile.getLifePolicy() == true) {
        MessageCli.ALREADY_HAS_LIFE_POLICY.printMessage(currentLoadedProfile.getUserName());
        return;
      }
      // Create and store the life policy, incrementing and setting life policy boolean to true
      LifePolicy newLifePolicy = new LifePolicy(currentLoadedProfile, options);
      MessageCli.NEW_POLICY_CREATED.printMessage("life", currentLoadedProfile.getUserName());
      policiesList.add(newLifePolicy);
      currentLoadedProfile.incrementPolicies();
      currentLoadedProfile.setLifePolicy();
    }
  }

  // Helper Function that will calculate the price of all the policies for a profile.
  private int totalBasePremium(Profile ownerProfile) {
    int totalBasePremium = 0;
    // Check for every match of the owner profile
    for (Policies policyIndex : policiesList) {
      if (policyIndex.getOwnerProfile().equals(ownerProfile)) {
        // When it matches add the base premium of that policy to a variable that holds the total
        totalBasePremium += policyIndex.basePremium();
      }
    }
    return totalBasePremium;
  }

  // Helper function that will print line by line every policy that a profile owns.
  private void printPolicies(Profile ownerProfile) {
    // If the current profile has no policies, we print nothing
    if (ownerProfile.getPolicyNumber() == 0) {
      return;
    }
    // Loop through our arraylist and find the policies of the owner profile
    for (Policies policyIndex : policiesList) {
      if (policyIndex.getOwnerProfile().equals(ownerProfile)) {
        if (policyIndex.getType().equals("Car Policy")) {
          MessageCli.PRINT_DB_CAR_POLICY.printMessage(
              policyIndex.getOption1(),
              policyIndex.getSumInsured(),
              Integer.toString(policyIndex.basePremiumNoDiscount()),
              Integer.toString(policyIndex.basePremium()));
        }
        if (policyIndex.getType().equals("Life Policy")) {
          MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
              policyIndex.getSumInsured(),
              Integer.toString(policyIndex.basePremiumNoDiscount()),
              Integer.toString(policyIndex.basePremium()));
        }
        if (policyIndex.getType().equals("Home Policy")) {
          MessageCli.PRINT_DB_HOME_POLICY.printMessage(
              policyIndex.getOption1(),
              policyIndex.getSumInsured(),
              Integer.toString(policyIndex.basePremiumNoDiscount()),
              Integer.toString(policyIndex.basePremium()));
        }
      }
    }
  }
}
