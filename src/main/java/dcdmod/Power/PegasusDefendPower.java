package dcdmod.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dcdmod.Vfx.Kuuga_dash;

public class PegasusDefendPower extends AbstractPower {
	public static final String POWER_ID = "PegasusDefendPower";
	private static final PowerStrings powerStrings;
	public static final String NAME;
	public static final String[] DESCRIPTIONS;

	public PegasusDefendPower(AbstractCreature owner, int amt) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amt;
		this.updateDescription();
		this.img = ImageMaster.loadImage("img/powers/PegasusDefendPower.png");
		this.loadRegion("PegasusDefendPower");
		this.type = PowerType.BUFF;
	}

	public void stackPower(int stackAmount) {
		updateDescription();
		if (this.amount == 0) {
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "PegasusDefendPower"));
		}
		this.fontScale = 8.0F;
		this.amount += stackAmount;
	}
	
	public float atDamageReceive(final float damage, final DamageInfo.DamageType damageType) {
		return 0;
	}
	
	public int onAttacked(final DamageInfo info, final int damageAmount) {
		if(info.owner != this.owner && info.type == DamageInfo.DamageType.NORMAL){
			if(this.owner.hasPower("KamenRideKuugaPower")){
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_dash(), 0F));
			}
			AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "PegasusDefendPower", 1));
		}
		return damageAmount;
	}
	
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}

	static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("PegasusDefendPower");
	       NAME = PegasusDefendPower.powerStrings.NAME;
	       DESCRIPTIONS = PegasusDefendPower.powerStrings.DESCRIPTIONS;
	}
}