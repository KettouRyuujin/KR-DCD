package dcdmod.Card.Uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcdmod.DCDmod;
import dcdmod.Actions.ApotheosisAction;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.AgitoLevelPower;
import dcdmod.Power.FlameLevelPower;
import dcdmod.Power.StormLevelPower;



public class PrometheusPower extends AbstractCustomCardWithType{
	
	public static final String ID = "PrometheusPower";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String IMG_PATH = "img/cards/PrometheusPower.png";
	private static final int COST = 1;

	
	
	public PrometheusPower() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF,CardColorType.Agito);
		this.tags.add(DCDmod.RiderCard);
		this.baseMagicNumber = this.magicNumber = 1;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApotheosisAction());
		if(p.hasPower("AgitoStormPower")) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StormLevelPower(p,1),1));
		}
		if(p.hasPower("AgitoFlamePower")) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FlameLevelPower(p,1),1));
		}
		if(p.hasPower("KamenRideAgitoPower")) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AgitoLevelPower(p,1),1));
		}
		for(AbstractCard c : AbstractDungeon.player.masterDeck.group) {
			if(c.cardID.equals("FormRideStorm") || c.cardID.equals("FormRideFlame")) {
				if(c.timesUpgraded<=8) {
					c.upgrade();
				}
				else {
					c.timesUpgraded = 9;
				}
			}
		}
		for(AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
			if(c.cardID.equals("FormRideStorm") || c.cardID.equals("FormRideFlame")) {
				if(c.timesUpgraded<=8) {
					c.upgrade();
				}
				else {
					c.timesUpgraded = 9;
				}
			}
		}
		for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
			if(c.cardID.equals("FormRideStorm") || c.cardID.equals("FormRideFlame")) {
				if(c.timesUpgraded<=8) {
					c.upgrade();
				}
				else {
					c.timesUpgraded = 9;
				}
			}
		}
		for(AbstractCard c : AbstractDungeon.player.discardPile.group) {
			if(c.cardID.equals("FormRideStorm") || c.cardID.equals("FormRideFlame")) {
				if(c.timesUpgraded<=8) {
					c.upgrade();
				}
				else {
					c.timesUpgraded = 9;
				}
			}
		}
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new PrometheusPower();
    }
	
	@Override
	public void optionDecade() {

	}

	@Override
	public void optionKuuga() {
		
	}

	@Override
	public void optionAgito() {
		
	}

	@Override
	public void optionRyuki() {
		
	}

	@Override
	public void optionFaiz() {
		
	}

	@Override
	public void optionBlade() {
		
	}

	@Override
	public void optionHibiki() {

	}

	@Override
	public void optionKabuto() {

	}

	@Override
	public void optionDenO() {

	}

	@Override
	public void optionKiva() {

	}

	@Override
	public void optionNeutral() {

	}
	
	@Override
    public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeBaseCost(0);
		}
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("PrometheusPower");
        NAME = PrometheusPower.cardStrings.NAME;
        DESCRIPTION = PrometheusPower.cardStrings.DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.UNCOMMON[0], DCDmod.UNCOMMON_P[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.UNCOMMON[6], DCDmod.UNCOMMON_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.UNCOMMON[cost], DCDmod.UNCOMMON_P[cost]);
		}
	}

	
	
}
