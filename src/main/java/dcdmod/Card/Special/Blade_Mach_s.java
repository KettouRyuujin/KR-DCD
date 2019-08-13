package dcdmod.Card.Special;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;

import basemod.helpers.TooltipInfo;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.BladeMachPower;



public class Blade_Mach_s extends AbstractCustomCardWithType{
	
	public static final String ID = "Blade_Mach_s";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/BladeMach.png";
	private static final int COST = 1;
	private List<TooltipInfo> tips;
	private boolean done = false;
	
	public Blade_Mach_s() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.SELF,CardColorType.Blade);
		this.tags.add(DCDmod.RiderCard);
		this.baseMagicNumber = this.magicNumber = 2;
		this.tips = new ArrayList<>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
		this.exhaust = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		CardCrawlGame.sound.playA("blade_mach", 0.0f);
		if(p.hasPower("KamenRideBladePower")) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BladeMachPower(p, 1), 1));
		}
		if(p.hasPower("Dexterity") && p.getPower("Dexterity").amount >=16) {
			for(AbstractCard c : p.drawPile.group) {
				if(c.cardID.equals("Blade_Mach") && !done) {
					p.drawPile.moveToHand(c, p.drawPile);
					done = true;
					break;
				}
			}
			for(AbstractCard c : p.discardPile.group) {
				if(c.cardID.equals("Blade_Mach") && !done) {
					p.drawPile.moveToHand(c, p.discardPile);
					done = true;
					break;
				}
			}
		}
		else {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
		}
	}
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Blade_Mach_s();
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
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Blade_Mach_s");
        NAME = Blade_Mach_s.cardStrings.NAME;
        DESCRIPTION = Blade_Mach_s.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Blade_Mach_s.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.SPECIAL[0], DCDmod.SPECIAL_P[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.SPECIAL[6], DCDmod.SPECIAL_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.SPECIAL[cost], DCDmod.SPECIAL_P[cost]);
		}
	}

	
	
}
