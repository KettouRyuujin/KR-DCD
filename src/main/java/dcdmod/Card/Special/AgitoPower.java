package dcdmod.Card.Special;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
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
import dcdmod.Power.AgitoPowerPower;
import dcdmod.Vfx.Agito_power;
import dcdmod.Vfx.Agito_power_t;



public class AgitoPower extends AbstractCustomCardWithType{
	
	public static final String ID = "AgitoPower";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/AgitoPower.png";
	private static final int COST = 0;

	
	
	public AgitoPower() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.SELF,CardColorType.Agito);
		this.tags.add(DCDmod.RiderCard);
		this.baseMagicNumber = this.magicNumber = 1;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		CardCrawlGame.sound.playA("attackride", 0.0f);
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AgitoPowerPower(p, this.magicNumber), this.magicNumber));
		if(p.hasPower("AgitoStormPower") && p.hasPower("AgitoFlamePower")) {
			AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_power_t(p.drawX - 200.00f, p.drawY + 250.00f), 0.6F));
		}
		else if(p.hasPower("KamenRideAgitoPower")){
			AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_power(p.drawX - 200.00f, p.drawY + 250.00f), 1.25F));
		}
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new AgitoPower();
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
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("AgitoPower");
        NAME = AgitoPower.cardStrings.NAME;
        DESCRIPTION = AgitoPower.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = AgitoPower.cardStrings.EXTENDED_DESCRIPTION;
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
