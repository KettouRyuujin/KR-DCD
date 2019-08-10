package dcdmod.Card.Special;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.StormSpecialPower;



public class StormSpecialCard extends AbstractCustomCardWithType{
	
	public static final String ID = "StormSpecialCard";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/StormSpecialCard.png";
	private static final int COST = 0;

	
	
	public StormSpecialCard() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.SELF,CardColorType.Agito);
		this.tags.add(DCDmod.RiderCard);
		this.baseMagicNumber = this.magicNumber = 1;
		this.exhaust = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StormSpecialPower(p, this.magicNumber), this.magicNumber));
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new StormSpecialCard();
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
			
		}
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("StormSpecialCard");
        NAME = StormSpecialCard.cardStrings.NAME;
        DESCRIPTION = StormSpecialCard.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = StormSpecialCard.cardStrings.EXTENDED_DESCRIPTION;
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
