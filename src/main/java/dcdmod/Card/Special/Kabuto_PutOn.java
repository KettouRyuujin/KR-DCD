package dcdmod.Card.Special;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.helpers.TooltipInfo;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.KabutoMaskedPower;
import dcdmod.Vfx.Kabuto_RiderToMasked;



public class Kabuto_PutOn extends AbstractCustomCardWithType{
	
	public static final String ID = "Kabuto_PutOn";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Kabuto_PutOn.png";
	private static final int COST = 0;
	private List<TooltipInfo> tips;

	
	
	public Kabuto_PutOn() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.SELF,CardColorType.Kabuto);
		this.tags.add(DCDmod.RiderCard);
		this.tags.add(DCDmod.KamenRide);
		this.exhaust = true;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToTop(new VFXAction(new Kabuto_RiderToMasked(p.drawX - 200.00f, p.drawY + 250.00f), 1.2F));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KabutoMaskedPower(p,1),1));
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Kabuto_CastOff(), 1));
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Kabuto_PutOn();
    }
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(p.hasPower("KabutoMaskedPower")) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		}
		if(!p.hasPower("KamenRideKabutoPower")) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[3];
		}
		return canUse;
	}
	
	@Override
	public void optionDecade() {
		this.damageType = DamageType.NORMAL;
	}

	@Override
	public void optionKuuga() {
		
	}

	@Override
	public void optionAgito() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionRyuki() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionFaiz() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionBlade() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionHibiki() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionKabuto() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionDenO() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionKiva() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionNeutral() {
		this.damageType = DamageType.NORMAL;
	}
    
	@Override
    public void upgrade() {
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Kabuto_PutOn");
        NAME = Kabuto_PutOn.cardStrings.NAME;
        DESCRIPTION = Kabuto_PutOn.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Kabuto_PutOn.cardStrings.EXTENDED_DESCRIPTION;
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
