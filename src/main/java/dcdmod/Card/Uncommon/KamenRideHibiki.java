package dcdmod.Card.Uncommon;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import basemod.helpers.TooltipInfo;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.KamenRideHibikiPower;
import dcdmod.Vfx.Hibiki_henshin_SoundsAndAnimation;



public class KamenRideHibiki extends AbstractCustomCardWithType{
	
	public static final String ID = "KamenRideHibiki";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/KamenRideHibiki.png";
	private static final int COST = 1;
	private List<TooltipInfo> tips;
	
	
	public KamenRideHibiki() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.POWER, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF,CardColorType.Hibiki);
		
		this.baseMagicNumber = this.magicNumber = 2;
		this.tags.add(DCDmod.RiderCard);
		this.tags.add(DCDmod.KamenRide);
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		if (p.hasPower("KamenRideDecadePower")){
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p, p, "KamenRideDecadePower"));
		}
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KamenRideHibikiPower(p,1),1));
		AbstractDungeon.actionManager.addToTop(new VFXAction(new Hibiki_henshin_SoundsAndAnimation(p.drawX, p.drawY), 2.91F));
	}		public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(!p.hasPower("KamenRideDecadePower")) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[2];
		}
		else if(p.hasPower("KamenRideHibikiPower")) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[3];
		}
		return canUse;
	}
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new KamenRideHibiki();
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
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
            this.isInnate = true;
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("KamenRideHibiki");
        NAME = KamenRideHibiki.cardStrings.NAME;
        DESCRIPTION = KamenRideHibiki.cardStrings.DESCRIPTION;        
        UPGRADE_DESCRIPTION = KamenRideHibiki.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = KamenRideHibiki.cardStrings.EXTENDED_DESCRIPTION;
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
