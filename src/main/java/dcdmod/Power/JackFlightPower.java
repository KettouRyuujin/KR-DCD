package dcdmod.Power;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class JackFlightPower extends AbstractPower {
	public static final String POWER_ID = "JackFlightPower";
	private static final PowerStrings powerStrings;
	public static final String NAME;
	public static final String[] DESCRIPTIONS;

	public JackFlightPower(AbstractCreature owner, int amt) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = -1;
		this.updateDescription();
		this.img = ImageMaster.loadImage("img/powers/flight.png");
		this.loadRegion("JackFlightPower");
		this.type = PowerType.BUFF;
	}
	
    public float atDamageReceive(final float damage, final DamageInfo.DamageType damageType) {
        if(damageType != DamageType.HP_LOSS && damageType != DamageType.THORNS) {
        	return damage/2;
        }
    	return damage;
    }
	
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}

	static {
	       powerStrings = CardCrawlGame.languagePack.getPowerStrings("JackFlightPower");
	       NAME = JackFlightPower.powerStrings.NAME;
	       DESCRIPTIONS = JackFlightPower.powerStrings.DESCRIPTIONS;
	}
}