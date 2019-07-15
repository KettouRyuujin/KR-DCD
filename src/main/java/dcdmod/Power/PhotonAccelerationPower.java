 package dcdmod.Power;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

 
 public class PhotonAccelerationPower extends AbstractPower
 {
	  public static final String POWER_ID = "PhotonAccelerationPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	  public static int x = 0;
	   public PhotonAccelerationPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = -1;
	    this.img = ImageMaster.loadImage("img/powers/invigorate.png");
	   updateDescription();
	   loadRegion("PhotonAccelerationPower");
	   }
	   
	    public void onExhaust(final AbstractCard card) {
			   if(x >= 5) {
				   this.amount = x-5;
				   x -= 5;
				   flash();
			   }
			   else {
				   this.amount = x;
			   }
	    }
	   
		public void onDrawOrDiscard() {
			   if(x >= 5) {
				   this.amount = x-5;
				   x -= 5;
				   flash();
			   }
			   else {
				   this.amount = x;
			   }
		}
	    
	   public void onRemove() {
			for(AbstractRelic r: AbstractDungeon.player.relics) {
				if(r.relicId == "PhotonAccelerationRelic") {
					AbstractDungeon.player.relics.remove(r);
					break;
				}
			}

	   }
	   
	   public void onVictory() {
			for(AbstractRelic r: AbstractDungeon.player.relics) {
				if(r.relicId == "PhotonAccelerationRelic") {
					AbstractDungeon.player.relics.remove(r);
					break;
				}
			}
	   }
	   
	   public void updateDescription() {
		   this.description = DESCRIPTIONS[0];
	   }
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("PhotonAccelerationPower");
	       NAME = PhotonAccelerationPower.powerStrings.NAME;
	       DESCRIPTIONS = PhotonAccelerationPower.powerStrings.DESCRIPTIONS;
	   }  
 }

