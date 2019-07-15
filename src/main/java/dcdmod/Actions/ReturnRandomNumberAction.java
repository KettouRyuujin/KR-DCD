/*    */ package dcdmod.Actions;
/*    */ 
/*    */ public class ReturnRandomNumberAction
/*    */ {
/*    */   private static int number;
/*    */   
/*    */   public static int ReturnRandomNumber()
/*    */   {
			int min;
			int max;
	
			min = (int) Math.ceil(1);
			max = (int) Math.floor(10);
			number = (int) (Math.floor(Math.random() * (max - min)) + min);
	
			return number;  
/*    */   }
/*    */ }


/* Location:              F:\SlayTheSpire\mods\kiritoMod_build0503_Beta.jar!\kirito\Action\ReturnRandomNumberAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */