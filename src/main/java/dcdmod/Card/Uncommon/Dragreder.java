package dcdmod.Card.Uncommon;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.helpers.TooltipInfo;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.DragrederPower;
import dcdmod.Vfx.Dragreder_appear;
import dcdmod.Vfx.Dragreder_sounds;


public class Dragreder extends AbstractCustomCardWithType{
	
	public static final String ID = "Dragreder";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Dragreder.png";
	private static final int COST = 3;
	private static final int MAGIC_NUM = 3;
	private List<TooltipInfo> tips;
	
	public Dragreder() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF,CardColorType.Ryuki);
		this.tags.add(DCDmod.RiderCard);
		this.exhaust = true;
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		this.tips = new ArrayList<>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		CardCrawlGame.sound.playA("attackride", 0.0f);
		AbstractDungeon.actionManager.addToTop(new VFXAction(new Dragreder_sounds(), 0.0F));
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new Dragreder_appear(), 2.5F));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DragrederPower(p, this.magicNumber), this.magicNumber));
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(p.hasPower("DragrederPower")) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		}
		return canUse;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Dragreder();
    }
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
	}
	
	@Override
	public void optionDecade() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
	}

	@Override
	public void optionKuuga() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
	}

	@Override
	public void optionAgito() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
	}

	@Override
	public void optionRyuki() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
	}

	@Override
	public void optionFaiz() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
	}

	@Override
	public void optionBlade() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
	}

	@Override
	public void optionHibiki() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
	}

	@Override
	public void optionKabuto() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
	}

	@Override
	public void optionDenO() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
	}

	@Override
	public void optionKiva() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
	}

	@Override
	public void optionNeutral() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
	}
	
	@Override
    public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeBaseCost(2);
		}
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Dragreder");
        NAME = Dragreder.cardStrings.NAME;
        DESCRIPTION = Dragreder.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Dragreder.cardStrings.EXTENDED_DESCRIPTION;
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
