package dcdmod.Power;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DragShieldPower extends AbstractPower {
	public static final String POWER_ID = "DragShieldPower";
	private static final PowerStrings powerStrings;
	public static final String NAME;
	public static final String[] DESCRIPTIONS;

	public DragShieldPower(AbstractCreature owner, int amt) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amt;
		this.updateDescription();
		this.img = ImageMaster.loadImage("img/powers/DragShieldPower.png");
		this.loadRegion("DragShieldPower");
		this.type = PowerType.BUFF;
	}

	public void stackPower(int stackAmount) {
		updateDescription();
		if (this.amount == 0) {
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "DragShieldPower"));
		}
		this.fontScale = 8.0F;
		this.amount += stackAmount;
	}
	
    public float atDamageReceive(final float damage, final DamageInfo.DamageType damageType) {
        if(damageType != DamageType.HP_LOSS && damageType != DamageType.THORNS && damage <= 15) {
        	return 0;
        }
        else if(damageType != DamageType.HP_LOSS && damageType != DamageType.THORNS && damage > 15) {
        	return damage-10;
        }
    	return damage;
    }
    
    public int onAttacked(final DamageInfo info, final int damageAmount) {
    	if(!AbstractDungeon.player.hasPower("DragrederPower")) {
    		AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "DragShieldPower", 1));
    	}
    	return damageAmount;
    }
	
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}

	static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("DragShieldPower");
	       NAME = DragShieldPower.powerStrings.NAME;
	       DESCRIPTIONS = DragShieldPower.powerStrings.DESCRIPTIONS;
	}
}