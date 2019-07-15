package dcdmod.Power;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Decade_Dash1Power extends AbstractPower {
	public static final String POWER_ID = "Decade_Dash1Power";
	private static final PowerStrings powerStrings;
	public static final String NAME;
	public static final String[] DESCRIPTIONS;
	AbstractMonster Target;

	public Decade_Dash1Power(AbstractCreature owner,AbstractMonster target, int amt) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amt;
		this.Target = target;
		this.updateDescription();
		this.img = ImageMaster.loadImage("img/powers/Decade_Dash1Power.png");
		this.loadRegion("Decade_Dash1Power");
		this.type = PowerType.BUFF;
	}

	public void stackPower(int stackAmount) {
		updateDescription();
		if (this.amount == 0) {
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "Decade_Dash1Power"));
		}
		this.fontScale = 8.0F;
		this.amount += stackAmount;
	}
	
	public int onAttacked(final DamageInfo info, final int damageAmount) {
		if(info.owner == Target) {
			AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "Decade_Dash1Power", 1));
			return 0;
		}
		return damageAmount;
	}
	
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}

	static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("Decade_Dash1Power");
	       NAME = Decade_Dash1Power.powerStrings.NAME;
	       DESCRIPTIONS = Decade_Dash1Power.powerStrings.DESCRIPTIONS;
	}
}