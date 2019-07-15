 package dcdmod.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
 
 public class HibikiKurenaiPower extends AbstractPower
 {
	  public static final String POWER_ID = "HibikiKurenaiPower";
	  private static final PowerStrings powerStrings;
	  public static final String NAME;
	  public static final String[] DESCRIPTIONS;
	   public HibikiKurenaiPower(AbstractCreature owner, int amt)
	   {
		   
	    this.name = NAME;
	    this.ID = POWER_ID;
	    this.owner = owner;
	    this.amount = -1;
	    this.img = ImageMaster.loadImage("img/powers/fire.png");
	   updateDescription();
	   loadRegion("HibikiKurenaiPower");
	   }
	  
	   
	   public void atEndOfRound() {
		   int x = 0;
		   if(AbstractDungeon.player.hasPower("HibikiKurenaiSpecialPower")) {
			   x = AbstractDungeon.player.getPower("HibikiKurenaiSpecialPower").amount/2;
		   }
			for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
				if ((!monster.isDead) && (!monster.isDying)) {
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, AbstractDungeon.player, new HibikiBurnPower(monster, x, AbstractDungeon.player), x));
				}
			}
	   }
	   
	   public void updateDescription() {
			 this.description = DESCRIPTIONS[0];}
	   
	   static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("HibikiKurenaiPower");
	       NAME = HibikiKurenaiPower.powerStrings.NAME;
	       DESCRIPTIONS = HibikiKurenaiPower.powerStrings.DESCRIPTIONS;
	   }
	   
	 
	   
	  
	  }

