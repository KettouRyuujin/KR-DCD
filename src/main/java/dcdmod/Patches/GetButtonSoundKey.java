 package dcdmod.Patches;
 
 import dcdmod.Actions.ReturnRandomNumberAction2;
 import dcdmod.DCDmod;

 public class GetButtonSoundKey
 {	 
   public static String CustomModeButtonSoundKey() {
       if(10 > ReturnRandomNumberAction2.ReturnRandomNumber()) {
           return "henshin(oilfish)";
       }
       else{
           return "ButtonSoundKey1";
       }
   }
 }
