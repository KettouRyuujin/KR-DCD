package dcdmod.Power;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DecadeBlockPower extends AbstractPower {
	public static final String POWER_ID = "DecadeBlockPower";
	private static final PowerStrings powerStrings;
	public static final String NAME;
	public static final String[] DESCRIPTIONS;
	boolean Block = false;

	public DecadeBlockPower(AbstractCreature owner, int amt) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amt;
		this.updateDescription();
		this.img = ImageMaster.loadImage("img/powers/DecadeBlockPower.png");
		this.loadRegion("DecadeBlockPower");
		this.type = PowerType.BUFF;
	}

	public void stackPower(int stackAmount) {
		updateDescription();
		if (this.amount == 0) {
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "DecadeBlockPower"));
		}
		this.fontScale = 8.0F;
		this.amount += stackAmount;
	}
	
    public float atDamageReceive(final float damage, final DamageInfo.DamageType damageType) {
		if(damage >= AbstractDungeon.player.currentBlock && AbstractDungeon.player.currentBlock > 0) {
			Block = true;
			return (int) (damage*0.5);
		}
    	return damage;
    }
	
	public int onAttacked(final DamageInfo info, final int damageAmount) {
		if(Block) {
			AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "DecadeBlockPower", 1));
			Block = false;
		}
		return damageAmount;
	}
	
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}

	static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("DecadeBlockPower");
	       NAME = DecadeBlockPower.powerStrings.NAME;
	       DESCRIPTIONS = DecadeBlockPower.powerStrings.DESCRIPTIONS;
	}
}