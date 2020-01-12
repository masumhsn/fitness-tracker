import java.io.File;
import java.util.Arrays;

public class User {

	//fields for user
   private String username;
   private String password;
   private int age;
   private int weight;
   
   //array of weight goal for each exercise
   private int[] weightGoals;


   //constructor
   public User(String username, String password, int age, int weight, int[] weightGoals) {
       super();
       this.username = username;
       this.password = password;
       this.age = age;
       this.weight = weight;
       this.weightGoals = weightGoals;
   }


   //getters and setters
   public String getUsername() {
       return username;
   }


   public void setUsername(String username) {
       this.username = username;
   }


   public String getPassword() {
       return password;
   }


   public void setPassword(String password) {
       this.password = password;
   }


   public int getAge() {
       return age;
   }


   public void setAge(int age) {
       this.age = age;
   }


   public int getWeight() {
       return weight;
   }


   public void setWeight(int weight) {
       this.weight = weight;
   }


   public int[] getWeightGoals() {
       return weightGoals;
   }


   public void setWeightGoals(int[] weightGoals) {
       this.weightGoals = weightGoals;
   }


   //tostring
   @Override
   public String toString() {
       return "User [username=" + username + ", password=" + password + ", age=" + age + ", weight=" + weight
               + ", weightGoals=" + Arrays.toString(weightGoals) + "]";
   }
}


