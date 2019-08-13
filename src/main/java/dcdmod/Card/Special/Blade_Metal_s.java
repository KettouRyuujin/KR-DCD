package dcdmod.Card.Special;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import basemod.helpers.TooltipInfo;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;



public class Blade_Metal_s extends AbstractCustomCardWithType{
	
	public static final String ID = "Blade_Metal_s";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/BladeMetal.png";
	private static final int COST = 1;
	private List<TooltipInfo> tips;
	private boolean done = false;
	
	public Blade_Metal_s() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.SELF,CardColorType.Blade);
		this.tags.add(DCDmod.RiderCard);
		this.baseMagicNumber = this.magicNumber = 5;
		this.tips = new ArrayList<>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
		this.exhaust = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		CardCrawlGame.sound.playA("blade_metal", 0.0f);
		if(p.hasPower("Metallicize") && p.getPower("Metallicize").amount >=35) {
			for(AbstractCard c : p.drawPile.group) {
				if(c.cardID.equals("Blade_Metal") && !done) {
					p.drawPile.moveToHand(c, p.drawPile);
					done = true;
					break;
				}
			}
			for(AbstractCard c : p.discardPile.group) {
				if(c.cardID.equals("Blade_Metal") && !done) {
					p.drawPile.moveToHand(c, p.discardPile);
					done = true;
					break;
				}
			}
		}
		else {
			if(p.hasPower("KamenRideBladePower")) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
			}
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MetallicizePower(p, this.magicNumber), this.magicNumber));
		}
	}
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Blade_Metal_s();
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
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Blade_Metal_s");
        NAME = Blade_Metal_s.cardStrings.NAME;
        DESCRIPTION = Blade_Metal_s.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Blade_Metal_s.cardStrings.EXTENDED_DESCRIPTION;
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
