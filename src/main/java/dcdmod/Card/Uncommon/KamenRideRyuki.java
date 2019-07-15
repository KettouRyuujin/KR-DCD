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
import dcdmod.Power.KamenRideRyukiPower;
import dcdmod.Vfx.Ryuki_dcdtoryuki;



public class KamenRideRyuki extends AbstractCustomCardWithType{
	
	public static final String ID = "KamenRideRyuki";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/KamenRideRyuki.png";
	private static final int COST = 1;
	private List<TooltipInfo> tips;
	
	
	public KamenRideRyuki() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.POWER, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF,CardColorType.Ryuki);
		
		this.baseMagicNumber = this.magicNumber = 1;
		this.tags.add(DCDmod.RiderCard);
		this.tags.add(DCDmod.KamenRide);
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
		if (AbstractDungeon.player.hasPower("KamenRideDecadePower")){
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p, p, "KamenRideDecadePower"));
		}
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KamenRideRyukiPower(p,1),1));
		CardCrawlGame.sound.playA("kamenride", 0.0f);
		CardCrawlGame.sound.playA("ryuki_hensin", 0.0f);
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new Ryuki_dcdtoryuki(p.drawX - 200.00f, p.drawY + 250.00f), 1F));
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(!p.hasPower("KamenRideDecadePower")) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[2];
		}
		else if(p.hasPower("KamenRideRyukiPower")) {
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
        return new KamenRideRyuki();
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
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("KamenRideRyuki");
        NAME = KamenRideRyuki.cardStrings.NAME;
        DESCRIPTION = KamenRideRyuki.cardStrings.DESCRIPTION;        
        UPGRADE_DESCRIPTION = KamenRideRyuki.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = KamenRideRyuki.cardStrings.EXTENDED_DESCRIPTION;
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
