package dcdmod.Card.Special;

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
import dcdmod.Power.DragShieldPower;
import dcdmod.Vfx.DragShield_sounds;
import dcdmod.Vfx.Ryuki_guard;



public class DragShield_s extends AbstractCustomCardWithType{
	
	public static final String ID = "DragShield";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/DragShield.png";
	private static final int COST = 1;
	private static final int MAGIC_NUM = 2;
	private List<TooltipInfo> tips;
	
	public DragShield_s() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF,CardColorType.Ryuki);
		this.tags.add(DCDmod.RiderCard);
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		this.tips = new ArrayList<>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
		this.exhaust = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		CardCrawlGame.sound.playA("attackride", 0.0f);
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DragShieldPower(p, this.magicNumber), this.magicNumber));
		if(!p.hasPower("DragShieldPower") && p.hasPower("KamenRideRyukiPower")) {
			AbstractDungeon.actionManager.addToTop(new VFXAction(new Ryuki_guard(), 0.8F));
		}
		AbstractDungeon.actionManager.addToTop(new VFXAction(new DragShield_sounds(), 1.5F));
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new DragShield_s();
    }
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
	}
	
	@Override
	public void optionDecade() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(this.upgraded) {
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
		}
	}

	@Override
	public void optionKuuga() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(this.upgraded) {
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
		}
	}

	@Override
	public void optionAgito() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(this.upgraded) {
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
		}
	}

	@Override
	public void optionRyuki() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
		if(this.upgraded) {
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 2;
		}
	}

	@Override
	public void optionFaiz() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(this.upgraded) {
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
		}
	}

	@Override
	public void optionBlade() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(this.upgraded) {
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
		}
	}

	@Override
	public void optionHibiki() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(this.upgraded) {
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
		}
	}

	@Override
	public void optionKabuto() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(this.upgraded) {
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
		}
	}

	@Override
	public void optionDenO() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(this.upgraded) {
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
		}
	}

	@Override
	public void optionKiva() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(this.upgraded) {
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
		}
	}

	@Override
	public void optionNeutral() {
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(this.upgraded) {
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
		}
	}
	
	@Override
    public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeMagicNumber(1);
		}
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("DragShield");
        NAME = DragShield_s.cardStrings.NAME;
        DESCRIPTION = DragShield_s.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = DragShield_s.cardStrings.EXTENDED_DESCRIPTION;
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
