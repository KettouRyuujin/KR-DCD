package dcdmod.Power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class UnarmedAttack3Power extends AbstractPower
 {
  public static final String POWER_ID = "UnarmedAttack3Power";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;

  
   public UnarmedAttack3Power(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = -1;
    this.img = ImageMaster.loadImage("img/powers/BrokenRibs.png");
   updateDescription();
   loadRegion("UnarmedAttack3Power");
 }

  
   public void updateDescription() {
		 this.description = DESCRIPTIONS[0];
   }
   
 
   public void atEndOfRound() { 
	   AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "UnarmedAttack3Power"));
   }
   
	public float atDamageGive(float damage, DamageInfo.DamageType type) {
		if (type == DamageInfo.DamageType.NORMAL) {
			return damage * 0.4f;
		}
		return damage;
	}
 
   
   static {
       powerStrings = CardCrawlGame.languagePack.getPowerStrings("UnarmedAttack3Power");
       NAME = UnarmedAttack3Power.powerStrings.NAME;
       DESCRIPTIONS = UnarmedAttack3Power.powerStrings.DESCRIPTIONS;
   }
   
 }