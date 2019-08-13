package dcdmod.Card.Uncommon;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.helpers.TooltipInfo;
import dcdmod.DCDmod;
import dcdmod.Actions.RemoveRouzePowerAction;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.BladeKickPower;



public class Blade_Kick extends AbstractCustomCardWithType{
	
	public static final String ID = "Blade_Kick";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/BladeKick.png";
	private static final int COST = 1;
	private List<TooltipInfo> tips;
	
	
	public Blade_Kick() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF,CardColorType.Blade);
		this.tags.add(DCDmod.RiderCard);
		this.baseMagicNumber = this.magicNumber = 1;
		this.tips = new ArrayList<>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
		this.exhaust = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		CardCrawlGame.sound.playA("blade_kick", 0.0f);
		if(!p.hasPower("BladeKickPower")) {
			AbstractDungeon.actionManager.addToBottom(new RemoveRouzePowerAction(p, p));
		}
		if(p.hasPower("KamenRideBladePower")) {
			boolean inhand = false;
			for(AbstractCard c : AbstractDungeon.player.hand.group) {
				if(c.cardID.equals("FinalAttackRide")) {
					inhand = true;
					break;
				}
			}
			if(!inhand) {
				boolean done = false;
				for(AbstractCard c : AbstractDungeon.player.discardPile.group) {
					if(c.cardID.equals("FinalAttackRide")) {
						AbstractDungeon.player.discardPile.removeCard(c);
						AbstractDungeon.player.hand.addToHand(c);
						done = true; 
						break;
					}
				}
				for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
					if(c.cardID.equals("FinalAttackRide") && !done) {
						AbstractDungeon.player.drawPile.removeCard(c);
						AbstractDungeon.player.hand.addToHand(c);
						done = true; 
						break;
					}
				}
				for(AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
					if(c.cardID.equals("FinalAttackRide") && !done) {
						AbstractDungeon.player.exhaustPile.removeCard(c);
						AbstractDungeon.player.hand.addToHand(c);
						break;
					}
				}
			}
		}
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BladeKickPower(p, this.magicNumber), this.magicNumber));
	}
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Blade_Kick();
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
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Blade_Kick");
        NAME = Blade_Kick.cardStrings.NAME;
        DESCRIPTION = Blade_Kick.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Blade_Kick.cardStrings.EXTENDED_DESCRIPTION;
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
