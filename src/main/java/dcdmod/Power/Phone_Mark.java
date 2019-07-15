  package dcdmod.Power;
  
  import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dcdmod.Actions.FaizGearAction;
 
 public class Phone_Mark extends AbstractPower
 {
   public static final String POWER_ID = "Phone_Mark";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;


   
   public Phone_Mark(AbstractCreature owner, int newAmount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = -1;
     this.img = ImageMaster.loadImage("img/powers/Mark.png");
     updateDescription();
     loadRegion("Phone_Mark");
     type = AbstractPower.PowerType.BUFF;
   }
   
   public void updateDescription() {	   
	   this.description = DESCRIPTIONS[0];}
   
	public int onAttacked(final DamageInfo info, final int damageAmount) {
		AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "Phone_Mark"));
		return damageAmount;
	}
   
   public void onRemove() {
             flash();
             FaizGearAction.FaizPoint += 1;
     } 
   
   static {
	     powerStrings = CardCrawlGame.languagePack.getPowerStrings("Phone_Mark");
	     NAME = Phone_Mark.powerStrings.NAME;
	     DESCRIPTIONS = Phone_Mark.powerStrings.DESCRIPTIONS;
	 }
   
   }
  


